package com.sb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@EnableBatchProcessing
@MapperScan("com.sb.**.repository")
public class SpringBatchApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringBatchApplication.class)
				// 禁用 Web 环境
				.web(WebApplicationType.NONE)
				.run(args);
	}

}
