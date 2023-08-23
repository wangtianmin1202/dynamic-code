package org.tianmin.idea.dcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tianmin.idea.dcc.entity.DynamicCode;
import org.tianmin.idea.dcc.service.DccService;
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
    @Autowired
    DccService dccService;

    @GetMapping("/demo-service-test-method")
    public ResponseEntity<?> demoServiceTestMethod(@RequestParam("word") String word) {
        String s = demoService.demoServiceTestMethod(word);
        return ResponseEntity.ok(s);
    }

    @PostMapping("/insert-new")
    public ResponseEntity<?> insertNew(@RequestBody DynamicCode dynamicCode) {
        dccService.insertNew(dynamicCode);
        return ResponseEntity.ok(1);
    }
    @PostMapping("/update-groovy")
    public ResponseEntity<?> updateGroovy(@RequestBody DynamicCode dynamicCode) {
        dccService.updateGroovy(dynamicCode);
        return ResponseEntity.ok(1);
    }
    @DeleteMapping("/delete-and-invalidate")
    public ResponseEntity<?> deleteAndInvalidate(@RequestBody DynamicCode dynamicCode) {
        dccService.deleteAndInvalidate(dynamicCode);
        return ResponseEntity.ok(1);
    }
}
