package com.denghanbo.resume.provider;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


//图片或者文件上传的工具类
@Component
public class FileUpload {

    //如果返回null表明上传失败，成功会返回路径
    public String uploadImage(MultipartFile multipartFile ){
        if (multipartFile == null || multipartFile.isEmpty())
            return null;

        //生成新的文件名及存储位置
        String fileName = multipartFile.getOriginalFilename();
        String newFileName = UUID.randomUUID().toString()
                .replaceAll("-", "")
                .concat(fileName.substring(fileName.lastIndexOf(".")));


        String fullPath = System.getProperty("user.dir")+"/src/main/resources/static/upload/".concat(newFileName);

        System.out.println("上传路径："+fullPath);

        try {
            File target = new File(fullPath);
            if (!target.getParentFile().exists()) { //判断文件父目录是否存在
                target.getParentFile().mkdirs();
            }

            multipartFile.transferTo(target);


            String imgUrl = "/upload/".concat(newFileName);


            return imgUrl;

        } catch (IOException ex) {
            return null;
        }
    }

}
