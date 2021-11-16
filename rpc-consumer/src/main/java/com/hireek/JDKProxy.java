package com.hireek;


import java.lang.reflect.Proxy;

/**
 * 描述
 *
 * @Author Hireek
 * @Date: 2021/11/16 23:13
 */
public class JDKProxy {
    public <T> T getProxy(final Class<T> interfs, final String serverAddress, final int port) {
        return (T) Proxy.newProxyInstance(interfs.getClassLoader(), new Class[]{interfs}, new ClientProxy(serverAddress, port));
    }
}
