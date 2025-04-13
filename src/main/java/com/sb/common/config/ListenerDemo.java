package com.sb.common.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sb.common.listener.MyJobListener;
import com.sb.common.listener.MyChunkListener;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ListenerDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    // @Bean
    // public Job listenerJob() {
    //     return jobBuilderFactory.get("listenerJob")
    //             .start(step1())
    //             .listener(new MyJobListener())
    //             .build();
    // }

    // step1 Chunk 使用方式
    // @Bean
    // public Step step1() {
    //     return stepBuilderFactory.get("step1")
    //             // 数据的读取, <String,String> 规定I/O的数据类型
    //             .<String, String>chunk(2) // 每读完2个数据进行1次输出处理
    //             // 容错
    //             .faultTolerant()
    //             .listener(new MyChunkListener())
    //             // 数据的读取
    //             .reader(read())
    //             // 数据的写入
    //             .writer(write())
    //             .build();
    // }

    @Bean
    public ItemWriter<String> write() {
        return new ItemWriter<String>() {
            @Override
            public void write(List<? extends String> list) throws Exception {
                list.forEach(System.out::println);
            }
        };
    }

    @Bean
    public ItemReader<String> read() {
        return new ListItemReader<>(Arrays.asList("item#1", "item#2", "item#3"));
    }
}
