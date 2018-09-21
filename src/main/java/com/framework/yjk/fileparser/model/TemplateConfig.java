package com.framework.yjk.fileparser.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/21 8:58
 * description：
 **/
@Data
public class TemplateConfig {

    /**
     * 协议
     */
    private String protocol;
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
     * 文件以分隔符开始   head|body|tail
     */
    private String startWithSplit;
    /**
     * 文件以分隔符结束   head|body|tail
     */
    private String endWithSplit;
    /**
     * 头信息定义
     */
    private List<String> head = new ArrayList<String>();
    /**
     * 文件体信息定义
     */
    private List<TemplateBodyConfig> body = new ArrayList<TemplateBodyConfig>();
    /**
     * 文件尾部信息定义
     */
    private List<String> tail = new ArrayList<String>();
    /**
     * 配置汇总字段
     */
    private List<String> summaryColumnPairs = new ArrayList<String>();
    /**
     * 行校验器
     */
    private List<String> rowValidators = new ArrayList<String>();
}
