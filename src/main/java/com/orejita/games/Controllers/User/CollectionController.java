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

import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.DTO.Requests.OnUpdate;
import com.orejita.games.DTO.User.CollectionDto;
import com.orejita.games.Entities.User.Collection;
import com.orejita.games.Services.Interfaces.ICollectionService;


@Controller
@Validated
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private ICollectionService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseBody
    public List<CollectionDto> getAllCollections() {
        List<Collection> collections = service.getAllCollections();
        return collections.stream()
                .map(this::convertToDto)
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CollectionDto getCollection(@PathVariable("id") long id) {
        Collection collection = service.getOneCollection(id);
        return convertToDto(collection);
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public CollectionDto getCollectionByUser(@PathVariable("id") long id) {
        Collection collection = service.getCollectionByUserId(id);
        return convertToDto(collection);
    }

    @GetMapping("/username/{username:.+}")
    @ResponseBody
    public CollectionDto getCollectionByUsername(@PathVariable("username") String username) {
        Collection collection = service.getCollectionByUserUsername(username);
        return convertToDto(collection);
    }

    @PostMapping("/user/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public CollectionDto createCollection(@PathVariable("id") long id, @Validated(OnCreate.class) @RequestBody CollectionDto collection) {
        Collection collectionEntity = this.convertToEntity(collection);
        Collection _collection = service.createCollection(id, collectionEntity);
        return convertToDto(_collection);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public CollectionDto updateCollection(@PathVariable("id") long id, @Validated(OnUpdate.class) @RequestBody CollectionDto collection) {
        Collection collectionEntity = this.convertToEntity(collection);
        Collection _collection = service.createCollection(id, collectionEntity);
        return convertToDto(_collection);
    }

    @PutMapping("/{id}/game/{gameId}")
    @ResponseBody
    public CollectionDto addGame(@PathVariable("id") long id, @PathVariable("gameId") long gameId) {
        Collection collection = service.addGameToCollection(id, gameId);
        return convertToDto(collection);
    }

    @PutMapping("/user/{id}/game/{gameId}")
    @ResponseBody
    public CollectionDto addGameByUser(@PathVariable("id") long id, @PathVariable("gameId") long gameId) {
        Collection collection = service.addGameToCollectionByUser(id, gameId);
        return convertToDto(collection);
    }

    @PutMapping("/{id}/console/{consoleId}")
    @ResponseBody
    public CollectionDto addConsole(@PathVariable("id") long id, @PathVariable("consoleId") long consoleId) {
        Collection collection = service.addConsoleToCollection(id, consoleId);
        return convertToDto(collection);
    }

    @PutMapping("/user/{id}/console/{consoleId}")
    @ResponseBody
    public CollectionDto addConsoleByUser(@PathVariable("id") long id, @PathVariable("consoleId") long consoleId) {
        Collection collection = service.addConsoleToCollectionByUser(id, consoleId);
        return convertToDto(collection);
    }

    @DeleteMapping("/{id}/game/{gameId}")
    @ResponseBody
    public CollectionDto deleteGame(@PathVariable("id") long id, @PathVariable("gameId") long gameId) {
        Collection collection = service.deleteGameFromCollection(id, gameId);
        return convertToDto(collection);
    }

    @DeleteMapping("/user/{id}/game/{gameId}")
    @ResponseBody
    public CollectionDto deleteGameByUser(@PathVariable("id") long id, @PathVariable("gameId") long gameId) {
        Collection collection = service.deleteGameFromCollectionByUser(id, gameId);
        return convertToDto(collection);
    }

    @DeleteMapping("/{id}/console/{consoleId}")
    @ResponseBody
    public CollectionDto deleteConsole(@PathVariable("id") long id, @PathVariable("consoleId") long consoleId) {
        Collection collection = service.deleteConsoleFromCollection(id, consoleId);
        return convertToDto(collection);
    }

    @DeleteMapping("/user/{id}/console/{consoleId}")
    @ResponseBody
    public CollectionDto deleteConsoleByUser(@PathVariable("id") long id, @PathVariable("consoleId") long consoleId) {
        Collection collection = service.deleteConsoleFromCollectionByUser(id, consoleId);
        return convertToDto(collection);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCollection(@PathVariable("id") long id) {
        service.deleteCollection(id);
    }

    private CollectionDto convertToDto(Collection collection) {
        CollectionDto dto = this.modelMapper.map(collection, CollectionDto.class);
        return dto;
    }

    private Collection convertToEntity(CollectionDto dto) {
        Collection model = this.modelMapper.map(dto, Collection.class);
        return model;
    }
    
}
