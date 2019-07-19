package com.hlsofttech.quartz;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.hlsofttech.util.Properties;
@Component
@Order(value=1)
public class ApplicationRunnerImpl implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        if("prod".equalsIgnoreCase(Properties.springProfilesActive)) {
        	 myTimer();
        }
    }

    public static void myTimer(){
    }
 
}