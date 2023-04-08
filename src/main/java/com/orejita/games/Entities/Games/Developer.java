package com.orejita.games.Entities.Games;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "gameDevelopers")
@Getter
@Setter
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String slug;
    private String logo;

    @OneToMany(mappedBy = "developerId")
    private List<Game> games;
    
}
