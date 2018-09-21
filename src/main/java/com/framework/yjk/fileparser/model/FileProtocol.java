package com.framework.yjk.fileparser.model;

import com.framework.yjk.fileparser.model.enums.FieldSplitTypeEnum;

import java.io.Serializable;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/20 9:12
 * description：文件协议
 **/
public class FileProtocol implements Serializable {

    /**
     * serial id
     */
    private static final long serialVersionUID = -2480893502922081070L;

    /**
     * 协议名称
     */
    private String name;

    /**
     * 字段分割类型枚举
     */
    private FieldSplitTypeEnum fieldSplitTypeEnum;
}
