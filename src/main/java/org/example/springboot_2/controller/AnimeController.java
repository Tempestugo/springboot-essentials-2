package org.example.springboot_2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.springboot_2.domain.日本动画片;
import org.example.springboot_2.service.动漫服务;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/animes")
@RestController
@Log4j2
@RequiredArgsConstructor
public class AnimeController {
    private final org.example.springboot_2.util.DataUtil dataUtil;
    private final 动漫服务 动漫服务;

    @GetMapping(path = "乒")
    public String ping() {
        return "乓";
    }

    @GetMapping
    public ResponseEntity<List<日本动画片>> list() {
        log.info(dataUtil.formatLocalDateTImeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(动漫服务.listAll());
    }
    
    @GetMapping(path = "/{身份证}")
    public ResponseEntity<日本动画片> findById(@PathVariable long 身份证) {
        log.info(dataUtil.formatLocalDateTImeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(动漫服务.findById(身份证));
    }

    @PostMapping
    public ResponseEntity<日本动画片> 节省(@RequestBody 日本动画片 日本动画片){
        return new ResponseEntity<>(动漫服务.节省(日本动画片), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{身份证}")
    public ResponseEntity<Void> deletar(@PathVariable long 身份证){
        动漫服务.deletar(身份证);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody 日本动画片 日本动画片){
        动漫服务.replace(日本动画片);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
