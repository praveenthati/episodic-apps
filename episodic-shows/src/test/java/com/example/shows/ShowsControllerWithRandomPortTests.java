package com.example.shows;


import com.example.MyTestBaseClass;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MultiValueMap;

import javax.transaction.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShowsControllerWithRandomPortTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    private ShowsRepository showsRepository;


    @Test
    public void testGetShows() throws Exception {

        showsRepository.deleteAll();

        Show show = new Show();
        show.setName("Show1");
        showsRepository.save(show);

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> response =  testRestTemplate.exchange("/shows", HttpMethod.GET,
                new HttpEntity<>(headers), String.class
        );

        JsonArray arrayResponse = new com.google.gson.JsonParser().parse(response.getBody()).getAsJsonArray();

        assert(arrayResponse.size() > 0);
    }




  /*  @Test
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

    }*/

}
