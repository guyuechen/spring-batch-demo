package com.sb.demo.error.skiplistener;

import com.sb.demo.error.retry.CustomRetryException;
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
public class SkipListenerDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ItemWriter<String> skipItemWriter;
    @Autowired
    private ItemProcessor<String, String> skipItemProcessor;

    @Autowired
    private MySkipListener mySkipListener;

    // @Bean
    // public Job skipListenerDemoJob() {
    //     return jobBuilderFactory.get("skipListenerDemoJob")
    //             .start(skipListenerDemoStep())
    //             .build();
    // }

    @Bean
    public Step skipListenerDemoStep() {
        return stepBuilderFactory.get("skipListenerDemoStep")
                .<String, String>chunk(5)
                .reader(reader())
                .processor(skipItemProcessor)
                .writer(skipItemWriter)
                // (!)
                .faultTolerant()
                .skip(CustomRetryException.class)
                .skipLimit(5)
                // (!)
                .listener(mySkipListener)
                .build();
    }

    @Bean
    @StepScope
    public ListItemReader<String> reader() {
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            items.add("ITEM_" + String.valueOf(i));
        }
        ListItemReader<String> reader = new ListItemReader<>(items);
        return reader;
    }
}
