package com.tencent.wxcloudrun.imageupload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileUploadServiceImp implements FileUploadService {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadService.class);

    @Override
    public String upload(MultipartFile file, String path) {
        String filename = file.getOriginalFilename();
        // 获取文件扩展名，如 abc.de.jpg，就获取 jpg
        String extensionName = filename.substring(filename.lastIndexOf(".") + 1);

        // 设置上传文件的文件名，防止命名冲突导致覆盖
        String uploadFilename = UUID.randomUUID().toString() + "." + extensionName;

        logger.info("开始上传文件，\t文件原名：{}，上传路径：{}，新文件名：{}",
                filename, path, uploadFilename);

        File uploadDir = new File(path);
        // 检查上传路径是否存在，不存在则创建
        if (!uploadDir.exists()) {
            logger.info("上传路径不存在，创建路径");
            // 设置可读权限，因为启用 tomcat 的用户可能没有写文件的权限
            uploadDir.setWritable(true);
            boolean createDirResult = uploadDir.mkdirs();
            logger.info("文件的读权限：{}，文件的写权限：{}，创建结果：{}",
                    uploadDir.canRead(), uploadDir.canWrite(), createDirResult);
        }

        File targetFile = new File(uploadDir, uploadFilename);

        try {
            file.transferTo(targetFile);
            // TODO 将 targetFile 上传到文件服务器上，上传完成后，删除 uploadDir 下的文件
            // targetFile.delete();
        } catch (IOException e) {
            logger.error("文件上传异常：", e);
            return null;
        }
        logger.info("文件路径：{}，文件名：{}", targetFile.getAbsolutePath(), targetFile.getName());
        return targetFile.getName();
    }

}
