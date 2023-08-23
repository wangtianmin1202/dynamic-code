package org.tianmin.idea.dcc.service;

import java.util.concurrent.ExecutionException;

/**
 * @Author wangtianmin
 * @Date 2023/8/23 10:50
 * @Description: TODO
 * @Version 1.0
 */
public interface DccService {
    void proceedBefore(String dccCode, Object[] args) throws InstantiationException, IllegalAccessException, ExecutionException;

    void proceedReturn(String dccCode, Object[] allArgs) throws InstantiationException, IllegalAccessException, ExecutionException;
}
