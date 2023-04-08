package com.orejita.games.Services.Interfaces;

import java.util.List;
import com.orejita.games.Entities.Games.Publisher;

public interface IPublisherService {

    List<Publisher> getAllPublishers();

    Publisher getOnePublisher(long id);

    Publisher createPublisher(Publisher publisher);

    Publisher updatePublisher(long id, Publisher publisher);

    void deletePublisher(long id);

}
