package com.example.android.toolboxskillstest;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoPlayer extends AppCompatActivity {

    VideoView videoView;
    ProgressBar videoProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        String videoTitle = getIntent().getStringExtra("title");
        String videoUrl = getIntent().getStringExtra("url");

        TextView videoTitleTextView = (TextView) findViewById(R.id.video_title);
        videoTitleTextView.setText(videoTitle);

        videoView = (VideoView) findViewById(R.id.video_view);
        videoProgressBar = (ProgressBar) findViewById(R.id.video_progress_bar);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse(videoUrl));
        videoView.start();
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoProgressBar.setVisibility(View.GONE);
                videoView.start();
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
                videoView.stopPlayback();
            }
        });
    }
}
