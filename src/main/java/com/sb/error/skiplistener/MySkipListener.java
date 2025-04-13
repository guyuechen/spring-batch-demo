package com.sb.error.skiplistener;

import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;

@Component
public class MySkipListener implements SkipListener<String, String> {
    @Override
    public void onSkipInRead(Throwable throwable) {

    }

    @Override
    public void onSkipInWrite(String s, Throwable throwable) {

    }

    @Override
    public void onSkipInProcess(String item, Throwable throwable) {
        System.out.println(item + "occurs exception: " + throwable);
    }
}
