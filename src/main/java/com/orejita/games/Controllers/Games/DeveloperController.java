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

import com.orejita.games.DTO.Games.DeveloperDto;
import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.DTO.Requests.OnUpdate;
import com.orejita.games.Entities.Games.Developer;
import com.orejita.games.Services.Interfaces.IDeveloperService;

@Controller
@RequestMapping("/developer")
@Validated
public class DeveloperController {
    
    @Autowired
    private IDeveloperService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseBody
    public List<DeveloperDto> getAllDevelopers() {
        List<Developer> developers = service.getAllDevelopers();
        return developers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public DeveloperDto getDeveloper(@PathVariable("id") long id) {
        Developer developer = service.getOneDeveloper(id);
        return convertToDto(developer);
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public DeveloperDto createDevelopert(@Validated(OnCreate.class) @RequestBody DeveloperDto developer) {
        Developer developerEntity = convertToEntity(developer);
        Developer _developer = service.createDeveloper(developerEntity);
        return convertToDto(_developer);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public DeveloperDto updateDeveloper(@PathVariable("id") long id, @Validated(OnUpdate.class) @RequestBody DeveloperDto developer) {
        Developer developerEntity = convertToEntity(developer);
        Developer _developer = service.updateDeveloper(id, developerEntity);
        return convertToDto(_developer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDeveloper(@PathVariable("id") long id)  {
        service.deleteDeveloper(id);
    }


    private DeveloperDto convertToDto(Developer developer) {
        DeveloperDto dto = this.modelMapper.map(developer, DeveloperDto.class);
        return dto;
    }

    private Developer convertToEntity(DeveloperDto dto) {
        Developer model = this.modelMapper.map(dto, Developer.class);
        return model;
    }

}
