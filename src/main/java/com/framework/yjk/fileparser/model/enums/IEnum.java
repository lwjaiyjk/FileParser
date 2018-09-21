package com.framework.yjk.fileparser.model.enums;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/20 9:16
 * description：枚举接口
 **/
public interface IEnum {

    /**
     * 获取对应的Code
     *
     * @return
     */
    public String getCode();

    /**
     * 获取对应的中文名称
     *
     * @return the chineseName
     */
    public String getChineseName();

    /**
     * 获取对应的英文名称
     *
     * @return the englishName
     */
    public String getEnglishName();

    /**
     * 获取对应的备注
     *
     * @return the desc
     */
    public String getDesc();
}
