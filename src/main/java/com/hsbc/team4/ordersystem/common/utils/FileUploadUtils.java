package com.hsbc.team4.ordersystem.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.common.utils
 * @Description :
 * @Date : 2018/8/17
 */
public class FileUploadUtils {


    /**
     *  getOriginalFileName
     * @param file
     * @return
     */
    public static String getOriginalFileName(MultipartFile file) {
        return file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
    }

    /**
     *  getSuffix
     * @param file
     * @return
     */
    public static String getSuffix(MultipartFile file) {
        return file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
    }

    /**
     *  getSuffix
     * @param file
     * @return
     */
    public static String getSuffix(File file) {
        return file.getName().substring(file.getName().lastIndexOf("."));
    }

    /**
     * validateImage
     * @param filePath
     * @return
     */
    public static boolean validateImage(String filePath){
        return filePath.matches(".+(.JPEG|.jpeg|.JPG|.jpg)$");
    }


}
