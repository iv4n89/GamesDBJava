package com.orejita.games.Services.Interfaces;

import java.util.List;

import com.orejita.games.Entities.Games.Developer;

public interface IDeveloperService {

    List<Developer> getAllDevelopers();
    Developer getOneDeveloper(int id);
    Developer createDeveloper(Developer developer);
    Developer updateDeveloper(int id, Developer developer);
    void deleteDeveloper(int id);

}
