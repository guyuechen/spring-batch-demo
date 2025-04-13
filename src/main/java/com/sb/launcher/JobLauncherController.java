package com.sb.launcher;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobLauncherController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job jobLauncherDemoJob;

    @RequestMapping("/job/{msg}")
    public String run1(@PathVariable String msg) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        // 把接收到的参数 传给Job
        JobParameters parameters = new JobParametersBuilder()
                .addString("msg", msg)
                .toJobParameters();

        // 启动任务 (任务的参数的传递进来的参数)
        jobLauncher.run(jobLauncherDemoJob, parameters);

        return "Job launched successfully";
    }
}
