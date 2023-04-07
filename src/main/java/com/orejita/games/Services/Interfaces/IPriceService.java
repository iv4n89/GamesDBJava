package com.orejita.games.Services.Interfaces;

import java.util.List;

import com.orejita.games.Entities.Common.Price;

public interface IPriceService {

    List<Price> getAllPricesByConsole(int consoleId);
    List<Price> getAllPricesByGame(int gameId);
    Price getOnePrice(int price);
    Price getLastConsolePrice(int consoleId);
    Price getLastGamePrice(int gameId);
    Price createPriceForConsole(Price price, int consoleId);
    Price createPriceForGame(Price price, int gameId);
    Price updatePrice(int priceId, Price price);
    void deletePrice(int price);
    
}
