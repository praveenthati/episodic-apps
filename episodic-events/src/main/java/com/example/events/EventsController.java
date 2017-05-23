package com.example.events;


import com.example.publishing.AmqpController;
import com.example.publishing.ProgessMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
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
    private final RabbitTemplate rabbitTemplate;

    public EventsController(EventsRepository eventsRepository,RabbitTemplate rabbitTemplate){
        this.eventsRepository = eventsRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/recent")
    public List<Event> getRecentEvents(){
        Pageable page = new PageRequest(0,20, new Sort(new Sort.Order(Sort.Direction.DESC,"createdAt")));
        return eventsRepository.findRecent(page);
    }

    @PostMapping
    @Transactional
    public Object createEvent(@RequestBody Event event) {

        Object obj = this.eventsRepository.save(event);

        if(obj!=null && event.getClass().getSimpleName().equalsIgnoreCase("progressevent")){
            ProgessMessage message = new ProgessMessage();
            message.setCreatedAt(event.getCreatedAt());
            message.setEpisodeId(event.getEpisodeId());
            message.setUserId(event.getUserId());
            message.setOffset(((ProgressEvent)event).getData().getOffset());
            rabbitTemplate.convertAndSend("my-exchange", "my-routing-key", message);
        }

        return  obj;

    }
}
