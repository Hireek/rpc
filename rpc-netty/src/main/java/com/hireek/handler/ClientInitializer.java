package com.hireek.handler;

import com.hireek.coder.Decoder;
import com.hireek.coder.Encoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 描述
 *
 * @Author Hireek
 * @Date: 2021/11/16 21:01
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().
                addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,
                        12,
                        4,
                        0,
                        0))
                .addLast(new Decoder())
                .addLast(new Encoder())
                .addLast(new ClientHandler());
    }
}
