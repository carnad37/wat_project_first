package com.hhs.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 *
 *
 *
 */
@Configuration
public class ConnectConfig {

    @Bean(name = "productTemplate")
    RestTemplate productTemplate() {
        HttpComponentsClientHttpRequestFactory tRequestFactory =
                new HttpComponentsClientHttpRequestFactory();
        //연결성공까지 걸리는 시간
        tRequestFactory.setConnectTimeout(30 * 1000);
        //연결후 데이터 읽는데 걸리는 시간
        tRequestFactory.setReadTimeout(50 * 1000);

        return new RestTemplate(tRequestFactory);
    }

}
