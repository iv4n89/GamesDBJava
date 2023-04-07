package com.orejita.games.Services.Interfaces;

import java.util.List;

import com.orejita.games.Entities.Consoles.Console;

public interface IConsoleService {
    List<Console> getAllConsoles();
    Console getOneConsole(Integer consoleId);
    Console createConsole(int manufacturerId, Console console);
    Console updateConsole(Console console, Integer consoleId);
    void deleteConsole(Integer consoleId);
}
