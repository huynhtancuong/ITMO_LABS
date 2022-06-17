package server;

import common.exceptions.ClosingSocketException;
import common.exceptions.ConnectionErrorException;
import common.exceptions.OpeningServerSocketException;
import common.utility.Outputer;
import server.utility.CommandManager;
import server.utility.ConnectionHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Runs the server.
 */
public class Server {
    private int port;
    private ServerSocket serverSocket;
    private CommandManager commandManager;
    private boolean isStopped;
    private ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private Semaphore semaphore;

    public Server(int port, int maxClients, CommandManager commandManager) {
        this.port = port;
        this.commandManager = commandManager;
        this.semaphore = new Semaphore(maxClients);
    }

    /**
     * Begins server operation.
     */
    public void run() {
        try {
            openServerSocket();
            while (!isStopped()) {
                try {
                    acquireConnection();
                    if (isStopped()) throw new ConnectionErrorException();
                    Socket clientSocket = connectToClient();
                    cachedThreadPool.submit(new ConnectionHandler(this, clientSocket, commandManager)); // Use cached thread pool for reading request
                } catch (ConnectionErrorException exception) {
                    if (!isStopped()) {
                        Outputer.printerror("An error occurred while connecting to the client!");
                        App.logger.error("An error occurred while connecting to the client!");
                    } else break;
                }
            }
            cachedThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            Outputer.println("Server shut down.");
        } catch (OpeningServerSocketException exception) {
            Outputer.printerror("Server cannot be started!");
            App.logger.fatal("Server cannot be started!");
        } catch (InterruptedException e) {
            Outputer.printerror("An error occurred while shutting down already connected clients!");
        }
    }

    /**
     * Acquire connection.
     */
    public void acquireConnection() {
        try {
            semaphore.acquire();
            App.logger.info("New connection granted.");
        } catch (InterruptedException exception) {
            Outputer.printerror("An error occurred while obtaining permission for a new connection!");
            App.logger.error("An error occurred while obtaining permission for a new connection!");
        }
    }

    /**
     * Release connection.
     */
    public void releaseConnection() {
        semaphore.release();
        App.logger.info("Connection disconnect registered.");
    }

    /**
     * Finishes server operation.
     */
    public synchronized void stop() {
        try {
            App.logger.info("Shutting down the server...");
            if (serverSocket == null) throw new ClosingSocketException();
            isStopped = true;
            cachedThreadPool.shutdown();
            serverSocket.close();
            Outputer.println("End of work with already connected clients...");
            App.logger.info("Server shut down.");
        } catch (ClosingSocketException exception) {
            Outputer.printerror("Unable to shut down server not yet running!");
            App.logger.error("Unable to shut down server not yet running!");
        } catch (IOException exception) {
            Outputer.printerror("An error occurred while shutting down the server!");
            Outputer.println("End of work with already connected clients...");
            App.logger.error("An error occurred while shutting down the server!");
        }
    }

    /**
     * Checked stops of server.
     *
     * @return Status of server stop.
     */
    private synchronized boolean isStopped() {
        return isStopped;
    }

    /**
     * Open server socket.
     */
    private void openServerSocket() throws OpeningServerSocketException {
        try {
            App.logger.info("Server start...");
            serverSocket = new ServerSocket(port);
            App.logger.info("Server started.");
        } catch (IllegalArgumentException exception) {
            Outputer.printerror("Port '" + port + "' is out of range!");
            App.logger.fatal("Port '" + port + "' is out of range!");
            throw new OpeningServerSocketException();
        } catch (IOException exception) {
            Outputer.printerror("An error occurred while trying to use the port '" + port + "'!");
            App.logger.fatal("An error occurred while trying to use the port '" + port + "'!");
            throw new OpeningServerSocketException();
        }
    }

    /**
     * Connecting to client.
     */
    private Socket connectToClient() throws ConnectionErrorException {
        try {
            Outputer.println("Port listening '" + port + "'...");
            App.logger.info("Port listening '" + port + "'...");
            Socket clientSocket = serverSocket.accept();
            Outputer.println("Client connection established.");
            App.logger.info("Client connection established.");
            return clientSocket;
        } catch (IOException exception) {
            throw new ConnectionErrorException();
        }
    }
}
