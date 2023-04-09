package com.orejita.games.Controllers.Common;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.orejita.games.DTO.Common.ZoneDto;
import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.DTO.Requests.OnUpdate;
import com.orejita.games.Entities.Common.Zone;
import com.orejita.games.Services.Interfaces.IZoneService;

@Controller
@Validated
@RequestMapping("/zone")
public class ZoneController {
    
    @Autowired
    private IZoneService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseBody
    public List<ZoneDto> getAllZones() {
        List<Zone> zones = service.getAllZones();
        return zones.stream()
                .map(this::convertToDto)
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ZoneDto getZone(@PathVariable("id") long id) {
        Zone zone = service.getOneZone(id);
        return convertToDto(zone);
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ZoneDto createZone(@Validated(OnCreate.class) @RequestBody ZoneDto zone) {
        Zone zoneEntity = convertToEntity(zone);
        Zone _zone = service.createZone(zoneEntity);
        return convertToDto(_zone);
    }

    @PostMapping("/{id}")
    @ResponseBody
    public ZoneDto updateZone(@PathVariable("id") long id, @Validated(OnUpdate.class) @RequestBody ZoneDto zone) {
        Zone zoneEntity = convertToEntity(zone);
        Zone _zone = service.updateZone(id, zoneEntity);
        return convertToDto(_zone);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteZone(@PathVariable("id") long id) {
        service.deleteZone(id);
    }


    private ZoneDto convertToDto(Zone zone) {
        ZoneDto dto = this.modelMapper.map(zone, ZoneDto.class);
        return dto;
    }

    private Zone convertToEntity(ZoneDto dto) {
        Zone model = this.modelMapper.map(dto, Zone.class);
        return model;
    }

}
