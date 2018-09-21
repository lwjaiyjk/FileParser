package com.framework.yjk.fileparser.model;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/20 9:17
 * description：文件解析异常
 **/
public class FileParserRunTimeException extends RuntimeException {

    /**
     * 构造函数
     *
     * @param message
     */
    public FileParserRunTimeException(String message) {
        super(message);
    }

    /**
     * 构造函数
     *
     * @param message
     * @param exception
     */
    public FileParserRunTimeException(String message, Exception exception) {
        super(message, exception);
    }
}
