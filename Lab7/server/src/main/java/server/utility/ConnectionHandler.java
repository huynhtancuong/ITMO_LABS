package server.utility;

import common.interaction.Request;
import common.interaction.Response;
import common.interaction.ResponseCode;
import common.utility.Outputer;
import server.App;
import server.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * Handles user connection.
 */
public class ConnectionHandler implements Runnable {
    private Server server;
    private Socket clientSocket;
    private CommandManager commandManager;
    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();


    public ConnectionHandler(Server server, Socket clientSocket, CommandManager commandManager) {
        this.server = server;
        this.clientSocket = clientSocket;
        this.commandManager = commandManager;
    }

    /**
     * Main handling cycle.
     */
    @Override
    public void run() {
        Request userRequest = null;
        Response responseToUser = null;
        boolean stopFlag = false;
        try (ObjectInputStream clientReader = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream clientWriter = new ObjectOutputStream(clientSocket.getOutputStream())) {
            do {
                userRequest = (Request) clientReader.readObject();Request finalUserRequest = userRequest;
                responseToUser = cachedThreadPool.submit(() -> { // Using cached thread pool for processing request
                    HandleRequestTask handleRequestTask = new HandleRequestTask(finalUserRequest, commandManager);
                    return handleRequestTask.compute();
                }).get();
                App.logger.info("Request '" + userRequest.getCommandName() + "' processed.");
                Response finalResponseToUser = responseToUser;
                if (!fixedThreadPool.submit(() -> { // Using fixed thread pool for writing response to client
                    try {
                        clientWriter.writeObject(finalResponseToUser);
                        clientWriter.flush();
                        return true;
                    } catch (IOException exception) {
                        Outputer.printerror("An error occurred while sending data to the client!");
                        App.logger.error("An error occurred while sending data to the client!");
                    }
                    return false;
                }).get()) break;
            } while (responseToUser.getResponseCode() != ResponseCode.SERVER_EXIT &&
                    responseToUser.getResponseCode() != ResponseCode.CLIENT_EXIT);
            if (responseToUser.getResponseCode() == ResponseCode.SERVER_EXIT)
                stopFlag = true;
        } catch (ClassNotFoundException exception) {
            Outputer.printerror("An error occurred while reading received data!");
            App.logger.error("An error occurred while reading received data!");
        } catch (CancellationException | ExecutionException | InterruptedException exception) {
            Outputer.println("A multithreading error occurred while processing the request!");
            App.logger.warn("A multithreading error occurred while processing the request!");
        } catch (IOException exception) {
            Outputer.printerror("Unexpected loss of connection with the client!");
            App.logger.warn("Unexpected loss of connection with the client!");
        } finally {
            try {
                fixedThreadPool.shutdown();
                clientSocket.close();
                Outputer.println("Client disconnected from server.");
                App.logger.info("Client disconnected from server.");
            } catch (IOException exception) {
                Outputer.printerror("An error occurred while trying to terminate the connection with the client!");
                App.logger.error("An error occurred while trying to terminate the connection with the client!");
            }
            if (stopFlag) server.stop();
            server.releaseConnection();
        }
    }
}
