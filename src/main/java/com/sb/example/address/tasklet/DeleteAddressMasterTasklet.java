package com.sb.example.address.tasklet;

import com.sb.example.address.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@StepScope
@RequiredArgsConstructor
public class DeleteAddressMasterTasklet implements Tasklet {

    private final AddressRepository addressRepository;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        addressRepository.deleteAll();
        return RepeatStatus.FINISHED;
    }

}
