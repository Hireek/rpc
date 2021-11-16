package com.hireek.coder;

import com.hireek.constant.Constant;
import com.hireek.constant.RequestTypeEnum;
import com.hireek.protocol.Header;
import com.hireek.protocol.Protocol;
import com.hireek.protocol.Request;
import com.hireek.protocol.Response;
import com.hireek.serialize.ISerializer;
import com.hireek.serialize.SerializerFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
 * 描述
 *
 * @Author Hireek
 * @Date: 2021/11/16 21:26
 */
@Log4j2
public class Decoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        log.info("========begin RpcDecoder==========");
        if (byteBuf.readableBytes() < Constant.HEAD_TOTOAL_LEN) {
            return;
        }
        byteBuf.markReaderIndex(); //标记读取开始索引
        short magic = byteBuf.readShort();

        if (magic != Constant.MAGIC) {
            throw new IllegalArgumentException("Illegal request parameter magic--->" + magic);
        }
        byte serialType = byteBuf.readByte();
        byte reqType = byteBuf.readByte();
        long requestId = byteBuf.readLong();
        int length = byteBuf.readInt();

        if (byteBuf.readableBytes() < length) {
            byteBuf.resetReaderIndex();
            return;
        }
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        RequestTypeEnum typeEnum = RequestTypeEnum.getByCode(reqType);
        ISerializer serializer = SerializerFactory.getSerializer(serialType);
        Header header = new Header(magic, serialType, reqType, requestId, length);
        switch (typeEnum) {
            case REQUEST:
                Protocol<Request> requestProtocol = new Protocol<>();
                requestProtocol.setHeader(header);
                Request request = serializer.deserialize(bytes, Request.class);
                requestProtocol.setContent(request);
                list.add(requestProtocol);
                break;
            case RESPONSE:
                Protocol<Response> responseProtocol = new Protocol<>();
                responseProtocol.setHeader(header);
                Response response = serializer.deserialize(bytes, Response.class);
                responseProtocol.setContent(response);
                list.add(responseProtocol);
                break;
            default:
                //
                break;
        }
    }
}
