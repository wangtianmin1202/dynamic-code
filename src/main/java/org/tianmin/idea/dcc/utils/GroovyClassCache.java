package org.tianmin.idea.dcc.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * @Author wangtianmin
 * @Date 2023/8/23 16:20
 * @Description: GroovyClass缓存 未来需要填充多线程高并发场景的
 * @Version 1.0
 */
public class GroovyClassCache {
    public static final Cache<String, Class<?>> cache = CacheBuilder.newBuilder().build();

    private GroovyClassCache() {
    }
}
