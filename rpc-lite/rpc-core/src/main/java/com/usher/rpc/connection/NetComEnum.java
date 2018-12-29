package com.usher.rpc.connection;

import com.usher.rpc.connection.jetty.client.JettyClient;
import com.usher.rpc.connection.jetty.server.JettyServer;
import com.usher.rpc.connection.netty.client.NettyClient;
import com.usher.rpc.connection.netty.server.NettyServer;

/**
 * 通讯组件
 */
public enum  NetComEnum {
    NETTY(NettyClient.class, NettyServer.class),
    JETTY(JettyClient.class, JettyServer.class);
    public Class<? extends AbstractClient> clientClass;
    public Class<? extends AbstractServer> serverClass;
    NetComEnum(Class<? extends AbstractClient> clientClass, Class<? extends AbstractServer> serverClass) {
        this.serverClass = serverClass;
        this.clientClass = clientClass;
    }
    public static NetComEnum match(String name, NetComEnum defaultNetCom){
        for(NetComEnum netCom : NetComEnum.values()){
            System.out.println(netCom.name());
            if(netCom.name().equals(name)){
                return netCom;
            }
        }
        return defaultNetCom;
    }

    public static void main(String[] args) {
        match("NETTY", NetComEnum.NETTY);
    }
}
