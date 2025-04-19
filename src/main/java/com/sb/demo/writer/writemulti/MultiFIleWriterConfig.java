package com.sb.demo.writer.writemulti;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MultiFIleWriterConfig {
    // 1. 类似于jsonWriter
    @Bean
    public FlatFileItemWriter<Customer> fileWriter() {
        // 把Customer对象 转成 字符串 输出到文件
        FlatFileItemWriter<Customer> writer = new FlatFileItemWriter<>();
        String path = "D:\\StudyProjects/springbatch/src/main/resources/res.txt";
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

    // 2. xmlWriter
    @Bean
    public StaxEventItemWriter<Customer> xmlWriter() {
        StaxEventItemWriter writer = new StaxEventItemWriter<Customer>();
        XStreamMarshaller marshaller = new XStreamMarshaller();
        // 告诉marshaller 把数据转成什么类型
        Map<String, Class> map = new HashMap<>();
        map.put("customer", Customer.class);
        marshaller.setAliases(map);

        writer.setRootTagName("customers");
        writer.setMarshaller(marshaller);

        String path = "D:\\StudyProjects/springbatch/src/main/resources/res.xml";
        writer.setResource(new FileSystemResource(path));
        try {
            writer.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return writer;

    }

    // (!) 之前的CompositeItemWriter: 调用输出到单个文件操作 来实现输出数据到多个文件
    @Bean
    public CompositeItemWriter<Customer> multiFileItemWriter() {
        CompositeItemWriter<Customer> writer = new CompositeItemWriter<>();
        // 输出到 2个不同的文件中
        writer.setDelegates(Arrays.asList(fileWriter(), xmlWriter()));
        try {
            writer.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return writer;
    }

    // (!) ClassifierCompositeItemWriter: 按照某种条件对数据进行分类存储不同文件
    @Bean
    public ClassifierCompositeItemWriter<Customer> multiFileItemWriter2() {
        ClassifierCompositeItemWriter<Customer> writer = new ClassifierCompositeItemWriter<>();
        writer.setClassifier(new Classifier<Customer, ItemWriter<? super Customer>>() {
            @Override
            public ItemWriter<? super Customer> classify(Customer customer) {
                ItemWriter<Customer> itemWriter =
                        customer.getId() % 2 == 0 ? fileWriter() : xmlWriter();
                return itemWriter;
            }
        });
        return writer;
    }
}
