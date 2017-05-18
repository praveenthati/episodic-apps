package com.example.users;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Praveen Thati on 5/18/17.
 */
public interface ViewingsRepository extends CrudRepository<Viewing, Long> {
    List<Viewing> findAllByUserIdOrderByIdDesc(long userId);
    Viewing findOneByUserIdAndEpisodeId(long userId,long episodeId);
}
