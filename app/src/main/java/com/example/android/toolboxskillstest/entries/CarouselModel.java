package com.example.android.toolboxskillstest.entries;

/**
 * Entries for Carousels
 */
public class CarouselModel {

    private String title;
    private String type;
    private String video;

    public CarouselModel(String title, String type, String video) {
        this.title = title;
        this.type = type;
        this.video = video;
    }

    public String getTitle() { return title; }
    public String getType() { return type; }
    public String getVideo() { return video; }
}
