package com.sb.demo.reader.restart;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("restartWriter")
public class RestartWriter implements ItemWriter<Customer> {
    @Override
    public void write(List<? extends Customer> list) throws Exception {
        list.forEach(System.out::println);
    }
}
