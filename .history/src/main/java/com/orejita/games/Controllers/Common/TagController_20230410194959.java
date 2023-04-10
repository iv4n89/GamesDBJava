package com.orejita.games.Controllers.Common;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.orejita.games.DTO.Common.TagDto;
import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.DTO.Requests.OnUpdate;
import com.orejita.games.Entities.Common.Tag;
import com.orejita.games.Services.Interfaces.ITagService;

@Controller
@Validated
@RequestMapping("/tag")
public class TagController {
    
    @Autowired
    private ITagService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseBody
    public List<TagDto> getAllTags() {
        List<Tag> tags = this.service.getAllTags();
        return tags.stream()
                .map(this::convertToDto)
                .toList();
    }

    @GetMapping("/console/{id}")
    @ResponseBody
    public List<TagDto> getAllTagsByConsoleId(@PathVariable("id") long id) {
        List<Tag> tags = this.service.getAllConsoleTags(id);
        return tags.stream()
                .map(this::convertToDto)
                .toList();
    }

    @GetMapping("/game/{id}")
    @ResponseBody
    public List<TagDto> getAllTagsByGameId(@PathVariable("id") long id) {
        List<Tag> tags = this.service.getAllGameTags(id);
        return tags.stream()
                .map(this::convertToDto)
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public TagDto getTag(@PathVariable("id") long id) {
        Tag tag = this.service.getOneTag(id);
        return convertToDto(tag);
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public TagDto createTag(@Validated(OnCreate.class) @RequestBody TagDto tag) {
        Tag entity = this.convertToEntity(tag);
        Tag _tag = this.service.createTag(entity);
        return this.convertToDto(_tag);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public TagDto updateTag(@PathVariable("id") long id, @Validated(OnUpdate.class) @RequestBody TagDto tag) {
        Tag entity = this.convertToEntity(tag);
        Tag _tag = this.service.updateTag(id, entity);
        return this.convertToDto(_tag);
    }

    @PutMapping("/{id}/console/{consoleId}")
    @ResponseBody
    public TagDto addConsoleToTag(@PathVariable("id") long id, @PathVariable("consoleId") long consoleId) {
        Tag tag = this.service.addConsoleToTag(id, consoleId);
        return this.convertToDto(tag);
    }

    @PutMapping("/{id}/consoles")
    @ResponseBody
    public TagDto addConsolesToTag(@PathVariable("id") long id, @RequestBody long[] ids) {
        Set<Long> consoles = Arrays.stream(ids).boxed().collect(Collectors.toSet());
        Tag tag = this.service.addConsolesToTag(id, consoles);
        return this.convertToDto(tag);
    }

    @PutMapping("/{id}/game/{gameId}")
    @Responsebo




    private TagDto convertToDto(Tag tag) {
        TagDto dto = this.modelMapper.map(tag, TagDto.class);
        return dto;
    }

    private Tag convertToEntity(TagDto dto) {
        Tag tag = this.modelMapper.map(dto, Tag.class);
        return tag;
    }

}
