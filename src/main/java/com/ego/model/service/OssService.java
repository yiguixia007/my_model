package com.ego.model.service;

import com.aliyun.oss.model.CannedAccessControlList;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * OSS文件上传
 * @author ego
 * @since 2018-10-27 16:07
 */
public interface OssService {

    /**
     * @param file                    上传文件
     * @param key                     上传文件地址+文件名
     * @param cannedAccessControlList
     * @return
     * @throws IOException
     */
    String upload(File file, String key, CannedAccessControlList cannedAccessControlList) throws IOException;

    /**
     * @param inputStream             文件流
     * @param key                     上传文件地址+文件名
     * @param cannedAccessControlList
     * @return
     * @throws IOException
     */
    String upload(InputStream inputStream, String key, CannedAccessControlList cannedAccessControlList) throws IOException;



    void download(String key, OutputStream outputStream);
}
