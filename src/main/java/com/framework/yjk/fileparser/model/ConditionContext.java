package com.framework.yjk.fileparser.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/21 8:35
 * description：条件上下文
 **/
@Data
public class ConditionContext implements Serializable{

    /**
     * serial ID
     */
    private static final long serialVersionUID = -7442406510935366054L;

    /**
     * 上下文map
     */
    private Map<String,Object> contextMap;
}
