package com.orejita.games.Services.Interfaces;

import java.util.List;

import com.orejita.games.Entities.Games.Developer;
import com.orejita.games.Services.IService;

public interface IDeveloperService extends IService<Developer> {

    List<Developer> getAllDevelopers();
    Developer getOneDeveloper(long id);
    Developer createDeveloper(Developer developer);
    Developer updateDeveloper(long id, Developer developer);
    void deleteDeveloper(long id);

}
