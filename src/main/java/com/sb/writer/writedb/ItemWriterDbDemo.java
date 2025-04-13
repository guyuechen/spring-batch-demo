package com.sb.writer.writedb;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class ItemWriterDbDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("itemWriterDb")
    private ItemWriter<? super Customer> itemWriterDb;

    @Autowired
    @Qualifier("fileItemReader")
    private ItemReader<? extends Customer> fileItemReader;

    @Bean
    public Job ItemWriterDbDemoJob() {
        return jobBuilderFactory.get("ItemWriterDbDemoJob")
                .start(ItemWriterDbDemoStep())
                .build();
    }

    @Bean
    public Step ItemWriterDbDemoStep() {
        return stepBuilderFactory.get("ItemWriterDbDemoStep")
                .<Customer, Customer>chunk(5)
                .reader(fileItemReader)
                .writer(itemWriterDb)
                .build();
    }

}
