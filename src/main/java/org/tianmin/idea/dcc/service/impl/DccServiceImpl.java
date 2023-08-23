package org.tianmin.idea.dcc.service.impl;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tianmin.idea.dcc.consts.DccConsts;
import org.tianmin.idea.dcc.entity.DynamicCode;
import org.tianmin.idea.dcc.service.DccService;
import org.tianmin.idea.dcc.utils.GroovyClassCache;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

/**
 * @Author wangtianmin
 * @Date 2023/8/23 10:51
 * @Description: TODO
 * @Version 1.0
 */
@Service
@Slf4j
public class DccServiceImpl implements DccService {
    GroovyClassLoader loader = new GroovyClassLoader();
    MessageDigest md = MessageDigest.getInstance("MD5");
    private final StringRedisTemplate stringRedisTemplate;
    private final JdbcTemplate jdbcTemplate;

    public DccServiceImpl(StringRedisTemplate stringRedisTemplate,
                          JdbcTemplate jdbcTemplate) throws NoSuchAlgorithmException {
        this.stringRedisTemplate = stringRedisTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void proceedBefore(String dccCode, Object[] args) throws
            ExecutionException, InstantiationException, IllegalAccessException {
        String mainKey = DccConsts.REDIS_PERFIX_BEFORE + dccCode;
        if (!(boolean) stringRedisTemplate.hasKey(mainKey)) return;
        String groovyString = stringRedisTemplate.opsForValue().get(mainKey);
        Class<?> groovyClass = GroovyClassCache
                .cache
                .get(mainKey, () -> loader.parseClass(groovyString, mainKey + ".groovy"));
        GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
        groovyObject.invokeMethod("proceed", args);
    }

    @Override
    public void proceedReturn(String dccCode, Object[] allArgs) throws
            InstantiationException, IllegalAccessException, ExecutionException {
        String mainKey = DccConsts.REDIS_PERFIX_RETURN + dccCode;
        if (!(boolean) stringRedisTemplate.hasKey(mainKey)) return;
        String groovyString = stringRedisTemplate.opsForValue().get(mainKey);
        Class<?> groovyClass = GroovyClassCache
                .cache
                .get(mainKey, () -> loader.parseClass(groovyString, mainKey + ".groovy"));
        GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
        groovyObject.invokeMethod("proceed", allArgs);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertNew(DynamicCode dynamicCode) {
        log.info(dynamicCode.getGroovyText());
        String insertSql = "" +
                "INSERT dynamic_code ( dcc_code, type, groovy_text ) VALUES ('%s','%s','%s')";
        jdbcTemplate.execute(String.format(insertSql, dynamicCode.getDccCode(),
                dynamicCode.getType(),
                dynamicCode.getGroovyText()));
        String mainKey = (dynamicCode.getType().equals("before")
                ? DccConsts.REDIS_PERFIX_BEFORE : DccConsts.REDIS_PERFIX_RETURN)
                + dynamicCode.getDccCode();
        stringRedisTemplate.opsForValue().set(mainKey, dynamicCode.getGroovyText());
        //
        GroovyClassCache.cache.invalidate(mainKey);
        GroovyClassCache.cache.put(mainKey, loader.parseClass(dynamicCode.getGroovyText(), mainKey + ".groovy"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAndInvalidate(DynamicCode dynamicCode) {

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateGroovy(DynamicCode dynamicCode) {

    }
}
