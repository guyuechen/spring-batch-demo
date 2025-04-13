package com.sb.reader.readxml;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableBatchProcessing
public class XmlItemReaderDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("xmlItemWriter")
    private ItemWriter<? super Customer> xmlItemWriter;

    @Bean
    public Job xmlItemReaderDemoJob() {
        return jobBuilderFactory.get("xmlItemReaderDemoJob")
                .start(xmlItemReaderDemoStep())
                .build();
    }

    @Bean
    public Step xmlItemReaderDemoStep() {
        return stepBuilderFactory.get("xmlItemReaderDemoStep")
                .<Customer, Customer>chunk(5)
                .reader(xmlItemReader())
                .writer(xmlItemWriter)
                .build();
    }

    @Bean
    @StepScope
    public StaxEventItemReader<Customer> xmlItemReader() {
        StaxEventItemReader<Customer> reader = new StaxEventItemReader<Customer>();
        reader.setResource(new ClassPathResource("customer.xml")); // 指定文件位置

        // 指定需要处理的根标签
        reader.setFragmentRootElementName("customer");
        // 把xml转成 对象
        XStreamMarshaller unmarshaller = new XStreamMarshaller();
        // 告诉unmarshaller 把xml转成什么类型
        Map<String, Class> map = new HashMap<>();
        map.put("customer", Customer.class);
        unmarshaller.setAliases(map);

        reader.setUnmarshaller(unmarshaller);
        return reader;
    }
}
