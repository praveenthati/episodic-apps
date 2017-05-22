package com.example.events;

import com.example.product.DigitalProduct;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Praveen Thati on 5/22/17.
 */
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class EventsControllerTests {


    private ObjectMapper om = new ObjectMapper();

    @Autowired
    MockMvc mvc;

    @Autowired
    EventsRepository eventsRepository;


    @Before
    public void deleteAll(){
        eventsRepository.deleteAll();
    }

    @Test
    public void testCreatePlayEvent() throws Exception{

        Map<String, Object> payload = new HashMap<String, Object>() {
            {
                put("type", "play");
                put("userId", 52);
                put("showId", 987);
                put("episodeId", 456);
                put("createdAt", "2017-11-08T15:59:13.0091745");
                put("data", new HashMap<String, Object>() {
                    {
                        put("offset",  4);
                    }
                });
            }
        };
        String jsonPayload = om.writeValueAsString(payload);

        mvc.perform(post("/")
                .content(jsonPayload)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", equalTo(52)))
                .andExpect(jsonPath("$.type", equalTo("play")))
                .andExpect(jsonPath("$.data.offset", equalTo(4)))
                .andExpect(jsonPath("$.data.speed").doesNotExist())
                .andDo(print());

    }

    @Test
    public void testCreateScrubEvent() throws Exception{

        Map<String, Object> payload = new HashMap<String, Object>() {
            {
                put("type", "scrub");
                put("userId", 52);
                put("showId", 987);
                put("episodeId", 456);
                put("createdAt", "2017-11-08T15:59:13.0091745");
                put("data", new HashMap<String, Object>() {
                    {
                        put("startOffset",  4);
                        put("endOffset",  41);
                    }
                });
            }
        };
        String jsonPayload = om.writeValueAsString(payload);

        mvc.perform(post("/")
                .content(jsonPayload)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", equalTo(52)))
                .andExpect(jsonPath("$.type", equalTo("scrub")))
                .andExpect(jsonPath("$.data.endOffset", equalTo(41)))
                .andExpect(jsonPath("$.data.startOffset", equalTo(4)))
                .andDo(print());

    }

    @Test
    public void testCreateProgressEvent() throws Exception{

        Map<String, Object> payload = new HashMap<String, Object>() {
            {
                put("type", "progress");
                put("userId", 52);
                put("showId", 987);
                put("episodeId", 456);
                put("createdAt", "2017-11-08T15:59:13.0091745");
                put("data", new HashMap<String, Object>() {
                    {
                        put("offset",  4);
                    }
                });
            }
        };
        String jsonPayload = om.writeValueAsString(payload);

        mvc.perform(post("/")
                .content(jsonPayload)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", equalTo(52)))
                .andExpect(jsonPath("$.type", equalTo("progress")))
                .andDo(print());

    }

    @Test
    public void testCreateFastForwardEvent() throws Exception{

        Map<String, Object> payload = new HashMap<String, Object>() {
            {
                put("type", "fastForward");
                put("userId", 52);
                put("showId", 987);
                put("episodeId", 456);
                put("createdAt", "2017-11-08T15:59:13.0091");
                put("data", new HashMap<String, Object>() {
                    {
                        put("speed",  4.5);
                        put("endOffset",  41);
                        put("startOffset",  4);
                    }
                });
            }
        };
        String jsonPayload = om.writeValueAsString(payload);

        mvc.perform(post("/")
                .content(jsonPayload)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", equalTo(52)))
                .andExpect(jsonPath("$.type", equalTo("fastForward")))
                .andExpect(jsonPath("$.data.endOffset", equalTo(41)))
                .andExpect(jsonPath("$.data.startOffset", equalTo(4)))
                .andExpect(jsonPath("$.data.speed", equalTo(4.5)))
                .andExpect(jsonPath("$.createdAt", equalTo("2017-11-08T15:59:13.0091")))
                .andDo(print());

    }

    @Test
    public void testCreateRewindEvent() throws Exception{

        Map<String, Object> payload = new HashMap<String, Object>() {
            {
                put("type", "rewind");
                put("userId", 52);
                put("showId", 987);
                put("episodeId", 456);
                put("createdAt", "2017-11-08T15:59:13.0091745");
                put("data", new HashMap<String, Object>() {
                    {
                        put("speed",  4.5);
                        put("endOffset",  41);
                        put("startOffset",  4);
                    }
                });
            }
        };
        String jsonPayload = om.writeValueAsString(payload);

        mvc.perform(post("/")
                .content(jsonPayload)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", equalTo(52)))
                .andExpect(jsonPath("$.type", equalTo("rewind")))
                .andExpect(jsonPath("$.data.endOffset", equalTo(41)))
                .andExpect(jsonPath("$.data.startOffset", equalTo(4)))
                .andExpect(jsonPath("$.data.speed", equalTo(4.5)))
                .andDo(print());

    }

    @Test
    public void getRecent() throws Exception{

        for(int i=0; i<21; i++) {
            PlayEvent.Data data = new PlayEvent.Data();
            PlayEvent playEvent = new PlayEvent(1L,1L,1L,new Date(), data);
            playEvent.setUserId(new Long(i));
            eventsRepository.save(playEvent);
        }


        mvc.perform(get("/recent")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(20)))
                .andExpect(jsonPath("$[0].type", equalTo("play")))
                .andDo(print());

    }
}
