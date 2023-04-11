package com.orejita.games.Controllers.Console;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.orejita.games.Controllers.BaseController;
import com.orejita.games.DTO.Consoles.ConsoleDto;
import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.DTO.Requests.OnUpdate;
import com.orejita.games.Entities.Consoles.Console;
import com.orejita.games.Services.Interfaces.IConsoleService;

@RestController
@RequestMapping("/console")
@Validated
public class ConsoleController extends BaseController<Console, ConsoleDto> {
    
    @Autowired
    private IConsoleService service;

    @GetMapping
    public List<ConsoleDto> getAllConsoles() {
        List<Console> consoles = service.getAllConsoles();
        return consoles.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ConsoleDto getConsole(@PathVariable("id") long id) {
        Console console = service.getOneConsole(id);
        return convertToDto(console);
    }

    @PostMapping("/manufacturer/{manId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ConsoleDto createConsole(@Validated(OnCreate.class) @RequestBody ConsoleDto console, @PathVariable("manId") long manId) throws ParseException {
        Console consoleEntity = convertToEntity(console);
        
        if (console.getZoneId() != null) {
            Long zoneId = console.getZoneId();
            Console _console = service.createConsole(manId, zoneId, consoleEntity);
            return convertToDto(_console);
        }

        Console _console = service.createConsole(manId, consoleEntity);
        return convertToDto(_console);
    }

    @PutMapping("/{id}")
    public ConsoleDto updateConsole(@PathVariable("id") long id, @Validated(OnUpdate.class)  @RequestBody ConsoleDto console) throws ParseException {
        Console consoleEntity = convertToEntity(console);
        Console _console = service.updateConsole(consoleEntity, id);
        return convertToDto(_console);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable("id") long id) {
        service.deleteConsole(id);
    }

}
