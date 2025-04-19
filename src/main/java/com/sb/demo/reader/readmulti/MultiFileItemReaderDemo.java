package com.sb.demo.reader.readmulti;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
public class MultiFileItemReaderDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("multiFileWriter")
    private ItemWriter<? super Customer> multiFileWriter;

    @Value("classpath:/file*.txt")
    private Resource[] fileResources;

    @Bean
    public Job multiFileItemReaderDemoJob() {
        return jobBuilderFactory.get("multiFileItemReaderDemoJob")
                .start(multiFileItemReaderDemoStep())
                .build();
    }

    @Bean
    public Step multiFileItemReaderDemoStep() {
        return stepBuilderFactory.get("multiFileItemReaderDemoStep")
                .<Customer,Customer>chunk(5)
                .reader(multiFileReader())
                .writer(multiFileWriter)
                .build();
    }

    // multiFileReader 虽说是多文件读取 但其实是逐个读取单个文件
    @Bean
    @StepScope
    public MultiResourceItemReader<Customer> multiFileReader() {
        MultiResourceItemReader <Customer> reader = new MultiResourceItemReader<>();
        reader.setDelegate(fileItemReaderDemoReader());
        reader.setResources(fileResources);
        return reader;
    }

    // 单个文件读取
    @Bean
    public FlatFileItemReader<Customer> fileItemReaderDemoReader() {
        FlatFileItemReader<Customer> reader = new FlatFileItemReader<Customer>();
        reader.setResource(new ClassPathResource("file1.txt"));

        // 数据解析
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[]{"id","firstName","lastName","birthday"});
        // 把解析出的一个数据映射为 Customer对象
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
