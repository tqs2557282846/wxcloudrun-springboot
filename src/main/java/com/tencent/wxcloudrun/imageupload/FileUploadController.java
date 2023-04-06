package com.tencent.wxcloudrun.imageupload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/vue")
@CrossOrigin(origins = "*", methods = {GET, POST}, allowedHeaders = "*", maxAge = 3600)
public class FileUploadController {


    private static final String IMAGE_URL_PREFIX = "http://localhost:8011/upload/";//记得改成自己后台的地址
    private static final String IMAGE_URL_PREFIX2 = "http://localhost:8011/head/";//记得改成自己后台的地址
    private static final String IMAGE_URL_PREFIX_FACE = "http://localhost:8011/face/";//记得改成自己后台的地址


    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    private final FileUploadService fileUploadService;


    @Autowired
    public FileUploadController(FileUploadService fileUploadService) throws Exception {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("image")
    public Map<String, String> uploadImage(MultipartFile file, HttpServletRequest request) {
        /*
         这个路径 path 就是工程下 /src/main/webapp/upload，这个路径是发布后才会存在.
         需要在前段传来的request里添加字段，名字随便，只要是个路径他就会自动识别,前端不加这个字段，点击提交后端日志就回提醒你差那个
         字段了。如果不做任何改变，前端也不加这个字段，那它就会默认存在c盘下。
         */

        String path = request.getSession().getServletContext().getRealPath("upload");//存储路径，getrealpath会自动找到webapp文件夹
        logger.info("文件存储路径：{}", path);
        String imageUrl = fileUploadService.upload(file, path);

        //  TinyMCE 要求图片上传后，需要返回一个 json 对象，这个对象必须有 location 属性，此处硬编码
        Map<String, String> map = new HashMap<>();
        map.put("location", IMAGE_URL_PREFIX + imageUrl);
        return map;
    }

    @PostMapping("/upload-content")
    public String uploadContent(@RequestBody String content) {
        logger.info("method: uploadContent，上传内容：{}", content);
        // TODO 直接将这部分内容存储到数据库即可，此时 content里的图片已经不是 base64
        return content;
    }

    @RequestMapping("/imagehead")
    public Map<String, String> uploadhead(MultipartFile file, HttpServletRequest request) {
        /*
         这个路径 path 就是工程下 /src/main/webapp/upload，这个路径是发布后才会存在.
         需要在前段传来的request里添加字段，名字随便，只要是个路径他就会自动识别,前端不加这个字段，点击提交后端日志就回提醒你差那个
         字段了。如果不做任何改变，前端也不加这个字段，那它就会默认存在c盘下。
         */

        String path = request.getSession().getServletContext().getRealPath("head");
        logger.info("文件存储路径：{}", path);
        String imageUrl = fileUploadService.upload(file, path);

        //  TinyMCE 要求图片上传后，需要返回一个 json 对象，这个对象必须有 location 属性，此处硬编码
        Map<String, String> map = new HashMap<>();
        map.put("local", IMAGE_URL_PREFIX2 + imageUrl);
        return map;
    }
    @RequestMapping("/imageuphead")
    public Map<String, String> updatehead(MultipartFile file, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("head");
        logger.info("文件存储路径：{}", path);
        String imageUrl = fileUploadService.upload(file, path);

        //  TinyMCE 要求图片上传后，需要返回一个 json 对象，这个对象必须有 location 属性，此处硬编码
        Map<String, String> map = new HashMap<>();
        map.put("location", IMAGE_URL_PREFIX2 + imageUrl);
        return map;
    }
    @RequestMapping("/imageup2head")
    public location updateheadw(MultipartFile file, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("head");//getRealPath方法意味找到webAPP包，head则是指在webapp下的head文件夹
        logger.info("文件存储路径：{}", path);
        String imageUrl = fileUploadService.upload(file, path);
        location location=new location();
        location.setLocation(IMAGE_URL_PREFIX2 + imageUrl);
        return location;
    }
    @RequestMapping("/faceupload")
    public location faceupload(MultipartFile file, HttpServletRequest request) throws Exception {
        String path = request.getSession().getServletContext().getRealPath("face");//getRealPath方法意味找到webAPP包，head则是指在webapp下的face文件夹
        logger.info("文件存储路径：{}", path);
        String imageUrl = fileUploadService.upload(file, path);
        location location=new location();
        location.setLocation(IMAGE_URL_PREFIX_FACE + imageUrl);
        return location;
    }
}
