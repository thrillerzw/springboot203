package com.example.springboot.common.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9000));
        serverSocketChannel.configureBlocking(false);//设置为非阻塞
        Selector selector = Selector.open();
        //服务端也需要注册
        SelectionKey selectionKey1 = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            if(selector.select(1000) == 0){//==0表示没有事件，>0 表示有事件
                System.out.println("NioServer no event");
                continue;
            }
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isAcceptable()){ // OP_ACCEPT
                    //为该客户端生成一个SocketChannel。因为已经有连接过来了，这块不会阻塞
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (selectionKey.isReadable()){//OP_READ
                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                    //得到注册时候的bufer  ByteBuffer.allocate(1024)
                    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                    socketChannel.read(byteBuffer);
                    System.out.println("from client "+new String(byteBuffer.array(),"utf-8"));
                }
                //手动从集合中移除当前key,防止重复操作
                iterator.remove();
            }

        }

    }

}
