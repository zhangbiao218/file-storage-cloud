package com.tiansuo.file.storage.core.util;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.tiansuo.file.storage.core.constant.CommonConstant;

/**
 * 对象存储工具类
 * @author zhangb
 */
public class CommonUtil {

    /**
     * 取得对象名称
     * @param fileMd5 文件fileMd5
     * @return 对象名称
     */
    public static String getObjectName(String fileMd5){
        return CommonUtil.getPathByDate() + CommonConstant.STRING_FXG + fileMd5;
    }

    /**
     * 根据当前时间取得路径
     * @return 路径
     */
    public static String getPathByDate(){
        return LocalDateTimeUtil.format(LocalDateTimeUtil.now(), CommonConstant.STRING_DATE_FORMAT1);
    }

}
