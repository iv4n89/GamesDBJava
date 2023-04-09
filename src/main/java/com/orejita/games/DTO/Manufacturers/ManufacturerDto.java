package com.orejita.games.DTO.Manufacturers;

import com.orejita.games.DTO.Common.IconDto;
import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.DTO.Requests.OnUpdate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ManufacturerDto {
    
    private Long id;

    @NotNull(groups = OnCreate.class)
    @Size(min = 2, max = 30, groups = { OnCreate.class, OnUpdate.class })
    private String name;

    @NotNull(groups = OnCreate.class)
    @Size(min = 1, max = 10, groups = { OnCreate.class, OnUpdate.class })
    private String slug;

    private String history;

    private IconDto icon;

    private String logo;

}
