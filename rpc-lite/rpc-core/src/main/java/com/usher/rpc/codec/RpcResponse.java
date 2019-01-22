package com.usher.rpc.codec;

import lombok.Data;

import java.io.Serializable;

@Data
public class RpcResponse implements Serializable {
    private String requestId;
    private Object result;
    private Object error;
}
