package org.magnum.dataup.config;

import org.magnum.dataup.utils.IdCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CalculatorConfig {

    @Bean(name = "IdCalculatorSingleton")
    @Scope("singleton")
    public IdCalculator idCalculatorSingleton() {
        return new IdCalculator();
    }
}
