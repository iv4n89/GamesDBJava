package com.orejita.games.DAO.Manufacturer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orejita.games.Entities.Manufacturer.Manufacturer;

@Repository
public interface IManufacturerDao extends JpaRepository<Manufacturer, Integer> {
    
}
