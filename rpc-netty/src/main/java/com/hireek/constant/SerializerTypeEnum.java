package com.hireek.constant;

public enum SerializerTypeEnum {

    JAVA((byte) 0),

    JSON((byte) 1);

    private byte code;

    public byte getCode() {
        return code;
    }

    SerializerTypeEnum(byte code) {
        this.code = code;
    }

}
