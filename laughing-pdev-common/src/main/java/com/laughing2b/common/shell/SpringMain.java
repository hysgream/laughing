package com.laughing2b.common.shell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: SpringMain
 * @Description: spring的容器
 * @author lifei.pan
 * @email plfnet@163.com
 * @date 2015年10月12日 上午10:10:01
 *
 */
public class SpringMain {

	private static final Logger logger = LoggerFactory
			.getLogger(SpringMain.class);

	ClassPathXmlApplicationContext context;

	private static volatile boolean running = true;

	public static void main(String[] args) {
		try {
			final SpringMain commonMain = new SpringMain();
			logger.info("start spring  service.");

			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {

					try {
						commonMain.stop();
						logger.info("app stopped!");
					} catch (Throwable t) {
						logger.error(t.getMessage(), t);
					}
					synchronized (SpringMain.class) {
						running = false;
						SpringMain.class.notify();
					}

				}
			});

			commonMain.start(args[0]);
			logger.info("app  started!");

			System.out.println(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]")
					.format(new Date()) + "  server started!");
		} catch (RuntimeException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			throw e;
		}
		synchronized (SpringMain.class) {
			while (running) {
				try {
					SpringMain.class.wait();
				} catch (Throwable e) {
				}
			}
		}
	}

	public void start(String configPath) {

		try {
			context = new ClassPathXmlApplicationContext("classpath:"
					+ configPath);
			context.start();

		} catch (Exception e) {
			logger.error("start spring context fail ", e);
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
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