package com.example.android.toolboxskillstest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.android.toolboxskillstest.entries.CarouselModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Helper Class to fetch data from a URL
 */
public class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private QueryUtils(){
    }

    public static Bitmap fetchImageData(String requestedUrl) {

        URL url = createUrl(requestedUrl);
        Bitmap jsonImageResponse = null;

        try {
            jsonImageResponse = makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonImageResponse;
    }

    /**
     * Helper method to create a URL instance
     * @param stringUrl URL in String format
     * @return URL instance
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;

        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * Recover bitmap data from a URL
     * @param url URL instance
     * @return Bitmap file
     * @throws IOException if openConnection() fails.
     */
    private static Bitmap makeHttpRequest(URL url) throws IOException{
        Bitmap jsonImageResponse = null;

        if(url == null)
            return null;

        try{
                jsonImageResponse = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            }catch (IOException e) {
                Log.e(LOG_TAG, "Problem retrieving the pokemon JSON results", e);
            }
        return jsonImageResponse;
    }

}
