package com.mujio.container.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Description: 用于读取配置文件
 * @Author: GZY
 * @Date: 2020/8/30
 */

public final class PropsUtil {
    private static final Logger log = LoggerFactory.getLogger(PropsUtil.class);

    //加载属性
    public static Properties loadProps(String fileName) {
        Properties properties = null;
        InputStream is = null;

        try {
            is = ClassUtil.getClassLoader().getResourceAsStream(fileName);
            if (is == null) {
                throw new FileNotFoundException(fileName + " 未找到");
            }
            properties = new Properties();
            properties.load(is);
        } catch (IOException e) {
            log.error("加载配置文件失败", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error("关闭输入流失败", e);
                }
            }
        }
        return properties;
    }

    // 获取属性值(默认为空)
    public static String getStringProp(Properties props, String key) {
        return getStringProp(props, key, "");
    }

    // 获取String类型配置值
    public static String getStringProp(Properties props, String key, String defaultValue) {
        if (props.containsKey(key)) {
            return props.getProperty(key);
        }
        return defaultValue;
    }

    // 获取int类型配置值(默认为0)
    public static int getIntProp(Properties props, String key) {
        return getIntProp(props, key, 0);
    }

    public static int getIntProp(Properties props, String key, int defaultValue) {
        if (props.containsKey(key)) {
            return Integer.parseInt(props.getProperty(key));
        }
        return defaultValue;
    }

    // 获取布尔类型配置值(默认为false)
    public static boolean getBooleanProp(Properties props, String key) {
        return getBooleanProp(props, key, false);
    }

    public static boolean getBooleanProp(Properties props, String key, boolean defaultValue) {
        if (props.containsKey(key)) {
            return Boolean.parseBoolean(props.getProperty(key));
        }
        return defaultValue;
    }
}
