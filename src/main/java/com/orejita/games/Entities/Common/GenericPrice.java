package com.orejita.games.Entities.Common;

import com.orejita.games.Entities.Consoles.Console;
import com.orejita.games.Entities.Games.Game;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "prices")
@Data
public class GenericPrice {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Integer id;
    private Float price;

    @OneToOne(optional = true)
    @JoinColumn(name = "console_initial_price_id")
    private Console consoleInitialPrice;

    @OneToOne(optional = true)
    @JoinColumn(name = "game_initial_price_id")
    private Game gameInitialPrice;
    
}
