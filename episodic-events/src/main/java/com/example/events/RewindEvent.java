package com.example.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by Praveen Thati on 5/22/17.
 */
public class RewindEvent extends Event {

    @JsonCreator
    public RewindEvent(@JsonProperty("userId") Long userId,@JsonProperty("showId")Long showId,@JsonProperty("episodeId")Long episodeId,@JsonProperty("createdAt")Date createdAt,@JsonProperty("data")Data data){

        super(userId,showId,episodeId,createdAt);
        this.data = data;
    }

    private Data data;

    public Data getData() {
        return data;
    }

    static class Data{
        public int getStartOffset() {
            return startOffset;
        }

        public void setStartOffset(int startOffset) {
            this.startOffset = startOffset;
        }

        public int getEndOffset() {
            return endOffset;
        }

        public void setEndOffset(int endOffset) {
            this.endOffset = endOffset;
        }

        private int startOffset;
        private int endOffset;

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        private  double speed;
    }
}
