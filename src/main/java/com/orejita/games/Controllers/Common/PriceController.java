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

import com.orejita.games.DTO.Common.PriceDto;
import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.DTO.Requests.OnUpdate;
import com.orejita.games.Entities.Common.Price;
import com.orejita.games.Services.Interfaces.IPriceService;

@Controller
@Validated
@RequestMapping("/price")
public class PriceController {

    @Autowired
    private IPriceService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/console/all/{id}")
    @ResponseBody
    public List<PriceDto> getAllConsolePrices(@PathVariable("id") int id) {
        List<Price> prices = service.getAllPricesByConsole(id);
        return prices.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/game/all/{id}")
    @ResponseBody
    public List<PriceDto> getAllGamePrices(@PathVariable("id") int id) {
        List<Price> prices = service.getAllPricesByGame(id);
        return prices.stream()
                .map(this::convertToDto)
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public PriceDto getOnePrice(@PathVariable("id") int id) {
        Price price = service.getOnePrice(id);
        return convertToDto(price);
    }

    @GetMapping("/last/console/{id}")
    @ResponseBody
    public PriceDto getLastConsolePrice(@PathVariable("id") int id) {
        Price price = service.getLastConsolePrice(id);
        return convertToDto(price);
    }

    @GetMapping("/last/game/{id}")
    @ResponseBody
    public PriceDto getLastGamePrice(@PathVariable("id") int id) {
        Price price = service.getLastGamePrice(id);
        return convertToDto(price);
    }

    @PostMapping("/console/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public PriceDto createConsolePrice(@PathVariable("id") int id, @Validated(OnCreate.class) @RequestBody PriceDto price) {
        Price priceEntity = convertToEntity(price);
        Price _price = service.createPriceForConsole(priceEntity, id);
        return convertToDto(_price);
    }

    @PostMapping("/game/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public PriceDto createGamePrice(@PathVariable("id") int id, @Validated(OnCreate.class) @RequestBody PriceDto price) {
        Price priceEntity = convertToEntity(price);
        Price _price = service.createPriceForGame(priceEntity, id);
        return convertToDto(_price);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public PriceDto updatePrice(@PathVariable("id") int id, @Validated(OnUpdate.class) @RequestBody PriceDto price) {
        Price priceEntity = convertToEntity(price);
        Price _price = service.updatePrice(id, priceEntity);
        return convertToDto(_price);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrice(@PathVariable("id") int id) {
        service.deletePrice(id);
    }


    private PriceDto convertToDto(Price price) {
        PriceDto dto = this.modelMapper.map(price, PriceDto.class);
        return dto;
    }

    private Price convertToEntity(PriceDto dto) {
        Price price = this.modelMapper.map(dto, Price.class);
        return price;
    }
    
}
