package com.ego.model.common.config;

import lombok.Data;

/**
 * @author ego
 * @since 2018-10-27 16:07
 */
@Data
public class OssProperties {
    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String host;

    private String bucketName;
}
