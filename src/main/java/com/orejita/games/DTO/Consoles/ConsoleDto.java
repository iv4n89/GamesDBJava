package com.orejita.games.DTO.Consoles;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.orejita.games.DTO.Common.IconDto;
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
public class ConsoleDto implements Serializable {
    
    
    private Integer id;

    @NotNull(groups = OnCreate.class)
    @Size(max = 35, groups = { OnCreate.class, OnUpdate.class })
    private String name;

    @NotNull(groups = OnCreate.class)
    @Size(max = 10, groups = { OnCreate.class, OnUpdate.class })
    private String slug;

    @NotNull(groups = OnCreate.class)
    private String description;

    @NotNull(groups = OnCreate.class)
    private String history;

    @NotNull(groups = OnCreate.class)
    private Date launchDate;

    private Date retirementDate;

    private List<String> otherNames;

    private IconDto icon;

    private String logo;

    private List<String> images;

    private List<String> boxImages;

}
