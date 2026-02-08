package org.example.springboot_2.service;

import org.example.springboot_2.domain.Anime;
import org.example.springboot_2.repository.AnimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AnimeService {
    private List<Anime> anime = List.of(new Anime(1L, "DBZ"), new Anime(3L, "Berserk"));

    public List<Anime> listAll() {
        return anime;
    }

    public Anime findById(Long id) {
        return anime.stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Anime not found"));
    }
}
