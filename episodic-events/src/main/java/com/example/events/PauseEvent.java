package com.example.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by Praveen Thati on 5/22/17.
 */
public class PauseEvent extends Event {

    @JsonCreator
    public PauseEvent(@JsonProperty("userId") Long userId,@JsonProperty("showId")Long showId,@JsonProperty("episodeId")Long episodeId,@JsonProperty("createdAt")Date createdAt,@JsonProperty("data")Data data){

        super(userId,showId,episodeId,createdAt);
        this.data = data;
    }

    private Data data;

    public Data getData() {
        return data;
    }

    static class Data{

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        private int offset;



    }
}
