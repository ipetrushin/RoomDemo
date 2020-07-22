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


    public Tune(int id, String artist, String title) {
        this.id = id;
        this.artist = artist;
        this.title = title;

    }

    @Override
    public String toString() { return artist + ": " + title ; }


}
