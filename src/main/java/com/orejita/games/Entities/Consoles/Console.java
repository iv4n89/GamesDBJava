package com.orejita.games.Entities.Consoles;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.orejita.games.Entities.Common.Icon;
import com.orejita.games.Entities.Common.Price;
import com.orejita.games.Entities.Games.Game;
import com.orejita.games.Entities.Manufacturer.Manufacturer;
import com.orejita.games.Entities.User.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "consoles")
@Getter
@Setter
public class Console {

    @Id
    private Long id;
    private String name;
    private String slug;
    private String description;
    private String history;
    private Date launchDate;
    private Date retirementDate;
    private List<String> otherNames;
    private String logo;
    private List<String> images;
    private List<String> boxImages;

    @OneToOne(mappedBy = "consoleInitialPrice")
    private Price initialPrice;

    @OneToMany(mappedBy = "consoleHistoryPrice")
    private List<Price> hitoryPrice;

    @OneToOne(mappedBy = "consoleIcon")
    private Icon icon;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "console")
    private List<Game> games;

    @ManyToMany
    private List<Collection> collections;

    public List<String> getImages() {
        if (this.images == null) {
            this.images = new ArrayList<>();
        }

        return this.images;
    }

    public List<String> getBoxImages() {
        if (this.boxImages == null) {
            this.boxImages = new ArrayList<>();
        }

        return this.boxImages;
    }
    
}
