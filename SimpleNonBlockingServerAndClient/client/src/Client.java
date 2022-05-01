import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    public static void main(String args[]) {
        SocketAddress address = new InetSocketAddress("localhost", 25565);
        SocketChannel socketChannel;
        try {
            socketChannel = SocketChannel.open(address);
            System.out.println("Connecting to server...");


            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream clientWriter = new ObjectOutputStream(byteArrayOutputStream);
            clientWriter.flush();

//            String str = new String("Hello this is a string");
            Request userRequest = new Request(12, "This is a string");

            clientWriter.writeObject(userRequest);
            clientWriter.flush();
            socketChannel.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));

            socketChannel.close();


            //////////////////////////////////////
//            ByteBuffer buffer = ByteBuffer.allocate(1024);
//
//            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
//            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
//
//            Response serverResponse = (Response) objectInputStream.readObject();
//
//            System.out.println("We received a response: " + serverResponse);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Sent request successfully");

    }
}
