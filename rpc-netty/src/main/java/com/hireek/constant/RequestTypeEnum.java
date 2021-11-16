package com.hireek.constant;

public enum RequestTypeEnum {

    REQUEST((byte) 0),

    RESPONSE((byte) 1);

    private byte code;

    public byte getCode() {
        return code;
    }

    RequestTypeEnum(byte code) {
        this.code = code;
    }

    public static RequestTypeEnum getByCode(int code) {
        for (RequestTypeEnum reqType : RequestTypeEnum.values()) {
            if (reqType.code == code) {
                return reqType;
            }
        }
        return null;
    }
}
