package com.orejita.games.Controllers.Common;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.orejita.games.DTO.Common.TagDto;
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

    @Postmapping

    private TagDto convertToDto(Tag tag) {
        TagDto dto = this.modelMapper.map(tag, TagDto.class);
        return dto;
    }

    private Tag convertToEntity(TagDto dto) {
        Tag tag = this.modelMapper.map(dto, Tag.class);
        return tag;
    }

}
