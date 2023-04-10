package com.orejita.games.DTO.Rating;

import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.DTO.Requests.OnUpdate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RatingDto {

    private Long id;

    @NotNull(groups = OnCreate.class)
    @Min(value = 0, groups = { OnCreate.class, OnUpdate.class })
    @Max(value = 5, groups = { OnCreate.class, OnUpdate.class })
    private Double rating;
    
}
