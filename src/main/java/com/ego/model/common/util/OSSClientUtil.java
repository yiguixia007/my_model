package com.ego.model.common.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.ego.model.common.config.CommonOSSProperties;
import com.ego.model.common.config.OssProperties;
import com.ego.model.common.exception.UploadFileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.Random;

/**
 * 阿里云 OSS文件类
 *
 * @author wg
 */

@Component
@Slf4j
public class OSSClientUtil {

  @Autowired
  private CommonOSSProperties commonProperties;

  /**
   * 上传图片
   *
   * @param url
   */
  public void uploadImage2Oss(String url) throws UploadFileException {
    File fileOnServer = new File(url);
    FileInputStream fin;
    try {
      fin = new FileInputStream(fileOnServer);
      String[] split = url.split("/");
      this.uploadFile2OSS(fin, split[split.length - 1]);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw new UploadFileException("文件上传失败");
    }
  }


  public String uploadImage2Oss(MultipartFile file) throws UploadFileException {
      if (file.getSize() > 1024 * 1024 * 20 ) {
          throw new UploadFileException("上传的图片大小不能超过20M！");
      }

      String originalFilename = file.getOriginalFilename();
      String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
      Random random = new Random();
      String name = random.nextInt(10000) + System.currentTimeMillis() + substring;

      try {
          InputStream inputStream = file.getInputStream();
          this.uploadFile2OSS(inputStream, name);
          return name;
      } catch (Exception e) {
          e.printStackTrace();
          throw new UploadFileException("图片上传失败");
      }
  }

  public String uploadFile2Oss(MultipartFile file) throws UploadFileException {
      String originalFilename = file.getOriginalFilename();

      String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
      Random random = new Random();
      String name = random.nextInt(10000) + System.currentTimeMillis() + substring;
      log.info("upload2Oss{}", name);
      try {
          InputStream inputStream = file.getInputStream();
          this.uploadFile2OSS(inputStream, name);
          return name;
      } catch (Exception e) {
          throw new UploadFileException("文件上传失败");
      }
  }


  /**
   * 获得文件路径
   *
   * @param fileUrl 文件地址
   * @return 文件路径
   */
  public String getFileUrl(String fileUrl) {
      if (!StringUtils.isEmpty(fileUrl)) {
          String[] split = fileUrl.split("/");
          return this.getUrl(  split[split.length - 1]);
      }
      return null;
  }

  /**
   * 上传到OSS服务器  如果同名文件会覆盖服务器上的
   *
   * @param inStream 文件流
   * @param fileName 文件名称 包括后缀名
   * @return 出错返回"" ,唯一MD5数字签名
   */
  public String uploadFile2OSS(InputStream inStream, String fileName) {
      OSSClient ossClient = getOssClient();
      String ret = "";
      try {
          // 创建上传Object的Metadata
          ObjectMetadata objectMetadata = new ObjectMetadata();
          objectMetadata.setContentLength(inStream.available());
          objectMetadata.setCacheControl("no-cache");
          objectMetadata.setHeader("Pragma", "no-cache");
          objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
          objectMetadata.setContentDisposition("inline;filename=" + fileName);

          // 上传文件
          PutObjectResult putResult = ossClient.putObject(commonProperties.getOss().getBucketName(),  fileName, inStream, objectMetadata);
          ret = putResult.getETag();
      } catch (IOException e) {
          log.error(e.getMessage(), e);
      } finally {
          try {
              if (inStream != null) {
                inStream.close();
              }
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
      return ret;
  }

  /**
   * Description: 判断OSS服务文件上传时文件的contentType
   *
   * @param FilenameExtension 文件后缀
   * @return String
   */
  public static String getcontentType(String FilenameExtension) {
      if (FilenameExtension.equalsIgnoreCase(".bmp")) {
          return "image/bmp";
      }
      if (FilenameExtension.equalsIgnoreCase(".gif")) {
          return "image/gif";
      }
      if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
              FilenameExtension.equalsIgnoreCase(".jpg") ||
              FilenameExtension.equalsIgnoreCase(".png")) {
          return "image/jpeg";
      }
      if (FilenameExtension.equalsIgnoreCase(".html")) {
          return "text/html";
      }
      if (FilenameExtension.equalsIgnoreCase(".txt")) {
          return "text/plain";
      }
      if (FilenameExtension.equalsIgnoreCase(".vsd")) {
          return "application/vnd.visio";
      }
      if (FilenameExtension.equalsIgnoreCase(".pptx") ||
              FilenameExtension.equalsIgnoreCase(".ppt")) {
          return "application/vnd.ms-powerpoint";
      }
      if (FilenameExtension.equalsIgnoreCase(".docx") ||
              FilenameExtension.equalsIgnoreCase(".doc")) {
          return "application/msword";
      }
      if(FilenameExtension.equalsIgnoreCase(".xlsx")  ||
              FilenameExtension.equalsIgnoreCase(".xls")){
          return "application/vnd.ms-excel";
      }
      if (FilenameExtension.equalsIgnoreCase(".xml")) {
          return "text/xml";
      }
      return "image/jpeg";
  }

  /**
   * 获得url链接
   *
   * @param key
   * @return
   */
  public String getUrl(String key) {
      OSSClient ossClient = getOssClient();
      // 设置URL过期时间为10年  3600l* 1000*24*365*10
      Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
      // 生成URL
      URL url = ossClient.generatePresignedUrl(commonProperties.getOss().getBucketName(), key, expiration);
      if (url != null) {
          return url.toString();
      }
      return null;
  }

  private OSSClient getOssClient() {
      OssProperties ossProperties = commonProperties.getOss();
      return new OSSClient(ossProperties.getEndpoint(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
  }
}
