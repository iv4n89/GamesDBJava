package com.orejita.games.Services.Common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orejita.games.DAO.Common.IIConDao;
import com.orejita.games.DAO.Consoles.IConsoleDao;
import com.orejita.games.DAO.Games.IGameDao;
import com.orejita.games.DAO.Manufacturer.IManufacturerDao;
import com.orejita.games.Entities.Common.Icon;
import com.orejita.games.Entities.Consoles.Console;
import com.orejita.games.Entities.Games.Game;
import com.orejita.games.Entities.Manufacturer.Manufacturer;
import com.orejita.games.Services.Interfaces.IIconService;

@Service
public class IconService implements IIconService {

    @Autowired
    private IIConDao dao;

    @Autowired 
    private IGameDao gameDao;

    @Autowired
    private IConsoleDao consoleDao;

    @Autowired
    private IManufacturerDao manufacturerDao;

    @Override
    public List<Icon> getAllIcons() {
        return dao.findAll();
    }

    @Override
    public Icon getIconById(int id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public Icon getIconByConsoleId(int consoleId) {
        return dao.findByConsoleIconId(consoleId).orElse(null);
    }

    @Override
    public Icon getIconByConsole(Console console) {
        return dao.findByConsoleIcon(console).orElse(null);
    }

    @Override
    public Icon getIconByManufacturerId(int manufacturerId) {
        return dao.findByManufacturerIconId(manufacturerId).orElse(null);
    }

    @Override
    public Icon getIconByManufacturer(Manufacturer manufacturer) {
        return dao.findByManufacturerIcon(manufacturer).orElse(null);
    }

    @Override
    public Icon getIconByGameId(int gameId) {
        return dao.findByGameIconId(gameId).orElse(null);
    }

    @Override
    public Icon getIconByGame(Game game) {
        return dao.findByGameIcon(game).orElse(null);
    }

    @Override
    public Icon createIconByGameId(int gameId, Icon icon) {

        Icon _icon = this.getIconByGameId(gameId);

        if (_icon != null) {
            Icon __icon = this.updateIcon(_icon.getId(), icon);
            return __icon;
        }
        
        Game game = gameDao.findById(gameId).orElse(null);
        icon.setGameIcon(game);
        return dao.save(icon);
    }

    @Override
    public Icon createIconByConsoleId(int consoleId, Icon icon) {
        Icon _icon = this.getIconByConsoleId(consoleId);

        if (_icon != null) {
            Icon __icon = this.updateIcon(_icon.getId(), icon);
            return __icon;
        }
        
        Console console = consoleDao.findById(consoleId).orElse(null);
        icon.setConsoleIcon(console);
        return dao.save(icon);
    }

    @Override
    public Icon createIconByManufacturerId(int manufacturerId, Icon icon) {
        Icon _icon = this.getIconByManufacturerId(manufacturerId);

        if (_icon != null) {
            Icon __icon = this.updateIcon(_icon.getId(), icon);
            return __icon;
        }
        
        Manufacturer manufacturer = manufacturerDao.findById(manufacturerId).orElse(null);
        icon.setManufacturerIcon(manufacturer);
        return dao.save(icon);
    }

    @Override
    public Icon updateIcon(int id, Icon icon) {
        Icon _icon = this.getIconById(id);

        if (_icon == null) {
            return null;
        }

        if (icon.getUrl() != null) {
            _icon.setUrl(icon.getUrl());
        }

        return dao.save(_icon);
    }

    @Override
    public void deleteIcon(int id) {
        dao.deleteById(id);
    }


    
}
