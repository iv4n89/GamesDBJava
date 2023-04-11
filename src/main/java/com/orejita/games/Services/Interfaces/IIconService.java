package com.orejita.games.Services.Interfaces;

import java.util.List;

import com.orejita.games.Entities.Common.Icon;
import com.orejita.games.Entities.Consoles.Console;
import com.orejita.games.Entities.Games.Game;
import com.orejita.games.Entities.Manufacturer.Manufacturer;
import com.orejita.games.Services.IService;

public interface IIconService extends IService<Icon> {
    
    List<Icon> getAllIcons();
    Icon getIconById(long id);
    Icon getIconByConsoleId(long consoleId);
    Icon getIconByConsole(Console console);
    Icon getIconByManufacturerId(long manufacturerId);
    Icon getIconByManufacturer(Manufacturer manufacturer);
    Icon getIconByGameId(long gameId);
    Icon getIconByGame(Game game);
    Icon createIconByGameId(long gameId, Icon icon);
    Icon createIconByManufacturerId(long manufacturerId, Icon icon);
    Icon createIconByConsoleId(long consoleId, Icon icon);
    Icon updateIcon(long id, Icon icon);
    void deleteIcon(long id);

}
