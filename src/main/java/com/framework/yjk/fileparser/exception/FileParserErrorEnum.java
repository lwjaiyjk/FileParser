package com.framework.yjk.fileparser.exception;

import com.framework.yjk.fileparser.model.FileParserRunTimeException;
import com.framework.yjk.fileparser.model.enums.IEnum;

import java.text.MessageFormat;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/21 10:06
 * description：
 **/
public enum FileParserErrorEnum implements IEnum {

    /**
     * 未知异常
     */
    UNKOWN("001", "未知异常", "UNKOWN", "未知异常"),

    /**
     * 内容为空
     */
    EMPTY("002", "内容为空", "EMPTY", "内容为空"),

    /**
     * 没有定义头
     */
    HEAD_NOT_DEFINED("003", "没有定义头", "HEAD_NOT_DEFINED", "没有定义头"),

    /**
     * 没有定义文件内容
     */
    BODY_NOT_DEFINED("004", "没有定义文件内容", "BODY_NOT_DEFINED", "没有定义文件内容"),

    /**
     * 字段没有定义
     */
    COLUMN_NOT_DEFINED("005", "字段没有定义", "COLUMN_NOT_DEFINED", "字段没有定义"),

    /**
     * 没有定义尾
     */
    TAIL_NOT_DEFINED("006", "没有定义尾", "TAIL_NOT_DEFINED", "没有定义尾"),

    /**
     * 协议布局模板定义错误
     */
    PROTOCOL_DEFINE_ERROR("007", "协议布局模板定义错误", "PROTOCOL_DEFINE_ERROR", "协议布局模板定义错误"),

    /**
     * 不存在
     */
    NOT_EXSIT("008", "不存在", "NOT_EXSIT", "不存在"),

    /**
     * 非法参数
     */
    ILLEGAL_ARGUMENT("009", "非法参数", "ILLEGAL_ARGUMENT", "非法参数"),

    /**
     * 数据错误
     */
    DATA_ERROR("010", "数据错误", "DATA_ERROR", "数据错误"),

    /**
     * 序列化错误
     */
    SERIALIZE_ERROR("011", "序列化错误", "SERIALIZE_ERROR", "序列化错误"),

    /**
     * 反序列化错误
     */
    DESERIALIZE_ERROR("012", "反序列化错误", "DESERIALIZE_ERROR", "反序列化错误"),

    /**
     * 校验异常
     */
    VALIDATE_ERROR("013", "校验异常", "VALIDATE_ERROR", "校验异常"),

    /**
     * 不支持操作
     */
    UNSUPPORTED_OPERATION("014", "不支持操作", "UNSUPPORTED_OPERATION", "不支持操作"),

    /**
     * 编码问题
     */
    ENCODING_ERROR("015", "编码问题", "ENCODING_ERROR", "编码问题"),

    /**
     * IO问题
     */
    IO_ERROR("016", "IO问题", "IO_ERROR", "IO问题"),

    /**
     * 汇总功能没有开启
     */
    SUMMARY_DISNABLE("017", "汇总功能没有开启", "SUMMARY_DISNABLE", "汇总功能没有开启"),

    /**
     * 汇总字段定义错误
     */
    SUMMARY_DEFINED_ERROR("018", "汇总字段定义错误", "SUMMARY_DEFINED_ERROR", "汇总字段定义错误"),

    /**
     * 不支持换行符
     */
    UNSUPPORT_LINEBREAK("019", "不支持换行符", "UNSUPPORT_LINEBREAK", "不支持换行符"),

    /**
     * 重复定义
     */
    DUPLICATE_DEFINED("020", "重复定义", "DUPLICATE_DEFINED", "重复定义"),

    /**
     * 日期类型转换错误
     */
    DATE_FORAMT_ERROR("021", "日期类型转换错误", "DATE_FORAMT_ERROR", "日期类型转换错误"),

    /**
     * 加载扩展服务失败
     */
    EXTENSION_ERROR("022", "加载扩展服务失败", "EXTENSION_ERROR", "加载扩展服务失败"),

    /**
     * 字段类型定义错误
     */
    COLUMN_TYPE_ERROR("023", "字段类型定义错误", "COLUMN_TYPE_ERROR", "字段类型定义错误"),

    /**
     * 对象实例化错误
     */
    INSTANTIATION_ERROR("024", "对象实例化错误", "INSTANTIATION_ERROR", "对象实例化错误"),

    /**
     * 函数定义错误
     */
    FUNCTION_ERROR("025", "函数定义错误", "FUNCTION_ERROR", "函数定义错误"),

    /**
     * 排序异常
     */
    SORT_ERROR("026", "排序异常", "SORT_ERROR", "排序异常"),

    /**
     * 需要排序
     */
    NEED_SORTED("027", "需要排序", "NEED_SORTED", "需要排序"),

    /**
     * 计算影响行数
     */
    ROWS_AFFECTED_ERROR("028", "计算影响行数", "ROWS_AFFECTED_ERROR", "计算影响行数"),

    /**
     * 数据定义模板错误
     */
    TEMPLATE_ERROR("029", "数据定义模板错误", "TEMPLATE_ERROR", "数据定义模板错误"),

    /**
     * 资源加载异常
     */
    RESOURCE_ERROR("030", "资源加载异常", "RESOURCE_ERROR", "资源加载异常"),

    /**
     * 类型转换失败
     */
    TYPE_CONVERTION_ERROR("040", "类型转换失败", "TYPE_CONVERTION_ERROR", "类型转换失败"),

    /**
     * 格式化错误
     */
    FORMAT_ERROR("041", "格式化错误", "FORMAT_ERROR", "格式化错误");


    /**
     * code
     */
    private String code;

    /**
     * 中文名称
     */
    private String chineseName;

    /**
     * 英文名称
     */
    private String englishName;

    /**
     * 描述
     */
    private String desc;

    /**
     * 根据code查询对应的枚举类型
     *
     * @param code 枚举代码
     * @return 枚举
     */
    public static FileParserErrorEnum getByCode(String code) {
        for (FileParserErrorEnum ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele;
            }
        }

        throw new FileParserRunTimeException(
                MessageFormat.format("根据code={0}查询对应的枚举FileParserErrorEnum不存在", code));
    }

    FileParserErrorEnum(String code, String chineseName, String englishName, String desc) {
        this.code = code;
        this.chineseName = chineseName;
        this.englishName = englishName;
        this.desc = desc;
    }

    /**
     * @return the code
     */
    @Override
    public String getCode() {
        return code;
    }


    /**
     * @return the chineseName
     */
    @Override
    public String getChineseName() {
        return chineseName;
    }


    /**
     * @return the englishName
     */
    @Override
    public String getEnglishName() {
        return englishName;
    }


    /**
     * @return the desc
     */
    @Override
    public String getDesc() {
        return desc;
    }
}
