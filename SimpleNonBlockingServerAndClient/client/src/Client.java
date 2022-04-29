import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String args[]) {
        SocketAddress address = new InetSocketAddress("localhost", 25565);
        SocketChannel socketChannel;
        try {
            socketChannel = SocketChannel.open(address);
            System.out.println("Connecting to server...");


            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream clientWriter = new ObjectOutputStream(byteArrayOutputStream);

            String str = new String("Hello this is a string");

            clientWriter.writeObject(str);
            clientWriter.flush();
            socketChannel.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));


            //////////////////////////////////////
            ByteBuffer buffer = ByteBuffer.allocate(socketChannel.getOption(StandardSocketOptions.SO_RCVBUF).intValue());

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            String re_data = (String) objectInputStream.readObject();

            System.out.println("We received a string: " + re_data);


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Sent request successfully");

    }
}
