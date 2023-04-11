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
import com.orejita.games.DTO.Common.PriceDto;
import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.DTO.Requests.OnUpdate;
import com.orejita.games.Entities.Common.Price;
import com.orejita.games.Services.Interfaces.IPriceService;

@RestController
@Validated
@RequestMapping("/price")
public class PriceController extends BaseController<Price, PriceDto> {

    @Autowired
    private IPriceService service;

    @GetMapping("/console/all/{id}")
    public List<PriceDto> getAllConsolePrices(@PathVariable("id") int id) {
        List<Price> prices = service.getAllPricesByConsole(id);
        return prices.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/game/all/{id}")
    public List<PriceDto> getAllGamePrices(@PathVariable("id") int id) {
        List<Price> prices = service.getAllPricesByGame(id);
        return prices.stream()
                .map(this::convertToDto)
                .toList();
    }

    @GetMapping("/{id}")
    public PriceDto getOnePrice(@PathVariable("id") int id) {
        Price price = service.getOnePrice(id);
        return convertToDto(price);
    }

    @GetMapping("/last/console/{id}")
    public PriceDto getLastConsolePrice(@PathVariable("id") long id) {
        Price price = service.getLastConsolePrice(id);
        return convertToDto(price);
    }

    @GetMapping("/last/game/{id}")
    public PriceDto getLastGamePrice(@PathVariable("id") long id) {
        Price price = service.getLastGamePrice(id);
        return convertToDto(price);
    }

    @PostMapping("/console/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public PriceDto createConsolePrice(@PathVariable("id") long id, @Validated(OnCreate.class) @RequestBody PriceDto price) {
        Price priceEntity = convertToEntity(price);
        Price _price = service.createPriceForConsole(priceEntity, id);
        return convertToDto(_price);
    }

    @PostMapping("/game/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public PriceDto createGamePrice(@PathVariable("id") long id, @Validated(OnCreate.class) @RequestBody PriceDto price) {
        Price priceEntity = convertToEntity(price);
        Price _price = service.createPriceForGame(priceEntity, id);
        return convertToDto(_price);
    }

    @PutMapping("/{id}")
    public PriceDto updatePrice(@PathVariable("id") long id, @Validated(OnUpdate.class) @RequestBody PriceDto price) {
        Price priceEntity = convertToEntity(price);
        Price _price = service.updatePrice(id, priceEntity);
        return convertToDto(_price);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrice(@PathVariable("id") long id) {
        service.deletePrice(id);
    }
    
}
