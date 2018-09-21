package com.framework.yjk.fileparser.exception;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/21 10:05
 * description：
 **/
public class FileParserRuntimeException extends RuntimeException {
    /**
     * serial id
     */
    private static final long serialVersionUID = -6725254693900716337L;
    /**
     * 错误类型
     */
    private FileParserErrorEnum errorEnum = FileParserErrorEnum.UNKOWN;

    public FileParserRuntimeException(String message, FileParserErrorEnum errorEnum) {
        super(message);
        this.errorEnum = errorEnum;
    }

    public FileParserRuntimeException(String message, Throwable cause, FileParserErrorEnum errorEnum) {
        super(message, cause);
        this.errorEnum = errorEnum;
    }

    public FileParserRuntimeException(Throwable cause, FileParserErrorEnum errorEnum) {
        super(cause);
        this.errorEnum = errorEnum;
    }

    public FileParserErrorEnum getErrorEnum() {
        return errorEnum;
    }
}