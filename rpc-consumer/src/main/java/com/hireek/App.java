package com.hireek;

import com.hireek.api.TestService;

/**
 * 描述
 *
 * @Author Hireek
 * @Date: 2021/11/16 23:03
 */
public class App {
    public static void main(String[] args) {
        JDKProxy proxy = new JDKProxy();
        TestService service = proxy.getProxy(TestService.class, "127.0.0.1", 8080);
        System.out.println(service.test("feng"));
    }
}
