package com.ego.model.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.OSSObject;
import com.ego.model.common.config.CommonOSSProperties;
import com.ego.model.common.config.OssProperties;
import com.ego.model.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.*;

/**
 * OSS文件上传
 */
@Service
public class OssServiceImpl implements OssService {

    @Autowired
    private CommonOSSProperties commonProperties;

    @Override
    public String upload(File file, String key, CannedAccessControlList cannedAccessControlList) throws IOException {
        return upload(new FileInputStream(file), key, cannedAccessControlList);
    }

    @Override
    public String upload(InputStream inputStream, String key, CannedAccessControlList cannedAccessControlList) {
        OssProperties ossProperties = commonProperties.getOss();
        OSSClient ossClient = getOssClient();
        String url;
        try {
            ossClient.putObject(ossProperties.getBucketName(), key, inputStream);
            url = ossProperties.getHost() + key;
        } finally {
            ossClient.shutdown();
        }
        return url;
    }

    @Override
    public void download(String key, OutputStream outputStream) {
        OssProperties ossProperties = commonProperties.getOss();
        OSSClient ossClient = getOssClient();
        try {
            OSSObject ossObject = ossClient.getObject(ossProperties.getBucketName(), key);
            StreamUtils.copy(ossObject.getObjectContent(), outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
    }

    private OSSClient getOssClient() {
        OssProperties ossProperties = commonProperties.getOss();
        return new OSSClient(ossProperties.getEndpoint(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
    }

}
