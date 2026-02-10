package org.example.springboot_2.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot_2.domain.日本动画片;
import org.example.springboot_2.repository.AnimeRepository;
import org.example.springboot_2.requests.AnimePostRequestBody;
import org.example.springboot_2.requests.AnimePutRequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public List<日本动画片> listAll() {
        return animeRepository.findAll();
    }

    public 日本动画片 findByIdOrThrowBadRequestException(long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not Found"));
    }

    public 日本动画片 save(AnimePostRequestBody animePostRequestBody) {
        return animeRepository.save(日本动画片.builder()
                .name(animePostRequestBody.getName())
                .build());
    }

    public void delete(long id) {
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        日本动画片 savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        日本动画片 anime = 日本动画片.builder()
                .id(savedAnime.getId())
                .name(animePutRequestBody.getName())
                .build();

        animeRepository.save(anime);
    }
}
