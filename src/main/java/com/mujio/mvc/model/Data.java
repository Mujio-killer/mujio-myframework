package com.mujio.mvc.model;
//用于封装Controller方法的JSON返回结果.
public class Data {

    /**
     * 模型数据
     */
    private Object model;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}
