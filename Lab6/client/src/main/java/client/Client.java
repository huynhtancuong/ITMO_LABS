package client;

import client.utility.UserHandler;
import common.exceptions.ConnectionErrorException;
import common.exceptions.NotInDeclaredLimitsException;
import common.interaction.Request;
import common.interaction.Response;
import common.interaction.ResponseCode;
import common.utility.Outputer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Runs the client.
 */
public class Client {
    private String host;
    private int port;
    private int reconnectionTimeout;
    private int reconnectionAttempts;
    private int maxReconnectionAttempts;
    private UserHandler userHandler;
    private SocketChannel socketChannel;
    private ObjectOutputStream serverWriter;
    private ObjectInputStream serverReader;

    public Client(String host, int port, int reconnectionTimeout, int maxReconnectionAttempts, UserHandler userHandler) {
        this.host = host;
        this.port = port;
        this.reconnectionTimeout = reconnectionTimeout;
        this.maxReconnectionAttempts = maxReconnectionAttempts;
        this.userHandler = userHandler;
    }

    /**
     * Begins client operation.
     */
    public void run() {
        try {
            boolean processingStatus = true;
            while (processingStatus) {
                try {
                    connectToServer();
                    processingStatus = processRequestToServer();
                } catch (ConnectionErrorException exception) {
                    if (reconnectionAttempts >= maxReconnectionAttempts) {
                        Outputer.printerror("Connection attempts exceeded!");
                        break;
                    }
                    try {
                        Thread.sleep(reconnectionTimeout);
                    } catch (IllegalArgumentException timeoutException) {
                        Outputer.printerror("Connection timeout '" + reconnectionTimeout +
                                "' is out of range!");
                        Outputer.println("Reconnection will be done immediately.");
                    } catch (Exception timeoutException) {
                        Outputer.printerror("An error occurred while trying to connect!");
                        Outputer.println("Reconnection will be done immediately.");
                    }
                }
                reconnectionAttempts++;
            }
            if (socketChannel != null) socketChannel.close();
            Outputer.println("Client job completed successfully.");
        } catch (NotInDeclaredLimitsException exception) {
            Outputer.printerror("Client cannot be started!");
        } catch (IOException exception) {
            Outputer.printerror("An error occurred while trying to terminate the connection to the server!");
        }
    }

    /**
     * Connecting to server.
     */
    private void connectToServer() throws ConnectionErrorException, NotInDeclaredLimitsException {
        try {
            if (reconnectionAttempts >= 1) Outputer.println("Reconnecting to the server...");
            socketChannel = SocketChannel.open(new InetSocketAddress(host, port));
            if (socketChannel != null) {
                Outputer.println("Connected to server.");
            } else {
                Outputer.println("Reconnecting to the server.");
            }
            Outputer.println("Waiting for permission to communicate...");
//            serverWriter = new ObjectOutputStream(socketChannel.socket().getOutputStream());
//            serverReader = new ObjectInputStream(socketChannel.socket().getInputStream());
            Outputer.println("Permission to share data received.");
        } catch (IllegalArgumentException exception) {
            Outputer.printerror("Server address entered incorrectly!");
            throw new NotInDeclaredLimitsException();
        } catch (IOException exception) {
            Outputer.printerror("An error occurred while connecting to the server!");
            throw new ConnectionErrorException();
        }
    }

    /**
     * Server request process.
     */
    private boolean processRequestToServer() {
        Request requestToServer = null;
        Response serverResponse = null;
        do {
            try {
                requestToServer = serverResponse != null ? userHandler.handle(serverResponse.getResponseCode()) :
                        userHandler.handle(null);
                if (requestToServer.isEmpty()) continue;

                // writing Request object here
                myWriteObject(requestToServer);
                // reading Response object here
                serverResponse = myReadObject();

                Outputer.print(serverResponse.getResponseBody());
            } catch (InvalidClassException | NotSerializableException exception) {
                Outputer.printerror(exception);
                Outputer.printerror("An error occurred while sending data to the server!");
            } catch (ClassNotFoundException exception) {
                Outputer.printerror("An error occurred while reading received data!");
            } catch (IOException exception) {
                Outputer.printerror("Server connection lost!");
                try {
                    reconnectionAttempts++;
                    connectToServer();
                } catch (ConnectionErrorException | NotInDeclaredLimitsException reconnectionException) {
                    if (requestToServer.getCommandName().equals("exit"))
                        Outputer.println("The command will not be registered on the server.");
                    else Outputer.println("Try repeating the command later.");
                }
            } catch (NullPointerException e) {
                Outputer.printerror("Catched NullPointerExeption");
            }
        } while (!requestToServer.getCommandName().equals("exit"));
        return false;
    }

    /**
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private Response myReadObject() throws IOException, ClassNotFoundException {
        Response serverResponse = new Response(ResponseCode.ERROR, "");
        ByteBuffer buffer = ByteBuffer.allocate(1024*16);
        buffer.clear();

        int read = socketChannel.read(buffer);
        if (read < 0) return serverResponse;

        buffer.flip();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
        serverReader = new ObjectInputStream(byteArrayInputStream);

        Object obj = serverReader.readObject();

        if (obj instanceof Response) {
            serverResponse = (Response) obj;
        }
        return serverResponse;
    }

    private void myWriteObject(Request requestToServer) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        serverWriter = new ObjectOutputStream(byteArrayOutputStream);
        serverWriter.flush();

        serverWriter.writeObject(requestToServer);

        ByteBuffer buffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        socketChannel.write(buffer);
    }
}
