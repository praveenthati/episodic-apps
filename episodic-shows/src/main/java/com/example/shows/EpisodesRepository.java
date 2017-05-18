package com.example.shows;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Praveen Thati on 5/18/17.
 */
public interface EpisodesRepository extends CrudRepository<Episode, Long>{
    Iterable<Episode> getAllByShowId(long showId);
}
