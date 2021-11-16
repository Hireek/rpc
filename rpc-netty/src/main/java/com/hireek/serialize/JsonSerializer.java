package com.hireek.serialize;

import com.alibaba.fastjson.JSON;
import com.hireek.constant.SerializerTypeEnum;

/**
 * 描述
 *
 * @author Hireek
 * @date 2021-11-16 19:00
 */
public class JsonSerializer implements ISerializer {
    @Override
    public byte[] serialize(Object obj) {
        return JSON.toJSONString(obj).getBytes();
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) {
        return JSON.parseObject(new String(data), clazz);
    }

    @Override
    public byte getType() {
        return SerializerTypeEnum.JSON.getCode();
    }
}
