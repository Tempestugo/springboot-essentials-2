package org.example.springboot_2.repository;

import org.example.springboot_2.domain.日本动画片;

import java.util.List;

public interface AnimeRepository {
    public List<日本动画片> listAll();
}
