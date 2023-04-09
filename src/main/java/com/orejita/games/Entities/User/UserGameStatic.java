package com.orejita.games.Entities.User;

import java.util.List;

import com.orejita.games.Entities.Games.Game;
import com.orejita.games.Enums.GameFavorite;
import com.orejita.games.Enums.GameStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_game_statics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserGameStatic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Enumerated(EnumType.STRING)
    private GameStatus status = GameStatus.NOT_PLAYED;

    private Double playedHours;

    @Enumerated(EnumType.ORDINAL)
    private GameFavorite favorite = GameFavorite.NO;
    
}
