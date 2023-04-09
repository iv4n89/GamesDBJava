package com.orejita.games.Services.Interfaces;

import java.util.List;

import com.orejita.games.Entities.Common.Zone;

public interface IZoneService {
    
    List<Zone> getAllZones();
    Zone getOneZone(long id);
    Zone createZone(Zone zone);
    Zone updateZone(long id, Zone zone);
    void deleteZone(long id);

}
