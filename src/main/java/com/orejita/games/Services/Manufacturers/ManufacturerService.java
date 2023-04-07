package com.orejita.games.Services.Manufacturers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orejita.games.DAO.Manufacturer.IManufacturerDao;
import com.orejita.games.Entities.Manufacturer.Manufacturer;
import com.orejita.games.Services.Interfaces.IManufacturerService;

@Service
public class ManufacturerService implements IManufacturerService {

    @Autowired
    private IManufacturerDao dao;

    @Override
    public List<Manufacturer> getAllManufacturers() {
        return dao.findAll();
    }

    @Override
    public Manufacturer getOneManufacturer(int id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public Manufacturer createManufacturer(Manufacturer manufacturer) {
        return dao.save(manufacturer);
    }

    @Override
    public Manufacturer updateManufacturer(int id, Manufacturer manufacturer) {
        Manufacturer _manufacturer = this.getOneManufacturer(id);

        if (_manufacturer == null) {
            return null;
        }

        if (manufacturer.getName() != null) {
            _manufacturer.setName(manufacturer.getName());
        }
        if (manufacturer.getSlug() != null) {
            _manufacturer.setSlug(manufacturer.getSlug());
        }
        if (manufacturer.getHistory() != null) {
            _manufacturer.setHistory(manufacturer.getHistory());
        }

        return dao.save(_manufacturer);
    }

    @Override
    public void deleteManufacturer(int id) {
        dao.deleteById(id);
    }
    
}
