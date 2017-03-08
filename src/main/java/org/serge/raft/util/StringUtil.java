package org.serge.raft.util;



/**
 * 字符串工具集
 */
public class StringUtil {

    public static boolean isEmpty(String str){
        if(str!=null) {
            str = str.trim();
        }
        return str==null || str.equals("");
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

    public static String[] splitString(String body, String reg) {
        return body.split(reg);
    }
}
