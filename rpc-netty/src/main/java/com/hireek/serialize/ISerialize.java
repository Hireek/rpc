package com.hireek.serialize;

/**
 * 描述
 *
 * @author Hireek
 * @date 2021-11-16 18:59
 */
public interface ISerialize {

    Object deSerialize (byte[] bytes);

    byte[] serialize(Object object);

}
