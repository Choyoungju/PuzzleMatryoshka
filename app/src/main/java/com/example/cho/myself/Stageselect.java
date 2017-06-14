package com.example.cho.myself;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Created by cho on 2015-05-18.
 */
public class Stageselect extends Activity {
    public Stage1 blockgen1;
    public Stage2 blockgen2;
    public Stage3 blockgen3;




    public static int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.stageselect);
        blockgen1= new Stage1(this);
        blockgen2 = new Stage2(this);
        blockgen3 = new Stage3(this);


      //setContentView(blockgen1); // Stage1 Connect

        //setContentView(blockgen2);
       // setContentView(R.layout.stage2layout);
         // Stage1 Connect
     //   setContentView(R.layout.b);
    }






    public void onClick(View v){
        switch(v.getId()){
            case R.id.bun1:
                //setContentView(R.layout.stage1);
                level = 1;
                Intent intent1 = new Intent(this, Stageload.class);
                MainActivity.mp.stop();
                startActivity(intent1);

  //              setContentView(blockgen1);

                break;
            case R.id.bun2:
                level= 2;

               Intent  intent2 = new Intent(this, Stageload.class);
                startActivity(intent2);
                break;

            case R.id.bun3:
                level= 3;

                Intent  intent3 = new Intent(this, Stageload.class);
                startActivity(intent3);
                break;
//                case R.id.textView3
//                    break;
        }
    }

    public int getStage(){
        Log.d("Debug : ", "getStage = " + ',' + level);
        return level;
    }


//    public void onClick(View view) {
//        Intent intent = new Intent(this, Stageload.class);
//        startActivity(intent);
//    }

}
