package com.ontop.bank.application.config;

import com.ontop.bank.service.fee.FeeServiceImpl;
import com.ontop.bank.service.fee.FeeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public FeeService feeService() {
        return new FeeServiceImpl();
    }

}
