package com.orejita.games.Entities.Manufacturer;

import java.util.List;

import com.orejita.games.Entities.Common.Icon;
import com.orejita.games.Entities.Consoles.Console;
import com.orejita.games.Entities.Games.Game;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "manufacturers")
@Data
public class Manufacturer {

    @Id
    private Long id;
    private String name;
    private String slug;
    private String history;
    
    private String logo;

    @OneToOne(mappedBy = "manufacturerIcon")
    private Icon icon;

    @OneToMany(mappedBy = "manufacturer")
    private List<Console> consoles;

    @OneToMany(mappedBy = "manufacturer")
    private List<Game> games;

}
