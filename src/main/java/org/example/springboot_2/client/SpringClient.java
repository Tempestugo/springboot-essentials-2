package org.example.springboot_2.client;

import org.example.springboot_2.domain.日本动画片;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<日本动画片> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/3", 日本动画片.class);
        log.info(entity);
        日本动画片 object = new RestTemplate().getForObject("http://localhost:8080/animes/3", 日本动画片.class);
        log.info(object);
    }
}
