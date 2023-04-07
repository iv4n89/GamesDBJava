package com.orejita.games.Entities.Common;

import java.util.Date;

import com.orejita.games.Entities.Consoles.Console;
import com.orejita.games.Entities.Games.Game;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "history-prices")
@Data
@EqualsAndHashCode(callSuper = false)
public class Price extends GenericPrice {

    private Date priceDate;

    @ManyToOne(optional = true)
    @JoinColumn(name = "console_history_price_id")
    private Console consoleHistoryPrice;

    @ManyToOne(optional = true)
    @JoinColumn(name = "game_history_price_id")
    private Game gameHistoryPrice;

}
