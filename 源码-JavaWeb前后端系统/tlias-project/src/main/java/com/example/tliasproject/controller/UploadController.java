package com.example.tliasproject.controller;

import com.example.tliasproject.pojo.Result;
import com.example.tliasproject.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    //    本地存储
//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image) throws Exception {
//        log.info("文件上传:{},{},{}", username, age, image);
//        String originalFilename = image.getOriginalFilename();
//        int index = originalFilename.lastIndexOf(".");
//        String extraName = originalFilename.substring(index);
//        String fileName = UUID.randomUUID().toString() + extraName;
//
//        image.transferTo(new File("C:\\Users\\ran\\Desktop\\myStudy\\imgs\\" + fileName));
//
//        return Result.success();
//    }
    @Autowired
    AliOSSUtils aliOSSUtils;

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("上传图片文件名:{}", image.getOriginalFilename());
        String url = aliOSSUtils.upload(image);
        log.info("上传完毕,url为:{}", url);
        return Result.success(url);
    }


}
