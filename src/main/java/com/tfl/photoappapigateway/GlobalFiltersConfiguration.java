package com.tfl.photoappapigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

@Configuration
public class GlobalFiltersConfiguration {

    final Logger logger = LoggerFactory.getLogger(GlobalFiltersConfiguration.class);

    @Bean
    @Order(1)
    public GlobalFilter secondPreFilter() {
        return (exchange, chain) -> {
            logger.info("Second pre filter is executed ...");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logger.info("Second post filter is executed ...");
            }));
        };
    }

    @Bean
    @Order(2)
    public GlobalFilter thirdPreFilter() {
        return (exchange, chain) -> {
            logger.info("Third pre filter is executed ...");
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                logger.info("Third post filter is executed ...");
            }));
        };
    }
}
