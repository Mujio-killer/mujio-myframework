package com.mujio.container.config;

/**
* @Description: 配置文件类，用于加载配置文件
* @Author: GZY
* @Date: 2020/8/30
*/
public interface ConfigConstant {
    // 默认配置文件名称
    String CONFIG_FILE = "mujio.properties";

    // 默认数据源
    String JDBC_DRIVER = "muijo.framework.jdbc.driver";
    String JDBC_URL = "mujio.framework.jdbc.url";
    String JDBC_USERNAME = "mujio.framework.jdbc.username";
    String JDBC_PASSWORD = "mujio.framework.jdbc.password";

    // java源码目录
    String APP_BASE_PACKAGE = "mujio.framework.app.base_package";
    // jsp页面路径
    String APP_JSP_PATH = "mujio.framework.app.jsp_path";
    // 静态资源路径
    String APP_ASSET_PATH = "mujio.framework.app.asset_path";

}
