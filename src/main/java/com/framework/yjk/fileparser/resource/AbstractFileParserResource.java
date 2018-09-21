package com.framework.yjk.fileparser.resource;


import com.framework.yjk.fileparser.model.FileTemplate;
import com.framework.yjk.fileparser.spi.FileParserResourceSpi;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/21 15:58
 * description：
 **/
public abstract class AbstractFileParserResource implements FileParserResourceSpi {

    /**
     * 资源类型
     */
    protected String resourceType;

    /**
     * 文件模板
     */
    protected FileTemplate fileTemplate;

    @Override
    public void init(FileTemplate t) {
        this.fileTemplate = t;
    }

    @Override
    public void resourceType(String resourceType) {
        this.resourceType = resourceType;
    }
}
