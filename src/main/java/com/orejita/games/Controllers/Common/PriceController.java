package com.orejita.games.Controllers.Common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orejita.games.Entities.Common.Price;
import com.orejita.games.Services.Interfaces.IPriceService;

@RestController
@RequestMapping("/price")
public class PriceController {

    @Autowired
    private IPriceService service;

    @GetMapping("/console/all/{id}")
    public ResponseEntity<List<Price>> getAllConsolePrices(@PathVariable("id") int id) {
        List<Price> prices = service.getAllPricesByConsole(id);
        return ResponseEntity.ok().body(prices);
    }

    @GetMapping("/game/all/{id}")
    public ResponseEntity<List<Price>> getAllGamePrices(@PathVariable("id") int id) {
        List<Price> prices = service.getAllPricesByGame(id);
        return ResponseEntity.ok().body(prices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Price> getOnePrice(@PathVariable("id") int id) {
        Price price = service.getOnePrice(id);
        return ResponseEntity.ok().body(price);
    }

    @GetMapping("/last/console/{id}")
    public ResponseEntity<Price> getLastConsolePrice(@PathVariable("id") int id) {
        Price price = service.getLastConsolePrice(id);
        return ResponseEntity.ok().body(price);
    }

    @GetMapping("/last/game/{id}")
    public ResponseEntity<Price> getLastGamePrice(@PathVariable("id") int id) {
        Price price = service.getLastGamePrice(id);
        return ResponseEntity.ok().body(price);
    }

    @PostMapping("/console/{id}")
    public ResponseEntity<Price> createConsolePrice(@PathVariable("id") int id, @RequestBody Price price) {
        Price _price = service.createPriceForConsole(price, id);
        return new ResponseEntity<>(_price, HttpStatus.CREATED);
    }

    @PostMapping("/game/{id}")
    public ResponseEntity<Price> createGamePrice(@PathVariable("id") int id, @RequestBody Price price) {
        Price _price = service.createPriceForGame(price, id);
        return new ResponseEntity<>(_price, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Price> updatePrice(@PathVariable("id") int id, @RequestBody Price price) {
        Price _price = service.updatePrice(id, price);
        return ResponseEntity.ok().body(_price);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Price> deletePrice(@PathVariable("id") int id) {
        service.deletePrice(id);
        return ResponseEntity.noContent().build();
    }
    
}
