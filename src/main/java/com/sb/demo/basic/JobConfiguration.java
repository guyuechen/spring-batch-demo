package com.sb.demo.basic;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {

    // 注入 创建任务对象的 对象
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    // 注入 创建Step对象的 对象 (任务的执行由若干Step组成)
    // @Autowired
    // private StepBuilderFactory stepBuilderFactory;
    //
    // // 创建 任务对象
    // @Bean
    // public Job helloWorldJob() {
    //     return jobBuilderFactory.get("helloWorldJob")
    //             .start(step1())
    //             .next(step2())
    //             .build();
    // }
    //
    // // Step#1
    // @Bean
    // public Step step1() {
    //     return stepBuilderFactory.get("step1")
    //             .tasklet(new Tasklet() {
    //                 @Override
    //                 public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
    //                     System.out.println("Hello World!");
    //                     return RepeatStatus.FINISHED;
    //                 }
    //             }).build();
    // }
    //
    // // Step#2
    // @Bean
    // public Step step2() {
    //     return stepBuilderFactory.get("step2")
    //             .tasklet(new Tasklet() {
    //                 @Override
    //                 public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
    //                     System.out.println("My first job!");
    //                     return RepeatStatus.FINISHED;
    //                 }
    //             }).build();
    // }
}
