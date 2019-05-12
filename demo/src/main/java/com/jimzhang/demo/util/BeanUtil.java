package com.jimzhang.demo.util;

import com.jimzhang.demo.test.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.cglib.beans.BeanMap;

/**
 * @author jimzhang
 * @version V1.0.0
 * @description 利用BeanMap进行对象与Map的相互转换
 * cglib 使用了缓存，初次创建bean时需要初始化，之后就使用缓存，所以速度极快
 * @home <>https://segmentfault.com/u/itzhangjm</>
 * @date 2017-11-16 17:00
 */
public class BeanUtil {
    /**
     * 将对象装换为map
     *
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将map装换为javabean对象
     *
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    /**
     * 将List<T>转换为List<Map<String, Object>>
     *
     * @param objList
     * @return
     */
    public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (objList != null && objList.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0, size = objList.size(); i < size; i++) {
                bean = objList.get(i);
                map = beanToMap(bean);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 将List<Map<String,Object>>转换为List<T>
     *
     * @param maps
     * @param clazz
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        List<T> list = new ArrayList<>();
        if (maps != null && maps.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0, size = maps.size(); i < size; i++) {
                map = maps.get(i);
                bean = clazz.newInstance();
                mapToBean(map, bean);
                list.add(bean);
            }
        }
        return list;
    }

    public static void main(String[] args) {

        User user = new User("zhangjim", "海淀区", 27, "北大");
        for (int i = 0; i < 1000000; i++) {
            Map<String, Object> stringObjectMap = beanToMap(user);
            System.out.println(stringObjectMap.toString());
        }

        int x=10,y=3;
        System.out.printf("%d,%d\n",x--,--y);

        int a[][] = {{1,3,5},{2,4,6},{3,5,7}};
        System.out.printf("%d%d%d%d\n",a[0][2],a[1][2],a[2][1],a[2][0]);

    }
}