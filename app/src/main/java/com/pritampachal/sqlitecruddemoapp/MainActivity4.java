package com.pritampachal.sqlitecruddemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity4 extends AppCompatActivity {
    VideoView videoView;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        videoView=findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://"+getPackageName()+"/"+R.raw.myvideo);
        mediaController=new MediaController(MainActivity4.this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(mediaController);
        videoView.start();
    }
}
