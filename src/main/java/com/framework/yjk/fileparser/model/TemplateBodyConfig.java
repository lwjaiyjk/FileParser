package com.framework.yjk.fileparser.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/21 9:38
 * description：模板体配置
 **/
@Data
public class TemplateBodyConfig implements Serializable {

    /**
     * serial id
     */
    private static final long serialVersionUID = 3165919492907539132L;

    /**
     * 条件列表
     */
    private List<String> condConfigs;

    /**
     * body 配置
     */
    private List<String> bodyConfigs;
}
