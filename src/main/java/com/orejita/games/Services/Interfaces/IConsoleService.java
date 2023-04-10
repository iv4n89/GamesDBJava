package com.orejita.games.Services.Interfaces;

import java.util.List;

import com.orejita.games.Entities.Consoles.Console;

public interface IConsoleService {
    List<Console> getAllConsoles();
    Console getOneConsole(long consoleId);
    Console createConsole(long manufacturerId, Console console);
    Console updateConsole(Console console, long consoleId);
    Console setConsoleImages(long consoleId, List<String> images);
    Console setConsoleImages(Console console, List<String> images);
    Console setConsoleBoxImages(long consoleId, List<String> boxImages);
    Console setConsoleBoxImages(Console console, List<String> boxImages);
    Console addTagToConsole(long consoleId, long tagId);
    Console deleteTagToConsole(long consoleId, long tagId);
    Console deleteConsoleImage(long consoleId, String fileName);
    Console deleteConsoleBoxImage(long consoleId, String fileName);
    void deleteConsole(long consoleId);
}
