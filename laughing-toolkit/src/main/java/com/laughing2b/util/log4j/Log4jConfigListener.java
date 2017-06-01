package com.laughing2b.util.log4j;

import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Log4jConfigurer;
import org.springframework.util.ResourceUtils;
import org.springframework.util.SystemPropertyUtils;

/**
* @ClassName: Log4jConfigListener 
* @Description: log4j辅助类，可以自动刷新log4j配置
* @author lifei.pan
* @email plfnet@163.com
* @date 2015年8月7日 下午4:08:25 
*
 */
public class Log4jConfigListener {
    private static Logger logger = LoggerFactory.getLogger(Log4jConfigListener.class);

    private String location;
    private int refreshInterval = 60000;

    public void setLocation(String location) {
        this.location = location;
    }


    public void setRefreshSeconds(int refreshInterval) {
        this.refreshInterval = refreshInterval;
    }

    public void init() {
        logger.info("log4j config listener start init");
        if (location != null) {
            // Perform actual log4j initialization; else rely on log4j's default initialization.
            try {
                // Return a URL (e.g. "classpath:" or "file:") as-is;
                // consider a plain file path as relative to the web application root directory.
                if (!ResourceUtils.isUrl(location)) {
                    // Resolve system property placeholders before resolving real path.
                    location = SystemPropertyUtils.resolvePlaceholders(location);

                }
                Log4jConfigurer.initLogging(location, refreshInterval);

            } catch (FileNotFoundException ex) {
                throw new IllegalArgumentException("Invalid 'log4jConfigLocation' parameter: " + ex.getMessage());
            }
        }else{
            logger.warn("log4j config file not specify,listener not work ");
        }
    }

    public static void shutdownLogging() {
       Log4jConfigurer.shutdownLogging();
    }
}
