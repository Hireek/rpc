package com.hireek.protocol;

import lombok.Data;

import java.io.Serializable;

/**
 * 描述
 *
 * @author Hireek
 * @date 2021-11-16 18:54
 */
@Data
public class Protocol<T> implements Serializable {

    private Header header;

    private T content;
}
