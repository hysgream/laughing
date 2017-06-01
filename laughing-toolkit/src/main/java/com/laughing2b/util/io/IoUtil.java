package com.laughing2b.util.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
* @ClassName: IoUtil 
* @Description: IO工具类
* @author lifei.pan
* @email plfnet@163.com
* @date 2015年8月7日 下午5:26:57 
*
 */
public class IoUtil {
    private static Logger logger = LoggerFactory.getLogger(IoUtil.class);

    public static byte[] readData(InputStream is) throws IOException {
        int len;
        int size = 1024;
        byte[] buf = new byte[0];

        try {
            if (is instanceof ByteArrayInputStream) {
                size = is.available();
                buf = new byte[size];
                len = is.read(buf, 0, size);
            } else {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                buf = new byte[size];
                while ((len = is.read(buf, 0, size)) != -1)
                    bos.write(buf, 0, len);
                buf = bos.toByteArray();
            }
        } catch (IOException e) {
            logger.error("read data from stream error");
        }finally {
            is.close();
        }

        return buf;
    }

}
