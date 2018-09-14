package com.usher.rpc.connection;

import com.usher.rpc.connection.jetty.client.JettyClient;
import com.usher.rpc.connection.jetty.server.JettyServer;
import com.usher.rpc.connection.netty.client.NettyClient;
import com.usher.rpc.connection.netty.server.NettyServer;
import lombok.Data;

/**
 * 通讯组件
 */
public enum  NetComEnum {
    NETTY(NettyClient.class, NettyServer.class),
    JETTY(JettyClient.class, JettyServer.class);
    public Class<? extends IClient> clientClass;
    public Class<? extends IServer> serverClass;
    NetComEnum(Class<? extends IClient> clientClass, Class<? extends IServer> serverClass) {
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
