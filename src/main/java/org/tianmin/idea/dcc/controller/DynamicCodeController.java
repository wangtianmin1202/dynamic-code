package org.tianmin.idea.dcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tianmin.idea.dcc.service.DemoService;

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
    DemoService demoService;

    @GetMapping("/demo-service-test-method")
    public ResponseEntity<?> demoServiceTestMethod(@RequestParam("word") String word) {
        String s = demoService.demoServiceTestMethod(word);
        return ResponseEntity.ok(s);
    }
}
