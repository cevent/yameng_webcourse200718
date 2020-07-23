package com.cevent.yameng.webcourse.server.util;/**
 * Created by Cevent on 2020/7/22.
 */

import java.util.UUID;

/**
 * @author cevent
 * @description
 * @date 2020/7/22 22:22
 */
public class UUIDUtil {

    /**
     * 短ID是根据将32位ID，转为62进制8位ID，减少存储空间
     * 原理是将UUID转为十进制，再对62取余，也可以再添加2个符号，转为64进制
     */
    public static String[] chars=new String[]{
            //定义id使用的62个数字+字母
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
            "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    /**
     * 获取短UUID-short
     */
    public static String getShortUUID(){
        StringBuffer shortBuffer=new StringBuffer();
        String uuid=UUIDUtil.getUUID();
        for (int i=0;i<8;i++){
            String str=uuid.substring(i*4,i*4+4);
            int x=Integer.parseInt(str,16);
            //对62取余
            shortBuffer.append(chars[x%0x3E]);
        }
        return shortBuffer.toString();
    }

    /**
     * 获取32位UUID
     */
    public static String getUUID(){
        String uuid= UUID.randomUUID().toString();
        //取消"-"符号
        return uuid.replaceAll("-","");
    }

    //测试UUID转化
    public static void main(String[] args) {
        System.out.println(getShortUUID());
    }
}
