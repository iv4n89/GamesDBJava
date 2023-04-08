package com.orejita.games.Services.Interfaces;

import java.util.List;

import com.orejita.games.Entities.Manufacturer.Manufacturer;

public interface IManufacturerService {
    List<Manufacturer> getAllManufacturers();
    Manufacturer getOneManufacturer(long id);
    Manufacturer createManufacturer(Manufacturer manufacturer);
    Manufacturer updateManufacturer(long id, Manufacturer manufacturer);
    void deleteManufacturer(long id);
}
