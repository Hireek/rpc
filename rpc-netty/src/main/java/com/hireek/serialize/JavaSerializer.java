package com.hireek.serialize;

import com.hireek.constant.SerializerTypeEnum;

import java.io.*;

/**
 * 描述
 *
 * @author Hireek
 * @date 2021-11-16 19:00
 */
public class JavaSerializer implements ISerializer {

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        try (ByteArrayInputStream in = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(in)) {
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            //
        }
        return null;
    }

    @Override
    public byte[] serialize(Object object) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(object);
            return bos.toByteArray();
        } catch (IOException e) {
            //
        }
        return new byte[0];
    }

    @Override
    public byte getType() {
        return SerializerTypeEnum.JAVA.getCode();
    }
}
