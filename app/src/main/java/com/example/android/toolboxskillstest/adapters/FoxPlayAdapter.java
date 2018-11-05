package com.example.android.toolboxskillstest.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.toolboxskillstest.interfaces.OnBasicContentItemListener;
import com.example.android.toolboxskillstest.R;
import com.example.android.toolboxskillstest.entries.CarouselModel;

import java.util.List;

public class FoxPlayAdapter extends RecyclerView.Adapter<CarouselHolder> {

    private List<CarouselModel> foxPlayContentList;
    private Context context;
    private Bitmap posterImage;
    private OnBasicContentItemListener onFoxPlayContentItemListener;

    public FoxPlayAdapter(Context context, List<CarouselModel> foxPlayContentList, Bitmap posterImage) {
        this.foxPlayContentList = foxPlayContentList;
        this.context = context;
        this.posterImage = posterImage;
    }

    public void setFoxPlayContentItemListener(OnBasicContentItemListener onFoxPlayContentItemListener) {
        this.onFoxPlayContentItemListener = onFoxPlayContentItemListener;
    }

    @NonNull
    @Override
    public CarouselHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fox_play_list_item, parent,false);
        return new CarouselHolder(v, onFoxPlayContentItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CarouselHolder holder, int position) {
        String posterTitle;
        CarouselModel currentCarouselItem = foxPlayContentList.get(position);

        posterTitle = currentCarouselItem.getTitle()
                .replaceAll("[\\[|\\]|,]", "");
        holder.getThumbTitle().setText(posterTitle);
        holder.getThumbImageView().setImageBitmap(posterImage);
    }

    @Override
    public int getItemCount() {
        return foxPlayContentList.size();
    }

}
