package com.hireek.protocol;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 描述
 *
 * @Author Hireek
 * @Date: 2021/11/16 23:19
 */
public class RequestHolder {
    public static final AtomicLong REQUEST_ID = new AtomicLong();

    public static final Map<Long, RpcFuture<Response>> REQUEST_MAP = new ConcurrentHashMap<>();
}
