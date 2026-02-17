package org.example.springboot_2.util;

import org.example.springboot_2.requests.AnimePutRequestBody;

public class AnimePutResquestBodyCreator {
    public static AnimePutRequestBody createAnimePutRequestBody(){
        return AnimePutRequestBody.builder()
                .id(AnimeCreator.createValidAnime().getId())
                .name(AnimeCreator.createValidUpdatedAnime().getName())
                .build();
    }

}

