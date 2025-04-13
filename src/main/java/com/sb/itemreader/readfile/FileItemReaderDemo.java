package com.sb.itemreader.readfile;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.validation.BindException;

@Configuration
@EnableBatchProcessing
public class FileItemReaderDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("fileItemWriter")
    private ItemWriter<? super Customer> fileItemWriter;

    @Bean
    public Job FileItemReaderDemo() {
        return jobBuilderFactory.get("FileItemReaderDemo")
                .start(FileItemReaderDemoStep())
                .build();
    }

    @Bean
    public Step FileItemReaderDemoStep() {
        return stepBuilderFactory.get("FileItemReaderDemoStep")
                .<Customer, Customer>chunk(5)
                .reader(fileItemReader())
                .writer(fileItemWriter)
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Customer> fileItemReader() {
        FlatFileItemReader<Customer> reader = new FlatFileItemReader<Customer>();
        reader.setResource(new ClassPathResource("customer.txt"));
        reader.setLinesToSkip(1); // 跳过第一行

        // 数据解析 使用 DelimitedLineTokenizer （这个类用 "," 作为分隔符）
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[]{"id","firstName","lastName","birthday"});
        // 把解析出的一个数据 映射为 Customer对象
        DefaultLineMapper<Customer> mapper = new DefaultLineMapper<>();
        mapper.setLineTokenizer(tokenizer);
        // 映射
        mapper.setFieldSetMapper(fieldSet -> {
            Customer customer = new Customer();
            customer.setId(fieldSet.readLong("id"));
            customer.setFirstName(fieldSet.readString("firstName"));
            customer.setLastName(fieldSet.readString("lastName"));
            customer.setBirthday(fieldSet.readString("birthday"));
            return customer;
        });

        mapper.afterPropertiesSet();
        reader.setLineMapper(mapper);
        return reader;
    }
}
