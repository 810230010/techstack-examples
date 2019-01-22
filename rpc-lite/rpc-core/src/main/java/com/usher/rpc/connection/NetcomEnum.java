package com.usher.rpc.connection;

import com.usher.rpc.connection.client.jetty.JettyClient;
import com.usher.rpc.connection.client.netty.NettyClient;
import com.usher.rpc.connection.server.jetty.JettyNetcomServer;
import com.usher.rpc.connection.server.netty.NettyNetcomServer;

/**
 * 通讯组件
 */
public enum NetcomEnum {
    NETTY(NettyClient.class, NettyNetcomServer.class),
    JETTY(JettyClient.class, JettyNetcomServer.class);
    public Class<? extends AbstractNetcomClient> clientClass;
    public Class<? extends AbstractNetcomServer> serverClass;
    NetcomEnum(Class<? extends AbstractNetcomClient> clientClass, Class<? extends AbstractNetcomServer> serverClass) {
        this.serverClass = serverClass;
        this.clientClass = clientClass;
    }
    public static NetcomEnum match(String name, NetcomEnum defaultNetCom){
        for(NetcomEnum netCom : NetcomEnum.values()){
            System.out.println(netCom.name());
            if(netCom.name().equals(name)){
                return netCom;
            }
        }
        return defaultNetCom;
    }

    public static void main(String[] args) {
        match("NETTY", NetcomEnum.NETTY);
    }
}
