package com.hireek.protocol;

import lombok.Data;

/**
 * 描述
 *
 * @author Hireek
 * @date 2021-11-16 18:54
 */
@Data
public class Response<T> {

    private Header header;

    private T body;

}
