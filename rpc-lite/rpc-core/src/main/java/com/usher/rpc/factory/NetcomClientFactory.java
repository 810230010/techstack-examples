package com.usher.rpc.factory;

import com.usher.rpc.common.NetcomType;
import com.usher.rpc.common.SerializorEnum;
import com.usher.rpc.connection.AbstractNetcomClient;
import com.usher.rpc.connection.client.jetty.JettyClient;
import com.usher.rpc.connection.client.mina.MinaClient;
import com.usher.rpc.connection.client.netty.NettyClient;
import com.usher.rpc.serializor.Serializor;

public class NetcomClientFactory {
    public static AbstractNetcomClient newClient(String netcomType, String serverAddress, int serverPort, String serialzorType){
        Serializor serializor = null;
        try {
            serializor = SerializorEnum.macth(serialzorType).getSerializor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if(NetcomType.JETTY.isEqualTo(netcomType)){
            return new JettyClient(serverAddress, serverPort, serializor);
        }else if(NetcomType.MINA.isEqualTo(netcomType)){
            return new MinaClient(serverAddress, serverPort, serializor);
        }else {
            return new NettyClient(serverAddress, serverPort, serializor);
        }
    }
}
