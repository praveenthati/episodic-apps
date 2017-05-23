package com.example.users;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Praveen Thati on 5/18/17.
 */
@Entity(name = "Viewings")
public class Viewing {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(Long episodeId) {
        this.episodeId = episodeId;
    }

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSS")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getTimecode() {
        return timecode;
    }

    public void setTimecode(int timecode) {
        this.timecode = timecode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long episodeId;
    private Date updatedAt;
    private int timecode;

}
