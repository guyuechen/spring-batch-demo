package com.sb.error.skip;

import com.sb.error.retry.CustomRetryException;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

//@Component
public class SkipItemProcessor implements ItemProcessor<String, String> {
    private int attemptCount = 0;

    @Override
    public String process(String item) throws Exception {
        System.out.println("processing item: " + item);
        if ("ITEM_13".equals(item)) {
            attemptCount++;
            if (attemptCount >= 3) {
                System.out.println("Retried " + attemptCount + " times and finally succeeded.");
                return "ITEM_" + (Integer.parseInt(item.substring(5)) * -1);
            } else {
                System.out.println("Process failure * " + attemptCount + ".");
                throw new CustomRetryException("Process failed and attempts: " + attemptCount);
            }
        } else {
            return "ITEM_" + (Integer.parseInt(item.substring(5)) * -1);
        }
    }
}
