package org.tianmin.idea.dcc.controller;

import groovy.lang.GroovyClassLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author wangtianmin
 * @Date 2023/8/22 17:54
 * @Description: TODO
 * @Version 1.0
 */

@RestController
@RequestMapping("/dynamic-code")
public class DynamicCodeController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/get-method")
    public ResponseEntity<?> getMethod(String description) {
        String dccValue = stringRedisTemplate.opsForValue().get("dcc:");
        return ResponseEntity.ok("description");
    }
}
