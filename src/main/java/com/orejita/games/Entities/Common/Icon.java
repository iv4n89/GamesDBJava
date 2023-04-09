package com.orejita.games.Entities.Common;

import com.orejita.games.Entities.Consoles.Console;
import com.orejita.games.Entities.Games.Game;
import com.orejita.games.Entities.Manufacturer.Manufacturer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "icons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Icon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @OneToOne(optional = true)
    @JoinColumn(name = "console_icon")
    private Console consoleIcon;

    @OneToOne(optional = true)
    @JoinColumn(name = "manufacturer_icon_id")
    private Manufacturer manufacturerIcon;

    @OneToOne(optional = true)
    @JoinColumn(name = "game_icon_id")
    private Game gameIcon;
    
}
