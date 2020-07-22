package com.example.roomdemo;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "tunes")
class Tune {
    @PrimaryKey
    @NonNull
    int id;
    String artist, title;
    int duration;
    float rating;

    public Tune(int id, String artist, String title, int duration, float rating) {
        this.id = id;
        this.artist = artist;
        this.title = title;
        this.duration = duration;
        this.rating = rating;
    }

    public String toSting() { return artist + ": " + title ; }


}
