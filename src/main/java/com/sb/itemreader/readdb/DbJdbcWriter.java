package com.sb.itemreader.readdb;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("dbJdbcWriter")
public class DbJdbcWriter implements ItemWriter<User> {
    @Override
    public void write(List<? extends User> list) throws Exception {
        System.out.println("FUCK");
        list.forEach(System.out::println);
    }
}
