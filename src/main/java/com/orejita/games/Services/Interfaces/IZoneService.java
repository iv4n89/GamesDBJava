package com.orejita.games.Services.Interfaces;

import java.util.List;
import java.util.Set;

import com.orejita.games.Entities.Common.Zone;
import com.orejita.games.Services.IService;

public interface IZoneService extends IService<Zone> {
    
    List<Zone> getAllZones();
    Zone getOneZone(long id);
    Zone createZone(Zone zone);
    Zone updateZone(long id, Zone zone);
    Zone addConsoleToZone(long id, long consoleId);
    Zone addConsolesToZone(long id, Set<Long> consolesId);
    Zone deleteConsoleFromZone(long id, long consoleId);
    Zone addGameToZone(long id, long gameId);
    Zone addGamesToZone(long id, Set<Long> gamesId);
    Zone deleteGameFromZone(long id, long gameId);
    void deleteZone(long id);

}
