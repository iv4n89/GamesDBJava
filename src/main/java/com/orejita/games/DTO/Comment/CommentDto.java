package com.orejita.games.DTO.Comment;

import java.time.LocalDateTime;

import com.orejita.games.DTO.Consoles.ConsoleDto;
import com.orejita.games.DTO.Games.GameDto;
import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.DTO.Requests.OnUpdate;
import com.orejita.games.DTO.User.UserDto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class CommentDto {
    
    private Long id;

    @NotNull(groups = OnCreate.class)
    @Size(min = 2, groups = { OnCreate.class, OnUpdate.class })
    private String comment;

    private LocalDateTime postedDate;

    @Min(value = 0, groups = { OnCreate.class, OnUpdate.class })
    @Max(value = 1, groups = { OnCreate.class, OnUpdate.class })
    private Integer isEdited;

    private LocalDateTime editedDate;

    private ConsoleDto console;

    private GameDto game;

    private UserDto user;

}
