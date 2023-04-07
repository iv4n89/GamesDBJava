package com.orejita.games.DTO.Common;

import java.util.Date;

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

    private Integer id;

    @NotNull(groups = OnCreate.class)
    private float price;

    @NotNull(groups = OnCreate.class)
    private Date priceDate;

    private ConsoleDto consoleHistoryPrice;

    private GameDto gameHistoryPrice;
    
}
