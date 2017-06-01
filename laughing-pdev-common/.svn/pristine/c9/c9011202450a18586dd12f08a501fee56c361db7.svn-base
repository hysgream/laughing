package com.laughing2b.common.shell;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @ClassName: SpringJettyMain 
* @Description:springMVC+Jetty的容器
* @author lifei.pan
* @email plfnet@163.com
* @date 2015年10月12日 上午10:09:47 
*
 */
public class SpringJettyMain {

    private static final Logger logger = LoggerFactory.getLogger(SpringJettyMain.class);

    private Server server;

    private WebAppContext webAppContext;


    public void start() throws Exception {

        logger.info("start jetty web context context= " + webAppContext.getContextPath() + ";resource base=" + webAppContext.getResourceBase() + ";descriptor=" + webAppContext.getDescriptor());


        try {

            server.start();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to start jetty server on " + ":" + ", cause: " + e.getMessage(), e);
        }

    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void setWebAppContext(WebAppContext webAppContext) {
        this.webAppContext = webAppContext;
    }

}