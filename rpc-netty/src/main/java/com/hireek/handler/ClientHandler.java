package com.hireek.handler;

import com.hireek.protocol.Protocol;
import com.hireek.protocol.RequestHolder;
import com.hireek.protocol.Response;
import com.hireek.protocol.RpcFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 描述
 *
 * @Author Hireek
 * @Date: 2021/11/16 22:35
 */
public class ClientHandler extends SimpleChannelInboundHandler<Protocol<Response>> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Protocol<Response> requestProtocol) throws Exception {
        RpcFuture<Response> rpcFuture = RequestHolder.REQUEST_MAP.remove(requestProtocol.getHeader().getRequestId());
        rpcFuture.getPromise().setSuccess(requestProtocol.getContent());
    }
}