package org.example.springboot_2.util;

import org.example.springboot_2.domain.日本动画片;
import org.example.springboot_2.requests.AnimePostRequestBody;

public class AnimePostResquestBodyCreator {
    public static AnimePostRequestBody createAnimePostRequestBody(){
        return AnimePostRequestBody.builder().name(AnimeCreator.createAnimeToBeSaved().getName()).build();
    }

}
