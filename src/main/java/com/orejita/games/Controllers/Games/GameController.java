package com.orejita.games.Controllers.Games;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.orejita.games.DTO.Games.GameDto;
import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.DTO.Requests.OnUpdate;
import com.orejita.games.Entities.Games.Game;
import com.orejita.games.Services.Interfaces.IGameService;

@Controller
@RequestMapping("/game")
@Validated
public class GameController {
    
    @Autowired
    private IGameService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseBody
    public List<GameDto> getAllGames() {
        List<Game> games = service.getAllGames();
        return games.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/console/{id}")
    @ResponseBody
    public List<GameDto> getAllConsoleGames(@PathVariable("id") long id) {
        List<Game> games = service.getAllGamesByConsole(id);
        return games.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    } 

    @GetMapping("/manufacturer/{id}")
    @ResponseBody
    public List<GameDto> getAllManufacturerGames(@PathVariable("id") long id) {
        List<Game> games = service.getAllGamesByManufacturer(id);
        return games.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public GameDto getGame(@PathVariable("id") long id) {
        Game game = service.getOneGame(id);
        return convertToDto(game);
    }

    @GetMapping("/name/{name}")
    @ResponseBody
    public GameDto getGameByName(@PathVariable("name") String name) {
        Game game = service.getGameByName(name);
        return convertToDto(game);
    }

    @GetMapping("/slug/{slug}")
    @ResponseBody
    public GameDto getGameBySlug(@PathVariable("slug") String slug) {
        Game game = service.getGameBySlug(slug);
        return convertToDto(game);
    }

    @PostMapping("/console/{consoleId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public GameDto createGame(@Validated(OnCreate.class) @RequestBody GameDto game, @PathVariable("consoleId") long consoleId) throws ParseException {
        Game gameEntity = convertToEntity(game);
        Game _game = service.createGame(consoleId, gameEntity);
        return convertToDto(service.getOneGame(_game.getId()));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public GameDto updateGame(@PathVariable("id") long id, @Validated(OnUpdate.class) @RequestBody Game game) {
        Game _game = service.updateGame(id, game);
        return convertToDto(_game);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable("id") long id) {
        service.deleteGame(id);
    }

    private GameDto convertToDto(Game game) {
        GameDto gameDto = modelMapper.map(game, GameDto.class);
        return gameDto;
    }

    private Game convertToEntity(GameDto gameDto) throws ParseException {
        Game game = modelMapper.map(gameDto, Game.class);
        return game;
    }

}
