package com.tencent.wxcloudrun.imageupload;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    /**
     * 文件上传处理
     * @param file 上传的文件
     * @param path 存储文件的路径
     * @return 返回文件的相对或者绝对路径
     */
    String upload(MultipartFile file, String path);
}
