package com.sb.example.address.tasklet;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
@StepScope
@RequiredArgsConstructor
public class CopyAddressMasterTasklet implements Tasklet {

    private final ResourceLoader resourceLoader;

    @Value("#{jobParameters[name]}")
    String name = "world"; // 実行パラメータの初期値も設定可能

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        // 元ファイルを取得
        System.out.println("元ファイルを取得します。");
        File fromFile = resourceLoader.getResource("./13TOKYO.CSV").getFile();

        // コピー先ファイルパス
        Path copyPath = FileSystems.getDefault().getPath("work/" + fromFile.getName());

        // 任意のディレクトリにコピー
        System.out.println("ファイルをコピーしました。");
        Files.copy(fromFile.toPath(), copyPath, StandardCopyOption.REPLACE_EXISTING);

        return RepeatStatus.FINISHED;
    }
}
