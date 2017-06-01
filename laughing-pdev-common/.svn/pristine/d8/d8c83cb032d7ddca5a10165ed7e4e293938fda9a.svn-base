package com.laughing2b.common.dubbo;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.alibaba.dubbo.common.Extension;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.container.Container;

/**
 * @ClassName: SpringWebContainer
 * @Description: springMVC+Jetty的容器
 * @author lifei.pan
 * @email plfnet@163.com
 * @date 2015年10月12日 上午10:09:34
 *
 */
@Extension("springWeb")
public class SpringWebContainer implements Container {

	private static final Logger logger = LoggerFactory
			.getLogger(SpringWebContainer.class);

	public static final String SPRING_CONFIG = "dubbo.spring.config";
	public static final String JETTY_PORT = "dubbo.jetty.port";
	public static final int DEFAULT_JETTY_PORT = 8080;
	public static final String JETTY_CONTEXT_PATH = "dubbo.jetty.context.path";
	private static final String DEFAULT_CONTEXTPATH = "/";
	public static final String JETTY_DESCRIPTOR = "dubbo.jetty.descriptor";
	private static final String DEFAULT_DESCRIPTOR = "webapp/WEB-INF/web.xml";
	public static final String DEFAULT_SPRING_CONFIG = "classpath*:META-INF/spring/*.xml";
	private Server server;
	XmlWebApplicationContext context;

	public void start() {
		String configPath = ConfigUtils.getProperty(SPRING_CONFIG);
		if (configPath == null || configPath.length() == 0) {
			configPath = DEFAULT_SPRING_CONFIG;
		}

		server = new Server();

		SelectChannelConnector connector = new SelectChannelConnector();
		String serverPort = ConfigUtils.getProperty(JETTY_PORT);
		int port;
		if (serverPort == null || serverPort.length() == 0) {
			port = DEFAULT_JETTY_PORT;
		} else {
			port = Integer.parseInt(serverPort);
		}
		connector.setPort(port);
		server.addConnector(connector);

		WebAppContext webAppContext = new WebAppContext();
		String contextPath = ConfigUtils.getProperty(JETTY_CONTEXT_PATH);
		webAppContext.setContextPath(DEFAULT_CONTEXTPATH + contextPath);
		webAppContext.setDescriptor(ConfigUtils.getProperty(JETTY_DESCRIPTOR,
				DEFAULT_DESCRIPTOR));
		webAppContext.setResourceBase("web");

		webAppContext.setParentLoaderPriority(true);
		server.setHandler(webAppContext);

		context = new XmlWebApplicationContext();
		// 可以设置parent
		// webAppContext.setClassLoader(applicationContext.getClassLoader());
		// context.setParent(applicationContext);
		context.setConfigLocation("classpath:" + configPath);
		context.setServletContext(webAppContext.getServletContext());
		context.refresh();

		webAppContext.setAttribute(
				WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
				context);

		try {
			logger.info("start jetty web context context= "
					+ webAppContext.getContextPath() + ";descriptor="
					+ webAppContext.getDescriptor());
			server.start();
		} catch (Exception e) {
			throw new IllegalStateException("Failed to start jetty server on "
					+ NetUtils.getLocalHost() + ":" + ", cause: "
					+ e.getMessage(), e);
		}

	}

	public void stop() {
		try {
			if (context != null) {
				context.stop();
				context.close();
				context = null;

			}
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		}
	}

}