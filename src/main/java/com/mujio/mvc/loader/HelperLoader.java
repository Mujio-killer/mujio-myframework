package com.mujio.mvc.loader;

import com.mujio.container.helper.BeanHelper;
import com.mujio.container.helper.ClassHelper;
import com.mujio.container.helper.ControllerHelper;
import com.mujio.container.helper.IocHelper;
import com.mujio.container.utils.ClassUtil;

//一个入口程序来加载ClassHelper, BeanHelper, IocHelper, ControllerHelper(实际上是加载静态代码块),
// 没有这个入口程序, 这些类也会被加载, 这里只是为了让加载更加集中.
public final class HelperLoader {

    public static void init() {
        Class<?>[] classList = {
            ClassHelper.class,
            BeanHelper.class,
            IocHelper.class,
            ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }
}
