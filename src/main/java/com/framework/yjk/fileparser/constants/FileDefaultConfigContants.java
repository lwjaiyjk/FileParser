package com.framework.yjk.fileparser.constants;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/21 15:36
 * description：文件默认配置常量
 **/
public interface FileDefaultConfigContants {

    /**加载的模板的编码格式*/
    String              DEFAULT_TEMPLATE_ENCONDIG = "utf-8";
    /**读取或者生成的文件编码格式*/
    String              DEFAULT_FILE_ENCONDIG     = "utf-8";
    /**写文件时的默认换行符*/
    String              DEFAULT_LINE_BREAK        = "\r\n";

    Map<String, Object> DEFAULT_FILE_PARAMS       = new ConcurrentHashMap<String, Object>();

    // -------------默认扩展资源地址-------------
    /**自动执行处理器*/
    String              RDF_PROCESSOR_PATH        = "classpath*:META-INF/rdf-file/auto-processor/";
    /**定义的文件协议(格式)*/
    String              RDF_PROTOCOL_PATH         = "classpath*:META-INF/rdf-file/protocol/";
    /**文件格式化映射文件*/
    String              RDF_FORMAT_PATH           = "classpath*:META-INF/rdf-file/format/";
    /**默认模板存放地址*/
    String              RDF_TEMPLATE_PATH         = "classpath:";
}
