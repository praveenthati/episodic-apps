package com.example.shows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Praveen Thati on 5/18/17.
 */
@Service
public class EpisodesService {

    @Autowired
    private EpisodesRepository episodesRepository;

    List<Episode> getEpisodesForAShow(long showId){

        List<Episode> list = new ArrayList<>();
        episodesRepository.getAllByShowId(showId).forEach(list::add);
        return list;

    }

    public Episode addEpisode(Episode episode, long showId){

        episode.setShowId(showId);
        return episodesRepository.save(episode);

    }

    public Episode getEpisodeById(long id){

        return episodesRepository.findOne(id);

    }
}
