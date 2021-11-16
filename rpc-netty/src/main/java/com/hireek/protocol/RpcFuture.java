package com.hireek.protocol;

import io.netty.util.concurrent.Promise;
import lombok.Data;

/**
 * 描述
 *
 * @author Hireek
 * @date 2021-11-16 19:00
 */
@Data
public class RpcFuture<T> {

    private Promise<T> promise;

    public RpcFuture(Promise<T> promise) {
        this.promise = promise;
    }

}
