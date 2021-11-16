package com.hireek.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 描述
 *
 * @author Hireek
 * @date 2021-11-16 18:50
 */
@Data
@AllArgsConstructor
public class Header {

    private short magic; //魔数 2字节
    private byte serialType; //序列化类型  1个字节
    private byte reqType; //消息类型  1个字节
    private long requestId; //请求id  8个字节
    private int length;//消息体长度，4个字节

}
