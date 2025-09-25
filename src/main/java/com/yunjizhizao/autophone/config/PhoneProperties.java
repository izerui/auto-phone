package com.yunjizhizao.autophone.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "volc-sdk")
@Data
public class PhoneProperties {

    private String accessKey;

    private String secretKey;
}
