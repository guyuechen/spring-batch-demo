package com.sb.demo.basic;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChildJobTwo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step childJob2Step1() {
        return stepBuilderFactory.get("childJob2Step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("This is step#1 in Job#2!");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step childJob2Step2() {
        return stepBuilderFactory.get("childJob2Step2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("This is step#2 in Job#2!");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    // @Bean
    // public Job childJob2() {
    //     return jobBuilderFactory.get("childJob2")
    //             .start(childJob2Step1())
    //             .next(childJob2Step2())
    //             .build();
    // }

}
