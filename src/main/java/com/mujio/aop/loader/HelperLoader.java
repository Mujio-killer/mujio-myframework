package com.mujio.aop.loader;

import com.mujio.aop.helper.AopHelper;
import com.mujio.container.helper.BeanHelper;
import com.mujio.container.helper.ClassHelper;
import com.mujio.container.helper.ControllerHelper;
import com.mujio.container.helper.IocHelper;
import com.mujio.container.utils.ClassUtil;

public final class HelperLoader {
    public static void init() {
        Class<?>[] classList = {
            ClassHelper.class,
            BeanHelper.class,
            AopHelper.class,
            IocHelper.class,
            ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }
}
