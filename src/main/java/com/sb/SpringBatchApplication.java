package com.sb;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringBatchApplication.class)
				.web(WebApplicationType.NONE)  // 禁用 Web 环境
				.run(args);
	}

}
