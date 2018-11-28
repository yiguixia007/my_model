package com.ego.model.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@ConfigurationProperties(prefix = "common")
@Component
public class CommonOSSProperties {
    private OssProperties oss;
}
