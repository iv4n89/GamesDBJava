package com.orejita.games.Services.Consoles;

import java.util.List;
import java.util.stream.Collectors;

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
    public Console getOneConsole(long consoleId) {
        return dao.findById(consoleId).orElse(null);
    }

    @Override
    public Console createConsole(long manufacturerId, Console console) {
        Manufacturer manufacturer = manufacturerDao.findById(manufacturerId).orElse(null);

        if (manufacturer != null) {
            console.setManufacturer(manufacturer);
        }
        
        return dao.save(console);
    }

    @Override
    public Console updateConsole(Console console, long consoleId) {
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
        if (console.getLogo() != null) {
            _console.setLogo(console.getLogo());
        }
        if (console.getImages() != null) {
            List<String> newImages = console.getImages();
            newImages.addAll(_console.getImages());
            List<String> noDuplicated = newImages.stream().collect(Collectors.toSet()).stream().toList();
            _console.setImages(noDuplicated);
        }
        if (console.getBoxImages() != null) {
            _console.getBoxImages().addAll(console.getBoxImages());
            _console.setBoxImages(_console.getBoxImages().stream().collect(Collectors.toSet()).stream().toList());
        }

        return dao.save(_console);
    }

    @Override
    public Console setConsoleImages(long consoleId, List<String> images) {
        Console console = this.getOneConsole(consoleId);
        console.setImages(images);
        return dao.save(console);
    }

    @Override
    public Console setConsoleImages(Console console, List<String> images) {
        console.setImages(images);
        return dao.save(console);
    }

    @Override
    public Console setConsoleBoxImages(long consoleId, List<String> boxImages) {
        Console console = this.getOneConsole(consoleId);
        console.setBoxImages(boxImages);
        return dao.save(console);
    }

    @Override
    public Console setConsoleBoxImages(Console console, List<String> boxImages) {
        console.setBoxImages(boxImages);
        return dao.save(console);
    }

    @Override
    public Console deleteConsoleImage(long consoleId, String fileName) {
        Console console = this.getOneConsole(consoleId);
        List<String> images = console.getImages().stream().filter(img -> !img.contains(fileName)).toList();
        console.setImages(images);
        return dao.save(console);
    }

    @Override
    public Console deleteConsoleBoxImage(long consoleId, String fileName) {
        Console console = this.getOneConsole(consoleId);
        List<String> images = console.getBoxImages().stream().filter(img -> !img.contains(fileName)).toList();
        console.setBoxImages(images);
        return dao.save(console);
    }

    @Override
    public void deleteConsole(long consoleId) {
        dao.deleteById(consoleId);
    }


    
}
