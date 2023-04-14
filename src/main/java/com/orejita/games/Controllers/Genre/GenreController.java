package com.orejita.games.Controllers.Genre;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orejita.games.Controllers.BaseController;
import com.orejita.games.DTO.Genre.GenreDto;
import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.DTO.Requests.OnUpdate;
import com.orejita.games.Entities.Genre.Genre;
import com.orejita.games.Services.Interfaces.IGenreService;

@RestController
@Validated
@RequestMapping("/genre")
public class GenreController extends BaseController<Genre, GenreDto> {

    @Autowired
    private IGenreService service;

    @GetMapping
    public List<GenreDto> getAllGenres() {
        List<Genre> genres = service.getAllGenres();
        return genres.stream()
                .map(this::convertToDto)
                .toList();
    }

    @GetMapping("/game/{id}")
    public List<GenreDto> getAllGenresByGameId(@PathVariable("id") long id) {
        List<Genre> genres = service.getAllGenresByGame(id);
        return genres.stream()
                .map(this::convertToDto)
                .toList();
    }

    @GetMapping("/{id}")
    public GenreDto getGenre(@PathVariable("id") long id) {
        Genre genre = service.getGenre(id);
        return convertToDto(genre);
    }

    @PostMapping
    public boolean createGenre(@Validated(OnCreate.class) @RequestBody GenreDto genre) {
        Genre _genre = convertToEntity(genre);
        return service.createGenre(_genre);
    }

    @PutMapping("/{id}")
    public boolean updateGenre(@PathVariable("id") long id, @Validated(OnUpdate.class) @RequestBody GenreDto genre) {
        Genre _genre = convertToEntity(genre);
        return service.updateGenre(id, _genre);
    }

    @PutMapping("/{id}/add-game/{gameId}")
    public boolean addGameToGenre(@PathVariable("id") long id, @PathVariable("gameId") long gameId) {
        return service.addGame(id, gameId);
    }


    @DeleteMapping("/{id}")
    public boolean deleteGenre(@PathVariable("id") long id) {
        return service.deleteGenre(id);
    }

}
