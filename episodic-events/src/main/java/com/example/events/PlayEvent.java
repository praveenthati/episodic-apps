package com.example.events;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class PlayEvent extends Event {

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
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
