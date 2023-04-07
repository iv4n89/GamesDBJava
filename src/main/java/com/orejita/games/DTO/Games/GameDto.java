package com.orejita.games.DTO.Games;

import java.io.Serializable;

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
public class GameDto implements Serializable {

    private Integer id;

    @NotNull(groups = OnCreate.class)
    @Size(min = 2, max = 30, groups = { OnCreate.class, OnUpdate.class })
    private String name;

    @NotNull(groups = OnCreate.class)
    @Size(min = 1, max = 10, groups = { OnCreate.class, OnUpdate.class })
    private String slug;

    @NotNull(groups = OnCreate.class)
    private DeveloperDto developerId;
    
    @NotNull(groups = OnCreate.class)
    private PublisherDto publisherId;

    public void setDeveloperId(DeveloperDto developer) {
        this.developerId = developer;
    }

    public void setDeveloperId(int id) {
        DeveloperDto developer = new DeveloperDto();
        developer.setId(id);
        this.developerId = developer;
    }

    public void setPublisherId(PublisherDto publisher) {
        this.publisherId = publisher;
    }

    public void setPublisherId(int id) {
        PublisherDto publisher = new PublisherDto();
        publisher.setId(id);
        this.publisherId = publisher;
    }
    
}
