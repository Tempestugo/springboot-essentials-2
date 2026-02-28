package org.example.springboot_2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.springboot_2.domain.日本动画片;
import org.example.springboot_2.requests.AnimePostRequestBody;
import org.example.springboot_2.requests.AnimePutRequestBody;
import org.example.springboot_2.service.动漫服务;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/animes")
@RestController
@Log4j2
@RequiredArgsConstructor
public class AnimeController {
    private final org.example.springboot_2.util.DataUtil dataUtil;
    private final 动漫服务 animeService;

    @GetMapping(path = "ping")
    public String ping() {
        return "pong";
    }

    @GetMapping
    public ResponseEntity<Page<日本动画片>> list(@ParameterObject Pageable pageable) {
        log.info(dataUtil.formatLocalDateTImeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.listAll(pageable));
    }

    @Operation(summary = "List all animes paginated",description = "The default size is 20, use the parameter size to change de default value",
    tags = {"anime"})
    @GetMapping(path = "/all")
    public ResponseEntity<List<日本动画片>> listAll() {
        log.info(dataUtil.formatLocalDateTImeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.listAllNonPageable());
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<日本动画片> findById(@PathVariable long id) {
        log.info(dataUtil.formatLocalDateTImeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "by-id/{id}")
    public ResponseEntity<日本动画片> findByIdAuthenticationPrincipal(@PathVariable long id,
                                                                      @AuthenticationPrincipal UserDetails userDetails) {
        log.info(userDetails);
        return ResponseEntity.ok(animeService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<日本动画片>> findByName(@RequestParam String name) {
        log.info(dataUtil.formatLocalDateTImeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.findByName(name));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<日本动画片> save(@RequestBody @Valid AnimePostRequestBody animePostRequestBody){
        return new ResponseEntity<>(animeService.save(animePostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    @ApiResponses(value ={@ApiResponse(responseCode = "204", description = "Successful Operation"),
    @ApiResponse(responseCode = "400",description = "When Anime is not found")
    })
    public ResponseEntity<Void> delete(@PathVariable long id){
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody AnimePutRequestBody animePutRequestBody){
        animeService.replace(animePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
