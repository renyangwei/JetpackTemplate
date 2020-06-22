package com.ryw.mapo;

import android.text.TextUtils;
import android.util.Log;

import com.ryw.mapo.anotations.Expose;
import com.ryw.mapo.anotations.SerializedName;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
     * 对象转换成键值对
     * @param object    对象
     * @return          Map
     */
    public Map<String, String> toMap(Object object) {
        Map<String, String> map = new HashMap<>();
        try {
            Class clazz = object.getClass();
            Log.d(LOG_TAG, "className=" + clazz.getSimpleName());
            List<Field> fieldList = new ArrayList<>() ;
            while (clazz != null && !clazz.getName().toLowerCase().equals("java.lang.object")) {//当父类为null的时候说明到达了最上层的父类(Object类).
                fieldList.addAll(Arrays.asList(clazz .getDeclaredFields()));
                clazz = clazz.getSuperclass(); //得到父类,然后赋给自己
            }
            for (Field field : fieldList) {
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
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 转化成FormUrl
     * @param map   键值对
     * @return      字符串
     */
    public String toFormUrlString(Map<String, String> map) {
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
