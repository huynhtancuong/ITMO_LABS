import jdk.nashorn.internal.objects.NativeArray;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class Server {

    private static void accept(SelectionKey key, Selector selector) throws IOException {
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        // get client socket channel
        SocketChannel channel = server.accept();
        System.out.println("Connection established!");
        // Non blocking I/O
        channel.configureBlocking(false);
        // record it for read/write operations (only read here)
        channel.register(selector, SelectionKey.OP_READ);
    }

    private static void read(SelectionKey key, Selector selector) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
//        socketChannel.register(key.selector(), SelectionKey.OP_WRITE);
//        socketChannel.configureBlocking(false);
        // Read byte coming from the client
        ByteBuffer buffer = ByteBuffer.allocate(16*1024);
        try {
            int readStatus = socketChannel.read(buffer);
            if (readStatus == -1) key.cancel();

            Object obj = null;
            if (readStatus != -1) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                obj = objectInputStream.readObject();
                buffer.flip();
                buffer.clear();
            }

            if (obj instanceof Request) {
                System.out.println("We got a Request object!!!\n" + ((Request) obj).toString());
            }

            buffer.clear();
//            key.interestOps(SelectionKey.OP_WRITE);
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            // cleint is no longer active
            e.printStackTrace();
            System.out.println("Current key is " + key.toString());
        }
    }

    public static void write(SelectionKey key, Selector selector) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.flip();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.flush();

        Response serverResponse = new Response("Hi, this is my request");

        objectOutputStream.writeObject(serverResponse);
        objectOutputStream.flush();

        int writeStatus = channel.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));

        if (buffer.hasRemaining()) { // nghia la chung ta van chua gui het du lieu trong buffer o vong lap truoc
            buffer.compact(); // di chuyen nhung du lieu con lai toi vi tri dau tien cua buffer
        } else {
            buffer.clear();
        }
//        key.interestOps(SelectionKey.OP_READ);
    }

    public static void main(String args[]) {
        try {
            Selector selector = Selector.open();

            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);

            InetSocketAddress hostAddress = new InetSocketAddress(25565);
            serverChannel.bind(hostAddress);

            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                selector.select();

                // process selected keys
                Set<SelectionKey> readyKeys = selector.selectedKeys();
                int readyCount = readyKeys.size();
                if (readyCount == 0) continue;
//                Iterator keyIterator = readyKeys.iterator();
//                while (keyIterator.hasNext()) {
//
//                    SelectionKey key = (SelectionKey) keyIterator.next();
//                    if (key.isValid()) {
//
//                        if (key.isAcceptable()) { // accept connection
//                            accept(key, selector);
//                        } else if (key.isReadable()) { // If readable then the server is ready to read
//                            read(key, selector);
//                        } else if (key.isWritable()) {
//                            write(key, selector);
//                        }
//
//                    }
//                    keyIterator.remove();
//                }
//                for (SelectionKey key: readyKeys) {
//                    if (key.isValid()){
//                        if (key.isAcceptable()) { // accept connection
//                            accept(key, selector);
//                        } else if (key.isReadable()) { // If readable then the server is ready to read
//                            read(key, selector);
//                        } else if (key.isWritable()) {
//                            write(key, selector);
//                        }
//                    }
//                }
//                Iterator iter;
                for (Iterator<SelectionKey> it = readyKeys.iterator(); it.hasNext(); ){
                    SelectionKey key = it.next();
                    it.remove();
                    if (key.isValid()) {
                        if (key.isAcceptable()) {accept(key, selector);} // accept
                        else if (key.isReadable()) {
                            read(key, selector);
                        }
                        else if (key.isWritable()) {
                            write(key, selector);
                        } // write
                    }
            }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
