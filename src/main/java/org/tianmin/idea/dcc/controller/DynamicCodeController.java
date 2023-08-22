package org.tianmin.idea.dcc.controller;

import groovy.lang.GroovyClassLoader;
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
    @GetMapping("/get-method")
    public ResponseEntity<?> getMethod(String description) {
        return ResponseEntity.ok("description");
    }
}
