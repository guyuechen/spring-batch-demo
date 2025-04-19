package com.sb.demo.reader.read;

import com.sb.common.listener.MyJobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class ItemReaderDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job itemReaderDemoJob() {
        return jobBuilderFactory.get("itemReaderDemoJob")
                .start(itemReaderDemoStep())
                .listener(new MyJobListener())
                .build();
    }

    @Bean
    public Step itemReaderDemoStep() {
        return stepBuilderFactory.get("itemReaderDemoStep")
                .chunk(2) // 每读2个才处理
                .reader(itemReaderDemoRead())
                .writer(list -> {
                    for (Object item : list) {
                        System.out.println(item + "...");
                    }
                }).build();
    }

    @Bean
    public MyReader itemReaderDemoRead() {
        List<String> data = Arrays.asList("鼠", "牛", "虎", "兔");
        return new MyReader(data);
    }

}
