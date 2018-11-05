package com.example.android.toolboxskillstest.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.graphics.Bitmap;

import com.example.android.toolboxskillstest.QueryUtils;
import com.example.android.toolboxskillstest.entries.CarouselModel;

import java.util.List;

/**
 * ImageLoader AsyncTask Class in order to fetch data from a URL outside the Main Thread
 */
public class ImageLoader extends AsyncTaskLoader<Bitmap>{

    private String url;

    public ImageLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public Bitmap loadInBackground() {
        if(url == null)
            return null;
        return QueryUtils.fetchImageData(url);
    }
}
