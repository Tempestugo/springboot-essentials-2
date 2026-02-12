package org.example.springboot_2.client;

import lombok.extern.log4j.Log4j2;
import org.example.springboot_2.domain.日本动画片;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<日本动画片> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/3", 日本动画片.class);
        log.info(entity);

        日本动画片[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/all", 日本动画片[].class);
        log.info(Arrays.toString(animes));

        ResponseEntity<List<日本动画片>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<日本动画片>>() {});
        log.info(exchange.getBody());

        日本动画片 kingdom = 日本动画片.builder().name("kingdom").build();
        日本动画片 kingdomSaved = new RestTemplate().postForObject("http://localhost:8080/animes", kingdom, 日本动画片.class);
        log.info("saved anime {}", kingdomSaved);

        日本动画片 kingdom2 = 日本动画片.builder().name("kingdom2").build();
        ResponseEntity<日本动画片> kingdomSaved2 = new RestTemplate().exchange("http://localhost:8080/animes",
                HttpMethod.POST,
                new HttpEntity<>(kingdom2, createJsonHeader()),
                日本动画片.class);
        log.info("saved anime {}", kingdomSaved2);

        日本动画片 animeToUpdate = kingdomSaved2.getBody();
        animeToUpdate.setName("kingdom 2 updated");

        ResponseEntity<Void> kingdomUpdated = new RestTemplate().exchange("http://localhost:8080/animes",
                HttpMethod.PUT,
                new HttpEntity<>(animeToUpdate, createJsonHeader()),
                Void.class);
        log.info(kingdomUpdated);

        ResponseEntity<Void> kingdomDeleted = new RestTemplate().exchange("http://localhost:8080/animes/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                animeToUpdate.getId());
        log.info(kingdomDeleted);
    }

    private static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
