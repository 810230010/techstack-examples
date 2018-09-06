package com.usher.rpc.connector.jetty.server;

import com.usher.rpc.codec.RpcRequest;
import com.usher.rpc.codec.RpcResponse;
import com.usher.rpc.connector.RpcServer;
import com.usher.rpc.common.serialization.Serializor;
import com.usher.rpc.util.HttpClientUtil;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class JettyServerHandler extends AbstractHandler {
    private Serializor serializor;

    public JettyServerHandler(Serializor serializor) {
        this.serializor = serializor;
    }

    @Override
    public void handle(String s, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        byte[] bytes = HttpClientUtil.readBytes(httpServletRequest);
        RpcRequest rpcRequest = (RpcRequest) serializor.deserialize(bytes, RpcRequest.class);
        RpcResponse response = RpcServer.invokeServiceImpl(rpcRequest, null);
        byte[] responseBytes = serializor.serialize(response);
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        request.setHandled(true);
        OutputStream os = httpServletResponse.getOutputStream();
        os.write(responseBytes);
        os.flush();
    }


}
