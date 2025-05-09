package com.sb.demo.writer.writemulti;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration
// @EnableBatchProcessing
// public class MultiFileItemWriterDemo {
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
//     @Qualifier("multiFileItemWriter2")
//     private ItemWriter<? super Customer> multiFileItemWriter; // (!)
//     @Autowired
//     @Qualifier("fileWriter")
//     private ItemStreamWriter<? extends Customer> jsonWriter; // (!)
//     @Autowired
//     @Qualifier("xmlWriter")
//     private ItemStreamWriter<? extends Customer> xmlWriter; // (!)
//
//     @Bean
//     public Job multiFileItemWriterDemoJob2() {
//         return jobBuilderFactory.get("multiFileItemWriterDemoJob2")
//                 .start(multiFileItemWriterDemoStep())
//                 .build();
//     }
//
//     @Bean
//     public Step multiFileItemWriterDemoStep() {
//         return stepBuilderFactory.get("multiFileItemWriterDemoStep")
//                 .<Customer,Customer>chunk(5)
//                 .reader(dbJdbcReader)
//                 .writer(multiFileItemWriter)
//                 .stream(jsonWriter)
//                 .stream(xmlWriter) // (!)
//                 .build();
//     }
//
// }
