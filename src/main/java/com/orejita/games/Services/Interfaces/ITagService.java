package com.orejita.games.Services.Interfaces;

import java.util.List;
import java.util.Set;

import com.orejita.games.Entities.Common.Tag;

public interface ITagService {

    List<Tag> getAllTags();
    List<Tag> getAllConsoleTags(long consoleId);
    List<Tag> getAllGameTags(long gameId);
    Tag getOneTag(long id);
    Tag createTag(Tag tag);
    Tag updateTag(long id, Tag tag);
    Tag addConsoleToTag(long id, long consoleId);
    Tag addConsolesToTag(long id, Set<Long> consoleIds);
    Tag addGameToTag(long id, long gameId);
    Tag addGamesToTag(long id, Set<Long> gamesId);
    Tag deleteConsoleFromTag(long id, long consoleId);
    Tag deleteGameFromTag(long id, long gameTag);
    void deleteTag(long id);

}
