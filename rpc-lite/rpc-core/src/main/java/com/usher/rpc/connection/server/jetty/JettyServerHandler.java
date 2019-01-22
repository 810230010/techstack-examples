package com.usher.rpc.connection.server.jetty;

import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.serializor.Serializor;
import com.usher.rpc.stub.RpcServiceInvoker;
import com.usher.rpc.util.HttpClientUtils;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class JettyServerHandler extends AbstractHandler {
    private Serializor serializor;
    public JettyServerHandler(Serializor serializor){
        this.serializor = serializor;
    }
    @Override
    public void handle(String s, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        byte[] requestBytes = HttpClientUtils.readBytes(httpServletRequest);
        RpcRequest rpcRequest = serializor.deserialize(requestBytes, RpcRequest.class);
        RpcResponse response = RpcServiceInvoker.invokeService(rpcRequest);

        // serialize response
        byte[] responseBytes = serializor.serialize(response);

        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        request.setHandled(true);

        OutputStream out = httpServletResponse.getOutputStream();
        out.write(responseBytes);
        out.flush();
    }
}
