package com.orejita.games.Controllers.User;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.orejita.games.Controllers.BaseController;
import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.DTO.Requests.OnUpdate;
import com.orejita.games.DTO.User.UserGameStaticDto;
import com.orejita.games.Entities.User.UserGameStatic;
import com.orejita.games.Services.Interfaces.IUserGameStaticService;

@RestController
@Validated
@RequestMapping("/game-static")
public class UserGameStaticController extends BaseController<UserGameStatic, UserGameStaticDto> {
    
    @Autowired
    private IUserGameStaticService service;

    @GetMapping("/user/{id}")
    public List<UserGameStaticDto> getAllGameStaticsByUser(@PathVariable("id") long id) {
        List<UserGameStatic> gameStatics = service.getAllUserGameStatics(id);
        return gameStatics.stream()
                .map(this::convertToDto)
                .toList();
    }

    @GetMapping("/game/{id}")
    public List<UserGameStaticDto> getAllGameStaticsByGame(@PathVariable("id") long id) {
        List<UserGameStatic> gameStatics = service.getAllUserGameStaticsByGame(id);
        return gameStatics.stream()
                .map(this::convertToDto)
                .toList();
    }

    @PostMapping("/user/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserGameStaticDto createGameStatic(@PathVariable("id") long id, @Validated(OnCreate.class) @RequestBody UserGameStaticDto gameStatic) {
        UserGameStatic entity = this.convertToEntity(gameStatic);
        UserGameStatic _gameStatic = service.createUserGameStatic(id, entity);
        return convertToDto(_gameStatic);
    }

    @PutMapping("/{id}")
    public UserGameStaticDto updateGameStatic(@PathVariable("id") long id, @Validated(OnUpdate.class) @RequestBody UserGameStaticDto gameStatic) {
        UserGameStatic entity = this.convertToEntity(gameStatic);
        UserGameStatic _gameStatic = service.createUserGameStatic(id, entity);
        return convertToDto(_gameStatic);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGameStatic(@PathVariable("id") long id) {
        service.deleteUserGameStatic(id);
    }

}
