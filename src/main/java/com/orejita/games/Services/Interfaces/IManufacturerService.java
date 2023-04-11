package com.orejita.games.Services.Interfaces;

import java.util.List;

import com.orejita.games.Entities.Manufacturer.Manufacturer;
import com.orejita.games.Services.IService;

public interface IManufacturerService extends IService<Manufacturer> {
    List<Manufacturer> getAllManufacturers();
    Manufacturer getOneManufacturer(long id);
    Manufacturer createManufacturer(Manufacturer manufacturer);
    Manufacturer updateManufacturer(long id, Manufacturer manufacturer);
    void deleteManufacturer(long id);
}
