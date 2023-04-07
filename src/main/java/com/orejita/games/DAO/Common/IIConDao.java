package com.orejita.games.DAO.Common;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.orejita.games.Entities.Common.Icon;
import com.orejita.games.Entities.Consoles.Console;
import com.orejita.games.Entities.Games.Game;
import com.orejita.games.Entities.Manufacturer.Manufacturer;

@Repository
public interface IIConDao extends JpaRepository<Icon, Integer> {

    Optional<Icon> findByConsoleIconId(Integer consoleId);
    Optional<Icon> findByConsoleIcon(Console consoleIcon);
    Optional<Icon> findByManufacturerIconId(Integer manufacturerId);
    Optional<Icon> findByManufacturerIcon(Manufacturer manufacturerIcon);
    Optional<Icon> findByGameIconId(Integer gameId);
    Optional<Icon> findByGameIcon(Game gameIcon);

    @Modifying
    @Query("delete from Icon i where i.gameIcon.id = :gameIcon")
    void deleteByGameIcon(@Param("gameIcon") Integer gameIcon);

    void deleteAllByManufacturerIconId(Integer manufacturerId);
    void deleteAllByConsoleIconId(Integer consoleId);

}
