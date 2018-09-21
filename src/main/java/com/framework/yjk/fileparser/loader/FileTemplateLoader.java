package com.framework.yjk.fileparser.loader;

import com.framework.yjk.fileparser.constants.FileDefaultConfigContants;
import com.framework.yjk.fileparser.model.FileTemplate;
import com.framework.yjk.fileparser.utils.FileParserUtil;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/21 9:45
 * description：文件模板加载器
 **/
public class FileTemplateLoader {

    /**
     * 加载模板文件
     *
     * @param templateFilePath
     * @param templateEncoding
     * @return
     */
    public FileTemplate load(String templateFilePath, String templateEncoding) {

        FileParserUtil.assertNotBlank(templateFilePath, "文件模板路径不能为空");
        if (FileParserUtil.isBlank(templateEncoding)) {
            templateEncoding = FileDefaultConfigContants.DEFAULT_TEMPLATE_ENCONDIG;
        }



        return null;
    }
}
