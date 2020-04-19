package com.example.springboot.common.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class LocalFileReadWrite {

    public static void main(String[] args) throws IOException {
//        bufferTest();
//        writeFile("/Users/thrillerzw/Downloads/test.txt");
//        readFile("/Users/thrillerzw/Downloads/test.txt");
        copyFile("/Users/thrillerzw/Downloads/test.txt","/Users/thrillerzw/Downloads/test4.txt");
    }
    //测试buffer
    public static void bufferTest(){
        IntBuffer intBuffer = IntBuffer.allocate(3);//可存放3个int的buffer
        intBuffer.put(1);
        intBuffer.put(2);
        intBuffer.flip();//buffer转换，读写切换
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
    //写文件
    public static void writeFile(String filePath) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        //FileOutputStream --> channel
        FileChannel channel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
        //hello占5个字节
        byteBuffer.put("hello".getBytes());
        byteBuffer.flip();
        //channel-->buffer 从缓冲区写入到通道
        channel.write(byteBuffer);
        //关闭底层流就行
        fileOutputStream.close();
    }

    //读文件
    public static void readFile(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        //FileInputStream --> channel
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        //从通道读取到缓冲区
        channel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }

    public static void copyFile(String fromPath,String toPath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fromPath);
        FileChannel readChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream(toPath);
        FileChannel writeChannel = fileOutputStream.getChannel();
        //方式1
        readChannel.transferTo(0,readChannel.size(),writeChannel);
        //方式2
//        writeChannel.transferFrom(readChannel, 0, readChannel.size());
        //方式3
       /* ByteBuffer byteBuffer = ByteBuffer.allocate(1);
        int readNum=0;
        while (readNum != -1){
             byteBuffer.clear();
             readNum = readChannel.read(byteBuffer);
             byteBuffer.flip();
             writeChannel.write(byteBuffer);
        }*/
        fileInputStream.close();
        fileOutputStream.close();
    }


}
