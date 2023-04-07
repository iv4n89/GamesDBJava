package com.orejita.games.DTO.Common;

import java.io.Serializable;

import com.orejita.games.DTO.Consoles.ConsoleDto;
import com.orejita.games.DTO.Games.GameDto;
import com.orejita.games.DTO.Manufacturers.ManufacturerDto;
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
public class IconDto {

    private Integer id;

    @NotNull(groups = OnCreate.class)
    private String url;

    private ConsoleDto consoleIcon;

    private ManufacturerDto manufacturerIcon;

    private GameDto gameIcon;
    
}
