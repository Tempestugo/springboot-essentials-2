package org.example.springboot_2.util;

import org.example.springboot_2.domain.日本动画片;

public class AnimeCreator {
    public static 日本动画片 createAnimeToBeSaved(){
        return 日本动画片.builder()
                .name("Hajime no Ito")
                .build();
    }
    public static 日本动画片 createValidAnime(){
        return 日本动画片.builder()
                .name("Hajime no Ito")
                .id(1L)
                .build();
    }
    public static 日本动画片 createValidUpdatedAnime(){
        return 日本动画片.builder()
                .name("Hajime no Ito")
                .id(1L)
                .build();
    }

}
