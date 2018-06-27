package com.mz.utils;

import java.util.Random;

/**
 * @author mz
 * @Description：解决文件重名问题
 * @date 2018/6/27
 * @time 20:35
 */
public class UploadUtils {
    /**
     * 获取文件目录,可以获取256个随机目录
     * @return 随机目录  /A/0
     */
    public static String getDir() {
        String s = "0123456789ABCDEF";
        Random r = new Random();
        return "/" + s.charAt(r.nextInt(16)) + "/" + s.charAt(r.nextInt(16));
    }


}
