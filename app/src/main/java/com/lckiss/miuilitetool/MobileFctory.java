package com.lckiss.miuilitetool;

/**
 * Created by lckiss on 17-10-24.
 */
public class MobileFctory {

    public static <T> T getInstance(String mobile) {
        T instance = null;
        try {
            instance = (T) Class.forName(mobile).newInstance();
        } catch (Exception e) {
        }
        return instance;
    }
}
