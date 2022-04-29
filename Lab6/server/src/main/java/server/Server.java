package server;

import common.exceptions.ClosingSocketException;
import common.exceptions.OpeningServerSocketException;
import common.interaction.Request;
import common.interaction.Response;
import common.interaction.ResponseCode;
import common.utility.Outputer;
import server.utility.RequestHandler;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Runs the server.
 */
public class Server {
    private int port;
    private int soTimeout;
    private ServerSocket serverSocket;

    private RequestHandler requestHandler;
    // NonBlocking IO
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public Server(int port, int soTimeout, RequestHandler requestHandler) {
        this.port = port;
        this.soTimeout = soTimeout;
        this.requestHandler = requestHandler;
    }

    /**
     * Begins server operation.
     */
    public void run() {
        Request userRequest = null;
        Response responseToUser = null;
        try {
            openServerSocket();
            boolean processingStatus = true;
            while (processingStatus) {
                int readyCount = selector.select();
                if (readyCount == 0) continue; // if there is no client connection so continue to the next loop
                // process selected keys
                Set<SelectionKey> readyKeys = selector.selectedKeys();
                Iterator keys = readyKeys.iterator();
                while (keys.hasNext()) {

                    SelectionKey key = (SelectionKey) keys.next();
                    // Remove key from set, so we don't process it twice
                    keys.remove();
                    // Operate on the channel
                    if (key.isValid()) {
                        if (key.isAcceptable()) {
                            serverSocketChannel = (ServerSocketChannel) key.channel();
                            // Get client socket channel
                            Outputer.println("Port listening '" + port + "'...");
                            App.logger.info("Port listening '" + port + "'...");

                            SocketChannel clientSocket = serverSocketChannel.accept();

                            Outputer.println("Client connection successfully established.");
                            App.logger.info("Client connection successfully established.");
                            // Non blocking I/O
                            clientSocket.configureBlocking(false);
                            // Record it for read/write operations (only read here)
                            clientSocket.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                        } else if (key.isReadable()) {
                            try {
                                SocketChannel clientSocket = (SocketChannel) key.channel();
                                clientSocket.configureBlocking(false);
                                clientSocket.register(key.selector(), SelectionKey.OP_WRITE);

                                ByteBuffer buffer = ByteBuffer.allocate(clientSocket.getOption(StandardSocketOptions.SO_RCVBUF).intValue());

                                clientSocket.read(buffer);

                                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
                                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

                                userRequest = (Request) objectInputStream.readObject();
                                responseToUser = requestHandler.handle(userRequest);
                                App.logger.info("Request '" + userRequest.getCommandName() + "' successfully processed.");
                            } catch (StreamCorruptedException e) {

                            }
                            catch (ClassNotFoundException exception) {
                                Outputer.printerror("An error occurred while reading received data!");
                                App.logger.error("An error occurred while reading received data!");
                            } catch (InvalidClassException | NotSerializableException exception) {
                                Outputer.printerror("An error occurred while sending data to the client!");
                                App.logger.error("An error occurred while sending data to the client!");
                            } catch (IOException exception) {
                                //exception.printStackTrace();
//                                if (userRequest == null) {
//                                    Outputer.printerror("Unexpected loss of connection with the client!");
//                                    App.logger.warn("123Unexpected loss of connection with the client!");
//                                } else {
//                                    Outputer.println("Client successfully disconnected from server!");
//                                    App.logger.info("Client successfully disconnected from server!");
//                                }
                            }

                        }
                        else if (key.isWritable()) {
                            try {
                                SocketChannel clientSocket = (SocketChannel) key.channel();

                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                ObjectOutputStream clientWriter = new ObjectOutputStream(byteArrayOutputStream);

                                clientWriter.writeObject(responseToUser);

                                clientSocket.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
                                clientWriter.flush();
                            } catch (StreamCorruptedException e) {

                            } catch (InvalidClassException | NotSerializableException exception) {
                                Outputer.printerror("An error occurred while sending data to the client!");
                                App.logger.error("An error occurred while sending data to the client!");
                            } catch (IOException exception) {
                                 exception.printStackTrace();
//                                if (userRequest == null) {
//                                    Outputer.printerror("Unexpected loss of connection with the client!");
//                                    App.logger.warn("Unexpected loss of connection with the client!");
//                                } else {
//                                    Outputer.println("Client successfully disconnected from server!");
//                                    App.logger.info("Client successfully disconnected from server!");
//                                }
                            }
                        }
                    }
                }
            }
            stop();
        } catch (OpeningServerSocketException exception) {
            Outputer.printerror("Server cannot be started!");
            App.logger.error("Server cannot be started!");
        } catch (IOException e) {
            Outputer.printerror("An error occurred while trying to terminate the connection with the client!");
            App.logger.error("An error occurred while trying to terminate the connection with the client!");
        }
    }

    /**
     * Finishes server operation.
     */
    private void stop() {
        try {
            App.logger.info("Shutting down the server...");
            if (serverSocketChannel == null) throw new ClosingSocketException();
            serverSocketChannel.close();
            Outputer.println("Server completed successfully.");
            App.logger.info("Server completed successfully.");
        } catch (ClosingSocketException exception) {
            Outputer.printerror("Unable to shut down server not yet running!");
            App.logger.error("Unable to shut down server not yet running!");
        } catch (IOException exception) {
            Outputer.printerror("An error occurred while shutting down the server!");
            App.logger.error("An error occurred while shutting down the server!");
        }
    }

    /**
     * Open server socket.
     */
    private void openServerSocket() throws OpeningServerSocketException {
        try {
            App.logger.info("Server start...");
//            serverSocket = new ServerSocket(port);
//            serverSocket.setSoTimeout(soTimeout);
            selector = Selector.open();

            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);

            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            App.logger.info("Server started successfully.");
        } catch (IllegalArgumentException exception) {
            Outputer.printerror("Port '" + port + "' is out of range!");
            App.logger.error("Port '" + port + "' is out of range!");
            throw new OpeningServerSocketException();
        } catch (IOException exception) {
            Outputer.printerror("An error occurred while trying to use the port '" + port + "'!");
            App.logger.error("An error occurred while trying to use the port '" + port + "'!");
            throw new OpeningServerSocketException();
        }
    }


    /**
     * The process of receiving a request from a client.
     */
    private boolean processClientRequest(SocketChannel clientSocket) {
        Request userRequest = null;
        Response responseToUser = null;
        try (ObjectInputStream clientReader = new ObjectInputStream(clientSocket.socket().getInputStream());
             ObjectOutputStream clientWriter = new ObjectOutputStream(clientSocket.socket().getOutputStream())) {
            do {
                userRequest = (Request) clientReader.readObject();
                responseToUser = requestHandler.handle(userRequest);
                App.logger.info("Request '" + userRequest.getCommandName() + "' successfully processed.");
                clientWriter.writeObject(responseToUser);
                clientWriter.flush();
            } while (responseToUser.getResponseCode() != ResponseCode.SERVER_EXIT);
            return false;
        } catch (ClassNotFoundException exception) {
            Outputer.printerror("An error occurred while reading received data!");
            App.logger.error("An error occurred while reading received data!");
        } catch (InvalidClassException | NotSerializableException exception) {
            Outputer.printerror("An error occurred while sending data to the client!");
            App.logger.error("An error occurred while sending data to the client!");
        } catch (IOException exception) {
            if (userRequest == null) {
                Outputer.printerror("Unexpected loss of connection with the client!");
                App.logger.warn("Unexpected loss of connection with the client!");
            } else {
                Outputer.println("Client successfully disconnected from server!");
                App.logger.info("Client successfully disconnected from server!");
            }
        }
        return true;
    }
}
