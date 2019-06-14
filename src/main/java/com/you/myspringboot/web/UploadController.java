package com.you.myspringboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
public class UploadController {

    @GetMapping("/upload")
    public String upload() {

        return "/upload.html";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(HttpServletRequest request, @RequestParam("file") MultipartFile file){
        System.out.println(request.getServletContext().getRealPath(""));
        System.out.println(File.separator);
        System.out.println(file.getOriginalFilename());
        try {
        // 根据时间戳创建新的文件名，这样即便是第二次上传相同名称的文件，也不会把第一次的文件覆盖了
        String fileName = System.currentTimeMillis()+file.getOriginalFilename();

        //通过req.getServletContext().getRealPath("") 获取当前项目的真实路径，然后拼接前面的文件名
        String destFileName=request.getServletContext().getRealPath("")+"upload"+fileName;

        //第一次运行的时候，这个文件所在的目录往往是不存在的，这里需要创建一下目录
        File destFile = new File(destFileName);
        destFile.getParentFile().mkdirs();

        //把浏览器上传的文件复制到希望的位置
         file.transferTo(destFile);

        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }
}
