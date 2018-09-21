package com.framework.yjk.fileparser.model;

import com.framework.yjk.fileparser.condition.Condition;
import com.framework.yjk.fileparser.model.enums.FieldSplitTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/20 9:23
 * description：文件行模板
 **/
@Data
public class FileRowTemplate implements Serializable {

    /**
     * serial id
     */
    private static final long serialVersionUID = 6876704669495378519L;

    /**
     * 文件字段模板信息列表
     */
    private List<FileFieldTemplateInfo> fileFieldTemplateInfos;

    /**
     * 使用这个行模板的条件
     */
    private List<Condition> conditions;

    /**
     * 字段分割类型枚举
     */
    private FieldSplitTypeEnum fieldSplitTypeEnum;

    /**
     * 文件编码
     */
    private String fileEncoding;

    /**
     * 字段分割
     */
    private String columnSplit = "|";

    /**
     * 换行符
     */
    private String lineBreak = "\r\n";

    /**
     * 行模板名称
     */
    private String name;
}
