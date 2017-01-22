package com.abhinav.kadiyala.yalephotos;

/**
 * Created by Kadiyala
 */

import javax.xml.parsers.ParserConfigurationException;

import com.googlecode.flickrjandroid.Flickr;
import com.googlecode.flickrjandroid.REST;
import com.googlecode.flickrjandroid.RequestContext;
import com.googlecode.flickrjandroid.groups.GroupsInterface;
import com.googlecode.flickrjandroid.interestingness.InterestingnessInterface;
import com.googlecode.flickrjandroid.oauth.OAuth;
import com.googlecode.flickrjandroid.oauth.OAuthToken;
import com.googlecode.flickrjandroid.photos.Photo;
import com.googlecode.flickrjandroid.photos.PhotoList;
import com.googlecode.flickrjandroid.photos.PhotosInterface;
import com.googlecode.flickrjandroid.photosets.Photoset;

public final class FlickrHelper {

    private static FlickrHelper instance = null;
    private static final String API_KEY = "cd1b82623b40cf1db10af8f563e92598";
    public static final String API_SEC = "b41f82fd00faf5bd";

    private FlickrHelper() {

    }

    public static FlickrHelper getInstance() {
        if (instance == null) {
            instance = new FlickrHelper();
        }

        return instance;
    }

    public Flickr getFlickr() {

        try {
            Flickr f = new Flickr(API_KEY, API_SEC);
            return f;
        } catch (Exception e) {
            return null;
        }
    }


    public PhotosInterface getPhotosInterface() {

        Flickr f = getFlickr();
        if (f != null) {
            return f.getPhotosInterface();
        } else {
            return null;
        }
    }

}