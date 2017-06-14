package com.example.cho.myself;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by cho on 2015-05-26.
 */
public class Stageload extends Activity {
    public Stage1 blockgen1;
    public Stage2 blockgen2;
    public Stage3 blockgen3;
    public Stageselect stageselet;
    static public MediaPlayer click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        blockgen1= new Stage1(this);
        blockgen2= new Stage2(this);
        blockgen3= new Stage3(this);

        stageselet = new Stageselect();
        setContentView(R.layout.stage1);


        if(stageselet.getStage()==1)
        setContentView(blockgen1);

        if(stageselet.getStage()==2)
            setContentView(blockgen2);

        if(stageselet.getStage()==3)
            setContentView(blockgen3);


        click = MediaPlayer.create(this, R.raw.click);
        click.setLooping(false);
        Stageload.click.start();


    }



}
