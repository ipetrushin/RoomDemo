package com.example.roomdemo;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Locale;

@Entity(tableName = "tunes")
class Tune {
    @PrimaryKey
    @NonNull
    int _id;
    String artist, title;
    int year;

    // ignore annotation here

    public Tune(int _id, String artist, String title, int year) {
        this._id = _id;
        this.artist = artist;
        this.title = title;
        this.year = year;

    }

    @Override
    public String toString() { return String.format(Locale.getDefault(), "%s: %s (%d)", artist, title , year); }


}
