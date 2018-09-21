package com.framework.yjk.fileparser.processor;

import com.framework.yjk.fileparser.model.FileFieldInfo;
import com.framework.yjk.fileparser.model.FileFieldTemplateInfo;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/21 8:20
 * description：处理器
 **/
public interface FieldProcessor {

    /**
     * 处理
     *
     * @param fileFieldInfo
     * @param fileFieldTemplateInfo
     */
    void process(FileFieldInfo fileFieldInfo, FileFieldTemplateInfo fileFieldTemplateInfo);
}
