package com.framework.yjk.fileparser.init;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/21 15:42
 * description：文件初始化器
 **/
public interface FileParserInit<T> {

    /**
     * 初始化
     *
     * @param t
     */
    void init(T t);
}
