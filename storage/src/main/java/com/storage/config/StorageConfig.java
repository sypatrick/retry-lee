package com.storage.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@Configuration
@EnableJpaRepositories(basePackages = "com.storage.repository")
@EntityScan(basePackages = "com.storage.entity")
@EnableJpaAuditing
public class StorageConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
        /**
         * 이 부분은 로그인 API 개발시 수정하도록 하겠습니다.
         */
        return () -> Optional.of("system");
    }
}
