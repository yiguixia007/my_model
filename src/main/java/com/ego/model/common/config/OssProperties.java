package com.ego.model.common.config;

import lombok.Data;


@Data
public class OssProperties {
    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String host;

    private String bucketName;
}
