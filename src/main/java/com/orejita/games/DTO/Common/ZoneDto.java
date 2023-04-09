package com.orejita.games.DTO.Common;

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
public class ZoneDto {
    
    private Long id;

    @NotNull(groups = OnCreate.class)
    private String name;

}
