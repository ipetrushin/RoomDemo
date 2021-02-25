package com.example.roomdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
        SimpleCursorAdapter adapter =
                new SimpleCursorAdapter(this, R.layout.tune_item, c, c.getColumnNames(), new int[]{R.id._id, R.id.artist, R.id.title, R.id.year}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        ListView lv = findViewById(R.id.listview);
        lv.setAdapter(adapter);

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ColorDrawable d = (ColorDrawable) view.getBackground();
                if (d != null && d.getColor() == Color.YELLOW) {
                    view.setBackgroundColor(Color.TRANSPARENT);
                } else view.setBackgroundColor(Color.YELLOW);
                // TODO задание: удалить запись, которая выделена пользователем (position - номер в списке)

                Cursor cur = adapter.getCursor();
                int index = cur.getInt(0);
                Log.d("mytag", "clicked on id" + index);


            }
        };
        lv.setOnItemClickListener(listener);


        //setCursorInUIThread(c);

        // задание: создать метод для генерации случайных продуктов и
        // вставки этих данных в таблицу

    }
    public void setCursorInUIThread(Cursor c) {
        Context ctx = getApplicationContext();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SimpleCursorAdapter adapter =
                        new SimpleCursorAdapter(ctx, R.layout.tune_item, c, c.getColumnNames(), new int[]{R.id._id, R.id.artist, R.id.title, R.id.year}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
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

                Playlist playlist = db.playlist();

                Random r = new Random();
                Tune t = new Tune(r.nextInt(10000), "The Prodigy", "Matrix theme", 2000);
                playlist.insert(t);

                Cursor c = db.query("SELECT * FROM tunes", null);
                Log.d("mytag", "Records after insert: "+c.getCount());
                setCursorInUIThread(c);
            }
        }.start(); // запустит созданный поток
    }
}
