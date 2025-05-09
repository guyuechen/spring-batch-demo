package com.sb.demo.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class IdFilterProcessor implements ItemProcessor<Customer, Customer> {
    @Override
    public Customer process(Customer customer) throws Exception {
        if (customer.getId() % 2 == 0) {
            return customer;
        } else {
            return null;
        }
    }
}
