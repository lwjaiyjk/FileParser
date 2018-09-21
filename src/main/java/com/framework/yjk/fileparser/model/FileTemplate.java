package com.framework.yjk.fileparser.model;

import com.framework.yjk.fileparser.model.enums.FieldSplitTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/20 9:23
 * description：文件模板
 **/
@Data
public class FileTemplate implements Serializable {
    /**
     * 文件模板
     */
    private static final long serialVersionUID = 87783364366263377L;

    /**
     * 文件头模板
     */
    private FileRowTemplate fileHeadRowTemplate;

    /**
     * 文件尾模板
     */
    private FileRowTemplate fileTailRowTemplate;

    /**
     * 文件内容模板
     */
    private List<FileRowTemplate> fileBodyRowTemplates;

    /**
     * 字段分割类型枚举
     */
    private FieldSplitTypeEnum fieldSplitTypeEnum;
}
