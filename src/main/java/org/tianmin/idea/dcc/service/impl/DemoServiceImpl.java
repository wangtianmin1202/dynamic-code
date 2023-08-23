package org.tianmin.idea.dcc.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tianmin.idea.dcc.annotation.EnableDcc;
import org.tianmin.idea.dcc.service.DemoService;

/**
 * @Author wangtianmin
 * @Date 2023/8/23 09:34
 * @Description: TODO
 * @Version 1.0
 */
@Service
@Slf4j
public class DemoServiceImpl implements DemoService {
    @EnableDcc("DemoServiceImpl.demoServiceTestMethod(java.lang.String)")
    @Override
    public String demoServiceTestMethod(String word) {
        System.out.println(word);
        log.info(word);
        return word;
    }
}
