package com.prashant.showcaseviewpager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import java.util.ArrayList;

public class ImageSliderActivity extends AppCompatActivity {

    ImageSliderFullScreen imageSlider;
    private static String ARGS_INIT_POS="com.prashantsolanki.showcaseviewpager.ImageSliderActivity.initPos";
    private static String ARGS_IMAGE_URL="com.prashantsolanki.showcaseviewpager.ImageSliderActivity.imageUrl";
    public static String ARGS_RESULT_POSITION = "com.prashantsolanki.showcaseviewpager.ImageSliderActivity.resultPos";
    public static final int RESULT_CODE=9364;
    ArrayList<String> imageUrls;
    int startingPos;

    public static Intent createIntent(Context context,ArrayList<String> imageUrls,int startingPos){
        Intent intent = new Intent(context,ImageSliderActivity.class);
        intent.putExtra(ARGS_INIT_POS,startingPos);
        intent.putStringArrayListExtra(ARGS_IMAGE_URL,imageUrls);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_image_slider);
        if(getIntent()!=null){
            imageUrls = getIntent().getStringArrayListExtra(ARGS_IMAGE_URL);
            startingPos = getIntent().getIntExtra(ARGS_INIT_POS, 0);
        }

        imageSlider = (ImageSliderFullScreen) findViewById(R.id.fullscreen_vp);

        imageSlider.init(getSupportFragmentManager(), imageUrls);
        imageSlider.setInitialPosition(startingPos);

    }

    @Override
    protected void onStop() {
        sendResult();
        super.onStop();
    }

    void sendResult(){
        Intent intent = new Intent();
        intent.putExtra(ARGS_RESULT_POSITION,imageSlider.getCurrentPosition());
        setResult(RESULT_CODE,intent);
    }

    @Override
    public void onBackPressed() {
        sendResult();
        super.onBackPressed();
    }
}
