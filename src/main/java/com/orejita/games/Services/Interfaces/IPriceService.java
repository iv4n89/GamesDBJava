package com.orejita.games.Services.Interfaces;

import java.util.List;

import com.orejita.games.Entities.Common.Price;
import com.orejita.games.Services.IService;

public interface IPriceService extends IService<Price> {

    List<Price> getAllPricesByConsole(long consoleId);
    List<Price> getAllPricesByGame(long gameId);
    Price getOnePrice(long price);
    Price getLastConsolePrice(long consoleId);
    Price getLastGamePrice(long gameId);
    Price createPriceForConsole(Price price, long consoleId);
    Price createPriceForGame(Price price, long gameId);
    Price updatePrice(long priceId, Price price);
    void deletePrice(long price);
    
}
