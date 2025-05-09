package com.sb.demo.basic;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

// 自定义的 决策器
public class MyDecider implements JobExecutionDecider {

    private int count; // 计数器, 初始为 0

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        count++;
        if (count % 2 == 0) {
            return new FlowExecutionStatus("EVEN偶数");
        } else {
            return new FlowExecutionStatus("ODD奇数");
        }
    }
}
