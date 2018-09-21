package com.framework.yjk.fileparser.loader;

import com.framework.yjk.fileparser.exception.FileParserErrorEnum;
import com.framework.yjk.fileparser.exception.FileParserRuntimeException;
import com.framework.yjk.fileparser.utils.FileParserUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/21 15:51
 * description：
 **/
@Slf4j
@SuppressWarnings("unchecked")
public class ExtensionLoader<T> {
    private static final String RDF_SERVICE_DIR = "META-INF/rdf-file/services/";

    private static final ConcurrentHashMap<Class<?>, ExtensionLoader<?>> EXTENSION_LOADERS = new ConcurrentHashMap<Class<?>, ExtensionLoader<?>>();

    private final Object instanceLock = new Object();
    private final Object clazzLock = new Object();

    private final Class<?> type;

    private ConcurrentHashMap<String, T> cachedInstances = null;

    private ConcurrentHashMap<String, Class<?>> cachedClasses = null;

    private ConcurrentHashMap<Class<?>, List<String>> cachedClass2Names = null;

    private ExtensionLoader(Class<?> type) {
        this.type = type;
    }

    public static <T> ExtensionLoader<T> getExtensionLoader(Class<T> type) {
        FileParserUtil.assertNotNull(type, "rdf-file#getExtensionLoader(type=null)",
                FileParserErrorEnum.ILLEGAL_ARGUMENT);

        ExtensionLoader<T> loader = (ExtensionLoader<T>) EXTENSION_LOADERS.get(type);
        if (loader == null) {
            loader = new ExtensionLoader<T>(type);
            EXTENSION_LOADERS.putIfAbsent(type, loader);
        }

        return loader;
    }

    public T getExtension(String name) {
        return getExtensions().get(FileParserUtil.assertTrimNotBlank(name, "Extension name == null"));
    }

    public T getNewExtension(String name) {
        Class<?> clazz = getExtensionClasses().get(FileParserUtil.assertTrimNotBlank(name,
                "rdf-file#Extension name == null", FileParserErrorEnum.ILLEGAL_ARGUMENT));

        if (clazz == null) {
            System.out.println(cachedClasses);
        }

        FileParserUtil.assertNotNull(clazz,
                "rdf-file#Extension type=" + type.getName() + ", name =" + name + ", class == null",
                FileParserErrorEnum.EXTENSION_ERROR);

        try {
            return (T) clazz.newInstance();
        } catch (InstantiationException e) {
            throw new FileParserRuntimeException(e, FileParserErrorEnum.EXTENSION_ERROR);
        } catch (IllegalAccessException e) {
            throw new FileParserRuntimeException(e, FileParserErrorEnum.EXTENSION_ERROR);
        }
    }

    public Map<String, T> getExtensions() {
        if (null == cachedInstances) {
            synchronized (instanceLock) {
                if (null == cachedInstances) {
                    ConcurrentHashMap<String, T> tempCachedInstances = new ConcurrentHashMap<String, T>();
                    loadFile(tempCachedInstances);
                    cachedInstances = tempCachedInstances;
                }
            }
        }

        return cachedInstances;
    }

    public List<String> getExtensionAlias(Class<?> clazz) {
        getExtensionClasses();

        return cachedClass2Names.get(clazz);
    }

    public Class<T> getExtensionClass(String name) {
        return (Class<T>) getExtensionClasses().get(name);
    }

    public ConcurrentHashMap<String, Class<?>> getExtensionClasses() {
        if (null == cachedClasses) {
            synchronized (clazzLock) {
                if (null == cachedClasses) {
                    ConcurrentHashMap<String, Class<?>> tempCachedClasses = new ConcurrentHashMap<String, Class<?>>();
                    cachedClass2Names = new ConcurrentHashMap<Class<?>, List<String>>();
                    loadExtensionFile(tempCachedClasses);
                    cachedClasses = tempCachedClasses;
                }
            }
        }

        return cachedClasses;
    }

    private void loadFile(ConcurrentHashMap<String, T> cachedInstances) {
        ConcurrentHashMap<String, Class<?>> extensionClasses = getExtensionClasses();

        for (Map.Entry<String, Class<?>> entry : extensionClasses.entrySet()) {
            try {
                cachedInstances.put(entry.getKey(), (T) entry.getValue().newInstance());
            } catch (InstantiationException e) {
                throw new FileParserRuntimeException(e, FileParserErrorEnum.EXTENSION_ERROR);
            } catch (IllegalAccessException e) {
                throw new FileParserRuntimeException(e, FileParserErrorEnum.EXTENSION_ERROR);
            }
        }
    }

    private void loadExtensionFile(ConcurrentHashMap<String, Class<?>> cachedClasses) {
        String fileName = RDF_SERVICE_DIR + type.getName();

        try {
            Enumeration<URL> urls = FileParserUtil.getDefaultClassLoader().getResources(fileName);

            if (null == urls) {
                return;
            }

            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(url.openStream(), "utf-8"));
                try {
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        line = line.trim();
                        int idx = line.indexOf('=');
                        if (idx < 1) {
                            throw new FileParserRuntimeException("rdf-file#type=" + type.getName()
                                    + ",extension服务配置格式错误 url=" + url.toString(),
                                    FileParserErrorEnum.EXTENSION_ERROR);
                        }

                        String key = FileParserUtil.assertTrimNotBlank(line.substring(0, idx));
                        line = FileParserUtil.assertTrimNotBlank(line.substring(idx + 1));

                        if (line.length() > 0) {
                            Class<?> clazz = Class.forName(line, true,
                                    FileParserUtil.getDefaultClassLoader());
                            if (!type.isAssignableFrom(clazz)) {
                                throw new FileParserRuntimeException(
                                        "rdf-file#type=" + type.getName() + ", class: [" + line
                                                + "] is not subtype of "
                                                + type.getName(),
                                        FileParserErrorEnum.EXTENSION_ERROR);
                            }
                            String[] aliases = key.split(",");

                            List<String> aliasList = cachedClass2Names.get(clazz);
                            if (null == aliasList) {
                                aliasList = new ArrayList<String>();
                                cachedClass2Names.put(clazz, aliasList);
                            }

                            for (String alias : aliases) {
                                alias = FileParserUtil.assertTrimNotBlank(alias);
                                Class<?> old = cachedClasses.get(alias);

                                if (null != old) {
                                    if (log.isWarnEnabled()) {
                                        log.warn("rdf-file#type=" + type.getName() + ",class=["
                                                        + clazz.getName() + "],key=[" + alias
                                                        + "] 重复定义了 url=" + url.getPath());
                                    }
                                } else {
                                    cachedClasses.putIfAbsent(alias, clazz);
                                    aliasList.add(alias);
                                }
                            }
                        }
                    }
                } finally {
                    if (null != reader) {
                        reader.close();
                    }
                }
            }
        } catch (FileParserRuntimeException e) {
            throw e;
        } catch (Throwable e) {
            throw new FileParserRuntimeException(
                    "rdf-file#type=" + type.getName() + ",ExtensionLoader " + fileName + " 扩展失败", e,
                    FileParserErrorEnum.EXTENSION_ERROR);
        }
    }
}