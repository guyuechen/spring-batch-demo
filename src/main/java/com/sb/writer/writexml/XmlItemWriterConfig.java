package com.sb.writer.writexml;

import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class XmlItemWriterConfig {
    @Bean
    public StaxEventItemWriter<Customer> xmlItemWriter() {
        StaxEventItemWriter<Customer> writer = new StaxEventItemWriter<>();
        XStreamMarshaller marshaller = new XStreamMarshaller();
        // 告诉marshaller 把数据转成什么类型
        Map<String, Class> map = new HashMap<>();
        map.put("customer", Customer.class);
        marshaller.setAliases(map);

        writer.setRootTagName("customers");
        writer.setMarshaller(marshaller);

        String path = "D:\\StudyProjects/springbatch/src/main/resources/output.xml";
        writer.setResource(new FileSystemResource(path));
        try {
            writer.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return writer;
    }
}
