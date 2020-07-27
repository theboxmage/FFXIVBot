package com.boxfort.demo.Configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("discordbot")
public class DiscordBotConfiguration {
    private String token;
    private String prefix;
    private String ffxivapikey;
}
