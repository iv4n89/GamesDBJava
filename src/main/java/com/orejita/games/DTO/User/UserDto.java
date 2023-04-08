package com.orejita.games.DTO.User;

import java.util.Date;

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
public class UserDto {

    private Long id;

    @NotNull(groups = OnCreate.class)
    @Size(min = 2, max = 30, groups = { OnCreate.class, OnUpdate.class })
    private String name;

    @NotNull(groups = OnCreate.class)
    @Size(min = 2, max = 30, groups = { OnCreate.class, OnUpdate.class })
    private String lastName;

    private Date birthDate;

    @NotNull(groups = OnCreate.class)
    @Size(min = 2, max = 20, groups = { OnCreate.class, OnUpdate.class })
    private String username;

    @NotNull(groups = OnCreate.class)
    @Size(min = 6, max = 20, groups = { OnCreate.class, OnUpdate.class })
    private String password;

    private String avatar;
    
}
