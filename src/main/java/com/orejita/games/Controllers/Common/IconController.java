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
import org.springframework.web.bind.annotation.RestController;

import com.orejita.games.Controllers.BaseController;
import com.orejita.games.DTO.Common.IconDto;
import com.orejita.games.DTO.Games.GameDto;
import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.DTO.Requests.OnUpdate;
import com.orejita.games.Entities.Common.Icon;
import com.orejita.games.Entities.Games.Game;
import com.orejita.games.Services.Interfaces.IGameService;
import com.orejita.games.Services.Interfaces.IIconService;

@RestController
@RequestMapping("/icon")
@Validated
public class IconController extends BaseController<Icon, IconDto> {

    @Autowired
    private IIconService service;

    @GetMapping
    public List<IconDto> getAllIcons() {
        List<Icon> icons = service.getAllIcons();
        return icons.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public IconDto getIconById(@PathVariable("id") long id) {
        Icon icon = service.getIconById(id);
        return convertToDto(icon);
    }

    @GetMapping("/console/{consoleId}")
    public IconDto getIconByConsoleId(@PathVariable("consoleId") long consoleId) {
        Icon icon = service.getIconByConsoleId(consoleId);
        return convertToDto(icon);
    }

    @GetMapping("/manufacturer/{manufacturerId}")
    public IconDto getIconByManufacturerId(@PathVariable("manufacturerId") long manufacturerId) {
        Icon icon = service.getIconByManufacturerId(manufacturerId);
        return convertToDto(icon);
    }

    @GetMapping("/game/{gameId}")
    public IconDto getIconByGameId(@PathVariable("gameId") long gameId) {
        Icon icon = service.getIconByGameId(gameId);
        return convertToDto(icon);
    }

    @PostMapping("/game/{gameId}")
    @ResponseStatus(HttpStatus.CREATED)
    public IconDto createIconByGameId(@PathVariable("gameId") long gameId, @Validated(OnCreate.class) @RequestBody IconDto icon) {
        Icon iconEntity = convertToEntity(icon);
        Icon _icon = service.createIconByGameId(gameId, iconEntity);
        return convertToDto(_icon);
    }

    @PostMapping("/console/{consoleId}")
    @ResponseStatus(HttpStatus.CREATED)
    public IconDto createIconByConsoleId(@PathVariable("consoleId") long consoleId, @Validated(OnCreate.class) @RequestBody IconDto icon) {
        Icon iconEntity = convertToEntity(icon);
        Icon _icon = service.createIconByConsoleId(consoleId, iconEntity);
        return convertToDto(_icon);
    }

    @PostMapping("/manufacturer/{manufacturerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public IconDto createIconByManufacturerId(@PathVariable("manufacturerId") long manufacturerId, @Validated(OnCreate.class) @RequestBody IconDto icon) {
        Icon iconEntity = convertToEntity(icon);
        Icon _icon = service.createIconByManufacturerId(manufacturerId, iconEntity);
        return convertToDto(_icon);
    }

    @PutMapping("/{id}")
    public IconDto updateIcon(@PathVariable("id") long id, @Validated(OnUpdate.class) @RequestBody IconDto icon) {
        Icon iconEntity = convertToEntity(icon);
        Icon _icon = service.updateIcon(id, iconEntity);
        return convertToDto(_icon);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIcon(@PathVariable("id") long id) {
        service.deleteIcon(id);
    }

}
