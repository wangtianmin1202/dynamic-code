package org.tianmin.idea.dcc.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.tianmin.idea.dcc.consts.DccConsts;
import org.tianmin.idea.dcc.entity.DynamicCode;

import java.util.List;

/**
 * 启动后刷一次DB数据到Redis
 */
@Component
public class DccApplicationRunner implements ApplicationRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String sql = "SELECT * FROM dynamic_code";
        List<DynamicCode> dynamicCodeListInDb = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DynamicCode.class));
        dynamicCodeListInDb.forEach(dcc -> {
            String mainKey = (dcc.getType().equals("before")
                    ? DccConsts.REDIS_PERFIX_BEFORE : DccConsts.REDIS_PERFIX_RETURN)
                    + dcc.getDccCode();
            if (!Boolean.TRUE.equals(stringRedisTemplate.hasKey(mainKey))) {
                stringRedisTemplate.opsForValue().set(mainKey, dcc.getGroovyText());
            }
        });
    }
}
