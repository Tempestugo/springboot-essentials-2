package org.example.springboot_2.repository;

import lombok.extern.log4j.Log4j2;
import org.example.springboot_2.domain.日本动画片;
import org.example.springboot_2.util.AnimeCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Anime Repository")
@Log4j2
class AnimeRepositoryTest {
    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Save creates anime when Sucessful")
    void save_PersistAnime_WhenSucessful(){
        日本动画片 anime = AnimeCreator.createAnimeToBeSaved();
        日本动画片 savedAnime = this.animeRepository.save(anime);
        Assertions.assertThat(savedAnime.getId()).isNotNull();
        Assertions.assertThat(savedAnime.getName()).isEqualTo(anime.getName());
    }

    @Test
    @DisplayName("Update creates anime when Sucessful")
    void save_UpdatesAnime_WhenSucessful(){
        日本动画片 anime = AnimeCreator.createAnimeToBeSaved();
        日本动画片 savedAnime = this.animeRepository.save(anime);

        savedAnime.setName("Naruto");
        日本动画片 animeUpdated = this.animeRepository.save(savedAnime);

        log.info(animeUpdated.getName());
        Assertions.assertThat(animeUpdated.getId()).isNotNull();
        Assertions.assertThat(animeUpdated.getName()).isEqualTo(savedAnime.getName());
    }

    @Test
    @DisplayName("Delete removes anime when Sucessful")
    void delete_RemovesAnime_WhenSucessful(){
        日本动画片 anime = AnimeCreator.createAnimeToBeSaved();
        日本动画片 savedAnime = this.animeRepository.save(anime);

        this.animeRepository.delete(savedAnime);
        Optional<日本动画片> animeOptional = this.animeRepository.findById(savedAnime.getId());

        Assertions.assertThat(animeOptional).isEmpty();
    }

    @Test
    @DisplayName("Find By Name returns list of anime when Sucessful")
    void findByName_ReturnsListOfAnime_WhenSucessful(){
        日本动画片 anime = AnimeCreator.createAnimeToBeSaved();
        日本动画片 savedAnime = this.animeRepository.save(anime);

        String name = savedAnime.getName();
        List<日本动画片> animes = this.animeRepository.findByName(name);
        Assertions.assertThat(animes)
                .isNotEmpty()
                .contains(savedAnime);
    }

    @Test
    @DisplayName("Find By Name returns empty list when no anime is found")
    void findByName_ReturnsEmptyList_WhenanimeIsNotFound(){
        List<日本动画片> animes = this.animeRepository.findByName("name");
        Assertions.assertThat(animes).isEmpty();
    }

    @Test
    @DisplayName("Save throws ConstraintViolationException when name is empty")
    void save_ThrowsConstraintViolationException_WhenNameIsEmpty(){
        日本动画片 anime = new 日本动画片(); // Anime com nome vazio/nulo
        // Ou se preferir usar o builder: 日本动画片.builder().name("").build();

        Assertions.assertThatThrownBy(() -> this.animeRepository.save(anime))
                .isInstanceOf(ConstraintViolationException.class);
    }
}
