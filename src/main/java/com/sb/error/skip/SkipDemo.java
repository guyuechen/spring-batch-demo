package com.sb.error.skip;

import com.sb.error.retry.CustomRetryException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class SkipDemo {

//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    private ItemWriter<String> skipItemWriter;
//    @Autowired
//    private ItemProcessor<String, String> skipItemProcessor;
//
//    @Bean
//    public Job skipDemoJob() {
//        return jobBuilderFactory.get("skipDemoJob")
//                .start(skipDemoStep())
//                .build();
//    }
//
//    @Bean
//    public Step skipDemoStep() {
//        return stepBuilderFactory.get("skipDemoStep")
//                .<String, String>chunk(5)
//                .reader(reader())
//                .processor(skipItemProcessor)
//                .writer(skipItemWriter)
//                // (!)
//                .faultTolerant()
//                .skip(CustomRetryException.class)
//                .skipLimit(5)
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public ListItemReader<String> reader() {
//        List<String> items = new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            items.add("ITEM_" + String.valueOf(i));
//        }
//        ListItemReader<String> reader = new ListItemReader<>(items);
//        return reader;
//    }
}
