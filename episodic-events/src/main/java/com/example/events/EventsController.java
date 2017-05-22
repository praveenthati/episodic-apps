package com.example.events;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Praveen Thati on 5/22/17.
 */
@RestController
public class EventsController {

    private final EventsRepository eventsRepository;

    public EventsController(EventsRepository eventsRepository){
        this.eventsRepository = eventsRepository;
    }

    @GetMapping("/recent")
    public List<Event> getRecentEvents(){
        Pageable page = new PageRequest(0,20, new Sort(new Sort.Order(Sort.Direction.DESC,"createdAt")));
        return eventsRepository.findRecent(page);
    }

    @PostMapping
    public Object createEvent(@RequestBody Event event) {

        return this.eventsRepository.save(event);

    }
}
