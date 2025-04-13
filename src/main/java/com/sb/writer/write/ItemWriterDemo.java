package com.sb.writer.write;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class ItemWriterDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    @Qualifier("myWriter")
    private ItemWriter<? super String> myWriter;

    @Bean
    public Job ItemWriterDemoJob() {
        return jobBuilderFactory.get("ItemWriterDemoJob")
                .start(ItemWriterDemoStep())
                .build();
    }

    @Bean
    public Step ItemWriterDemoStep() {
        return stepBuilderFactory.get("ItemWriterDemoStep")
                .<String, String>chunk(5)
                .reader(myReader())
                .writer(myWriter).build();
    }

    @Bean
    public ItemReader<String> myReader() {
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            items.add("ITEM_" + i);
        }
        return new ListItemReader<String>(items);
    }

}
