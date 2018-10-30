package com.nicfeanny.youtubeplayer;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    static final String GOOGLE_API_KEY = "AIzaSyCwq26i3mBum_WIzbCbMkdl_iC2XXdLasE";
    static final String YOUTUBE_VIDEO_ID = "NH9Pu69PPFg";
    static final String YOUTUBE_PLAYLIST = "TODO";
    private static final String TAG = "YoutubeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_youtube);
        //ConstraintLayout constraintLayout = findViewById(R.id.activity_youtube);
        //This line below does the same thing as the two previous lines above (The ones commented out)
        //setContentView inflates the xml passed in and sets the acitivity's view to be the view it creates from the xml
        //conversely, inflates the xml and assigns the view that is created to our constraint layout variable then passes the view to an overloaded version of setContentView
        //The end result is the same but the avenues is which you can complete this task exists in many forms
        //**The warning for root = null is okay here as this is the root view as it is the ROOT layout
        ConstraintLayout layout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_youtube, null);
        setContentView(layout);

        //this is the context in which the button is being created in, i.e., THIS layout view
        /*Button button1 = new Button(this);
        button1.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
        button1.setText("Button Added");
        layout.addView(button1);*/

        YouTubePlayerView youTubePlayerView = new YouTubePlayerView(this);
        youTubePlayerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(youTubePlayerView);
        youTubePlayerView.initialize(GOOGLE_API_KEY, this);



    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {

        /*
        * when you get an object as a parameter, we can determine what kind of object it is by passing the
        * "getClass() method; we are using toString() because we want to be able to print the object out.
        * Toast to let the user know the success
        * */
        Log.d(TAG, "onInitializationSuccess: provider is " + provider.getClass().toString());
        Toast.makeText(this, "initialize YouTube Player successful", Toast.LENGTH_LONG);


        /*listeners for the setPlaybackEventListener and setPlayerStateChangeListener
        * interfaces*/
        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);


        /*
        * we only want to play a new video if the player isn;'t resuming playback
        * of an existing video; if wasRestored was false then we call cueVideo() to play the
        * new video
        * */
        if(!wasRestored){
            youTubePlayer.cueVideo(YOUTUBE_VIDEO_ID);
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        final int REQUEST_CODE = 1;

        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show();
        }else {
            String errorMessage = String.format("There was an error initializing the youtube player (%1$s)", youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }


    //this is an interface and so we HAVE to implement ALL the methods within the interface!!
    //These methods within the interface will be called automatically in the event the specified behavior occurs
    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {
            Toast.makeText(YoutubeActivity.this, "The video is playing okay!", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPaused() {
            Toast.makeText(YoutubeActivity.this, "The video is paused!", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onStopped() {
            Toast.makeText(YoutubeActivity.this, "The video has stopped!", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    /*

    */
    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {
            Toast.makeText(YoutubeActivity.this, "Click Ad now!", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onVideoStarted() {
            Toast.makeText(YoutubeActivity.this, "Video has started!", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onVideoEnded() {
            Toast.makeText(YoutubeActivity.this, "!The video has ended!", Toast.LENGTH_LONG).show();

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };

}
