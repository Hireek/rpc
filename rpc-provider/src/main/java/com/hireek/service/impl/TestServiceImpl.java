package com.hireek.service.impl;

import com.hireek.api.TestService;
import org.springframework.stereotype.Service;

/**
 * 描述
 *
 * @Author Hireek
 * @Date: 2021/11/16 22:28
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public String test(String name) {
        return "hello," + name + "!";
    }
}
