package com.example.users;

import com.example.shows.Episode;
import com.example.shows.EpisodesService;
import com.example.shows.ShowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Praveen Thati on 5/18/17.
 */
@Service
public class ViewingsService {

    @Autowired
    private ViewingsRepository viewingsRepository;

    @Autowired
    private ShowsService showsService;

    @Autowired
    private EpisodesService episodesService;


    void patchViewings(Viewing viewing, long userId){

        if(episodesService.getEpisodeById(viewing.getEpisodeId()) != null) {

            Viewing existingViewing = viewingsRepository.findOneByUserIdAndEpisodeId(userId,viewing.getEpisodeId());

           if(existingViewing == null)
           {
               viewing.setUserId(userId);
               viewingsRepository.save(viewing);
           }
           else {

               existingViewing.setUpdatedAt(viewing.getUpdatedAt());
               existingViewing.setTimecode(viewing.getTimecode());
               viewingsRepository.save(existingViewing);
           }
        }

    }

    List<RecentlyViewedViewing> getUsersRecentViewing(long userId){

        List<RecentlyViewedViewing> list = viewingsRepository
                .findAllByUserIdOrderByIdDesc(userId)
                .stream().map(e->{
                    RecentlyViewedViewing rv = new RecentlyViewedViewing();
                    Episode episode = episodesService.getEpisodeById(e.getEpisodeId());

                    rv.setEpisode(episode);
                    rv.setShow(showsService.getShowById(episode.getShowId()));
                    rv.setTimecode(e.getTimecode());
                    rv.setUpdatedAt(e.getUpdatedAt());

                    return rv;
                }).collect(Collectors.toList());

        return  list;
    }
}
