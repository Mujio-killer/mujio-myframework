package com.mujio.container.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @Description: ClassUtil
 * @Author: GZY
 * @Date: 2020/8/30
 */

public final class ClassUtil {
    private static final Logger log = LoggerFactory.getLogger(ClassUtil.class);

    // 获取类加载器
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
    * @Description: 根据类名、是否需要初始化类 加载类
    * @Param: [className : 类名, isInitinalized : 是否已经初始化]
    * @return: java.lang.Class<?>
    */
    public static Class<?> loadClass(String className, boolean isInitinalized) {
        Class<?> clazz;
        try {
            clazz = Class.forName(className, isInitinalized, getClassLoader());
        } catch (ClassNotFoundException e) {
            log.error("类加载失败", e);
            throw new RuntimeException(e);
        }

        return clazz;
    }

    // 默认初始化类，根据类名直接加载
    public static Class<?> loadClass(String className) {
        return loadClass(className, true);
    }

    // 获取指定包名下所有类
    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<>();

        try {
            // Enumeration接口中定义了两个方法，一个用于返回枚举类的一个元素，一个用于返回是否还有其他元素
            // 实际代码中已被迭代器取代，目前通常用于Vector、Properties等传统类的定义中。
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));

            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();

                    if ("file".equals(protocol)) { // 获取普通类文件
                        // 格式化包路径
                        String packagePath = url.getPath().replaceAll("%20", " ");
                        // 根据包路径、报名放到不同set中
                        addClass(classSet, packagePath, packageName);
                    } else if ("jar".equals(protocol)) { // 获取jar中的类文件
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if (jarURLConnection != null) {
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (jarFile != null) {
                                Enumeration<JarEntry> jarEntries = jarFile.entries();
                                while (jarEntries.hasMoreElements()) {
                                    JarEntry jarEntry = jarEntries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if (jarEntryName.endsWith(".class")) {
                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                        doAddClass(classSet, className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("获取类集合 set 失败", e);
            throw new RuntimeException(e);
        }
        return classSet;
    }

    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        File[] files = new File(packagePath)
                .listFiles(file -> (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory());
        for (File file : files) {
            String fileName = file.getName();
            if (file.isFile()) {
                // 获取全类名
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if (StringUtils.isNotEmpty(packageName)) {
                    className = packageName + "." + className;
                }
                  doAddClass(classSet, className);
            } else { // 递归获取下级目录中的类名
                String subPackagePath = fileName;
                if (StringUtils.isNotEmpty(packagePath)) {
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = fileName;
                if (StringUtils.isNotEmpty(packageName)) {
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }

    private static void doAddClass(Set<Class<?>> classSet, String className) {
        Class<?> cls = loadClass(className, false);
        classSet.add(cls);
    }
}
