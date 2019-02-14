package com.usher.rpc.connection;

import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.common.exception.RpcException;

import java.util.concurrent.*;

public class RpcFutureReponse implements Future<RpcResponse> {
    private RpcResponse response;
    private RpcRequest request;
    private RpcFutureManager futureManager;


    private boolean done;
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    public RpcFutureReponse addIntoFutureManager(RpcFutureManager futureManager){
        this.setFutureManager(futureManager);
        return this;
    }
    public RpcFutureReponse addRpcRequest(RpcRequest request){
        this.setRequest(request);
        this.futureManager.addRpcInvokeFuture(request.getRequestId(), this);
        return this;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public RpcResponse get() throws InterruptedException, ExecutionException {
        try {
            return get(-1, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            e.printStackTrace();
            throw new RpcException("oops, something error with the request!");
        }

    }

    @Override
    public RpcResponse get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        if(!done){
            if(timeout < 0){
                countDownLatch.await();
            }else{
                countDownLatch.await(timeout, unit);
            }
        }
        if(!done){
            throw new TimeoutException("oops, something error with the request!");
        }
        return response;
    }

    public RpcResponse getResponse() {
        return response;
    }
    public void setResponse(RpcResponse response) {
        this.response = response;
        done = true;
        countDownLatch.countDown();
    }
    public RpcRequest getRequest() {
        return request;
    }

    public void setRequest(RpcRequest request) {
        this.request = request;
    }

    public RpcFutureManager getFutureManager() {
        return futureManager;
    }

    public void setFutureManager(RpcFutureManager futureManager) {
        this.futureManager = futureManager;
    }
}
