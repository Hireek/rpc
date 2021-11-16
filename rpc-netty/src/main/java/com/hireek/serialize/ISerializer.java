package com.hireek.serialize;

/**
 * 描述
 *
 * @author Hireek
 * @date 2021-11-16 18:59
 */
public interface ISerializer {

    <T> T deserialize(byte[] bytes, Class<T> clazz);

    byte[] serialize(Object object);

    byte getType();
}
