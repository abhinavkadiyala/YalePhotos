package com.abhinav.kadiyala.yalephotos;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.icu.util.IslamicCalendar;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.googlecode.flickrjandroid.*;
import com.googlecode.flickrjandroid.groups.pools.*;
import com.googlecode.flickrjandroid.photos.Photo;
import com.googlecode.flickrjandroid.photos.PhotoList;
import com.googlecode.flickrjandroid.photos.PhotosInterface;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = (Button) findViewById(R.id.button3);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,AlbumView.class);
                startActivity(i);
            }
        });

//
//        InputStream in = null;
//        try {
//            in = new URL(url).openStream();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Bitmap img = BitmapFactory.decodeStream(in);
//
//
//        flickrImage.setImageBitmap(img);

//        URL url = null;
//        try {
//            url = new URL(address);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        InputStream content = null;
//        try {
//            content = (InputStream)url.getContent();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Drawable d = Drawable.createFromStream(content , "src");
//        flickrImage.setImageDrawable(d);

//        Thread thread = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                try  {
//                    URL url = null;
//                    try {
//                        url = new URL(address);
//                    } catch (MalformedURLException e) {
//                        e.printStackTrace();
//                    }
//                    InputStream content = null;
//                    try {
//                        content = (InputStream)url.getContent();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    Drawable d = Drawable.createFromStream(content , "src");
//                    flickrImage.setImageDrawable(d);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        thread.start();
//
//
//    }

    }

}
