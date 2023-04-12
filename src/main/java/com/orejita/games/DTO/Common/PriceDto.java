package com.orejita.games.DTO.Common;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orejita.games.DTO.Consoles.ConsoleDto;
import com.orejita.games.DTO.Games.GameDto;
import com.orejita.games.DTO.Requests.OnCreate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceDto {

    private Long id;

    @NotNull(groups = OnCreate.class)
    private float price;

    private List<ConsoleDto> consoles;

    private Long consoleId;

    private List<GameDto> games;

    private Long gameId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Long getConsoleId() {
        return this.consoleId;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Long getGameId() {
        return this.gameId;
    }
    
}
