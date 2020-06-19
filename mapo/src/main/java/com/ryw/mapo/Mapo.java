package com.ryw.mapo;

import android.text.TextUtils;
import android.util.Log;

import com.ryw.mapo.anotations.Expose;
import com.ryw.mapo.anotations.SerializedName;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 键值对Map和对象相互转换
 */
public class Mapo {

    private static final String LOG_TAG = "jetpack";

    public Mapo() {
    }

    /**
     * 转换成键值对
     * 根据getXxx()方法判断
     * 但是不能判断注解
     * 所以要结合在一起
     * 先判断属性
     * 然后判断方法
     * 好像都不行
     * @param object    对象
     * @return          Map
     */
    public Map<String, String> toMap(Object object) throws IllegalAccessException {
        Map<String, String> map = new HashMap<>();
        Class clazz = object.getClass();
        Log.d(LOG_TAG, "className=" + clazz.getSimpleName());
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String key = field.getName();
            Object value = field.get(object);
            String s = String.valueOf(value);
            if (field.getAnnotations() != null) {
                if (field.isAnnotationPresent(Expose.class)) {
                    Expose expose = field.getAnnotation(Expose.class);
                    if (!expose.serialize())
                        continue;
                }
                if (field.isAnnotationPresent(SerializedName.class)) {
                    SerializedName serializedName = field.getAnnotation(SerializedName.class);
                    String v = serializedName.value();
                    if (!TextUtils.isEmpty(v)) {
                        key = v;
                    }
                }
            }
            map.put(key, s);
            Log.d(LOG_TAG, field.getName());
        }
        return map;
    }

    public String toFormUrlEncoded(Map<String, String> map) {
        if (map.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Set<String> set = map.keySet();
        for (String key : set) {
            sb.append(key).append("=").append(map.get(key)).append("&");
        }
        return sb.toString().substring(0, sb.length()-1);
    }


}
