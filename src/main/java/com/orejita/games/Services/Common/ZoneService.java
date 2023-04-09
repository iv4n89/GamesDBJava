package com.orejita.games.Services.Common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orejita.games.DAO.Common.IZoneDao;
import com.orejita.games.Entities.Common.Zone;
import com.orejita.games.Services.Interfaces.IZoneService;

@Service
public class ZoneService implements IZoneService {

    @Autowired
    private IZoneDao dao;

    @Override
    public List<Zone> getAllZones() {
        return dao.findAll();
    }

    @Override
    public Zone getOneZone(long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public Zone createZone(Zone zone) {
        return dao.save(zone);
    }

    @Override
    public Zone updateZone(long id, Zone zone) {
        Zone _zone = this.getOneZone(id);

        if (_zone == null) {
            return null;
        }

        if (zone.getName() != null) {
            _zone.setName(zone.getName());
        }

        return dao.save(_zone);
    }

    @Override
    public void deleteZone(long id) {
        dao.deleteById(id);
    }


    
}
