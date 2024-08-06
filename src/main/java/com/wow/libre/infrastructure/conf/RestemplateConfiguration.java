package com.wow.libre.infrastructure.conf;

import com.wow.libre.infrastructure.filter.RestTemplateHeaderModifierInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestemplateConfiguration {

    @Value("${restemplate.config.connect-timeout:60}")
    private Integer connectTimeout;
    @Value("${restemplate.config.read-timeout:30}")
    private Integer readTimeout;


    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = createRestTemplate(connectTimeout, readTimeout);
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(new RestTemplateHeaderModifierInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    private RestTemplate createRestTemplate(final int connectTimeout, final int readTimeout) {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        return restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(connectTimeout))
                .setReadTimeout(Duration.ofSeconds(readTimeout)).build();
    }

}
