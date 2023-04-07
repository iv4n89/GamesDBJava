package com.orejita.games.Controllers.Console;

import java.text.ParseException;
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

import com.orejita.games.DTO.Consoles.ConsoleDto;
import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.DTO.Requests.OnUpdate;
import com.orejita.games.Entities.Consoles.Console;
import com.orejita.games.Entities.Manufacturer.Manufacturer;
import com.orejita.games.Services.Interfaces.IConsoleService;
import com.orejita.games.Services.Interfaces.IManufacturerService;

@Controller
@RequestMapping("/console")
@Validated
public class ConsoleController {
    
    @Autowired
    private IConsoleService service;

    @Autowired
    private IManufacturerService manufacturerService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseBody
    public List<ConsoleDto> getAllConsoles() {
        List<Console> consoles = service.getAllConsoles();
        return consoles.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ConsoleDto getConsole(@PathVariable("id") int id) {
        Console console = service.getOneConsole(id);
        return convertToDto(console);
    }

    @PostMapping("/manufacturer/{manId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ConsoleDto createConsole(@Validated(OnCreate.class) @RequestBody ConsoleDto console, @PathVariable("manId") int manId) throws ParseException {
        Console consoleEntity = convertToEntity(console);
        Console _console = service.createConsole(manId, consoleEntity);
        return convertToDto(_console);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ConsoleDto updateConsole(@PathVariable("id") int id, @Validated(OnUpdate.class)  @RequestBody ConsoleDto console) throws ParseException {
        Console consoleEntity = convertToEntity(console);
        Console _console = service.updateConsole(consoleEntity, id);
        return convertToDto(_console);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable("id") int id) {
        service.deleteConsole(id);
    }

    private ConsoleDto convertToDto(Console console) {
        ConsoleDto consoleDto = modelMapper.map(console, ConsoleDto.class);
        return consoleDto;
    }

    private Console convertToEntity(ConsoleDto consoleDto) throws ParseException {
        Console console = modelMapper.map(consoleDto, Console.class);

        return console;
    }

}
