package server;

import common.exceptions.ClosingSocketException;
import common.exceptions.ConnectionErrorException;
import common.exceptions.OpeningServerSocketException;
import common.interaction.Request;
import common.interaction.Response;
import common.interaction.ResponseCode;
import common.utility.Outputer;
import server.utility.RequestHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Runs the server.
 */
public class Server {
    private int port;
    private int soTimeout;
    private ServerSocket serverSocket;
    private RequestHandler requestHandler;

    public Server(int port, int soTimeout, RequestHandler requestHandler) {
        this.port = port;
        this.soTimeout = soTimeout;
        this.requestHandler = requestHandler;
    }

    /**
     * Begins server operation.
     */
    public void run() {
        try {
            openServerSocket();
            boolean processingStatus = true;
            while (processingStatus) {
                try (Socket clientSocket = connectToClient()) {
                    processingStatus = processClientRequest(clientSocket);
                } catch (ConnectionErrorException | SocketTimeoutException exception) {
                    break;
                } catch (IOException exception) {
                    Outputer.printerror("An error occurred while trying to terminate the connection with the client!");
                    App.logger.error("An error occurred while trying to terminate the connection with the client!");
                }
            }
            stop();
        } catch (OpeningServerSocketException exception) {
            Outputer.printerror("Server cannot be started!");
            App.logger.error("Server cannot be started!");
        }
    }

    /**
     * Finishes server operation.
     */
    private void stop() {
        try {
            App.logger.info("Shutting down the server...");
            if (serverSocket == null) throw new ClosingSocketException();
            serverSocket.close();
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
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(soTimeout);
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
     * Connecting to client.
     */
    private Socket connectToClient() throws ConnectionErrorException, SocketTimeoutException {
        try {
            Outputer.println("Port listening '" + port + "'...");
            App.logger.info("Port listening '" + port + "'...");
            Socket clientSocket = serverSocket.accept();
            Outputer.println("Client connection successfully established.");
            App.logger.info("Client connection successfully established.");
            return clientSocket;
        } catch (SocketTimeoutException exception) {
            Outputer.printerror("Connection timed out!");
            App.logger.warn("Connection timed out!");
            throw new SocketTimeoutException();
        } catch (IOException exception) {
            Outputer.printerror("An error occurred while connecting to the client!");
            App.logger.error("An error occurred while connecting to the client!");
            throw new ConnectionErrorException();
        }
    }

    /**
     * The process of receiving a request from a client.
     */
    private boolean processClientRequest(Socket clientSocket) {
        Request userRequest = null;
        Response responseToUser = null;
        try (ObjectInputStream clientReader = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream clientWriter = new ObjectOutputStream(clientSocket.getOutputStream())) {
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
