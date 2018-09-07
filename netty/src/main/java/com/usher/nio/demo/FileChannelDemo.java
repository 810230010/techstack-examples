package com.usher.nio.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class FileChannelDemo {
    private static final String READ_WRITE_MODE = "rw";
    private static final String FILE_PATH = "E:\\test2.txt";
    public static void readFileToByteBuffer(String filePath) throws IOException {
        RandomAccessFile file = new RandomAccessFile(new File(filePath), READ_WRITE_MODE);
        Channel fileChannel = file.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int size = ((FileChannel) fileChannel).read(byteBuffer);
        while (size > 0){
            Buffer buffer = byteBuffer.flip();
            Charset charset = Charset.forName("UTF-8");
            String content = charset.newDecoder().decode(byteBuffer).toString();
            System.out.println(content);
            byteBuffer.clear();
            size = ((FileChannel) fileChannel).read(byteBuffer);
        }
        fileChannel.close();
        file.close();
    }
    public static void copyFile(String filePath, String desFile) throws IOException {
        RandomAccessFile file = new RandomAccessFile(new File(filePath), READ_WRITE_MODE);
        File f = new File(desFile);
        if(!f.exists()){
            f.createNewFile();
        }
        RandomAccessFile file2 = new RandomAccessFile(f, READ_WRITE_MODE);
        Channel fileChannel = file.getChannel();
        Channel fileChannel2 = file2.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        int size = ((FileChannel) fileChannel).read(byteBuffer);
        while (size > 0){
            byteBuffer.flip();
            ((FileChannel) fileChannel2).write(byteBuffer);
            byteBuffer.clear();
            size = ((FileChannel) fileChannel).read(byteBuffer);
        }
        fileChannel.close();
        file.close();
    }
}
