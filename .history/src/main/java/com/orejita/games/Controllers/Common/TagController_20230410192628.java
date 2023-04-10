package com.orejita.games.Controllers.Common;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @

    private TagDto convertToDto(Tag tag) {
        TagDto dto = this.modelMapper.map(tag, TagDto.class);
        return dto;
    }

    private Tag convertToEntity(TagDto dto) {
        Tag tag = this.modelMapper.map(dto, Tag.class);
        return tag;
    }

}
