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
import com.orejita.games.DTO.User.CollectionDto;
import com.orejita.games.Entities.User.Collection;
import com.orejita.games.Services.Interfaces.ICollectionService;


@RestController
@Validated
@RequestMapping("/collection")
public class CollectionController extends BaseController<Collection, CollectionDto> {

    @Autowired
    private ICollectionService service;

    @GetMapping
    public List<CollectionDto> getAllCollections() {
        List<Collection> collections = service.getAllCollections();
        return collections.stream()
                .map(this::convertToDto)
                .toList();
    }

    @GetMapping("/{id}")
    public CollectionDto getCollection(@PathVariable("id") long id) {
        Collection collection = service.getOneCollection(id);
        return convertToDto(collection);
    }

    @GetMapping("/user/{id}")
    public CollectionDto getCollectionByUser(@PathVariable("id") long id) {
        Collection collection = service.getCollectionByUserId(id);
        return convertToDto(collection);
    }

    @GetMapping("/username/{username:.+}")
    public CollectionDto getCollectionByUsername(@PathVariable("username") String username) {
        Collection collection = service.getCollectionByUserUsername(username);
        return convertToDto(collection);
    }

    @PostMapping("/user/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public CollectionDto createCollection(@PathVariable("id") long id, @Validated(OnCreate.class) @RequestBody CollectionDto collection) {
        Collection collectionEntity = this.convertToEntity(collection);
        Collection _collection = service.createCollection(id, collectionEntity);
        return convertToDto(_collection);
    }

    @PutMapping("/{id}")
    public CollectionDto updateCollection(@PathVariable("id") long id, @Validated(OnUpdate.class) @RequestBody CollectionDto collection) {
        Collection collectionEntity = this.convertToEntity(collection);
        Collection _collection = service.createCollection(id, collectionEntity);
        return convertToDto(_collection);
    }

    @PutMapping("/{id}/game/{gameId}")
    public CollectionDto addGame(@PathVariable("id") long id, @PathVariable("gameId") long gameId) {
        Collection collection = service.addGameToCollection(id, gameId);
        return convertToDto(collection);
    }

    @PutMapping("/user/{id}/game/{gameId}")
    public CollectionDto addGameByUser(@PathVariable("id") long id, @PathVariable("gameId") long gameId) {
        Collection collection = service.addGameToCollectionByUser(id, gameId);
        return convertToDto(collection);
    }

    @PutMapping("/{id}/console/{consoleId}")
    public CollectionDto addConsole(@PathVariable("id") long id, @PathVariable("consoleId") long consoleId) {
        Collection collection = service.addConsoleToCollection(id, consoleId);
        return convertToDto(collection);
    }

    @PutMapping("/user/{id}/console/{consoleId}")
    public CollectionDto addConsoleByUser(@PathVariable("id") long id, @PathVariable("consoleId") long consoleId) {
        Collection collection = service.addConsoleToCollectionByUser(id, consoleId);
        return convertToDto(collection);
    }

    @DeleteMapping("/{id}/game/{gameId}")
    public CollectionDto deleteGame(@PathVariable("id") long id, @PathVariable("gameId") long gameId) {
        Collection collection = service.deleteGameFromCollection(id, gameId);
        return convertToDto(collection);
    }

    @DeleteMapping("/user/{id}/game/{gameId}")
    public CollectionDto deleteGameByUser(@PathVariable("id") long id, @PathVariable("gameId") long gameId) {
        Collection collection = service.deleteGameFromCollectionByUser(id, gameId);
        return convertToDto(collection);
    }

    @DeleteMapping("/{id}/console/{consoleId}")
    public CollectionDto deleteConsole(@PathVariable("id") long id, @PathVariable("consoleId") long consoleId) {
        Collection collection = service.deleteConsoleFromCollection(id, consoleId);
        return convertToDto(collection);
    }

    @DeleteMapping("/user/{id}/console/{consoleId}")
    public CollectionDto deleteConsoleByUser(@PathVariable("id") long id, @PathVariable("consoleId") long consoleId) {
        Collection collection = service.deleteConsoleFromCollectionByUser(id, consoleId);
        return convertToDto(collection);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCollection(@PathVariable("id") long id) {
        service.deleteCollection(id);
    }
    
}
