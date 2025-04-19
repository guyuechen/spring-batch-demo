package com.sb.demo.error.retry;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RetryDemo {

//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    private ItemWriter<String> retryItemWriter;
//    @Autowired
//    private ItemProcessor<String, String> retryItemProcessor;
//
//    @Bean
//    public Job retryDemoJob() {
//        return jobBuilderFactory.get("retryDemoJob")
//                .start(retryDemoStep())
//                .build();
//    }
//
//    @Bean
//    public Step retryDemoStep() {
//        return stepBuilderFactory.get("retryDemoStep")
//                .<String, String>chunk(5)
//                .reader(reader())
//                .processor(retryItemProcessor)
//                .writer(retryItemWriter)
//                // (!)
//                .faultTolerant()
//                .retry(CustomRetryException.class)
//                .retryLimit(5)
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public ListItemReader<String> reader() {
//        List<String> items = new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            items.add("ITEM_" + String.valueOf(i));
//        }
//        ListItemReader<String> reader = new ListItemReader<>(items);
//        return reader;
//    }
}
