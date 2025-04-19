package com.sb.demo.processor;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

// @Configuration
// @EnableBatchProcessing
// public class ItemProcessorDemo {
//
//     @Autowired
//     private JobBuilderFactory jobBuilderFactory;
//     @Autowired
//     private StepBuilderFactory stepBuilderFactory;
//
//     @Autowired
//     @Qualifier("dbJdbcReader")
//     private ItemReader<? extends Customer> dbJdbcReader;
//     @Autowired
//     @Qualifier("fileItemWriter")
//     private ItemWriter<? super Customer> fileItemWriter;
//
//     @Autowired
//     private ItemProcessor<Customer, Customer> firstNameUpperProcessor;
//     @Autowired
//     private ItemProcessor<Customer, Customer> idFilterProcessor;
//
//     @Bean
//     public Job itemProcessorDemoJob() {
//         return jobBuilderFactory.get("itemProcessorDemoJob")
//                 .start(itemProcessorDemoStep())
//                 .build();
//     }
//
//     @Bean
//     public Step itemProcessorDemoStep() {
//         return stepBuilderFactory.get("itemProcessorDemoStep")
//                 .<Customer, Customer>chunk(5)
//                 .reader(dbJdbcReader)
//                 //.processor(firstNameUpperProcessor) // 这样只能有一种处理方式
//                 .processor(process()) // 这样可以有多种处理方式
//                 .writer(fileItemWriter)
//                 .build();
//     }
//
//     @Bean
//     public CompositeItemProcessor<Customer, Customer> process() {
//         CompositeItemProcessor<Customer, Customer> processor = new CompositeItemProcessor<Customer, Customer>();
//         List<ItemProcessor<Customer, Customer>> delegates = new ArrayList<>();
//         delegates.add(firstNameUpperProcessor);
//         delegates.add(idFilterProcessor);
//         processor.setDelegates(delegates);
//         return processor;
//     }
//
// }
