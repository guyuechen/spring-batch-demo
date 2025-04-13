package com.sb.joblauncher;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobInstanceAlreadyExistsException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("job2")
public class JobOperatorController {

    @Autowired
    private JobOperator jobOperator;

    @GetMapping("/{msg}")
    public String run2(@PathVariable String msg) throws JobInstanceAlreadyExistsException, NoSuchJobException, JobParametersInvalidException {
        // 启动任务: 传入任务名(而不是对象) 参数是键值对的形式
        jobOperator.start("jobOperatorDemoJob", "msg=" + msg);
        return "Job operated successfully";
    }
}
