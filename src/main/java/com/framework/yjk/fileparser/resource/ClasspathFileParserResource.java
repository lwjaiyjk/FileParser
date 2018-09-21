package com.framework.yjk.fileparser.resource;

import com.framework.yjk.fileparser.utils.FileParserUtil;

import java.io.InputStream;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/21 16:01
 * description：
 **/
public class ClasspathFileParserResource extends AbstractFileParserResource{

    @Override
    public FileParserInputStream getInputStream(String path) {
        InputStream is = FileParserUtil.getDefaultClassLoader().getResourceAsStream(path);

        if (null == is) {
            is = FileParserUtil.class.getResourceAsStream(path);
        }

        if (null == is) {
            return null;
        }

        return new FileParserInputStream(is);
    }
}
