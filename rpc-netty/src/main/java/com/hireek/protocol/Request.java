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
public class Request implements Serializable {

    private String className;

    private String methodName;

    private Object[] params;

    private Class[] paramTypes;

}
