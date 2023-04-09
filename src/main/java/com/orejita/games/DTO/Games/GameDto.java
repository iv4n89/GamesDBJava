package com.orejita.games.DTO.Games;

import java.io.Serializable;
import java.util.List;

import com.orejita.games.DTO.Common.IconDto;
import com.orejita.games.DTO.Common.TagDto;
import com.orejita.games.DTO.Common.ZoneDto;
import com.orejita.games.DTO.Consoles.ConsoleDto;
import com.orejita.games.DTO.Requests.OnCreate;
import com.orejita.games.DTO.Requests.OnUpdate;
import com.orejita.games.Entities.Consoles.Console;

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

    private Long id;

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

    private ConsoleDto console;

    private IconDto icon;

    private String logo;

    private List<String> images;

    private List<String> boxImages;

    private Integer isSpecialEdition;

    private ZoneDto zone;

    private List<TagDto> tags;

    public void setDeveloperId(DeveloperDto developer) {
        this.developerId = developer;
    }

    public void setDeveloperId(long id) {
        DeveloperDto developer = new DeveloperDto();
        developer.setId(id);
        this.developerId = developer;
    }

    public void setPublisherId(PublisherDto publisher) {
        this.publisherId = publisher;
    }

    public void setPublisherId(long id) {
        PublisherDto publisher = new PublisherDto();
        publisher.setId(id);
        this.publisherId = publisher;
    }

    public void setZone(long id) {
        ZoneDto zone = new ZoneDto();
        zone.setId(id);
        this.zone = zone;
    }

    public void setZone(ZoneDto zone) {
        this.zone = zone;
    }
    
}
