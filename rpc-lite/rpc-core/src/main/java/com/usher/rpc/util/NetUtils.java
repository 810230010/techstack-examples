package com.usher.rpc.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

/**
 * @author Usher
 * @date 2018.12.19
 */
public class NetUtils {
    public static final int MIN_PORT = 0;
    public static final int MAX_PORT = 65535;
    public static final int RANDOM_PORT_START = 10000;
    public static final int RANDOM_PORT_END = 30000;
    /**
     * @return local address
     */
    public static String getLocalHostAddress(){
        String address = "";
        try {
            address = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return address;
    }

    public static int getAvailablePort(int port, int gap){
        if(port < 0){
            return getAvailablePort();
        }
        int availablePort = -1;
        ServerSocket serverSocket = null;
        for(int i=port; i<MAX_PORT; i+=gap){
            try {
                serverSocket = new ServerSocket(i);
                return i;
            } catch (IOException e) {
                //do something
            }finally {
                if(serverSocket != null){
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                        //do something
                    }
                }
            }
        }
        return availablePort;
    }
    /**
     *
     * @return available port
     */
    public static int getAvailablePort(int port){
      return getAvailablePort(port, 10);
    }
    /**
     *
     * @return available port
     */
    public static int getAvailablePort(){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(null);
            int localPort = serverSocket.getLocalPort();
            return localPort;
        } catch (IOException e) {
            return getAvailablePort(RANDOM_PORT_START, 10);
        }finally {
            if(serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    //do something
                }
            }
        }
    }
}
