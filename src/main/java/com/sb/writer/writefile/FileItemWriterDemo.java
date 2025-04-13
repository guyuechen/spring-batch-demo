package com.sb.writer.writefile;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class FileItemWriterDemo {

//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    @Qualifier("dbJdbcReader")
//    private ItemReader<? extends Customer> dbJdbcReader;
//    @Autowired
//    @Qualifier("fileItemWriter")
//    private ItemWriter<? super Customer> fileItemWriter;
//
//    @Bean
//    public Job fileItemWriterDemoJob() {
//        return jobBuilderFactory.get("fileItemWriterDemoJob")
//                .start(fileItemWriterDemoStep())
//                .build();
//    }
//
//    @Bean
//    public Step fileItemWriterDemoStep() {
//        return stepBuilderFactory.get("fileItemWriterDemoStep")
//                .<Customer, Customer>chunk(5)
//                .reader(dbJdbcReader)
//                .writer(fileItemWriter)
//                .build();
//    }
}
