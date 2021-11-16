package com.hireek.serialize;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述
 *
 * @Author Hireek
 * @Date: 2021/11/16 20:22
 */
public class SerializerFactory {
    private final static ConcurrentHashMap<Byte, ISerializer> serializerMap = new ConcurrentHashMap<>();

    static {
        ISerializer json = new JsonSerializer();
        ISerializer java = new JavaSerializer();
        serializerMap.put(json.getType(), json);
        serializerMap.put(java.getType(), java);
    }

    private SerializerFactory() {
    }

    public static ISerializer getSerializer(byte key) {
        ISerializer iSerializer = serializerMap.get(key);
        if (iSerializer == null) {
            return new JavaSerializer();
        }
        return iSerializer;
    }
}
