package org.magnum.dataup.config;

import org.magnum.dataup.repository.VideoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VideoRepositoryConfig {

    @Bean(name = "videoRepositorySingleton")
    public VideoRepository videoRepositorySingleton() {
        return new VideoRepository();
    }
}
