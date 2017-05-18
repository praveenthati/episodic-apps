package com.example.shows;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Praveen Thati on 5/18/17.
 */
@Service
public class ShowsService {

    @Autowired
    private ShowsRepository showsRepository;

    public List<Show> getAllShows(){

        List<Show> list = new ArrayList<>();
        showsRepository.findAll().forEach(list::add);
        return list;

    }

    public Show addShow(Show show){
        return showsRepository.save(show);
    }

    public Show getShowById(long id){
        return showsRepository.findOne(id);
    }
}
