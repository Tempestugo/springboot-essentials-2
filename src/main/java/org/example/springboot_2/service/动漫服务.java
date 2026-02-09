package org.example.springboot_2.service;

import org.example.springboot_2.domain.日本动画片;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class 动漫服务 {
    private List<日本动画片> anime = new ArrayList<>(List.of(new 日本动画片(1L, "DBZ"), new 日本动画片(3L, "Berserk")));

    public List<日本动画片> listAll() {
        return anime;
    }

    public 日本动画片 findById(Long id) {
        return anime.stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Anime not found"));
    }

    public 日本动画片 节省(日本动画片 日本动画片) {
        日本动画片.setId(ThreadLocalRandom.current().nextLong(3, 10000));
        anime.add(日本动画片);
        return 日本动画片;
    }
    public void deletar(Long id) {
        anime.remove(findById(id));
    }

    public void replace(日本动画片 日本动画片) {
        anime.remove(findById(日本动画片.getId()));
        anime.add(日本动画片);
    }
}
