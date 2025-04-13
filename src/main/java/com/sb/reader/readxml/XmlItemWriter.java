package com.sb.reader.readxml;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("xmlItemWriter")
public class XmlItemWriter implements ItemWriter<Customer> {
    @Override
    public void write(List<? extends Customer> list) throws Exception {
        System.out.println("--each chunk--");
        list.forEach(System.out::println);
    }
}
