package client.utility;

import common.interaction.Request;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SimpleClient {
    public static void main(String args[]) {
        SocketAddress address = new InetSocketAddress("localhost", 1821);
        SocketChannel socketChannel;
        try {
            socketChannel = SocketChannel.open(address);
            System.out.println("Connecting to server...");


            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream clientWriter = new ObjectOutputStream(byteArrayOutputStream);
            clientWriter.flush();

//            String str = new String("Hello this is a string");
            Request userRequest = new Request("this is a command Name", "This is a string argument", "This is a object command");

            clientWriter.writeObject(userRequest);
            clientWriter.flush();
            socketChannel.write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));

            socketChannel.close();


            //////////////////////////////////////
//            ByteBuffer buffer = ByteBuffer.allocate(socketChannel.getOption(StandardSocketOptions.SO_RCVBUF).intValue());
//
//            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
//            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
//
//            String re_data = (String) objectInputStream.readObject();
//
//            System.out.println("We received a string: " + re_data);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Sent request successfully");

    }
}
