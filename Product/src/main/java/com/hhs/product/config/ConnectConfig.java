package com.hhs.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 *
 *  주문 어플리케이션과 통신용 RestTemplate 초기화
 *
 */
@Configuration
public class ConnectConfig {

    @Bean(name = "orderTemplate")
    RestTemplate orderTemplate() {
        HttpComponentsClientHttpRequestFactory tRequestFactory =
                new HttpComponentsClientHttpRequestFactory();
        //연결성공까지 걸리는 시간
        tRequestFactory.setConnectTimeout(30 * 1000);
        //연결후 데이터 읽는데 걸리는 시간
        tRequestFactory.setReadTimeout(50 * 1000);

        return new RestTemplate(tRequestFactory);
    }

}
