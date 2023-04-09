package com.orejita.games.DTO.Common;

import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.DTO.Requests.OnUpdate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    
    private Long id;

    @NotNull(groups = OnCreate.class)
    @Size(min = 2, max = 30, groups = { OnCreate.class, OnUpdate.class })
    private String name;

}
