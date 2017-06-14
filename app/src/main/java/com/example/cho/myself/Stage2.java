package com.example.cho.myself;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


public class Stage2 extends View {

    private Bitmap block[] = new Bitmap[12];
    boolean guide = true;

    final static char F = 6;

    private float x1 = 0;
    private float y1 = 93 * 1;

    private float x2 = 93 * 1;
    private float y2 = 0;
    private float x3 = 93 * 2;
    private float y3 = 0;
    private float x4 = 93 * 3;
    private float y4 = 0;

    private float x5 = 93 * 2;
    private float y5 = 93 * 3;


    float px1 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, x1, getResources().getDisplayMetrics()) + 500;
    float py1 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, y1, getResources().getDisplayMetrics());
    float px2 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, x2, getResources().getDisplayMetrics()) + 500;
    float py2 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, y2, getResources().getDisplayMetrics());
    float px3 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, x3, getResources().getDisplayMetrics()) + 500;
    float py3 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, y3, getResources().getDisplayMetrics());
    float px4 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, x4, getResources().getDisplayMetrics()) + 500;
    float py4 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, y4, getResources().getDisplayMetrics());
    float px5 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, x5, getResources().getDisplayMetrics()) + 500;
    float py5 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, y5, getResources().getDisplayMetrics());


    boolean stageClear = false;

    int[][] lv1 = {
            {0, 2, 10, 2},
            {2, 1, 1, 8},
            {0, 1, 1, 9},
            {0, 0, F, 0}
    };


    int idx = 1;//블록인덱스값


    //초기화
    public Stage2(Context context) {
        super(context);

        Resources r = getResources();

        //맵 타일 읽기
        block[0] = null;
        block[1] = BitmapFactory.decodeResource(r, R.drawable.blank);
        block[2] = BitmapFactory.decodeResource(r, R.drawable.doll1);
        block[3] = BitmapFactory.decodeResource(r, R.drawable.push_doll1);
        block[4] = BitmapFactory.decodeResource(r, R.drawable.blank_doll1);
        block[5] = BitmapFactory.decodeResource(r, R.drawable.full_doll);
        block[6] = BitmapFactory.decodeResource(r, R.drawable.fire);
        block[7] = BitmapFactory.decodeResource(r, R.drawable.full_fire);
        block[8] = BitmapFactory.decodeResource(r, R.drawable.arrow_down);
        block[9] = BitmapFactory.decodeResource(r, R.drawable.arrow_left);
        block[10] = BitmapFactory.decodeResource(r, R.drawable.doll2);
        block[11] = BitmapFactory.decodeResource(r, R.drawable.push_doll2);
    }

    public void onDraw(Canvas canvas) {


        int w = block[1].getWidth() - 30;//블록 넓이
        int h = block[1].getHeight() - 30;//블록 높이
        int isClear = 0;

        if (guide == true){
            Toast.makeText(getContext().getApplicationContext(), "불 타일은 옮겨가며 지나온 길의 인형들을 태웁니다. ", Toast.LENGTH_SHORT).show();
            guide=false;
        }

        Bitmap backimage = BitmapFactory.decodeResource(getResources(), R.drawable.bg1);
        canvas.drawBitmap(backimage, 0, 0, null);

        isClear = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                idx = lv1[i][j];

                if (idx == 0)
                    continue;

                canvas.drawBitmap(block[idx], 500+ w * j, h * i, null);
                //   canvas.drawBitmap(block[idx], w * j, h * i, null);

                if (lv1[i][j] == 1 || lv1[i][j] == 2 || lv1[i][j] == 6)
                    isClear++;
                Log.d("Debug : ", "iv3 = " + lv1[i][j] + ',' + isClear);
            }
        }
        if (isClear == 0) {
            Toast.makeText(getContext().getApplicationContext(), "Cleared", Toast.LENGTH_SHORT).show();
            Bitmap clearimg = BitmapFactory.decodeResource(getResources(), R.drawable.stageclear);
            canvas.drawBitmap(clearimg, (this.getWidth() - clearimg.getWidth()) / 2, (this.getHeight() - clearimg.getHeight()) / 2, null);
            stageClear = true;
        }

        Bitmap reset = BitmapFactory.decodeResource(getResources(), R.drawable.reset);
        canvas.drawBitmap(reset, 1600, 100, null);

//        Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.jump);
//        canvas.drawBitmap(mBitmap, w * 3, h, null);

    }


    public boolean onTouchEvent(MotionEvent event) {
        // 현재의 터치 액션의 종류를 받아온다.
        int action = event.getAction();
        // 터치 된 x좌표
        float x = event.getX();
        // 터치 된 y좌표
        float y = event.getY();
        int[][] lv1_copy = {
                {0, 2, 10, 2},
                {2, 1, 1, 8},
                {0, 1, 1, 9},
                {0, 0, F, 0}
        };


        // 액션의 종류에 따른 역할 수행
        switch (action) {

            case MotionEvent.ACTION_DOWN:

                if(stageClear == true) {
                    ((Activity)getContext()).finish();
                }
                if (x > this.px1  && x < this.px1 + block[2].getWidth()
                        && y > this.py1 && y < this.py1 + block[2].getHeight()
                        && lv1[1][0] ==2) {

                    lv1[1][0] = 3;

                    // 좌표 이동이 끝났으면 화면을 갱신한다.
                    invalidate();
                }

                if (x > this.px2   && x < this.px2 + block[2].getWidth()
                        && y > this.py2 && y < this.py2 + block[2].getHeight()
                        &&lv1[0][1] == 2) {

                    lv1[0][1] = 3;
                    invalidate();
                }


                if (x > this.px3 && x < this.px3 + block[2].getWidth()
                        && y > this.py3 && y < this.py3 + block[2].getHeight()
                        && lv1[0][2]==10) {

                    lv1[0][2] = 11;

                    invalidate();

                }

                if (x > this.px4   && x < this.px4 + block[2].getWidth()
                        && y > this.py4 && y < this.py4 + block[2].getHeight()
                        && lv1[0][3] == 2) {

                    lv1[0][3] = 3;

                    invalidate();
                }

                if (x > 1600 && x <1900
                        && y > 100 && y < 360) {

                    lv1 = lv1_copy;

                    invalidate();

                }

                break;


/////////////////////////////////////////////////////////////////////////////////////////////////////


            case MotionEvent.ACTION_UP:

                Stageload.click.start();

                if (x > this.px1 && x < this.px1 + block[2].getWidth()
                        && y > this.py1 && y < this.py1 + block[2].getHeight()
                        && lv1[1][0] == 3

                        && (lv1[2][1] != 5)) {


                    if(lv1[1][1]==1){
                        lv1[1][1] = 5;
                    } else {

                        if(lv1[1][2]==1)
                            lv1[1][2]=5;
                        else{
                            if( lv1[2][2] ==1 )
                                lv1[2][2] =5;
                            else
                                lv1[2][1] = 5;
                        }


                    }

                    lv1[1][0] = 4;
                    Stageload.click.start();
                    // 좌표 이동이 끝났으면 화면을 갱신한다.
                    invalidate();
                }

                if (x > this.px2 && x < this.px2 + block[2].getWidth()
                        && y > this.py2 && y < this.py2 + block[2].getHeight()
                        && lv1[0][1] == 3

                        && (lv1[1][1] != 5 || lv1[2][1]!=5)) {


                    if(lv1[1][1]==1)
                        lv1[1][1] = 5;
                    else
                        lv1[2][1] = 5;


                    lv1[0][1] = 4;

                    Stageload.click.start();
                    invalidate();
                }

                if (x > this.px3 && x < this.px3 + block[2].getWidth()
                        && y > this.py3 && y < this.py3 + block[2].getHeight()
                        && lv1[0][2] == 11
                        && (lv1[1][2] != 5 || lv1[2][2]!=5)) {


                    if(lv1[1][2]==1 && lv1[2][2]==1){
                        lv1[1][2] = 5;
                        lv1[2][2] = 5;
                        lv1[0][2] = 4;

                    }


                    Stageload.click.start();
                    invalidate();
                }

                if (x > this.px4 && x < this.px4 + block[2].getWidth()
                        && y > this.py4 && y < this.py4 + block[2].getHeight()
                        && lv1[0][3] == 3
                        && (lv1[2][1] != 5 || lv1[2][2]!=5)) {


                    if(lv1[2][2]==1)
                        lv1[2][2] = 5;
                    else
                        lv1[2][1] = 5;


                    lv1[0][3] = 4;

                    Stageload.click.start();
                    invalidate();
                }

                if (x > this.px5 && x < this.px5 + block[2].getWidth()
                        && y > this.py5 && y < this.py5 + block[2].getHeight()
                        && lv1[3][2]==F) {


                    if(lv1[2][2]==1)
                        lv1[2][2] = 7;
                    else
                    {
                        lv1[1][2] = 7;
                        lv1[2][2] = 1;

                    }

                    lv1[3][2] = 4;

                    Stageload.click.start();
                    invalidate();
                }

                break;
        }

        return true;
    }

    public  boolean toClear(){
        return stageClear;
    }
}