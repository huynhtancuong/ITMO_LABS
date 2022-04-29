import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {
    public static void main(String args[]) {
        try {
            Selector selector = Selector.open();

            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);

            InetSocketAddress hostAddress = new InetSocketAddress(25565);
            serverChannel.bind(hostAddress);

            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                int readyCount = selector.selectNow();
                if (readyCount == 0) continue;
                // process selected keys
                Set<SelectionKey> readyKeys = selector.selectedKeys();
//                System.out.println((readyKeys.toString()));

                for (Iterator itr = readyKeys.iterator(); itr.hasNext();) {
                    SelectionKey key = (SelectionKey) itr.next();
                    // Remove key from set so we don't process it twice
                    itr.remove();
                    //System.out.println(itr.toString() + " was removed");
                    // Operate on the channel
                    // Client requires a connection
                    if (key.isValid()) {
                        if (key.isAcceptable()) { // accept connection
                            ServerSocketChannel server = (ServerSocketChannel) key.channel();
                            // get client socket channel
                            SocketChannel channel = server.accept();
                            System.out.println("Connection established!");
                            // Non blocking I/O
                            channel.configureBlocking(false);
                            // record it for read/write operations (only read here)
                            channel.register(selector, SelectionKey.OP_READ);
                            continue;
                        } else if (key.isReadable()) { // If readable then the server is ready to read
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            socketChannel.register(key.selector(), SelectionKey.OP_WRITE);
                            socketChannel.configureBlocking(false);
                            // Read byte coming from the client
                            ByteBuffer buffer = ByteBuffer.allocate(socketChannel.getOption(StandardSocketOptions.SO_RCVBUF).intValue());
                            try {
                                socketChannel.read(buffer);

                                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
                                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

                                Object obj = objectInputStream.readObject();

                                if (obj instanceof String) {
                                    System.out.println("We got a String object!!!" + (String) obj);
                                    break;
                                }

                                buffer.clear();
                                key.interestOps(SelectionKey.OP_WRITE);
                            } catch (StreamCorruptedException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                // cleint is no longer active
                                e.printStackTrace();
                                System.out.println("Current key is " + key.toString());
                            }
                        }
                        else if (key.isWritable()) {

                            SocketChannel channel = (SocketChannel) key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(channel.getOption(StandardSocketOptions.SO_RCVBUF).intValue());
                            buffer.flip();
                            channel.write(buffer);
                            if (buffer.hasRemaining()) { // nghia la chung ta van chua gui het du lieu trong buffer o vong lap truoc
                                buffer.compact(); // di chuyen nhung du lieu con lai toi vi tri dau tien cua buffer
                            } else {
                                buffer.clear();
                            }
                            key.interestOps(SelectionKey.OP_READ);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
