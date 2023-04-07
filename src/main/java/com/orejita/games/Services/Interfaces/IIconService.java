package com.orejita.games.Services.Interfaces;

import java.util.List;

import com.orejita.games.Entities.Common.Icon;
import com.orejita.games.Entities.Consoles.Console;
import com.orejita.games.Entities.Games.Game;
import com.orejita.games.Entities.Manufacturer.Manufacturer;

public interface IIconService {
    
    List<Icon> getAllIcons();
    Icon getIconById(int id);
    Icon getIconByConsoleId(int consoleId);
    Icon getIconByConsole(Console console);
    Icon getIconByManufacturerId(int manufacturerId);
    Icon getIconByManufacturer(Manufacturer manufacturer);
    Icon getIconByGameId(int gameId);
    Icon getIconByGame(Game game);
    Icon createIconByGameId(int gameId, Icon icon);
    Icon createIconByManufacturerId(int manufacturerId, Icon icon);
    Icon createIconByConsoleId(int consoleId, Icon icon);
    Icon updateIcon(int id, Icon icon);
    void deleteIcon(int id);

}
