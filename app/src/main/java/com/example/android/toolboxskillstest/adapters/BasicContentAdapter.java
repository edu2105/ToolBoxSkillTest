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

public class BasicContentAdapter extends RecyclerView.Adapter<CarouselHolder> {

    private List<CarouselModel> basicContentListItems;
    private Context context;
    private Bitmap posterImage;
    private OnBasicContentItemListener onBasicContentItemListener;

    public BasicContentAdapter(Context context, List<CarouselModel> basicContentListItems, Bitmap posterImage) {
        this.basicContentListItems = basicContentListItems;
        this.context = context;
        this.posterImage = posterImage;
    }

    public void setBasicContentListItems(OnBasicContentItemListener onBasicContentItemListener) {
        this.onBasicContentItemListener = onBasicContentItemListener;
    }

    @NonNull
    @Override
    public CarouselHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fox_basic_content_list_item, parent,false);
        return new CarouselHolder(v, onBasicContentItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CarouselHolder holder, int position) {
        String posterTitle;
        CarouselModel currentCarouselItem = basicContentListItems.get(position);

        posterTitle = currentCarouselItem.getTitle()
                .replaceAll("[\\[|\\]|,]", "");
        holder.getTitleTextView().setText(posterTitle);
        holder.getPosterImageView().setImageBitmap(posterImage);
    }

    @Override
    public int getItemCount() {
        return basicContentListItems.size();
    }

}
