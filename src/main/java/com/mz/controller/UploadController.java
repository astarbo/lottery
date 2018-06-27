package com.mz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author mz
 * @Description：
 * @date 2018/6/27
 * @time 20:23
 */
@Controller
public class UploadController {
    private static String DIR="F:/2_server/java_web_pro/SpringBootStudy/lottery/src/main/resources/static/img";

    @PostMapping("/upload")
    public String upload(MultipartFile file) {

        try {
            //获取的是 <input type="file" name="image" /> name属性对应的值
            String name = file.getName();
            //获取上传的文件名
            String filename = file.getOriginalFilename();

            //判断目标文件夹是否已经
            File dir = new File(DIR);
            if (!dir.exists()){
                dir.mkdirs();
            }
            //指定文件保存的位置
            File destFile = new File(DIR,filename);
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/successUI";
    }

    @GetMapping("/successUI")
    public String success() {
        return "success";
    }
}
