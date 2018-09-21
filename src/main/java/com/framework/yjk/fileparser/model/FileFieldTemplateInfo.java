package com.framework.yjk.fileparser.model;

import com.framework.yjk.fileparser.processor.FieldProcessor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/20 9:24
 * description：文件字段信息
 **/
@Data
public class FileFieldTemplateInfo implements Serializable {

    /**
     * serial id
     */
    private static final long serialVersionUID = -3474867774406628018L;

    /**
     * 字段名称
     */
    private String name;

    /**
     * 描述
     */
    private String desc;

    /**
     * 字段所在行对应的索引
     */
    private int index;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 字段格式化
     */
    private String format;

    /**
     * 是否必填
     */
    private boolean required;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 字段对应的处理器
     */
    private List<FieldProcessor> fieldProcessors;
}
