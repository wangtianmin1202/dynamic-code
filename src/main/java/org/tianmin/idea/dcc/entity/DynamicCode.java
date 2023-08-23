package org.tianmin.idea.dcc.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @Author wangtianmin
 * @Date 2023/8/23 16:56
 * @Description: TODO
 * @Version 1.0
 */
//region
// CREATE TABLE `dynamic_code` ( `id` bigint(20) NOT NULL AUTO_INCREMENT, `dcc_code` varchar(255) NOT NULL, `type` varchar(255) NOT NULL, `groovy_text` text NOT NULL, PRIMARY KEY (`id`), UNIQUE KEY `dynamic_code_u1` (`dcc_code`,`type`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
//endregion
@Getter
@Setter
public class DynamicCode {
    private Long id;//pk
    @NonNull
    private String dccCode;
    @NonNull
    private String type;//before|return
    @NonNull
    private String groovyText;
}
