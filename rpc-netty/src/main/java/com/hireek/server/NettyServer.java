package com.hireek.server;

import com.hireek.handler.ServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.log4j.Log4j2;

/**
 * 描述
 *
 * @Author Hireek
 * @Date: 2021/11/16 20:45
 */
@Log4j2
public class NettyServer {

    private String serverAddress;

    private int port;

    public NettyServer(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public void startNettyServer() {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerInitializer());
        try {
            ChannelFuture future = serverBootstrap.bind(serverAddress, port).sync();
            log.info("Server start Success on serverAddress-{},Port-{}", serverAddress, port);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

}
