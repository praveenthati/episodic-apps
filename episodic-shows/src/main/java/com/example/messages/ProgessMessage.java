package com.example.messages;

import java.util.Date;

/**
 * Created by Praveen Thati on 5/22/17.
 */
public class ProgessMessage {

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    private Long userId;
    private Long episodeId;
    private Date createdAt;
    private int offset;
}
