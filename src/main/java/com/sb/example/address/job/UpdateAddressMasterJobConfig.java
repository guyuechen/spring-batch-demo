package com.sb.example.address.job;

import com.sb.common.listener.ChunkListener;
import com.sb.example.address.entity.Address;
import com.sb.example.address.processor.AddressConvertHanToZenProcessor;
import com.sb.example.address.tasklet.CopyAddressMasterTasklet;
import com.sb.example.address.tasklet.DeleteAddressMasterTasklet;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class UpdateAddressMasterJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final CopyAddressMasterTasklet copyAddressMasterTasklet;

    private final DeleteAddressMasterTasklet deleteAddressMasterTasklet;

    private final AddressConvertHanToZenProcessor addressConvertHanToZenProcessor;

    private final FlatFileItemReader<Address> addressFileReader;

    private final FlatFileItemReader<Address> addressFileZenReader;

    private final FlatFileItemWriter<Address> addressFileWriter;

    private final MyBatisBatchItemWriter<Address> addressDbWriter;

    private final ChunkListener chunkListener;

    @Bean
    public Job updateAddressMasterJob() {
        return jobBuilderFactory.get("updateAddressMasterJob")
                .start(copyAddressMaster())
                .listener(chunkListener)
                .build();
    }

    public Step copyAddressMaster() {
        return stepBuilderFactory.get("copyAddressMaster")
                .<Address, Address>chunk(100)
                .reader(addressFileReader)
                .processor(addressConvertHanToZenProcessor)
                .writer(addressDbWriter)
                .allowStartIfComplete(true)
                .build();
    }

    public Step convertKana() {
        return stepBuilderFactory.get("convertKana")
                .<Address, Address>chunk(100)
                .reader(addressFileReader)
                .processor(addressConvertHanToZenProcessor)
                .writer(addressFileWriter)
                .build();
    }

    public Step cleanAddressMaster() {
        return stepBuilderFactory.get("cleanAddressMaster")
                .tasklet(deleteAddressMasterTasklet)
                .build();
    }

    public Step updateAddressMaster() {
        return stepBuilderFactory.get("updateAddressMaster")
                .<Address, Address>chunk(100)
                .reader(addressFileZenReader)
                .writer(addressDbWriter)
                .build();
    }

}
