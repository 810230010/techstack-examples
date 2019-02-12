package com.usher.netty.client;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectManager {
    private static Map<String, Channel> channels = new ConcurrentHashMap<>();

    public static Channel getConnect(String serverAddress){
        Channel connect = null;
        connect = channels.get(serverAddress);
        return connect;
    }

    public static void storeClientConnect(String serverAddress, Channel channel){
        channels.putIfAbsent(serverAddress, channel);
    }
}
