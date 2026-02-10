package org.example.springboot_2.repository;

import org.example.springboot_2.domain.日本动画片;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimeRepository extends JpaRepository<日本动画片, Long> {

}
