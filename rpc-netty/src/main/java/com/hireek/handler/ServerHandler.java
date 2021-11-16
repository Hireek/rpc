package com.hireek.handler;

import com.hireek.constant.RequestTypeEnum;
import com.hireek.protocol.Header;
import com.hireek.protocol.Protocol;
import com.hireek.protocol.Request;
import com.hireek.protocol.Response;
import com.hireek.spring.SpringBeanManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 描述
 *
 * @Author Hireek
 * @Date: 2021/11/16 22:05
 */
public class ServerHandler extends SimpleChannelInboundHandler<Protocol<Request>> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Protocol<Request> requestProtocol) throws Exception {
        Request request = requestProtocol.getContent();
        Response response = new Response();
        response.setMsg("success!");
        Object invoke = invoke(request);
        response.setData(invoke);

        Protocol<Response> responseProtocol = new Protocol<>();
        responseProtocol.setContent(response);

        Header header = requestProtocol.getHeader();
        header.setReqType(RequestTypeEnum.RESPONSE.getCode());
        responseProtocol.setHeader(header);

        channelHandlerContext.writeAndFlush(responseProtocol);
    }

    private Object invoke(Request request) {
        try {
            Class<?> aClass = Class.forName(request.getClassName());
            Method method = aClass.getDeclaredMethod(request.getMethodName(), request.getParamTypes());
            return method.invoke(SpringBeanManager.getBean(aClass), request.getParams());
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
