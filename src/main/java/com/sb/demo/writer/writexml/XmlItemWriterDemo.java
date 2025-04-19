package com.sb.demo.writer.writexml;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class XmlItemWriterDemo {

//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    @Qualifier("dbJdbcReader")
//    private ItemReader<? extends Customer> dbJdbcReader;
//    @Autowired
//    @Qualifier("xmlItemWriter")
//    private ItemWriter<? super Customer> xmlItemWriter;
//
//    @Bean
//    public Job xmlItemWriterDemoJob() {
//        return jobBuilderFactory.get("xmlItemWriterDemoJob")
//                .start(xmlItemWriterDemoStep())
//                .build();
//    }
//
//    @Bean
//    public Step xmlItemWriterDemoStep() {
//        return stepBuilderFactory.get("xmlItemWriterDemoStep")
//                .<Customer, Customer>chunk(5)
//                .reader(dbJdbcReader)
//                .writer(xmlItemWriter)
//                .build();
//    }

}
