package com.hireek;

import com.hireek.client.NettyClient;
import com.hireek.constant.Constant;
import com.hireek.constant.RequestTypeEnum;
import com.hireek.constant.SerializerTypeEnum;
import com.hireek.protocol.*;
import io.netty.channel.DefaultEventLoop;
import io.netty.util.concurrent.DefaultPromise;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 描述
 *
 * @Author Hireek
 * @Date: 2021/11/16 23:04
 */
public class ClientProxy implements InvocationHandler {

    private String serverAddress;

    private int port;

    public ClientProxy(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Protocol<Request> requestProtocol = new Protocol<>();
        long requestId = RequestHolder.REQUEST_ID.incrementAndGet();
        Header header = new Header(Constant.MAGIC, SerializerTypeEnum.JAVA.getCode(), RequestTypeEnum.REQUEST.getCode(), requestId, 0);
        Request request = new Request();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParamTypes(method.getParameterTypes());
        request.setParams(args);
        requestProtocol.setHeader(header);
        requestProtocol.setContent(request);
        NettyClient nettyClient = new NettyClient(serverAddress, port);
        RpcFuture<Response> future = new RpcFuture<>(new DefaultPromise<>(new DefaultEventLoop()));
        RequestHolder.REQUEST_MAP.put(requestId, future);
        nettyClient.sendRequest(requestProtocol);
        return future.getPromise().get().getData();
    }
}
