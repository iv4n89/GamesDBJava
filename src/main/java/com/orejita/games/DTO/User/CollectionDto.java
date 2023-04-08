package com.orejita.games.DTO.User;

import java.util.List;

import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.Entities.Consoles.Console;
import com.orejita.games.Entities.Games.Game;
import com.orejita.games.Entities.User.User;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CollectionDto {
    
    private Long id;

    @NotNull(groups = OnCreate.class)
    private User user;

    private List<Console> consoles;

    private List<Game> games;

}
