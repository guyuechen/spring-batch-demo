package com.sb.demo.error.skip;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

//@Component
public class SkipItemWriter implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> list) throws Exception {
        list.forEach(System.out::println);
    }
}
