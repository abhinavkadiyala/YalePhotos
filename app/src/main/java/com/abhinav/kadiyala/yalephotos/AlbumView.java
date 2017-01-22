package com.abhinav.kadiyala.yalephotos;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.googlecode.flickrjandroid.photos.PhotoList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class AlbumView extends AppCompatActivity implements  LoadImageTask.Listener{

    ImageView flickrImage = null;
    Button prev;
    Button next;

    JSONObject jsonPhoto = null;
    String photoUrl = null;
    JSONArray arr = null;

    String photolist = null;

    int currentPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_view);

        flickrImage = (ImageView) findViewById(R.id.flickrImage);

        currentPhoto = 0;

        prev = (Button) findViewById(R.id.button);
        next = (Button) findViewById(R.id.button2);

        prev.bringToFront();
        next.bringToFront();



//        YaleFlickr yaleFlickr = new YaleFlickr();
//
//        PhotoList list = yaleFlickr.getAlbums().get(0);

        final String url = "https://api.flickr.com/services/rest/?method=flickr.photosets.getPhotos&api_key=027f5a09422014ff7850ab76c8974eb4&photoset_id=72157676622669966&user_id=12208415%40N08&format=json&nojsoncallback=1";

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                photolist = UrlReader.getUrlContents(url);
//                if(photolist == null)
//                {
//                    throw new RuntimeException("Cannot get url contents");
//                }
//
//            }
//        }).run();

        new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                photolist = UrlReader.getUrlContents(url);
                if(photolist == null)
                {
                    throw new RuntimeException("Cannot get url contents");
                }

                return null;
            }
        }.execute();

        // wait for jsonobject
        while(photolist == null);

        if(photolist == null)
            throw new RuntimeException("photoList is null");

        JSONObject photos = null;
        try {
            photos = new JSONObject(photolist);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        arr = null;
        try {
            arr = photos.getJSONObject("photoset").getJSONArray("photo");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        jsonPhoto = null;
        try {
            jsonPhoto = arr.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        photoUrl = null;
        try {
            photoUrl = YaleFlickr.getPictureUrl(jsonPhoto.getString("farm"),jsonPhoto.getString("server"),
                    jsonPhoto.getString("id"),jsonPhoto.getString("secret"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new LoadImageTask(this).execute(photoUrl);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPhoto > 0) {
                    --currentPhoto;
                    try {
                        jsonPhoto = arr.getJSONObject(currentPhoto);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        photoUrl = YaleFlickr.getPictureUrl(jsonPhoto.getString("farm"),jsonPhoto.getString("server"),
                                jsonPhoto.getString("id"),jsonPhoto.getString("secret"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    new LoadImageTask(AlbumView.this).execute(photoUrl);
                }

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPhoto < 81) {
                    ++currentPhoto;
                    try {
                        jsonPhoto = arr.getJSONObject(currentPhoto);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        photoUrl = YaleFlickr.getPictureUrl(jsonPhoto.getString("farm"),jsonPhoto.getString("server"),
                                jsonPhoto.getString("id"),jsonPhoto.getString("secret"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    new LoadImageTask(AlbumView.this).execute(photoUrl);
                }

            }
        });
    }

    @Override
    public void onImageLoaded (Bitmap bmap){
        flickrImage.setImageBitmap(bmap);
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Error Loading Image !", Toast.LENGTH_SHORT).show();
    }

}
