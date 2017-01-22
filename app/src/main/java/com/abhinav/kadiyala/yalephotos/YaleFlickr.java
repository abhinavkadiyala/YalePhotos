package com.abhinav.kadiyala.yalephotos;

import android.provider.Settings;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.FlickrException;
import com.googlecode.flickrjandroid.photos.PhotoList;
import com.googlecode.flickrjandroid.photos.PhotosInterface;
import com.googlecode.flickrjandroid.photosets.Photoset;
import com.googlecode.flickrjandroid.photosets.Photosets;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Kadiyala on 1/21/17.
 */

public class YaleFlickr {

    private Flickr flickr;
    private String id = "12208415@N08";

    public YaleFlickr()
    {
        flickr = FlickrHelper.getInstance().getFlickr();
    }

    public ArrayList<PhotoList> getAlbums() {
        Collection<Photoset> collection = null;
        try {
            collection = flickr.getPhotosetsInterface().getList(id).getPhotosets();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FlickrException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(collection == null)
            System.exit(1);

        ArrayList<PhotoList> list = new ArrayList<PhotoList>();

        for(Photoset set : collection)
        {
            list.add(set.getPhotoList());
        }

        return list;
    }

    public static String getPictureUrl(String farmId, String serverId, String id, String secret)
    {
        return "https://farm" + farmId +
                ".staticflickr.com/"+serverId+"/"+id+ "_"+secret+".jpg";
    }
}
