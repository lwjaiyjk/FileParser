package com.framework.yjk.fileparser.utils;

import com.framework.yjk.fileparser.exception.FileParserErrorEnum;
import com.framework.yjk.fileparser.exception.FileParserRuntimeException;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/21 15:24
 * description：
 **/
@Slf4j
public class FileParserUtil {

    private static final String EMPTY = "";

    private static final int BUF_SIZE = 8192;

    public static String trimNotNull(String text) {
        if (null == text) {
            return null;
        }

        return text.trim();
    }

    public static void assertNotBlank(String text) {
        if (null == text || 0 == text.trim().length()) {
            throw new FileParserRuntimeException("字符串不能为空", FileParserErrorEnum.ILLEGAL_ARGUMENT);
        }
    }

    public static void assertNotBlank(String text, String errorMsg) {
        if (null == text || 0 == text.trim().length()) {
            throw new FileParserRuntimeException(errorMsg, FileParserErrorEnum.ILLEGAL_ARGUMENT);
        }
    }

    public static void assertNotBlank(String text, String errorMsg, FileParserErrorEnum errorCode) {
        if (null == text || 0 == text.trim().length()) {
            throw new FileParserRuntimeException(errorMsg, errorCode);
        }
    }

    public static boolean isBlank(String text) {
        if (null == text || 0 == text.trim().length()) {
            return true;
        }

        return false;
    }

    public static boolean isNotBlank(String text) {
        return !isBlank(text);
    }

    public static void assertNull(Object obj, String errorMsg) {
        if (null != obj) {
            throw new FileParserRuntimeException(errorMsg, FileParserErrorEnum.ILLEGAL_ARGUMENT);
        }
    }

    public static void assertNull(Object obj, String errorMsg, FileParserErrorEnum errorCode) {
        if (null != obj) {
            throw new FileParserRuntimeException(errorMsg, errorCode);
        }
    }

    public static void assertNotNull(Object obj, String errorMsg) {
        if (null == obj) {
            throw new FileParserRuntimeException(errorMsg, FileParserErrorEnum.ILLEGAL_ARGUMENT);
        }
    }

    public static void assertNotNull(Object obj, String errorMsg, FileParserErrorEnum errorCode) {
        if (null == obj) {
            throw new FileParserRuntimeException(errorMsg, errorCode);
        }
    }

    public static void assertEquals(String str1, String str2) {
        if (str1 == null && str2 != null) {
            throw new FileParserRuntimeException("rdf-file#字符串不相等 str1 == null, str2 == " + str2,
                    FileParserErrorEnum.ILLEGAL_ARGUMENT);
        }

        if (!str1.equals(str2)) {
            throw new FileParserRuntimeException("rdf-file#字符串不相等 str1 == " + str1 + ", str2 == " + str2,
                    FileParserErrorEnum.ILLEGAL_ARGUMENT);
        }
    }

    public static String assertTrimNotBlank(String text) {
        if (null == text || 0 == text.trim().length()) {
            throw new FileParserRuntimeException("rdf-file#字符串不能为空", FileParserErrorEnum.ILLEGAL_ARGUMENT);
        }

        return text.trim();
    }

    public static String assertTrimNotBlank(String text, String errorMsg) {
        if (null == text || 0 == text.trim().length()) {
            throw new FileParserRuntimeException(errorMsg, FileParserErrorEnum.ILLEGAL_ARGUMENT);
        }

        return text.trim();
    }

    public static String assertTrimNotBlank(String text, String errorMsg, FileParserErrorEnum errorCode) {
        if (null == text || 0 == text.trim().length()) {
            throw new FileParserRuntimeException(errorMsg, errorCode);
        }

        return text.trim();
    }

    public static boolean equals(String str1, String str2) {
        if (null == str1) {
            return str2 == null;
        }

        return str1.equals(str2);
    }

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back to system class loader...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = FileParserUtil.class.getClassLoader();
        }
        return cl;
    }

    public static String safeReadFully(InputStream is, String encoding) {
        try {
            InputStreamReader rdr = new InputStreamReader(is, encoding);

            final char[] buffer = new char[BUF_SIZE];
            int bufferLength = 0;
            StringBuffer textBuffer = null;
            while (bufferLength != -1) {
                bufferLength = rdr.read(buffer);
                if (bufferLength > 0) {
                    textBuffer = (textBuffer == null) ? new StringBuffer() : textBuffer;
                    textBuffer.append(new String(buffer, 0, bufferLength));
                }
            }
            return (textBuffer == null) ? "" : textBuffer.toString();
        } catch (UnsupportedEncodingException e) {
            throw new FileParserRuntimeException(e, FileParserErrorEnum.UNKOWN);
        } catch (IOException e) {
            throw new FileParserRuntimeException(e, FileParserErrorEnum.UNKOWN);
        }
    }

    public static Object newInstance(String clazz) {
        try {
            Class<?> cl = getDefaultClassLoader().loadClass(clazz);
            return cl.newInstance();
        } catch (ClassNotFoundException e) {
            throw new FileParserRuntimeException("类" + clazz + "不存在", e, FileParserErrorEnum.INSTANTIATION_ERROR);
        } catch (InstantiationException e) {
            throw new FileParserRuntimeException("类" + clazz + "实例化对象出错", e,
                    FileParserErrorEnum.INSTANTIATION_ERROR);
        } catch (IllegalAccessException e) {
            throw new FileParserRuntimeException("类" + clazz + "实例化对象出错", e,
                    FileParserErrorEnum.INSTANTIATION_ERROR);
        }
    }

    /**
     * 基金格式中对数值补位
     *
     * @param str
     * @param size
     * @param padChar
     * @return
     */
    public static String alignRight(String str, int size, char padChar) {
        String val = alignRight(str, size, String.valueOf(padChar));

        if (val.length() != size) {
            throw new FileParserRuntimeException(
                    "数值" + str + "补位后" + val + "长度" + val.length() + "模板定义长度" + size,
                    FileParserErrorEnum.ILLEGAL_ARGUMENT);
        }

        return val;
    }

    public static String alignRight(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }

        if ((padStr == null) || (padStr.length() == 0)) {
            padStr = " ";
        }

        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;

        if (pads <= 0) {
            return str;
        }

        if (pads == padLen) {
            return padStr.concat(str);
        } else if (pads < padLen) {
            return padStr.substring(0, pads).concat(str);
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();

            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }

            return new String(padding).concat(str);
        }
    }

    /**
     * 左对齐，填充空
     * <p>
     * 中文字符按字节计算
     *
     * @param str
     * @param size
     * @return
     */
    public static String alignLeftBlank(Object obj, int size, String encoding) {
        if (null == obj) {
            return alignLeft("", size, " ");
        }

        return alignLeftBlank(String.valueOf(obj).trim(), size, encoding);
    }

    /**
     * 左对齐，填充空
     * <p>
     * 中文字符按字节计算
     * <p>
     * 拷贝之StringUtil
     *
     * @param str
     * @param size
     * @return
     */
    public static String alignLeftBlank(String str, int size, String encoding) {
        if (null == str) {
            return alignLeft("", size, " ");
        }

        try {
            int len = str.trim().getBytes(encoding).length;

            if (len > size) {
                throw new FileParserRuntimeException("字符串[" + str + "]超过了模板定义的长度" + size + "无法补位",
                        FileParserErrorEnum.FORMAT_ERROR);
            }

            int pads = size - len;
            if (pads <= 0) {
                return str;
            }

            String padStr = " ";

            int padLen = padStr.length();

            if (pads <= 0) {
                return str;
            }

            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();

            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }

            return str.concat(new String(padding));

        } catch (UnsupportedEncodingException e) {
            throw new FileParserRuntimeException(str + " 编码出错", e, FileParserErrorEnum.FORMAT_ERROR);
        }
    }

    public static String alignLeft(String str, int size, String padStr) {
        if (str == null) {
            return null;
        }

        if ((padStr == null) || (padStr.length() == 0)) {
            padStr = " ";
        }

        int padLen = padStr.length();
        int strLen = str.length();
        int pads = size - strLen;

        if (pads <= 0) {
            return str;
        }

        if (pads == padLen) {
            return str.concat(padStr);
        } else if (pads < padLen) {
            return str.concat(padStr.substring(0, pads));
        } else {
            char[] padding = new char[pads];
            char[] padChars = padStr.toCharArray();

            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }

            return str.concat(new String(padding));
        }
    }

    public static String[] split(String str, String separator) {
        if (isBlank(separator)) {
            throw new FileParserRuntimeException("rdf-file# split 分隔符为空", FileParserErrorEnum.ILLEGAL_ARGUMENT);
        }

        if (null == str) {
            return null;
        }

        int len = str.length();

        if (len == 0) {
            return new String[0];
        }

        List<String> subStrings = new ArrayList<String>();
        int separatorLength = separator.length();
        int beg = 0;
        int end = 0;
        while (end < len) {
            end = str.indexOf(separator, beg);

            if (end > -1) {
                if (end > beg) {
                    subStrings.add(str.substring(beg, end));
                } else {
                    subStrings.add(EMPTY);
                }
                beg = end + separatorLength;
            } else {
                subStrings.add(str.substring(beg));
                end = len;
            }
        }

        return subStrings.toArray(new String[subStrings.size()]);
    }


    public static void copyDirectoryToDirectory(File srcDir, File destDir) {
        assertNotNull(srcDir, "rdf-file#Source must not be null", FileParserErrorEnum.ILLEGAL_ARGUMENT);
        assertNotNull(destDir, "rdf-file#Destination must not be null",
                FileParserErrorEnum.ILLEGAL_ARGUMENT);
        if (destDir.exists() && destDir.isDirectory() == false) {
            throw new FileParserRuntimeException("rdf-file#Destination '" + destDir + "' is not a directory",
                    FileParserErrorEnum.ILLEGAL_ARGUMENT);
        }

        copyDirectory(srcDir, new File(destDir, srcDir.getName()), true);
    }

    public static void copyDirectory(File srcDir, File destDir, boolean preserveFileDate) {
        copyDirectory(srcDir, destDir, null, preserveFileDate);
    }

    public static void copyDirectory(File srcDir, File destDir, FileFilter filter,
                                     boolean preserveFileDate) {
        assertNotNull(srcDir, "rdf-file#Source must not be null", FileParserErrorEnum.ILLEGAL_ARGUMENT);
        assertNotNull(destDir, "rdf-file#Destination must not be null",
                FileParserErrorEnum.ILLEGAL_ARGUMENT);
        if (srcDir.exists() == false) {
            throw new FileParserRuntimeException("rdf-file#Source '" + srcDir + "' does not exist",
                    FileParserErrorEnum.IO_ERROR);
        }
        if (srcDir.isDirectory() == false) {
            throw new FileParserRuntimeException(
                    "rdf-file#Source '" + srcDir + "' exists but is not a directory",
                    FileParserErrorEnum.IO_ERROR);
        }
        try {
            if (srcDir.getCanonicalPath().equals(destDir.getCanonicalPath())) {
                throw new FileParserRuntimeException("rdf-file#Source '" + srcDir + "' and destination '"
                        + destDir + "' are the same",
                        FileParserErrorEnum.IO_ERROR);
            }

            // Cater for destination being directory within the source directory (see IO-141)
            List<String> exclusionList = null;
            if (destDir.getCanonicalPath().startsWith(srcDir.getCanonicalPath())) {
                File[] srcFiles = filter == null ? srcDir.listFiles() : srcDir.listFiles(filter);
                if (srcFiles != null && srcFiles.length > 0) {
                    exclusionList = new ArrayList<String>(srcFiles.length);
                    for (File srcFile : srcFiles) {
                        File copiedFile = new File(destDir, srcFile.getName());
                        exclusionList.add(copiedFile.getCanonicalPath());
                    }
                }
            }
            doCopyDirectory(srcDir, destDir, filter, preserveFileDate, exclusionList);
        } catch (IOException e) {
            throw new FileParserRuntimeException(
                    "rdf-file#Source '" + srcDir + "' and destination '" + destDir + "'", e,
                    FileParserErrorEnum.IO_ERROR);
        }
    }

    private static void doCopyDirectory(File srcDir, File destDir, FileFilter filter,
                                        boolean preserveFileDate, List<String> exclusionList) {
        // recurse
        File[] srcFiles = filter == null ? srcDir.listFiles() : srcDir.listFiles(filter);
        // null if abstract pathname does not denote a directory, or if an I/O error occurs
        assertNotNull(srcFiles, "rdf-fiel#Failed to list contents of " + srcDir,
                FileParserErrorEnum.IO_ERROR);

        if (destDir.exists()) {
            if (destDir.isDirectory() == false) {
                throw new FileParserRuntimeException(
                        "rdf-fiel#Destination '" + destDir + "' exists but is not a directory",
                        FileParserErrorEnum.IO_ERROR);
            }
        } else {
            if (!destDir.mkdirs() && !destDir.isDirectory()) {
                throw new FileParserRuntimeException(
                        "rdf-file#Destination '" + destDir + "' directory cannot be created",
                        FileParserErrorEnum.IO_ERROR);
            }
        }
        if (destDir.canWrite() == false) {
            throw new FileParserRuntimeException(
                    "rdf-file#Destination '" + destDir + "' cannot be written to",
                    FileParserErrorEnum.IO_ERROR);
        }
        for (File srcFile : srcFiles) {
            File dstFile = new File(destDir, srcFile.getName());
            try {
                if (exclusionList == null || !exclusionList.contains(srcFile.getCanonicalPath())) {
                    if (srcFile.isDirectory()) {
                        doCopyDirectory(srcFile, dstFile, filter, preserveFileDate, exclusionList);
                    } else {
                        doCopyFile(srcFile, dstFile, preserveFileDate);
                    }
                }
            } catch (IOException e) {
                throw new FileParserRuntimeException("rdf-file# srcFile=" + srcFile.getAbsolutePath()
                        + ", dstFile=" + dstFile.getAbsolutePath(),
                        e, FileParserErrorEnum.IO_ERROR);
            }
        }

        // Do this last, as the above has probably affected directory metadata
        if (preserveFileDate) {
            destDir.setLastModified(srcDir.lastModified());
        }
    }

    private static final long FILE_COPY_BUFFER_SIZE = 1024 * 1024 * 30;

    private static void doCopyFile(File srcFile, File destFile, boolean preserveFileDate) {
        if (destFile.exists() && destFile.isDirectory()) {
            throw new FileParserRuntimeException(
                    "rdf-file#doCopyFile Destination '" + destFile + "' exists but is a directory",
                    FileParserErrorEnum.IO_ERROR);
        }

        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel input = null;
        FileChannel output = null;
        try {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);
            input = fis.getChannel();
            output = fos.getChannel();
            long size = input.size();
            long pos = 0;
            long count = 0;
            while (pos < size) {
                count = size - pos > FILE_COPY_BUFFER_SIZE ? FILE_COPY_BUFFER_SIZE : size - pos;
                pos += output.transferFrom(input, pos, count);
            }
        } catch (FileNotFoundException e) {
            throw new FileParserRuntimeException("rdf-file# Failed to copy full contents from '" + srcFile
                    + "' to '" + destFile + "' 文件不存在",
                    e, FileParserErrorEnum.IO_ERROR);
        } catch (IOException e) {
            throw new FileParserRuntimeException("rdf-file# Failed to copy full contents from '" + srcFile
                    + "' to '" + destFile + "' io error",
                    e, FileParserErrorEnum.IO_ERROR);
        } finally {
            if (null != output) {
                try {
                    output.close();
                } catch (IOException e) {
                    if (log.isWarnEnabled()) {
                        log.warn("rdf-file# Failed to copy full contents from '" + srcFile
                                        + "' to '" + destFile + "' output.close() error",
                                e);
                    }
                }
            }
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    if (log.isWarnEnabled()) {
                         log.warn("rdf-file# Failed to copy full contents from '" + srcFile
                                                + "' to '" + destFile + "' fos.close() error",
                                        e);
                    }
                }
            }
            if (null != input) {
                try {
                    input.close();
                } catch (IOException e) {
                    if (log.isWarnEnabled()) {
                        log
                                .warn("rdf-file# Failed to copy full contents from '" + srcFile
                                                + "' to '" + destFile + "' input.close() error",
                                        e);
                    }
                }
            }
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    if (log.isWarnEnabled()) {
                        log
                                .warn("rdf-file# Failed to copy full contents from '" + srcFile
                                                + "' to '" + destFile + "' fis.close() error",
                                        e);
                    }
                }
            }
        }

        if (srcFile.length() != destFile.length()) {
            throw new FileParserRuntimeException("rdf-file# Failed to copy full contents from '" + srcFile
                    + "' to '" + destFile + "'",
                    FileParserErrorEnum.IO_ERROR);
        }
        if (preserveFileDate) {
            destFile.setLastModified(srcFile.lastModified());
        }
    }

    public static void copyFile(File srcFile, File destFile) {
        copyFile(srcFile, destFile, true);
    }

    public static void copyFile(File srcFile, File destFile, boolean preserveFileDate) {
        assertNotNull(srcFile, "rdf-file#Source must not be null", FileParserErrorEnum.ILLEGAL_ARGUMENT);
        assertNotNull(destFile, "rdf-file#Destination must not be null",
                FileParserErrorEnum.ILLEGAL_ARGUMENT);

        if (srcFile.exists() == false) {
            throw new FileParserRuntimeException("rdf-file#Source '" + srcFile + "' does not exist",
                    FileParserErrorEnum.NOT_EXSIT);
        }
        if (srcFile.isDirectory()) {
            throw new FileParserRuntimeException(
                    "rdf-file#Source '" + srcFile + "' exists but is a directory",
                    FileParserErrorEnum.ILLEGAL_ARGUMENT);
        }
        try {
            if (srcFile.getCanonicalPath().equals(destFile.getCanonicalPath())) {
                throw new FileParserRuntimeException("rdf-fiel#Source '" + srcFile + "' and destination '"
                        + destFile + "' are the same",
                        FileParserErrorEnum.ILLEGAL_ARGUMENT);
            }
        } catch (IOException e) {
            throw new FileParserRuntimeException(
                    "rdf-file#Source '" + srcFile + "' and destination '" + destFile + "'", e,
                    FileParserErrorEnum.IO_ERROR);
        }
        File parentFile = destFile.getParentFile();
        if (parentFile != null) {
            if (!parentFile.mkdirs() && !parentFile.isDirectory()) {
                throw new FileParserRuntimeException(
                        "rdf-file#Destination '" + parentFile + "' directory cannot be created",
                        FileParserErrorEnum.IO_ERROR);
            }
        }
        if (destFile.exists() && destFile.canWrite() == false) {
            throw new FileParserRuntimeException(
                    "rdf-file#Destination '" + destFile + "' exists but is read-only",
                    FileParserErrorEnum.IO_ERROR);
        }

        doCopyFile(srcFile, destFile, preserveFileDate);
    }

    /**
     * 组装文件路径
     *
     * @param pathOrFilename
     * @return
     */
    public static String combinePath(String... pathOrFilename) {
        return FileNameUtils.normalize(FileNameUtils.join(pathOrFilename, File.separator));
    }


}
