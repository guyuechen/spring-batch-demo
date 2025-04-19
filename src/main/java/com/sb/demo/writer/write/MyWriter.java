package com.sb.demo.writer.write;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("myWriter")
public class MyWriter implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> list) throws Exception {
        // 输出一批的数量, 即chunk的size参数
        System.out.println("chunk size: " + list.size());
        list.forEach(System.out::println);
    }
}
