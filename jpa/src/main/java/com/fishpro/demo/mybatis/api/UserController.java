package com.fishpro.demo.mybatis.api;

import com.fishpro.demo.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : UserController
 * @Author : Administrator
 * @Date: 2021/1/9 18:12
 * @Description : UserController
 */
@RestController
@RequestMapping(value = "/user")
@ResponseBody
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/uploadExcl")
    public Map<String ,Object> uploadExcl(
            HttpServletRequest request,
            @RequestParam("file") MultipartFile file) {
        Map<String, Object> result = new HashMap<>();
        try {
            // 如果文件不为空，写入上传路径
            if (!file.isEmpty()) {
                result = userService.uploadExcl(file);
            } else {
                result.put("code", "1");
                result.put("message", "上传文件为空！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/downloadExcl")
    public Map<String ,Object> downloadLocal(HttpServletResponse response){

        Map<String, Object> result = new HashMap<>();
        /** 获取静态文件的路径 .*/
        try {
            String path = ResourceUtils.getURL("classpath:").getPath() + "templates/CRM_导入模板.xlsx";
            /** 获取文件的名称 . */
            String fileName = path.substring(path.lastIndexOf("/") + 1);
            ClassPathResource classPathResource = new ClassPathResource("test_model.xlsx");
            if (!classPathResource.exists()) {
                result.put("code", "1");
                result.put("message", "路径有误，文件不存在！");
                return result;
            }

            /** 将文件名称进行编码 */
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(classPathResource.getFilename(), "UTF-8"));
            response.setContentType("content-type:octet-stream");

            BufferedInputStream inputStream = new BufferedInputStream(classPathResource.getInputStream());

            OutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            /** 将流中内容写出去 .*/
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            inputStream.close();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
