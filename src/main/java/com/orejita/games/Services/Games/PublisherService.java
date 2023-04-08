package com.orejita.games.Services.Games;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orejita.games.DAO.Games.IPublisherDao;
import com.orejita.games.Entities.Games.Publisher;
import com.orejita.games.Services.Interfaces.IPublisherService;

@Service
public class PublisherService implements IPublisherService {

    @Autowired
    private IPublisherDao dao;

    @Override
    public List<Publisher> getAllPublishers() {
        return dao.findAll();
    }

    @Override
    public Publisher getOnePublisher(long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public Publisher createPublisher(Publisher publisher) {
        return dao.save(publisher);
    }

    @Override
    public Publisher updatePublisher(long id, Publisher publisher) {
        Publisher _publisher = this.getOnePublisher(id);

        if (_publisher == null) {
            return null;
        }

        if (publisher.getName() != null) {
            _publisher.setName(publisher.getName());
        }
        if (publisher.getSlug() != null) {
            _publisher.setSlug(publisher.getSlug());
        }

        return dao.save(_publisher);
    }

    @Override
    public void deletePublisher(long id) {
        dao.deleteById(id);
    }


    
}
