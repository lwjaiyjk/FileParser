package com.framework.yjk.fileparser.loader;

import com.framework.yjk.fileparser.constants.FileDefaultConfigContants;
import com.framework.yjk.fileparser.exception.FileParserErrorEnum;
import com.framework.yjk.fileparser.exception.FileParserRuntimeException;
import com.framework.yjk.fileparser.init.FileParserInit;
import com.framework.yjk.fileparser.resource.FileParserInputStream;
import com.framework.yjk.fileparser.spi.FileParserResourceSpi;
import com.framework.yjk.fileparser.utils.FileParserUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/21 15:38
 * description：资源加载
 **/
@Slf4j
public class ResourceLoader {

    private static final String DEFAULT_TYPE = "classpath";

    private static final String SPLIT        = ":";

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static FileParserInputStream getInputStream(String path) {
        path = FileParserUtil.assertTrimNotBlank(path, "rdf-file#ResourceLoader.getInputStream path为空",
                FileParserErrorEnum.ILLEGAL_ARGUMENT);

        int idx = path.indexOf(SPLIT);

        String resourceType = null;

        if (idx < 0) {
            resourceType = DEFAULT_TYPE;
        } else {
            resourceType = path.substring(0, idx);
            path = path.substring(idx + 1);
        }

        FileParserResourceSpi rdfResource = ExtensionLoader
                .getExtensionLoader(FileParserResourceSpi.class).getExtension(resourceType);

        if (null == rdfResource) {
            throw new FileParserRuntimeException("rdf-file#ResourceLoader.getInputStream(path=" + path
                    + ")  resourceType=" + resourceType + "没有对应的实现!",
                    FileParserErrorEnum.NOT_EXSIT);
        }

        rdfResource.resourceType(resourceType);

        if (rdfResource instanceof FileParserInit) {
            ((FileParserInit) rdfResource).init(FileDefaultConfigContants.DEFAULT_FILE_PARAMS.get(resourceType));
        }

        return rdfResource.getInputStream(path);
    }

    public static String buildResource(String resourcePath, String defaultResourceType) {
        resourcePath = FileParserUtil.assertTrimNotBlank(resourcePath,
                "rdf-file#ResourceLoader.buildResource resourcePath为空", FileParserErrorEnum.ILLEGAL_ARGUMENT);
        defaultResourceType = FileParserUtil.assertTrimNotBlank(defaultResourceType,
                "rdf-file#ResourceLoader.buildResource defaultResourceType为空",
                FileParserErrorEnum.ILLEGAL_ARGUMENT);

        int idx = resourcePath.indexOf(SPLIT);

        if (idx == 0) {
            throw new FileParserRuntimeException(
                    "rdf-file#ResourceLoader.buildResource resourcePath=" + resourcePath + "格式不对",
                    FileParserErrorEnum.ILLEGAL_ARGUMENT);
        } else if (idx < 0) {
            resourcePath = defaultResourceType + resourcePath;
        }

        if (log.isDebugEnabled()) {
            log.debug("rdf-file#ResourceLoader.buildResource(resourcePath=" + resourcePath
                            + ", defaultResourceType=" + defaultResourceType
                            + ") buildResource后resourcePath=" + resourcePath);
        }

        return resourcePath;
    }
}
