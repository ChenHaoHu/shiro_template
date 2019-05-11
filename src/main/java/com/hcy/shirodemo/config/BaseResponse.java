package com.hcy.shirodemo.config;

import lombok.Data;

/**
 * API接口的基础返回类
 *
 * @author XiongNeng
 * @version 1.0
 * @since 2018/1/7
 */


@Data
public class BaseResponse {
    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 说明
     */
    private String msg;

    /**
     * 返回数据
     */
    private Object data;

    public BaseResponse() {

    }

    public BaseResponse(boolean success, String msg, Object data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }
}