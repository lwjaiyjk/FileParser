package com.framework.yjk.fileparser.spi;

import com.framework.yjk.fileparser.init.FileParserInit;
import com.framework.yjk.fileparser.model.FileTemplate;
import com.framework.yjk.fileparser.resource.FileParserInputStream;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/21 15:45
 * description：
 **/
public interface FileParserResourceSpi extends FileParserInit<FileTemplate> {

    /**
     * 资源加载类型
     *
     * @param resourceType
     */
    void resourceType(String resourceType);

    /***
     * 根据路径获取输入流
     *
     * @param path
     * @return
     */
    FileParserInputStream getInputStream(String path);
}
