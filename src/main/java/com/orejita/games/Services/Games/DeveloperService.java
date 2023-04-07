package com.orejita.games.Services.Games;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orejita.games.DAO.Games.IDeveloperDao;
import com.orejita.games.Entities.Games.Developer;
import com.orejita.games.Services.Interfaces.IDeveloperService;

@Service
public class DeveloperService implements IDeveloperService {

    @Autowired
    private IDeveloperDao dao;

    @Override
    public List<Developer> getAllDevelopers() {
        return dao.findAll();
    }

    @Override
    public Developer getOneDeveloper(int id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public Developer createDeveloper(Developer developer) {
        return dao.save(developer);
    }

    @Override
    public Developer updateDeveloper(int id, Developer developer) {
        Developer _developer = this.getOneDeveloper(id);

        if (_developer == null) {
            return null;
        }

        if (developer.getName() != null) {
            _developer.setName(developer.getName());
        }
        if (developer.getSlug() != null) {
            _developer.setSlug(developer.getSlug());
        }

        return dao.save(_developer);
    }

    @Override
    public void deleteDeveloper(int id) {
        dao.deleteById(id);
    }
    
}
