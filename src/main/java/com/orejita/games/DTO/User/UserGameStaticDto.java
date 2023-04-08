package com.orejita.games.DTO.User;

import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.Entities.Games.Game;
import com.orejita.games.Entities.User.User;
import com.orejita.games.Enums.GameFavorite;
import com.orejita.games.Enums.GameStatus;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserGameStaticDto {
    
    private Long id;

    @NotNull(groups = OnCreate.class)
    private User user;

    @NotNull(groups = OnCreate.class)
    private Game game;

    private GameStatus status;

    private Double playedHours;

    private GameFavorite favorite;

}
