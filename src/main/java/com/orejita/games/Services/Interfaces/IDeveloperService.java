package com.orejita.games.Services.Interfaces;

import java.util.List;

import com.orejita.games.Entities.Games.Developer;

public interface IDeveloperService {

    List<Developer> getAllDevelopers();
    Developer getOneDeveloper(long id);
    Developer createDeveloper(Developer developer);
    Developer updateDeveloper(long id, Developer developer);
    void deleteDeveloper(long id);

}
