package com.tomh.studentspringboot.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.tomh.studentspringboot.common.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

//File upload controller
@RestController
@RequestMapping("/files")
public class FileController {
    // File upload save direction
    private static final String filePath = System.getProperty("user.dir") + "/file/";

    // upload
    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        synchronized (FileController.class) {
            // Get current TimeMill
            String flag = System.currentTimeMillis() + "";
            // Get file name
            String fileName = file.getOriginalFilename();
            try {
                // Create file directory if theres none
                if (!FileUtil.isDirectory(filePath)) {
                    FileUtil.mkdir(filePath);
                }
                // Save rule: Time + file name
                FileUtil.writeBytes(file.getBytes(), filePath + flag + "-" + fileName);
                System.out.println(fileName + "--Uploaded file");
                Thread.sleep(1L);
            }catch (Exception e){
                System.err.println(fileName + "--Fail to upload");
            }
            return Result.success(flag);
        }
    }

    // Get file
    @GetMapping("/{flag}")
    public void avatarPath(@PathVariable String flag, HttpServletResponse response){
        if(!FileUtil.isDirectory(filePath)){
            FileUtil.mkdir(filePath);
        }
        OutputStream os;
        List<String> fileNames = FileUtil.listFileNames(filePath);
        String avatar = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");
        try {
            if(StrUtil.isNotEmpty(avatar)){
                response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(avatar,"UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(filePath + avatar);
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        }catch (Exception e){
            System.err.println("Failed to download file");
        }
    }
}


