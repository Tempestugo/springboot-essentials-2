package org.example.springboot_2.repository;

import org.example.springboot_2.domain.Anime;

import java.util.List;

public interface AnimeRepository {
    public List<Anime> listAll();
}
