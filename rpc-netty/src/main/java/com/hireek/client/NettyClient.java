package com.hireek.client;

import com.hireek.handler.ClientInitializer;
import com.hireek.protocol.Protocol;
import com.hireek.protocol.Request;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

/**
 * 描述
 *
 * @Author Hireek
 * @Date: 2021/11/16 22:52
 */
@Data
@Log4j2
public class NettyClient {

    private final Bootstrap bootstrap;
    private final EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
    private String serverAddress;
    private int serverPort;

    public NettyClient(String serverAddress, int serverPort) {

        this.bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ClientInitializer());
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void sendRequest(Protocol<Request> protocol) throws InterruptedException {
        final ChannelFuture future = bootstrap.connect(this.serverAddress, this.serverPort).sync();
        future.addListener(listener -> {
            if (future.isSuccess()) {
                log.info("connect rpc server {} success.", this.serverAddress);
            } else {
                log.error("connect rpc server {} failed. ", this.serverAddress);
                future.cause().printStackTrace();
                eventLoopGroup.shutdownGracefully();
            }
        });
        log.info("begin transfer data");
        future.channel().writeAndFlush(protocol);
    }
}
