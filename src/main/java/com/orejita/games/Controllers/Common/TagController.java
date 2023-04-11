package com.orejita.games.Controllers.Common;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.orejita.games.Controllers.BaseController;
import com.orejita.games.DTO.Common.TagDto;
import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.DTO.Requests.OnUpdate;
import com.orejita.games.Entities.Common.Tag;
import com.orejita.games.Services.Interfaces.ITagService;

@RestController
@Validated
@RequestMapping("/tag")
public class TagController extends BaseController<Tag, TagDto> {
    
    @Autowired
    private ITagService service;

    @GetMapping
    public List<TagDto> getAllTags() {
        List<Tag> tags = this.service.getAllTags();
        return tags.stream()
                .map(this::convertToDto)
                .toList();
    }

    @GetMapping("/console/{id}")
    public List<TagDto> getAllTagsByConsoleId(@PathVariable("id") long id) {
        List<Tag> tags = this.service.getAllConsoleTags(id);
        return tags.stream()
                .map(this::convertToDto)
                .toList();
    }

    @GetMapping("/game/{id}")
    public List<TagDto> getAllTagsByGameId(@PathVariable("id") long id) {
        List<Tag> tags = this.service.getAllGameTags(id);
        return tags.stream()
                .map(this::convertToDto)
                .toList();
    }

    @GetMapping("/{id}")
    public TagDto getTag(@PathVariable("id") long id) {
        Tag tag = this.service.getOneTag(id);
        return convertToDto(tag);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TagDto createTag(@Validated(OnCreate.class) @RequestBody TagDto tag) {
        Tag entity = this.convertToEntity(tag);
        Tag _tag = this.service.createTag(entity);
        return this.convertToDto(_tag);
    }

    @PutMapping("/{id}")
    public TagDto updateTag(@PathVariable("id") long id, @Validated(OnUpdate.class) @RequestBody TagDto tag) {
        Tag entity = this.convertToEntity(tag);
        Tag _tag = this.service.updateTag(id, entity);
        return this.convertToDto(_tag);
    }

    @PutMapping("/{id}/console/{consoleId}")
    public TagDto addConsoleToTag(@PathVariable("id") long id, @PathVariable("consoleId") long consoleId) {
        Tag tag = this.service.addConsoleToTag(id, consoleId);
        return this.convertToDto(tag);
    }

    @PutMapping("/{id}/consoles")
    public TagDto addConsolesToTag(@PathVariable("id") long id, @RequestBody long[] ids) {
        Set<Long> consoles = Arrays.stream(ids).boxed().collect(Collectors.toSet());
        Tag tag = this.service.addConsolesToTag(id, consoles);
        return this.convertToDto(tag);
    }

    @PutMapping("/{id}/game/{gameId}")
    public TagDto addGameToTag(@PathVariable("id") long id, @PathVariable("gameId") long gameId) {
        Tag tag = this.service.addGameToTag(id, gameId);
        return this.convertToDto(tag);
    }

    @PutMapping("/{id}/games")
    public TagDto addGamesToTag(@PathVariable("id") long id, @RequestBody long[] ids) {
        Set<Long> games = Arrays.stream(ids).boxed().collect(Collectors.toSet());
        Tag tag = this.service.addGamesToTag(id, games);
        return this.convertToDto(tag);
    }

    @DeleteMapping("/{id}/console/{consoleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsoleFromTag(@PathVariable("id") long id, @PathVariable("consoleId") long consoleId) {
        this.service.deleteConsoleFromTag(id, consoleId);
    }

    @DeleteMapping("/{id}/game/{gameId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGameFromTag(@PathVariable("id") long id, @PathVariable("gameId") long gameId) {
        this.service.deleteGameFromTag(id, gameId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTag(@PathVariable("id") long id) {
        this.service.deleteTag(id);
    }

}
