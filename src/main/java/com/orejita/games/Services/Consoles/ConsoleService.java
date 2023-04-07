package com.orejita.games.Services.Consoles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orejita.games.DAO.Consoles.IConsoleDao;
import com.orejita.games.DAO.Manufacturer.IManufacturerDao;
import com.orejita.games.Entities.Consoles.Console;
import com.orejita.games.Entities.Manufacturer.Manufacturer;
import com.orejita.games.Services.Interfaces.IConsoleService;

@Service
public class ConsoleService implements IConsoleService {

    @Autowired
    private IConsoleDao dao;

    @Autowired
    private IManufacturerDao manufacturerDao;

    @Override
    public List<Console> getAllConsoles() {
        return dao.findAll();
    }

    @Override
    public Console getOneConsole(Integer consoleId) {
        return dao.findById(consoleId).orElse(null);
    }

    @Override
    public Console createConsole(int manufacturerId, Console console) {
        Manufacturer manufacturer = manufacturerDao.findById(manufacturerId).orElse(null);

        if (manufacturer != null) {
            console.setManufacturer(manufacturer);
        }
        
        return dao.save(console);
    }

    @Override
    public Console updateConsole(Console console, Integer consoleId) {
        Console _console = this.getOneConsole(consoleId);
        if (console.getName() != null) {
            _console.setName(console.getName());
        }
        if (console.getDescription() != null) {
            _console.setDescription(console.getDescription());
        }
        if (console.getSlug() != null) {
            _console.setDescription(console.getDescription());
        }
        if (console.getHistory() != null) {
            _console.setHistory(console.getHistory());
        }
        if (console.getLaunchDate() != null) {
            _console.setLaunchDate(console.getLaunchDate());
        }
        if (console.getRetirementDate() != null) {
            _console.setRetirementDate(console.getRetirementDate());
        }
        if (console.getOtherNames() != null) {
            _console.setOtherNames(console.getOtherNames());
        }

        return dao.save(_console);
    }

    @Override
    public void deleteConsole(Integer consoleId) {
        dao.deleteById(consoleId);
    }


    
}
