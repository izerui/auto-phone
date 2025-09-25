package com.yunjizhizao.autophone;

import com.volcengine.service.acep.ACEPService;
import com.yunjizhizao.autophone.config.PhoneProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(PhoneProperties.class)
public class Application implements CommandLineRunner {

    @Bean
    public ACEPService acepService(PhoneProperties phoneProperties) throws Exception {
        ACEPService instance = ACEPService.getInstance();
        instance.setAccessKey(phoneProperties.getAccessKey());
        instance.setSecretKey(phoneProperties.getSecretKey());
        return instance;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("{}: {}", "启动成功", "http://localhost:8080/swagger-ui.html");
    }
}
