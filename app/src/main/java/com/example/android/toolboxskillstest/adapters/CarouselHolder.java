package com.example.android.toolboxskillstest.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.toolboxskillstest.interfaces.OnBasicContentItemListener;
import com.example.android.toolboxskillstest.R;

/**
 * CarouselHolder Class for RecyclerView
 */
public class CarouselHolder extends RecyclerView.ViewHolder {

    private TextView posterTitle;
    private ImageView posterImageView;
    private TextView thumbTitle;
    private ImageView thumbImageView;

    public CarouselHolder(final View itemView, final OnBasicContentItemListener onBasicContentItemListener) {
        super(itemView);

        posterTitle = (TextView) itemView.findViewById(R.id.poster_carousel_title);
        posterImageView = (ImageView) itemView.findViewById(R.id.poster_carousel_image);
        thumbTitle = (TextView) itemView.findViewById(R.id.thumb_carousel_title);
        thumbImageView = (ImageView) itemView.findViewById(R.id.thumb_carousel_image);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();

                if(position != RecyclerView.NO_POSITION)
                    onBasicContentItemListener.onBasicContentItemListener(position);
            }
        });
    }

    public TextView getTitleTextView() { return posterTitle; }

    public ImageView getPosterImageView() { return posterImageView; }

    public TextView getThumbTitle() { return thumbTitle; }

    public ImageView getThumbImageView() { return thumbImageView; }
}
