package com.ego.model.web.controller;

import com.ego.model.common.mo.DownloadKeyMO;
import com.ego.model.common.mo.ResponseMO;
import com.ego.model.common.util.OSSClientUtil;
import com.ego.model.common.util.ResponseUtil;
import com.ego.model.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wg
 * @since  2018-06-12 下午1:40
 */
@Api(tags = "文件上传")
@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private OSSClientUtil ossClient;

    @Autowired
    private OssService ossService;

    @ApiOperation("上传文件")
    @PostMapping("/uploadFile")
    @ResponseBody
    public ResponseMO uploadFile(MultipartFile file) {
        if (file == null || file.getSize() <= 0) {
            return ResponseUtil.error("文件不能为空!");
        }

        String imageKey;
        try {
            imageKey = ossClient.uploadImage2Oss(file);

        } catch (Exception e) {
            log.info("文件上传失败:");
            e.getStackTrace();
            return ResponseUtil.error("文件上传失败!");
        }
        return ResponseUtil.successWithData(imageKey);
    }

    @ApiOperation("上传图片")
    @PostMapping("/uploadImage")
    @ResponseBody
    public ResponseMO uploadImage(MultipartFile file) {
        if (file == null || file.getSize() <= 0) {
            return ResponseUtil.error("图片不能为空!");
        }

        String fileKey;
        try {
            fileKey = ossClient.uploadFile2Oss(file);
        } catch (Exception e) {
            log.info("图片上传失败:");
            e.getStackTrace();
            return ResponseUtil.error("图片上传失败!");
        }
        return ResponseUtil.successWithData(fileKey);
    }

    @ApiOperation("下载文件")
    @PostMapping("/download")
    public void download(@RequestBody DownloadKeyMO downloadKeyMO, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/x-download");
        response.addHeader("Content-Disposition",
                "attachment;fileName=" + downloadKeyMO.getKey());
        ossService.download(downloadKeyMO.getKey(), outputStream);

        outputStream.flush();
    }
}
