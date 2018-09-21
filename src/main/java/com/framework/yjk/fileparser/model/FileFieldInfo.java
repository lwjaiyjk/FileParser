package com.framework.yjk.fileparser.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/21 8:22
 * description：
 **/
@Data
public class FileFieldInfo implements Serializable {
    /**
     * serial id
     */
    private static final long serialVersionUID = -3474867774406628018L;

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 字段原始值
     */
    private String fieldOriginValue;

    /**
     * 字段结果值
     */
    private Object fieldResultValue;
}
