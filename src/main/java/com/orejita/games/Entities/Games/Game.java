package com.orejita.games.Entities.Games;

import java.util.List;

import com.orejita.games.Entities.Common.GenericPrice;
import com.orejita.games.Entities.Common.Icon;
import com.orejita.games.Entities.Common.Image;
import com.orejita.games.Entities.Common.Price;
import com.orejita.games.Entities.Consoles.Console;
import com.orejita.games.Entities.Manufacturer.Manufacturer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "games")
@Data
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String slug;
    private String description;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "console_id")
    private Console console;

    @OneToOne(mappedBy = "gameInitialPrice")
    private GenericPrice initialPrice;

    @OneToMany(mappedBy = "gameHistoryPrice")
    private List<Price> historyPrices;

    @OneToOne(mappedBy = "gameLogo")
    private Image logo;

    @OneToMany(mappedBy = "gameImage")
    private List<Image> images;

    @OneToMany(mappedBy = "gameBoxImage")
    private List<Image> boxImages;

    @OneToOne(mappedBy = "gameIcon")
    private Icon icon;

    @ManyToOne
    @JoinColumn(name = "developer_id")
    private Developer developerId;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisherId;

}
