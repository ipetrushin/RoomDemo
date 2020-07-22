package com.example.roomdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TunesDB db = TunesDB.create(this, true);

        db = Room.databaseBuilder(this, TunesDB.class, "tunes").allowMainThreadQueries().build();
        //
        //Playlist playlist = db.playlist();
        //db.query("INSERT INTO tunes VALUES (1, \"U2\", \"STorm\", 100, 1)", null);

        Log.d("mytag", "Inserting: ");
        //playlist.insert(new Tune(100, "U2", "Storm"));
        Cursor c = db.query("SELECT * FROM tunes", null);
        Log.d("mytag", "Records: "+c.getCount());
        //Log.d("mytag", "Playlist: "+playlist.);

    }
}
