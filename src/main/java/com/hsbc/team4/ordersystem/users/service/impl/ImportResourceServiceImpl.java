package com.hsbc.team4.ordersystem.users.service.impl;

import com.hsbc.team4.ordersystem.common.utils.FileUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.users.service.impl
 * @Description :
 * @Date : 2018/8/17
 */
@Service
@Slf4j
public class ImportResourceServiceImpl {

    @Value("${upload}")
    private String loadPath;

    /**
     * upload File
     * @param file
     * @param savePath
     * @return
     */
    public Map<String,String> uploadFile(MultipartFile file, String savePath) {
        Map<String,String> map=new HashMap<>();
        File dest;
        //reset fileName
        String reName = System.currentTimeMillis()+"-"+file.getOriginalFilename();
        if (!FileUploadUtils.validateImage(file.getOriginalFilename())) {
            map.put("msg","the file  format  is not correct,please checking it ");
            return map;
        } else {
            if (!file.isEmpty()) {
                dest = new File(loadPath+savePath + reName);
                log.info("The file path"+loadPath+savePath + file.getOriginalFilename());
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                try {
                    //save file
                    file.transferTo(dest);
                } catch (IllegalStateException | IOException e) {
                    log.error("the errors ：",e);
                }
            }
            map.put("path",savePath +reName);
            return map;
        }
    }


}
