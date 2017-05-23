package com.example.users;

import com.example.messages.ProgessMessage;
import com.example.shows.Episode;
import com.example.shows.EpisodesRepository;
import com.example.shows.Show;
import com.example.shows.ShowsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * Created by Praveen Thati on 5/23/17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ViewingServiceTests {

    @Autowired
    ViewingsService viewingsService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ShowsRepository showsRepository;

    @Autowired
    private EpisodesRepository episodesRepository;

    @Autowired
    private ViewingsRepository viewingsRepository;

    @Test
    @Rollback
    @Transactional
    public void testPatchViewing() throws Exception {

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


        ProgessMessage pm = new ProgessMessage();
        pm.setEpisodeId(episode.getId());
        pm.setUserId(user.getId());
        pm.setOffset(100);
        pm.setCreatedAt(new Date());
        viewingsService.patchViewings(pm);

        assert(viewingsService.getUsersRecentViewing(user.getId()).size() == 1);


    }

}
