package com.example.roomdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TunesDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = TunesDB.create(this, false); // открывает БД, если её нет, создаёт

        // отобразим данные в БД на ListView
        Cursor c = db.query("SELECT * FROM tunes", null);
        setCursorInUIThread(c);

    }
    public void setCursorInUIThread(Cursor c) {
        Context ctx = getApplicationContext();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                SimpleCursorAdapter adapter = new SimpleCursorAdapter(ctx, R.layout.tune_item, c, c.getColumnNames(), new int[]{R.id._id, R.id.artist, R.id.title, R.id.year}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                Log.d("mytag", "Records in adapter: " + adapter.getCount());
                ListView lv = findViewById(R.id.listview);
                lv.setAdapter(adapter);
            }
        });

    }


    public void onClearClick(View v) {
        new Thread() {
            @Override
            public void run() {
                db.clearAllTables();
                Cursor c = db.query("SELECT * FROM tunes", null);
                setCursorInUIThread(c);

            }
        }.start();

    }

    public void onAddTuneClick(View v) {
        new Thread() {
            @Override
            public void run() {
                Cursor c = db.query("SELECT * FROM tunes", null);
                Log.d("mytag", "Records before insert: "+c.getCount());
                Playlist playlist = db.playlist();

                Random r = new Random();
                playlist.insert(new Tune(r.nextInt(10000), "The Prodigy", "Matrix theme", 2000));

                c = db.query("SELECT * FROM tunes", null);
                Log.d("mytag", "Records after insert: "+c.getCount());
                setCursorInUIThread(c);
            }
        }.start(); // запустит созданный поток
    }
}
