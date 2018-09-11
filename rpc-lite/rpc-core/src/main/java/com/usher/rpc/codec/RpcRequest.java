package com.usher.rpc.codec;

import lombok.Data;

import java.io.Serializable;
@Data
public class RpcRequest implements Serializable {
    private String requestId;
    private String serverAddress;
    private int port;
    private String methodName;
    private Class[] paramTypes;
    private Object[] params;
    private String ifaceName;
}
