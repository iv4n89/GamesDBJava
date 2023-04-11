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
import org.springframework.web.bind.annotation.RestController;

import com.orejita.games.Controllers.BaseController;
import com.orejita.games.DTO.Games.DeveloperDto;
import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.DTO.Requests.OnUpdate;
import com.orejita.games.Entities.Games.Developer;
import com.orejita.games.Services.Interfaces.IDeveloperService;

@RestController
@RequestMapping("/developer")
@Validated
public class DeveloperController extends BaseController<Developer, DeveloperDto> {
    
    @Autowired
    private IDeveloperService service;

    @GetMapping
    public List<DeveloperDto> getAllDevelopers() {
        List<Developer> developers = service.getAllDevelopers();
        return developers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DeveloperDto getDeveloper(@PathVariable("id") long id) {
        Developer developer = service.getOneDeveloper(id);
        return convertToDto(developer);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeveloperDto createDevelopert(@Validated(OnCreate.class) @RequestBody DeveloperDto developer) {
        Developer developerEntity = convertToEntity(developer);
        Developer _developer = service.createDeveloper(developerEntity);
        return convertToDto(_developer);
    }

    @PutMapping("/{id}")
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

}
