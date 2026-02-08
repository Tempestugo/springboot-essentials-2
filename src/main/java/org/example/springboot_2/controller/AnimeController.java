package org.example.springboot_2.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.springboot_2.domain.Anime;
import org.example.springboot_2.service.AnimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
@RequestMapping("/animes")
@RestController
@Log4j2
//@AllArgsConstructor
@RequiredArgsConstructor
public class AnimeController {
    private final org.example.springboot_2.util.DataUtil dataUtil;
    private final AnimeService animeService;

    @GetMapping(path = "ping")
    public String ping() {
        return "pong";
    }

    @GetMapping
    public ResponseEntity<List<Anime>> list() {
        log.info(dataUtil.formatLocalDateTImeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.listAll());
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> findById(@PathVariable long id) {
        log.info(dataUtil.formatLocalDateTImeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.findById(id));
    }

}
