package com.orejita.games.DTO.Games;

import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.DTO.Requests.OnUpdate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeveloperDto {

    private Integer id;

    @NotNull(groups = OnCreate.class)
    @Size(min = 2, max = 30, groups = { OnCreate.class, OnUpdate.class })
    private String name;

    @NotNull(groups = OnCreate.class)
    @Size(min = 1, max = 10, groups = { OnCreate.class, OnUpdate.class })
    private String slug;

    private String logo;

}
