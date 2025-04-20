package com.sb.example.address.config;

import com.sb.example.address.entity.Address;
import com.sb.example.address.mapper.AddressFieldSetMapper;
import com.sb.example.address.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.builder.MyBatisBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@RequiredArgsConstructor
public class UpdateAddressMasterConfig {

    private final SqlSessionFactory sqlSessionFactory;

    private final AddressFieldSetMapper addressFieldSetMapper;

    @Bean
    FlatFileItemReader<Address> addressFileReader() {
        Resource resource = new ClassPathResource("input/13TOKYO.CSV");

        DefaultLineMapper<Address> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_COMMA));
        lineMapper.setFieldSetMapper(addressFieldSetMapper);

        return new FlatFileItemReaderBuilder<Address>().name("addressFileReader").resource(resource)
                .lineMapper(lineMapper).build();
    }

    @Bean
    FlatFileItemReader<Address> addressFileZenReader() {
        Resource resource = new ClassPathResource("input/13TOKYO_zen.CSV");

        DefaultLineMapper<Address> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_COMMA));
        lineMapper.setFieldSetMapper(addressFieldSetMapper);

        return new FlatFileItemReaderBuilder<Address>().name("addressFileZenReader").strict(false).resource(resource)
                .lineMapper(lineMapper).build();
    }

    @Bean
    FlatFileItemWriter<Address> addressFileWriter() {

        BeanWrapperFieldExtractor<Address> extractor = new BeanWrapperFieldExtractor<Address>();
        extractor.setNames(
                new String[] {"jis", "zipcodeOld", "zipcode", "prefectureKana", "cityKana", "townKana", "prefecture",
                        "city", "town", "duplicateTown", "koaza", "hasTyome", "duplicateZipcode", "update", "changeReason"});

        DelimitedLineAggregator<Address> lineAggregator = new DelimitedLineAggregator<Address>();
        lineAggregator.setDelimiter(DelimitedLineTokenizer.DELIMITER_COMMA);
        lineAggregator.setFieldExtractor(extractor);

        Resource writeResource = new ClassPathResource("output/13TOKYO_zen.CSV");

        return new FlatFileItemWriterBuilder<Address>().name("addressFileWriter")
                // 設定しないとbuild()が失敗するため初期値としてResource設定する。インスタンスのsetResource()で上書き可能。
                .resource(writeResource).lineAggregator(lineAggregator).shouldDeleteIfExists(true).build();
    }

    @Bean
    MyBatisBatchItemWriter<Address> addressDbWriter() {
        return new MyBatisBatchItemWriterBuilder<Address>().sqlSessionFactory(sqlSessionFactory)
                .statementId(AddressRepository.class.getName() + ".insertAddress").build();
    }

}
