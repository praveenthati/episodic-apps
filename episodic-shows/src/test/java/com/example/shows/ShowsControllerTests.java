package com.example.shows;


import com.example.MyTestBaseClass;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ShowsControllerTests extends MyTestBaseClass {

    @Autowired
    MockMvc mvc;

    @Autowired
    private ShowsRepository showsRepository;
    @Autowired
    private EpisodesRepository episodesRepository;

    private Gson gson = new GsonBuilder().create();

    @Test
    @Rollback
    @Transactional
    public void testGetShows() throws Exception {

        Show show = new Show();
        show.setName("Show1");
        showsRepository.save(show);

        mvc.perform(get("/shows")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

    }

    @Test
    @Rollback
    @Transactional
    public void testPostShows() throws Exception {

        Show show = new Show();
        show.setName("Show1");


        mvc.perform(post("/shows")
                .content(gson.toJson(show))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Show1")));

    }

    @Test
    @Rollback
    @Transactional
    public void testGetShowEpisodes() throws Exception {

        Show show = new Show();
        show.setName("Show1");
        showsRepository.save(show);

        Episode episode = new Episode();
        episode.setShowId(show.getId());
        episode.setEpisodeNumber(1L);
        episode.setSeasonNumber(1L);
        episodesRepository.save(episode);

        episode = new Episode();
        episode.setShowId(show.getId());
        episode.setEpisodeNumber(1L);
        episode.setSeasonNumber(2L);
        episodesRepository.save(episode);

        mvc.perform(get("/shows/"+show.getId()+"/episodes")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].seasonNumber", equalTo(1)))
                .andExpect(jsonPath("$[1].seasonNumber", equalTo(2)));

    }


    @Test
    @Rollback
    @Transactional
    public void testPostEpisodes() throws Exception {

        Show show = new Show();
        show.setName("Show1");
        showsRepository.save(show);

        Episode episode = new Episode();
        episode.setShowId(show.getId());
        episode.setEpisodeNumber(1L);
        episode.setSeasonNumber(1L);


        mvc.perform(post("/shows/"+show.getId()+"/episodes")
                .content(gson.toJson(episode))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.showId").doesNotExist())
                .andExpect(jsonPath("$.seasonNumber", equalTo(episode.getSeasonNumber().intValue())))
                .andExpect(jsonPath("$.episodeNumber", equalTo(episode.getEpisodeNumber().intValue())))
                .andExpect(jsonPath("$.title", equalTo(episode.getTitle())));

    }

}
