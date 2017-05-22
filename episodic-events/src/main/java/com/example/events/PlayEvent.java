package com.example.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class PlayEvent extends Event {

    @JsonCreator
    public PlayEvent(@JsonProperty("userId") Long userId,@JsonProperty("showId")Long showId,@JsonProperty("episodeId")Long episodeId,@JsonProperty("createdAt")Date createdAt,@JsonProperty("data")Data data){

        super(userId,showId,episodeId,createdAt);
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    private Data data;

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
