package com.sb.common.config;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ParametersDemo implements StepExecutionListener {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    private Map<String, JobParameter> params;

    // @Bean
    // public Job parameterJob() {
    //     return jobBuilderFactory.get("parameterJob")
    //             .start(paramStep())
    //             //.listener(new MyJobListener())
    //             .build();
    // }

    // Job执行的是 Step, 故 Job使用的参数本质就是 Step使用的参数
    // 那么如何 向Step 传递参数呢?
    // 可以使用 监听, 如 Step级别的监听来实现
    @Bean
    public Step paramStep() {
        return stepBuilderFactory.get("paramStep")
                .listener(this)
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        // 打印 接收到的参数
                        System.out.println(params.get("key1"));
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        params = stepExecution.getJobParameters().getParameters();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
