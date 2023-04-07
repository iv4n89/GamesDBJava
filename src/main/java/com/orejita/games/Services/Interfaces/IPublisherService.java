package com.orejita.games.Services.Interfaces;

import java.util.List;
import com.orejita.games.Entities.Games.Publisher;

public interface IPublisherService {

    List<Publisher> getAllPublishers();

    Publisher getOnePublisher(int id);

    Publisher createPublisher(Publisher publisher);

    Publisher updatePublisher(int id, Publisher publisher);

    void deletePublisher(int id);

}
