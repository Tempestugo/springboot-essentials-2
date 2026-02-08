package academy.devodojo.springboot2.controller;

import academy.devodojo.springboot2.domain.Anime;
import academy.devodojo.springboot2.util.DataUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
@RequestMapping("/animes")
@RestController
@Log4j2
//@AllArgsConstructor
@RequiredArgsConstructor
public class AnimeController {
    private DataUtil dataUtil;

    @RequestMapping(path = "ping")


    @GetMapping(path = "ping")
    public String ping() {
        return "pong";
    }

    @GetMapping(path = "list")
    public List<Anime> list() {
        log.info(dataUtil.formatLocalDateTImeToDatabaseStyle(LocalDateTime.now()));
        return List.of(new Anime("DBZ"), new Anime("Berserk"));
    }

}
