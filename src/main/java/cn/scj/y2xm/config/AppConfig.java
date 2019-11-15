package cn.scj.y2xm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author by Shaochenjie
 * @Classname AppConfig
 * @Description TODO
 * @Date 2019/11/15 11:35
 */
@Configuration
public class AppConfig  {
    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }
}
