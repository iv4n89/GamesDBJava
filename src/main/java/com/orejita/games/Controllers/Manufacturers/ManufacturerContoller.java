package com.orejita.games.Controllers.Manufacturers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.orejita.games.DTO.Manufacturers.ManufacturerDto;
import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.DTO.Requests.OnUpdate;
import com.orejita.games.Entities.Manufacturer.Manufacturer;
import com.orejita.games.Services.Interfaces.IManufacturerService;

@RestController
@RequestMapping("/manufacturer")
@Validated
public class ManufacturerContoller extends BaseController<Manufacturer, ManufacturerDto> {
    
    @Autowired
    private IManufacturerService service;

    @GetMapping
    public List<ManufacturerDto> getAllManufacturers() {
        List<Manufacturer> manufacturers = service.getAllManufacturers();
        return manufacturers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ManufacturerDto getManufacturer(@PathVariable("id") long id) {
        Manufacturer manufacturer = service.getOneManufacturer(id);
        return convertToDto(manufacturer);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ManufacturerDto createManufacturer(@Validated(OnCreate.class) @RequestBody ManufacturerDto manufacturer) {
        Manufacturer manufacturerEntity = convertToEntity(manufacturer);
        Manufacturer _manufacturer = service.createManufacturer(manufacturerEntity);
        return convertToDto(_manufacturer);
    }

    @PutMapping("/{id}")
    public ManufacturerDto updateManufacturer(@PathVariable("id") long id, @Validated(OnUpdate.class) @RequestBody ManufacturerDto manufacturer) {
        Manufacturer manufacturerEntity = convertToEntity(manufacturer);
        Manufacturer _manufacturer = service.updateManufacturer(id, manufacturerEntity);
        return convertToDto(_manufacturer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteManufacturer(@PathVariable("id") long id) {
        service.deleteManufacturer(id);
    }

}
