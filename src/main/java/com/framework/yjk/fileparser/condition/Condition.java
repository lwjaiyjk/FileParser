package com.framework.yjk.fileparser.condition;

import com.framework.yjk.fileparser.model.ConditionContext;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/21 8:32
 * description：条件
 **/
public interface Condition {

    /**
     * 条件名称
     *
     * @return
     */
    String getName();

    /**
     * 执行
     *
     * @param context
     * @return
     */
    boolean eval(ConditionContext context);
}
