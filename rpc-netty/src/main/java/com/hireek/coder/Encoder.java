package com.hireek.coder;

import com.hireek.protocol.Header;
import com.hireek.protocol.Protocol;
import com.hireek.serialize.SerializerFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.log4j.Log4j2;

/**
 * 描述
 *
 * @Author Hireek
 * @Date: 2021/11/16 21:12
 */
@Log4j2
public class Encoder extends MessageToByteEncoder<Protocol<Object>> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Protocol<Object> protocol, ByteBuf byteBuf) throws Exception {
        log.info("============begin encode=========");
        Header header = protocol.getHeader();
        byteBuf.writeShort(header.getMagic());
        byteBuf.writeByte(header.getSerialType());
        byteBuf.writeByte(header.getReqType());
        byteBuf.writeLong(header.getRequestId());


        Object content = protocol.getContent();
        byte[] serialize = SerializerFactory.getSerializer(header.getSerialType()).serialize(content);
        byteBuf.writeInt(serialize.length);
        byteBuf.writeBytes(serialize);

        log.info("============end encode=========");
    }
}
