package com.example.springboot.common.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient {

    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        boolean connSuccess = socketChannel.connect(new InetSocketAddress("127.0.0.1", 9000));
        if (!connSuccess){
            while (!socketChannel.finishConnect()){
                System.out.println("连接需要时间，但不会阻塞。一直等着连接成功");
            }
        }
        String msg="hello";
        ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
        socketChannel.write(byteBuffer);
        System.in.read(); //如果没有这行 或者 把client停止掉，服务端会一直死循环收到读事件，打印出from client hello
    }
}
