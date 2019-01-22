package com.usher.rpc.factory;

import com.usher.rpc.common.NetcomType;
import com.usher.rpc.common.SerializorEnum;
import com.usher.rpc.connection.AbstractNetcomServer;
import com.usher.rpc.connection.client.jetty.JettyClient;
import com.usher.rpc.connection.server.jetty.JettyNetcomServer;
import com.usher.rpc.connection.server.mina.MinaNetcomServer;
import com.usher.rpc.connection.server.netty.NettyNetcomServer;
import com.usher.rpc.serializor.Serializor;

public class NetcomServerFactory {
    public static AbstractNetcomServer newServer(String netcomType, int serverPort, String serialzorType){
        Serializor serializor = null;
        try {
            serializor = SerializorEnum.macth(serialzorType).getSerializor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if(NetcomType.JETTY.isEqualTo(netcomType)){
            return new JettyNetcomServer().init(serverPort, serializor);
        }else if(NetcomType.MINA.isEqualTo(netcomType)){
            return new MinaNetcomServer().init(serverPort, serializor);
        }else {
            return new NettyNetcomServer().init(serverPort, serializor);
        }
    }
}
