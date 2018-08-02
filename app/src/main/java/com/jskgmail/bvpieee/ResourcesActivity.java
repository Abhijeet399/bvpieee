package com.jskgmail.bvpieee;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResourcesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ieee_videos");
        DatabaseReference myRef1 = myRef.child("cs");
        myRef1.child("Android development 5").setValue("youtube.com");
        DatabaseReference myRef11 = myRef.child("wie");
        myRef11.child("Android development 2").setValue("youtube.com");
        DatabaseReference myRef12 = myRef.child("ias");
        myRef12.child("Android development 3").setValue("youtube.com");
        DatabaseReference myRef13 = myRef.child("hkn");
        myRef13.child("Android development 4").setValue("youtube.com");


        DatabaseReference myRef2 = database.getReference("All events");
        DatabaseReference myRef3 = myRef2.child("1108");
        myRef3.child("topic").setValue("orientation");
        myRef3.child("date").setValue("youtube.com");
        myRef3.child("time").setValue("youtube.com");
        myRef3.child("by").setValue("youtube.com");

        //   myRef.child("2").setValue("82gurcharansingh@gmail.com");





        VideoView videoView = (VideoView) findViewById(R.id.videoView);

        String vidAddress = "http://download.itcuties.com/teaser/itcuties-teaser-480.mp4";

        Uri videoUri = Uri.parse(vidAddress);

        MediaController mediaController = new MediaController(this); mediaController.setAnchorView(videoView); videoView.setMediaController(mediaController);

        videoView.setVideoURI(videoUri); videoView.start();













    }
}
