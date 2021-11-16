package com.hireek.protocol;

import lombok.Data;

/**
 * 描述
 *
 * @author Hireek
 * @date 2021-11-16 18:50
 */
@Data
public class Header {

    private byte magic;

    private short version;

    private byte reqType;

    private int contentLength;

}
