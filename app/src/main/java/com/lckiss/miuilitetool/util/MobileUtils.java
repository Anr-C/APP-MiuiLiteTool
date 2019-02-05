package com.lckiss.miuilitetool.util;

/**
 * Created by lckiss on 17-11-2.
 */

public class MobileUtils {


    public static String getMode(String mobile){
        String mode=null;
        if ("MI-4C".equals(mobile)) {
            mode="Mi4cLite";
        } else if ("MI 5".equals(mobile)) {
            mode="Mi5Normal";
        }else if ("REDMI NOTE 2".equals(mobile)){
            mode="RedMi2Normal";
        }
        return mode;
    }

}
