package com.orejita.games.Services.Interfaces;

import java.util.List;

import com.orejita.games.Entities.Consoles.Console;

public interface IConsoleService {
    List<Console> getAllConsoles();
    Console getOneConsole(Integer consoleId);
    Console createConsole(int manufacturerId, Console console);
    Console updateConsole(Console console, Integer consoleId);
    Console setConsoleImages(int consoleId, List<String> images);
    Console setConsoleImages(Console console, List<String> images);
    Console setConsoleBoxImages(int consoleId, List<String> boxImages);
    Console setConsoleBoxImages(Console console, List<String> boxImages);
    Console deleteConsoleImage(int consoleId, String fileName);
    Console deleteConsoleBoxImage(int consoleId, String fileName);
    void deleteConsole(Integer consoleId);
}
