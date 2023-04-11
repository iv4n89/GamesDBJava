package com.orejita.games.Services.Interfaces;

import java.util.List;

import com.orejita.games.Entities.User.Collection;
import com.orejita.games.Services.IService;

public interface ICollectionService extends IService<Collection> {
    
    List<Collection> getAllCollections();
    Collection getOneCollection(Long id);
    Collection getCollectionByUserId(Long userId);
    Collection getCollectionByUserUsername(String username);
    Collection createCollection(long userId, Collection collection);
    Collection updateCollection(Long id, Collection collection);
    Collection addGameToCollection(Long collectionId, Long gameId);
    Collection addGameToCollectionByUser(Long userId, Long gameId);
    Collection addConsoleToCollection(Long collectionId, Long consoleId);
    Collection addConsoleToCollectionByUser(Long userId, Long consoleId);
    Collection deleteGameFromCollection(Long collectionId, Long gameId);
    Collection deleteGameFromCollectionByUser(Long userId, Long gameId);
    Collection deleteConsoleFromCollection(Long collectionId, Long consoleId);
    Collection deleteConsoleFromCollectionByUser(Long userId, Long consoleId);
    void deleteCollection(Long id);

}
