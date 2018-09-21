package com.framework.yjk.fileparser.resource;

import com.framework.yjk.fileparser.exception.FileParserErrorEnum;
import com.framework.yjk.fileparser.utils.FileParserUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author yujiakui
 * @version 1.0
 * Email: jkyu@haiyi-info.com
 * date: 2018/9/21 15:47
 * description：
 **/
public class FileParserInputStream extends InputStream {
    private Iterator<InputStream> streamIter;

    private InputStream currentStream;

    public FileParserInputStream(InputStream is) {
        FileParserUtil.assertNotNull(is, "rdf-file#new RdfInputStream(is = null)",
                FileParserErrorEnum.ILLEGAL_ARGUMENT);
        this.currentStream = is;
        List<InputStream> streams = new ArrayList<InputStream>(1);
        streams.add(is);
        this.streamIter = streams.iterator();
    }

    public FileParserInputStream(List<InputStream> streams) {
        FileParserUtil.assertNotNull(streams, "rdf-file#new RdfInputStream(is = null)",
                FileParserErrorEnum.ILLEGAL_ARGUMENT);
        this.streamIter = streams.iterator();
    }

    public boolean hasNext() {
        if (null == streamIter) {
            return false;
        }

        return streamIter.hasNext();
    }

    public boolean next() {
        if (null == streamIter) {
            return false;
        }

        boolean hasNext = streamIter.hasNext();
        if (hasNext) {
            currentStream = streamIter.next();
        }

        return hasNext;
    }

    @Override
    public int read() throws IOException {
        return currentStream.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return currentStream.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return currentStream.read(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return currentStream.skip(n);
    }

    @Override
    public int available() throws IOException {
        return currentStream.available();
    }

    @Override
    public void close() throws IOException {
        currentStream.close();
    }

    @Override
    public synchronized void mark(int readlimit) {
        currentStream.mark(readlimit);
    }

    @Override
    public synchronized void reset() throws IOException {
        currentStream.reset();
    }

    @Override
    public boolean markSupported() {
        return currentStream.markSupported();
    }
}
