package com.usher.rpc.connection;

import com.usher.rpc.codec.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 用来存放连接产生的响应
 */
@Slf4j
public class RpcFutureManager {
    private static volatile RpcFutureManager INSTANCE = new RpcFutureManager();
    private ConcurrentHashMap<String, RpcFutureReponse> rpcFutures = new ConcurrentHashMap<>();

    public static RpcFutureManager getInstance() {
        return INSTANCE;
    }

    public void addRpcInvokeFuture(String requestId, RpcFutureReponse futureReponse){
        rpcFutures.putIfAbsent(requestId, futureReponse);
    }

    public void removeRpcInvokeFuture(String requestId){
        rpcFutures.remove(requestId);
    }

    public void notifyFutureResponse(String requestId, RpcResponse rpcResponse) {
        RpcFutureReponse futureReponse = rpcFutures.get(requestId);
        if(null == futureReponse){
            log.info("no such request, which request_id = " + requestId);
            return;
        }
        futureReponse.setResponse(rpcResponse);
        removeRpcInvokeFuture(requestId);
    }
}
