import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Crunchify.com
 * Java NIO (Non-blocking I/O) with Server-Client Example - java.nio.ByteBuffer and channels.Selector
 * This is CrunchifyNIOServer.java
 */

public class ExampleServer {

    @SuppressWarnings("unused")
    public static void main(String[] args) throws IOException {

        // Selector: A multiplexor of SelectableChannel objects.
        // A selector may be created by invoking the open method of this class, which will use the system's default selector provider to create a new selector.
        // A selector may also be created by invoking the openSelector method of a custom selector provider. A selector remains open until it is closed via its close method.
        Selector selector = Selector.open(); // selector is open here

        // ServerSocketChannel: A selectable channel for stream-oriented listening sockets.
        // A server-socket channel is created by invoking the open method of this class.
        // It is not possible to create a channel for an arbitrary, pre-existing ServerSocket.
        ServerSocketChannel crunchifySocket = ServerSocketChannel.open();

        // InetSocketAddress: This class implements an IP Socket Address (IP address + port number) It can also be a pair (hostname + port number),
        // in which case an attempt will be made to resolve the hostname.
        // If resolution fails then the address is said to be unresolved but can still be used on some circumstances like connecting through a proxy.
        InetSocketAddress crunchifyAddr = new InetSocketAddress("localhost", 1111);

        // Binds the channel's socket to a local address and configures the socket to listen for connections
        crunchifySocket.bind(crunchifyAddr);

        // Adjusts this channel's blocking mode.
        crunchifySocket.configureBlocking(false);

        int ops = crunchifySocket.validOps();

        // SelectionKey: A token representing the registration of a SelectableChannel with a Selector.
        // A selection key is created each time a channel is registered with a selector.
        // A key remains valid until it is cancelled by invoking its cancel method, by closing its channel, or by closing its selector.
        SelectionKey selectKy = crunchifySocket.register(selector, ops, null);

        // Infinite loop..
        // Keep server running
        while (true) {

            log("I'm a server and I'm waiting for new connection and buffer select...");
            // Selects a set of keys whose corresponding channels are ready for I/O operations
            int readyKeys = selector.select();

            // token representing the registration of a SelectableChannel with a Selector
            Set<SelectionKey> crunchifyKeys = selector.selectedKeys();
            Iterator<SelectionKey> crunchifyIterator = crunchifyKeys.iterator();

            while (crunchifyIterator.hasNext()) {
                SelectionKey myKey = crunchifyIterator.next();

                // Tests whether this key's channel is ready to accept a new socket connection
                if (myKey.isAcceptable()) {
                    SocketChannel crunchifyClient = crunchifySocket.accept();

                    // Adjusts this channel's blocking mode to false
                    crunchifyClient.configureBlocking(false);

                    // Operation-set bit for read operations
                    crunchifyClient.register(selector, SelectionKey.OP_READ);
                    log("Connection Accepted: " + crunchifyClient.getLocalAddress() + "\n");

                    // Tests whether this key's channel is ready for reading
                } else if (myKey.isReadable()) {

                    SocketChannel crunchifyClient = (SocketChannel) myKey.channel();

                    // ByteBuffer: A byte buffer.
                    // This class defines six categories of operations upon byte buffers:
                    // Absolute and relative get and put methods that read and write single bytes;
                    // Absolute and relative bulk get methods that transfer contiguous sequences of bytes from this buffer into an array;
                    ByteBuffer crunchifyBuffer = ByteBuffer.allocate(256);
                    int status = crunchifyClient.read(crunchifyBuffer);
                    if (status == -1) crunchifyClient.close();
                    String result = new String(crunchifyBuffer.array()).trim();

                    log("Message received: " + result);

                    if (result.equals("Crunchify.com")) {
                        crunchifyClient.close();
                        log("\nIt's time to close connection as we got last company name 'Crunchify'");
                        log("\nServer will keep running. Try running client again to establish new connection");
                    }
                } else if (myKey.isWritable()) {
                    System.out.println("The key is writable!");
                }
                crunchifyIterator.remove();
            }
        }
    }

    private static void log(String str) {

        System.out.println(str);
    }
}