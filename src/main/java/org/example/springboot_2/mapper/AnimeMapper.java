package org.example.springboot_2.mapper;

import org.example.springboot_2.domain.日本动画片;
import org.example.springboot_2.requests.AnimePostRequestBody;
import org.example.springboot_2.requests.AnimePutRequestBody;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class AnimeMapper {
    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "url", ignore = true)
    public abstract 日本动画片 toAnime(AnimePostRequestBody animePostRequestBody);

    @Mapping(target = "url", ignore = true)
    public abstract 日本动画片 toAnime(AnimePutRequestBody animePutRequestBody);
}
