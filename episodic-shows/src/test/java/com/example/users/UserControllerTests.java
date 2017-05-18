package com.example.users;

import com.example.shows.Episode;
import com.example.shows.EpisodesRepository;
import com.example.shows.Show;
import com.example.shows.ShowsRepository;
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

import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class UserControllerTests {

    @Autowired
    MockMvc mvc;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ShowsRepository showsRepository;

    @Autowired
    private EpisodesRepository episodesRepository;

    @Autowired
    private ViewingsRepository viewingsRepository;

    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

    @Test
    @Rollback
    @Transactional
    public void testGetUsers() throws Exception {

        User user = new User();
        user.setEmail("user1@email.com");
        usersRepository.save(user);

        mvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

    }

    @Test
    @Rollback
    @Transactional
    public void testPostUsers() throws Exception {

        User user = new User();
        user.setEmail("user1@email.com");


        mvc.perform(post("/users")
                .content(gson.toJson(user))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", equalTo("user1@email.com")));

    }

    @Test
    @Rollback
    @Transactional
    public void testPatchViewings() throws Exception {

        User user = new User();
        user.setEmail("user1@email.com");
        usersRepository.save(user);

        Show show = new Show();
        show.setName("Show1");
        showsRepository.save(show);

        Episode episode = new Episode();
        episode.setShowId(show.getId());
        episode.setEpisodeNumber(1L);
        episode.setSeasonNumber(2L);
        episodesRepository.save(episode);

        Viewing viewing = new Viewing();
        viewing.setEpisodeId(episode.getId());
        viewing.setTimecode(1);
        viewing.setUserId(user.getId());
        viewing.setUpdatedAt(new Date());


        mvc.perform(patch("/users/"+user.getId()+"/viewings")
                .content(gson.toJson(viewing))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @Rollback
    @Transactional
    public void testGetUsersRecentViewings() throws Exception {

        User user = new User();
        user.setEmail("user1@email.com");
        usersRepository.save(user);

        Show show = new Show();
        show.setName("Show1");
        showsRepository.save(show);

        Episode episode = new Episode();
        episode.setShowId(show.getId());
        episode.setEpisodeNumber(1L);
        episode.setSeasonNumber(2L);
        episodesRepository.save(episode);

        Viewing viewing = new Viewing();
        viewing.setEpisodeId(episode.getId());
        viewing.setTimecode(1);
        viewing.setUserId(user.getId());
        viewing.setUpdatedAt(new Date());
        viewingsRepository.save(viewing);


        mvc.perform(get("/users/"+user.getId()+"/recently-watched")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].show.name",equalTo(show.getName())))
                .andExpect(jsonPath("$[0].episode.seasonNumber",equalTo(episode.getSeasonNumber().intValue())))
                .andExpect(jsonPath("$[0].episode.episodeNumber",equalTo(episode.getEpisodeNumber().intValue())))
                .andExpect(jsonPath("$[0].episode.showId").doesNotExist());

    }


}
