package com.example.shows;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Praveen Thati on 5/18/17.
 */
@RestController
public class ShowsController {

    private final ShowsService ShowsService;

    public ShowsController(ShowsService showsService) {
        this.ShowsService = showsService;
    }

    @GetMapping("/shows")
    public List<Show> getShows() {

        return ShowsService.getAllShows();
    }

    @PostMapping("/shows")
    public Show createShow(@RequestBody Show show) {

        return ShowsService.addShow(show);
    }

}