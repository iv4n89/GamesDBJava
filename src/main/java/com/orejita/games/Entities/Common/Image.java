package com.orejita.games.Entities.Common;

import com.orejita.games.Entities.Consoles.Console;
import com.orejita.games.Entities.Games.Game;
import com.orejita.games.Entities.Manufacturer.Manufacturer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "images")
@Data
public class Image {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String url;

    @OneToOne(mappedBy = "logo", optional = true, orphanRemoval = true)
    private Console consoleLogo;

    @ManyToOne(optional = true)
    @JoinColumn(name = "console_id")
    private Console consoleImage;

    @ManyToOne(optional = true)
    @JoinColumn(name = "console_box_id")
    private Console consoleBoxImage;

    @OneToOne(optional = true)
    @JoinColumn(name = "manufacturer_logo_id")
    private Manufacturer manufacturerLogo;

    @OneToOne(optional = true)
    @JoinColumn(name = "game_logo_id")
    private Game gameLogo;

    @ManyToOne(optional = true)
    @JoinColumn(name = "game_id")
    private Game gameImage;

    @ManyToOne(optional = true)
    @JoinColumn(name = "game_box_id")
    private Game gameBoxImage;
}
