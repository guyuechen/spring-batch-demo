package com.sb.itemprocessor.process;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class FileItemWriterConfig {
    @Bean
    public FlatFileItemWriter<Customer> fileItemWriter() {
        // 把Customer对象 转成 字符串 输出到文件
        FlatFileItemWriter<Customer> writer = new FlatFileItemWriter<>();
        String path = "D:\\StudyProjects/springbatch/src/main/resources/output.txt";
        writer.setResource(new FileSystemResource(path));
        // 把Customer对象 转成 字符串
        writer.setLineAggregator(new LineAggregator<Customer>() {
            ObjectMapper mapper = new ObjectMapper();
            @Override
            public String aggregate(Customer customer) {
                String str = null;
                try {
                    str = mapper.writeValueAsString(customer);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                return str;
            }
        });
        try {
            writer.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return writer;
    }
}
