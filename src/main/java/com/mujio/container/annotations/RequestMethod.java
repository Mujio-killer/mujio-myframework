package com.mujio.container.annotations;

// 枚举类是天生的单例（为防止破坏其唯一性，JVM将其设计为单例模式）
// 枚举类中定义了多少个元素，JVM加载时就会实例化多少个对象
public enum RequestMethod {
    GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE;
   /* // 枚举类只存在私有构造器
    RequestMethod() {
    }*/
}
