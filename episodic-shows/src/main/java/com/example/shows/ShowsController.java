package com.example.shows;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Praveen Thati on 5/18/17.
 */
@RestController
public class ShowsController {

    private final ShowsService ShowsService;
    private final EpisodesService EpisodesService;

    public ShowsController(ShowsService showsService,EpisodesService episodesService) {
        this.ShowsService = showsService;
        this.EpisodesService = episodesService;
    }

    @GetMapping("/shows")
    public List<Show> getShows() {

        return ShowsService.getAllShows();
    }

    @PostMapping("/shows")
    public Show createShow(@RequestBody Show show) {

        return ShowsService.addShow(show);
    }

    @GetMapping("/shows/{id}/episodes")
    public List<Episode> getShowEpisodes(@PathVariable Long id) {

        return EpisodesService.getEpisodesForAShow(id);
    }

    @PostMapping("/shows/{id}/episodes")
    public Episode createEpisode(@RequestBody Episode episode) {

        return EpisodesService.addEpisode(episode);
    }

}