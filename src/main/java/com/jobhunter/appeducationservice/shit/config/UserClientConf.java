package com.jobhunter.appeducationservice.shit.config;

import com.jobhunter.appeducationservice.shit.aop.AuthorizeExecutor;
import com.jobhunter.appeducationservice.shit.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@RequiredArgsConstructor
public class UserClientConf {
    private final LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction;
    //    @Value("${app.user-service}")
    private String url = "http://user-service";

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(url)
                .filter(loadBalancedExchangeFilterFunction)
                .build();
    }

    @Bean
    public UserClient userClient() {
        return HttpServiceProxyFactory
                .builder()
                .exchangeAdapter(WebClientAdapter.create(webClient()))
                .build()
                .createClient(UserClient.class);
    }

    @Bean
    public AuthorizeExecutor authorizeExecutor() {
        return new AuthorizeExecutor(userClient());
    }
}
