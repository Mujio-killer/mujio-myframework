package com.mujio.aop.helper;

import com.mujio.container.config.ConfigConstant;
import com.mujio.container.utils.PropsUtil;

import java.util.Properties;

/**
 * @Description: ConfigHelper
 * @Author: GZY
 * @Date: 2020/9/2
 */

public final class ConfigHelper {

    // 加载配置文件的属性
    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    // 获取 JDBC 驱动
    public static String getJdbcDriver() {
        return PropsUtil.getStringProp(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
    }

    /**
     * 获取 JDBC URL
     */
    public static String getJdbcUrl() {
        return PropsUtil.getStringProp(CONFIG_PROPS, ConfigConstant.JDBC_URL);
    }

    /**
     * 获取 JDBC 用户名
     */
    public static String getJdbcUsername() {
        return PropsUtil.getStringProp(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
    }

    /**
     * 获取 JDBC 密码
     */
    public static String getJdbcPassword() {
        return PropsUtil.getStringProp(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
    }

    /**
     * 获取应用基础包名
     */
    public static String getAppBasePackage() {
        return PropsUtil.getStringProp(CONFIG_PROPS, ConfigConstant.APP_BASE_PACKAGE);
    }

    /**
     * 获取应用 JSP 路径
     */
    public static String getAppJspPath() {
        return PropsUtil.getStringProp(CONFIG_PROPS, ConfigConstant.APP_JSP_PATH, "/WEB-INF/view/");
    }

    /**
     * 获取应用静态资源路径
     */
    public static String getAppAssetPath() {
        return PropsUtil.getStringProp(CONFIG_PROPS, ConfigConstant.APP_ASSET_PATH, "/asset/");
    }

    /**
     * 根据属性名获取 String 类型的属性值
     */
    public static String getStringProp(String key) {
        return PropsUtil.getStringProp(CONFIG_PROPS, key);
    }

    /**
     * 根据属性名获取 int 类型的属性值
     */
    public static int getIntProp(String key) {
        return PropsUtil.getIntProp(CONFIG_PROPS, key);
    }

    /**
     * 根据属性名获取 boolean 类型的属性值
     */
    public static boolean getBooleanProp(String key) {
        return PropsUtil.getBooleanProp(CONFIG_PROPS, key);
    }
}
