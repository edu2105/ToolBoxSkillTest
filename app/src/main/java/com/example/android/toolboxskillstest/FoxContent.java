package com.example.android.toolboxskillstest;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.android.toolboxskillstest.adapters.BasicContentAdapter;
import com.example.android.toolboxskillstest.adapters.FoxPlayAdapter;
import com.example.android.toolboxskillstest.entries.CarouselModel;
import com.example.android.toolboxskillstest.interfaces.OnBasicContentItemListener;
import com.example.android.toolboxskillstest.loaders.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FoxContent extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Bitmap> {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private static final String JSON_FOX_CONTENT =
            "[{\"title\":\"Carousel thumb\",\"type\":\"thumb\",\"items\":[{\"title\":\"La Playa\"," +
                    "\"url\":\"http://placeimg.com/640/480/any\",\"video\":\"\"},{\"title\":\"" +
                    "Peligro En Bangkok\",\"url\":\"http://placeimg.com/640/480/any\",\"video\":" +
                    "\"https://download.blender.org/peach/bigbuckbunny_movies/BigBuckBunny_320x180.mp4" +
                    "\"},{\"title\":\"Todas Contra John\",\"url\":\"http://placeimg.com/640/480/any\"," +
                    "\"video\":\"\"},{\"title\":\"Quisiera Ser Millonario\",\"url\":\"" +
                    "http://placeimg.com/640/480/any\",\"video\":\"\"}]},{\"title\":\"Carousel Poster" +
                    "\",\"type\":\"poster\",\"items\":[{\"title\":\"Todas Contra John\",\"url\":" +
                    "\"http://placeimg.com/640/480/any\"},{\"title\":\"Todas Contra John\",\"url\":" +
                    "\"http://placeimg.com/640/480/any\"},{\"title\":\"Todas Contra John\",\"url\":" +
                    "\"http://placeimg.com/640/480/any\"},{\"title\":\"Todas Contra John\",\"url\":" +
                    "\"http://placeimg.com/640/480/any\"}]}]";

    private static final String BASIC_CONTENT_IMAGE_URL =
            "http://placeimg.com/320/480/any";

    private static final String FOX_PLAY_CONTENT_IMAGE_URL =
            "http://placeimg.com/640/480/any";

    private List<CarouselModel> carouselBasicContentModelList;
    private List<CarouselModel> carouselFoxPlayModelList;
    private RecyclerView posterRecyclerView;
    private RecyclerView thumbRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fox_content);

        carouselBasicContentModelList = new ArrayList<>();
        carouselFoxPlayModelList = new ArrayList<>();

        posterRecyclerView = (RecyclerView) findViewById(R.id.carousel_poster_list_view);
        thumbRecyclerView = (RecyclerView) findViewById(R.id.carousel_thumb_list_view);

        LinearLayoutManager layoutManagerBasicContent = new LinearLayoutManager(this);
        LinearLayoutManager layoutManagerFoxPlayContent = new LinearLayoutManager(this);

        layoutManagerBasicContent.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManagerFoxPlayContent.setOrientation(LinearLayoutManager.HORIZONTAL);

        posterRecyclerView.setHasFixedSize(true);
        thumbRecyclerView.setHasFixedSize(true);

        posterRecyclerView.setLayoutManager(layoutManagerBasicContent);
        thumbRecyclerView.setLayoutManager(layoutManagerFoxPlayContent);

        fetchFoxContentData(JSON_FOX_CONTENT);

        LoaderManager fetchBasicContentImageData = getLoaderManager();
        LoaderManager fetchFoxPlayContentImageData = getLoaderManager();

        fetchBasicContentImageData.initLoader(0, null, this);
        fetchFoxPlayContentImageData.initLoader(1, null, this);
    }

    @Override
    public Loader<Bitmap> onCreateLoader(int id, Bundle args) {
        if(id == 0) {
            Uri baseUri1 = Uri.parse(BASIC_CONTENT_IMAGE_URL);
            return new ImageLoader(this, baseUri1.toString());
        }else {
            Uri baseUri2 = Uri.parse(FOX_PLAY_CONTENT_IMAGE_URL);
            return new ImageLoader(this, baseUri2.toString());
        }
    }

    @Override
    public void onLoadFinished(Loader<Bitmap> loader, Bitmap data) {
        if(loader.getId() == 0) {
            BasicContentAdapter basicContentAdapter =
                    new BasicContentAdapter(
                            this,
                            carouselBasicContentModelList,
                            data);
            final ProgressBar basicContentProgressBar = (ProgressBar) findViewById(R.id.basic_contentprogress_bar);
            basicContentProgressBar.setVisibility(View.GONE);
            basicContentAdapter.setBasicContentListItems(new OnBasicContentItemListener() {
                @Override
                public void onBasicContentItemListener(int position) {
                    CarouselModel currentItem = carouselBasicContentModelList.get(position);
                    if(!currentItem.getVideo().equals("")) {
                        Intent intent = new Intent(FoxContent.this, VideoPlayer.class);
                        intent.putExtra("title", currentItem.getTitle());
                        intent.putExtra("url", currentItem.getVideo());
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(getBaseContext(), R.string.video_not_available, Toast.LENGTH_SHORT).show();
                    }
            });
            posterRecyclerView.setAdapter(basicContentAdapter);
        }
        else {
            FoxPlayAdapter foxPlayContentAdapter =
                    new FoxPlayAdapter(
                            this,
                            carouselFoxPlayModelList,
                            data);
            ProgressBar foxPlayProgressBar = (ProgressBar) findViewById(R.id.fox_play_progress_bar);
            foxPlayProgressBar.setVisibility(View.GONE);
            foxPlayContentAdapter.setFoxPlayContentItemListener(new OnBasicContentItemListener() {
                @Override
                public void onBasicContentItemListener(int position) {
                    CarouselModel currentItem = carouselFoxPlayModelList.get(position);
                    if(!currentItem.getVideo().equals("")) {
                        Intent intent = new Intent(FoxContent.this, VideoPlayer.class);
                        intent.putExtra("title", currentItem.getTitle());
                        intent.putExtra("url", currentItem.getVideo());
                        startActivity(intent);
                    }else
                        Toast.makeText(getBaseContext(), R.string.video_not_available, Toast.LENGTH_SHORT).show();
                }
            });
            thumbRecyclerView.setAdapter(foxPlayContentAdapter);
        }
    }

    @Override
    public void onLoaderReset(Loader<Bitmap> loader) {
        loader.reset();
    }

    /**
     * Fetch JSON String Message Content Data
     * @param jsonMessage JSON message on String format
     * @return List<CarouselModel> with all the data extracted
     */
    private void fetchFoxContentData(String jsonMessage)  {
        if(TextUtils.isEmpty(jsonMessage))
            return;

        List<CarouselModel> contentListToReturn = new ArrayList<>();
        String movieTitle;
        String type;
        String video;

        try{
            JSONArray root = new JSONArray(jsonMessage);
            for(int i=0; i<root.length(); i++) {
                JSONObject carousel = root.getJSONObject(i);
                type = carousel.getString("type");
                JSONArray carouselItemsArray = carousel.getJSONArray("items");
                for(int j=0; j<carouselItemsArray.length(); j++) {
                    JSONObject item = carouselItemsArray.getJSONObject(j);
                    movieTitle = item.getString("title");
                    if(item.has("video"))
                        video = item.getString("video");
                    else
                        video = "";
                    if(type.equals("thumb"))
                        carouselBasicContentModelList.add(
                                new CarouselModel(movieTitle, type, video));
                    else
                        carouselFoxPlayModelList.add(
                                new CarouselModel(movieTitle, type, video));
                }
            }
        }catch(JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the Fox Content JSON results", e);
        }
    }
}
