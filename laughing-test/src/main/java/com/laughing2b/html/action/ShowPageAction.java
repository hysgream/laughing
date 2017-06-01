package com.laughing2b.html.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShowPageAction {
	private static Logger logger = LoggerFactory.getLogger(ShowPageAction.class);
	@Value("${test.projectName}")
	private String projectName;

	@RequestMapping("test")
	public String showInitPage() {
		logger.info("projectName={}", projectName);
		return "test";
	}
}
