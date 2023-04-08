package com.orejita.games.Entities.Common;

import java.util.Date;

import com.orejita.games.Entities.Consoles.Console;
import com.orejita.games.Entities.Games.Game;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "prices")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Price {

    @Id
    private Long id;
    private Float price;

    @OneToOne(optional = true)
    @JoinColumn(name = "console_initial_price_id")
    private Console consoleInitialPrice;

    @OneToOne(optional = true)
    @JoinColumn(name = "game_initial_price_id")
    private Game gameInitialPrice;

    private Date priceDate;

    @ManyToOne(optional = true)
    @JoinColumn(name = "console_history_price_id")
    private Console consoleHistoryPrice;

    @ManyToOne(optional = true)
    @JoinColumn(name = "game_history_price_id")
    private Game gameHistoryPrice;

}
