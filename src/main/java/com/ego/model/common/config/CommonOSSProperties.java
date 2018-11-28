package com.ego.model.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ego
 * @since 2018-10-27 16:07
 */
@Data
@ConfigurationProperties(prefix = "common")
@Component
public class CommonOSSProperties {

    private OssProperties oss;
}
