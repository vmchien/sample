package com.example.spring.test;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.photos.SearchParameters;

import java.io.IOException;
import java.util.Properties;

public class SearchExample {
    static String apiKey;

    static String sharedSecret;

    Flickr f;

    REST rest;

    RequestContext requestContext;

    Properties properties;

    public SearchExample() throws IOException {
        f = new Flickr("49431daf2475bbb39fd56281e133b944", "0740be4b2d43ba5b", new REST());
        requestContext = RequestContext.getRequestContext();
        Auth auth = new Auth();
        auth.setPermission(Permission.READ);
//        auth.setToken(properties.getProperty("token"));
//        auth.setTokenSecret(properties.getProperty("tokensec
        auth.setToken("72157720890774941-4b4fc8d9884e7ff2");
        auth.setTokenSecret("00c150ccc199bc88");
        requestContext.setAuth(auth);
        Flickr.debugRequest = false;
        Flickr.debugStream = false;
    }

    private void search(String text) throws FlickrException {
        PhotosInterface photos = f.getPhotosInterface();
        SearchParameters params = new SearchParameters();
        params.setMedia("photos"); // One of "photos", "videos" or "all"
//        params.setExtras(Stream.of("media").collect(Collectors.toSet()));
        params.setText(text);
        PhotoList<Photo> results = photos.search(params, 0, 0);

        results.forEach(p ->
        {
            System.out.println(String.format("Title: %s", p.getTitle()));
            System.out.println(String.format("Media: %s", p.getMedia()));
            System.out.println(String.format("Original Video URL: %s", p.getVideoOriginalUrl()));
        });

    }

    public static void main(String[] args) throws Exception {
        SearchExample t = new SearchExample();
        t.search(args.length == 0 ? "London" : args[0]);
    }

}