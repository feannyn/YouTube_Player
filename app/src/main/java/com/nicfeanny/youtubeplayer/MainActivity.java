package com.nicfeanny.youtubeplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSingle = findViewById(R.id.buttonPlaySingle);
        Button buttonStandalone = findViewById(R.id.buttonStandalone);
        buttonSingle.setOnClickListener(this);
        buttonStandalone.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {

       /*
       * similar to standalone class
       * however, we created our own intents.
       * the constructor here, we provide a context "this"
       * and then we tell the constructor which activity to start
       *
       * "YoutubeActivity.class is called a class literal"
       *    it a way to pass reference to a class as a parameter
       *    you want to pass "1" or "hello"...this is the same thing in a way
       *
       *    when you want to pass a class to a function as an argument you have to use.class at
       *    the end to clarify this
       *
       * */

        Intent intent = null;

        switch(v.getId()){
            case R.id.buttonPlaySingle:
                intent = new Intent(this, YoutubeActivity.class);
                break;
            case R.id.buttonStandalone:
                intent = new Intent(this, StandaloneActivity.class);
                break;
            default:
        }

        if(intent != null){
            startActivity(intent);
        }

    }
}
