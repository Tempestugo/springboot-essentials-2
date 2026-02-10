package org.example.springboot_2.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot_2.domain.日本动画片;
import org.example.springboot_2.mapper.AnimeMapper;
import org.example.springboot_2.repository.AnimeRepository;
import org.example.springboot_2.requests.AnimePostRequestBody;
import org.example.springboot_2.requests.AnimePutRequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class 动漫服务 {

    private final AnimeRepository animeRepository;
    private final AnimeMapper animeMapper;

    public List<日本动画片> listAll() {
        return animeRepository.findAll();
    }

    public List<日本动画片> findByName(String name) {
        return animeRepository.findByName(name);
    }

    public 日本动画片 findByIdOrThrowBadRequestException(long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not Found"));
    }


//    @Transactional(rollbackFor = Exception.class)
    @Transactional
    public 日本动画片 save(AnimePostRequestBody animePostRequestBody) {
        return animeRepository.save(animeMapper.toAnime(animePostRequestBody));
    }

    public void delete(long id) {
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        日本动画片 savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        日本动画片 anime = animeMapper.toAnime(animePutRequestBody);
        anime.setId(savedAnime.getId());
        animeRepository.save(anime);
    }
}
