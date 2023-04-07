package com.orejita.games.Controllers.Common;

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

import com.orejita.games.DTO.Common.IconDto;
import com.orejita.games.DTO.Games.GameDto;
import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.DTO.Requests.OnUpdate;
import com.orejita.games.Entities.Common.Icon;
import com.orejita.games.Entities.Games.Game;
import com.orejita.games.Services.Interfaces.IGameService;
import com.orejita.games.Services.Interfaces.IIconService;

@Controller
@RequestMapping("/icon")
@Validated
public class IconController {

    @Autowired
    private IIconService service;

    @Autowired
    private IGameService gameService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseBody
    public List<IconDto> getAllIcons() {
        List<Icon> icons = service.getAllIcons();
        return icons.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public IconDto getIconById(@PathVariable("id") int id) {
        Icon icon = service.getIconById(id);
        return convertToDto(icon);
    }

    @GetMapping("/console/{consoleId}")
    @ResponseBody
    public IconDto getIconByConsoleId(@PathVariable("consoleId") int consoleId) {
        Icon icon = service.getIconByConsoleId(consoleId);
        return convertToDto(icon);
    }

    @GetMapping("/manufacturer/{manufacturerId}")
    @ResponseBody
    public IconDto getIconByManufacturerId(@PathVariable("manufacturerId") int manufacturerId) {
        Icon icon = service.getIconByManufacturerId(manufacturerId);
        return convertToDto(icon);
    }

    @GetMapping("/game/{gameId}")
    @ResponseBody
    public IconDto getIconByGameId(@PathVariable("gameId") int gameId) {
        Icon icon = service.getIconByGameId(gameId);
        return convertToDto(icon);
    }

    @PostMapping("/game/{gameId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public IconDto createIconByGameId(@PathVariable("gameId") int gameId, @Validated(OnCreate.class) @RequestBody IconDto icon) {
        Icon iconEntity = convertToEntity(icon);
        Icon _icon = service.createIconByGameId(gameId, iconEntity);
        return convertToDto(_icon);
    }

    @PostMapping("/console/{consoleId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public IconDto createIconByConsoleId(@PathVariable("consoleId") int consoleId, @Validated(OnCreate.class) @RequestBody IconDto icon) {
        Icon iconEntity = convertToEntity(icon);
        Icon _icon = service.createIconByConsoleId(consoleId, iconEntity);
        return convertToDto(_icon);
    }

    @PostMapping("/manufacturer/{manufacturerId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public IconDto createIconByManufacturerId(@PathVariable("manufacturerId") int manufacturerId, @Validated(OnCreate.class) @RequestBody IconDto icon) {
        Icon iconEntity = convertToEntity(icon);
        Icon _icon = service.createIconByManufacturerId(manufacturerId, iconEntity);
        return convertToDto(_icon);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public IconDto updateIcon(@PathVariable("id") int id, @Validated(OnUpdate.class) @RequestBody IconDto icon) {
        Icon iconEntity = convertToEntity(icon);
        Icon _icon = service.updateIcon(id, iconEntity);
        return convertToDto(_icon);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIcon(@PathVariable("id") int id) {
        service.deleteIcon(id);
    }
    


    private IconDto convertToDto(Icon icon) {
        IconDto dto = this.modelMapper.map(icon, IconDto.class);
        return dto;
    }

    private Icon convertToEntity(IconDto dto) {
        Icon model = this.modelMapper.map(dto, Icon.class);
        return model;
    }

}
