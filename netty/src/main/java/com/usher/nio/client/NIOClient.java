package com.usher.nio.client;

import org.hibernate.sql.Select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NIOClient {
    private Selector selector;
    public NIOClient initClient(String serverAddress, int port){
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(serverAddress, port));


            selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            return this;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }
    public void startClient(){
       while (true){
           try {
               selector.select();
               Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
               while(keys.hasNext()){
                   SelectionKey key = keys.next();
                   keys.remove();
                   if(key.isConnectable()){
                       SocketChannel channel=(SocketChannel)key.channel();

                       //如果正在连接，则完成连接
                       if(channel.isConnectionPending()){
                           channel.finishConnect();
                       }

                       channel.configureBlocking(false);
                       //向服务器发送消息
                       channel.write(ByteBuffer.wrap(new String("send message to server.").getBytes()));

                       //连接成功后，注册接收服务器消息的事件
                       channel.register(selector, SelectionKey.OP_READ);
                       System.out.println("客户端连接成功");
                   }else if(key.isReadable()){
                       SocketChannel channel = (SocketChannel)key.channel();

                       ByteBuffer buffer = ByteBuffer.allocate(100);
                       channel.read(buffer);
                       byte[] data = buffer.array();
                       String message = new String(data);

                       System.out.println("recevie message from server:, size:" + buffer.position() + " msg: " + message);
                   }
               }

           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    }

    public static void main(String[] args) {
        new NIOClient().initClient("localhost", 9981).startClient();
    }
}
