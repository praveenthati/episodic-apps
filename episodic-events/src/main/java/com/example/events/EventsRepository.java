package com.example.events;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created by Praveen Thati on 5/22/17.
 */
public interface EventsRepository extends MongoRepository<Event, String> {
    @Query("{}")
    public List<Event> findRecent(Pageable page);
}
