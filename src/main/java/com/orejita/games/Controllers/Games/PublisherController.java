package com.orejita.games.Controllers.Games;

import java.util.List;
import java.util.stream.Collectors;

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

import com.orejita.games.DTO.Games.PublisherDto;
import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.DTO.Requests.OnUpdate;
import com.orejita.games.Entities.Games.Publisher;
import com.orejita.games.Services.Interfaces.IPublisherService;

@Controller
@RequestMapping("/publisher")
@Validated
public class PublisherController {

    @Autowired
    private IPublisherService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseBody
    public List<PublisherDto> getAllPublishers() {
        List<Publisher> publishers = service.getAllPublishers();
        return publishers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public PublisherDto getPublisher(@PathVariable("id") long id) {
        Publisher publisher = service.getOnePublisher(id);
        return convertToDto(publisher);
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public PublisherDto createPublisher(@Validated(OnCreate.class) @RequestBody PublisherDto publisher) {
        Publisher publisherEntity = convertToEntity(publisher);
        Publisher _publisher = service.createPublisher(publisherEntity);
        return convertToDto(_publisher);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public PublisherDto updatePublisher(@PathVariable("id") long id, @Validated(OnUpdate.class) @RequestBody PublisherDto publisher) {
        Publisher publisherEntity = convertToEntity(publisher);
        Publisher _publisher = service.updatePublisher(id, publisherEntity);
        return convertToDto(_publisher);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublisher(@PathVariable("id") long id) {
        service.deletePublisher(id);
    }


    private PublisherDto convertToDto(Publisher publisher) {
        PublisherDto dto = this.modelMapper.map(publisher, PublisherDto.class);
        return dto;
    }

    private Publisher convertToEntity(PublisherDto dto) {
        Publisher model = this.modelMapper.map(dto, Publisher.class);
        return model;
    }
    
}
