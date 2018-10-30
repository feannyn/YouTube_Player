package com.nicfeanny.youtubeplayer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.google.android.youtube.player.YouTubeStandalonePlayer;

public class StandaloneActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_standalone);

        Button playVideo = findViewById(R.id.buttonPlayVideo);
        Button playList = findViewById(R.id.buttonPlayList);

        playVideo.setOnClickListener(this);
        playList.setOnClickListener(this);

        /*
        * Contrary to the above, this creates an anonymous inner class and assign an instance of it to a variable
        * this is fine when you want to assign the same behavior to multiple widgets (like in the calculator app)
        * */
        /*View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };

        playVideo.setOnClickListener(onClickListener);
        playList.setOnClickListener(onClickListener);

        The other way of achieving this(the above), is using an anonymous class without assigning an instance to a variable.
        The object is not given a name, it is instantiated using new and then passed directly
        to the setOnClickListener and this listener can only be assigned to a single button

        IN BOTH CASES WHAT WE ARE DOING IS PASSING TO THE SETONCLICKLISTENER IS AN OBJECT THAT IMPLEMENTS
        THE ONCLICKLISTENER INTERFACE AND PROVIDES AN ONCLICK METHOD WHICH IS CALLED WHEN THE BUTTON IS TAPPED

        as we did initially, the standalone activity class was able to implement the OnclickListener interface
        when our activity is created we can refer to that instance using the keyword "this"

        all three of the ways achieve the same goal, however, the difference is that the on line delcaration and
        initialization can only be used for one button(below); whereas the other two can be used for multiple buttons

        playVideo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

            }
        })*/

    }

    @Override
    public void onClick(View v) {

        /*
        * An intent, the code checks to see what button has been pressed, i.e., "what's the intent"
        * it then sets the data for that "intent" BEFORE calling "start activity"
        *
        *
        * MORE on INTENTS
        *   Google API -> " an abstract description of an operation to be performed.
        *       "abstract" meaning it is not tied to an particular operation
        *       "a messaging object you can use to request an action from another app."
        *       it can be used with startActivity to launch an activity
        *       also can be used to send data to components to receive broadcasts
        *       such as a text message/notifications that have been received
        *       you can use an intent to launch an activity with linking your code directly to said activity
        *       if you wanted to launch email in the app, you can just specify email in the app
        *       without having to know what app the user has installed to run the email app.
        *       "It is the glue between activities, i.e., the intents act to bind activities together"
        *
        *       In the case of our app you can create an intent, but we don't have to because
        *       the youtube standalone app create them for us. so if we want an intent to play video
        *       we just call the "create video intent method" which will return an intent which can then be used
        *       in our code to start an activity to perform the operation we want.
        *
        *       both methods need to know which activity to start in the intent which is why we pass "this"
        *       in the parameter list.
        *
        *       we passed in the google_api key as well as the unique id of what we wanted to load
        *
        * */
        Intent intent = null;

        switch(v.getId()){
            case R.id.buttonPlayVideo:
                intent = YouTubeStandalonePlayer.createVideoIntent(this, YoutubeActivity.GOOGLE_API_KEY, YoutubeActivity.YOUTUBE_VIDEO_ID);
                break;
            case R.id.buttonPlayList:
                intent = YouTubeStandalonePlayer.createPlaylistIntent(this, YoutubeActivity.GOOGLE_API_KEY, YoutubeActivity.YOUTUBE_PLAYLIST);
                break;
            default:

        }

        if(intent != null){
            startActivity(intent);
        }
    }
}
