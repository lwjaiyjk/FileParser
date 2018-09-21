package com.framework.yjk.fileparser.model.enums;

import com.framework.yjk.fileparser.model.FileParserRunTimeException;

import java.text.MessageFormat;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/20 9:15
 * description：字段分割类型
 **/
public enum FieldSplitTypeEnum implements IEnum {

    /**
     * 固定长度分割
     */
    FIX_LENGTH_SPLIT("01", "固定长度分割", "fix length split", "固定长度分割"),
    /**
     * 分割符分割
     */
    SEP_SPLIT("02", "分割符分割", "SEP_SPLIT", "分割符分割"),;


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
    public static FieldSplitTypeEnum getByCode(String code) {
        for (FieldSplitTypeEnum ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele;
            }
        }

        throw new FileParserRunTimeException(
                MessageFormat.format("根据code={0}查询对应的枚举ExchangeOpTypeEnum不存在", code));
    }

    FieldSplitTypeEnum(String code, String chineseName, String englishName, String desc) {
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
