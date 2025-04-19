package com.sb.demo.basic;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class DeciderDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step deciderDemoStep1() {
        return stepBuilderFactory.get("deciderDemoStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("This is step#1!");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step deciderDemoStep2() {
        return stepBuilderFactory.get("deciderDemoStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("This is step#2");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step deciderDemoStep3() {
        return stepBuilderFactory.get("deciderDemoStep3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("This is step#3");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    // 把自定义的 MyDecider 注册到容器中
    @Bean
    public JobExecutionDecider myDecider(){
        return new MyDecider();
    }

    // 创建任务: 会按照 step#1 -> myDecider -> step#3 -> myDecider -> step#2 的顺序执行
    // @Bean
    // public Job deciderDemoJob(){
    //     return jobBuilderFactory.get("deciderDemoJob")
    //             .start(deciderDemoStep1())
    //             .next(myDecider())
    //             .from(myDecider()).on("EVEN偶数").to(deciderDemoStep2())
    //             .from(myDecider()).on("ODD奇数").to(deciderDemoStep3())
    //             .from(deciderDemoStep3()).on("*").to(myDecider()) // 无论返回什么都回到决策器 ↑next(myDecider())
    //             .end()
    //             .build();
    // }

}


