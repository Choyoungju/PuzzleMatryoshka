package com.example.cho.myself;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


public class Stage3 extends View {

    private Bitmap block[] = new Bitmap[12];

    final static char F = 6;

    private float x1 = 93 * 1;
    private float y1 = 93 * 0;
    private float x2 = 93 * 2;
    private float y2 = 93 * 0;
    private float x3 = 93 * 3;
    private float y3 = 93 * 0;


    private float x4 = 93 * 0;
    private float y4 = 93 * 1;
    private float x5 = 93 * 4;
    private float y5 = 93 * 1;

    private float x6 = 93 * 0;
    private float y6 = 93 * 2;
    private float x7 = 93 * 4;
    private float y7 = 93 * 2;

    private float x8 = 93 * 2;
    private float y8 = 93 * 3;


    float px1 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, x1, getResources().getDisplayMetrics()) + 200;
    float py1 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, y1, getResources().getDisplayMetrics());
    float px2 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, x2, getResources().getDisplayMetrics()) + 200;
    float py2 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, y2, getResources().getDisplayMetrics());
    float px3 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, x3, getResources().getDisplayMetrics()) + 200;
    float py3 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, y3, getResources().getDisplayMetrics());
    float px4 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, x4, getResources().getDisplayMetrics()) + 200;
    float py4 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, y4, getResources().getDisplayMetrics());
    float px5 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, x5, getResources().getDisplayMetrics()) + 200;
    float py5 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, y5, getResources().getDisplayMetrics());
    float px6 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, x6, getResources().getDisplayMetrics()) + 200;
    float py6 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, y6, getResources().getDisplayMetrics());
    float px7 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, x7, getResources().getDisplayMetrics()) + 200;
    float py7 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, y7, getResources().getDisplayMetrics());
    float px8 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, x8, getResources().getDisplayMetrics()) + 200;
    float py8 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, y8, getResources().getDisplayMetrics());


    boolean stageClear = false;

    int[][] lv3 = {
            {0, 10, 2, 10, 0},
            {F, 1, 1, 1, 10},
            {F, 1, 1, 1, 2},
            {0, 0, 10, 0, 0}

    };


    int idx = 1;//블록인덱스값


    //초기화
    public Stage3(Context context) {
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

        Bitmap backimage = BitmapFactory.decodeResource(getResources(), R.drawable.bg1);
        canvas.drawBitmap(backimage, 0, 0, null);

        isClear = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                idx = lv3[i][j];

                if (idx == 0)
                    continue;

                canvas.drawBitmap(block[idx], 200 + w * j, h * i, null);
                //   canvas.drawBitmap(block[idx], w * j, h * i, null);

                if (lv3[i][j] == 1 || lv3[i][j] == 2 || lv3[i][j] == 6)
                    isClear++;
                Log.d("Debug : ", "iv3 = " + lv3[i][j] + ',' + isClear);
            }
        }
        if (isClear == 0) {
            Toast.makeText(getContext().getApplicationContext(), "Cleared", Toast.LENGTH_SHORT).show();
            Bitmap clearimg = BitmapFactory.decodeResource(getResources(), R.drawable.stageclear);
            canvas.drawBitmap(clearimg, (this.getWidth() - clearimg.getWidth()) / 2, (this.getHeight() - clearimg.getHeight
                    ()) / 2, null);
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

        int[][] lv3_copy = {
                {0, 10, 2, 10, 0},
                {F, 1, 1, 1, 10},
                {F, 1, 1, 1, 2},
                {0, 0, 10, 0, 0}

        };

        // 액션의 종류에 따른 역할 수행
        switch (action) {

            case MotionEvent.ACTION_DOWN:

                if (stageClear == true) {
                    ((Activity) getContext()).finish();
                }
                if (x > this.px1 && x < this.px1 + block[2].getWidth()
                        && y > this.py1 && y < this.py1 + block[2].getHeight()
                        && lv3[0][1] == 10) {

                    lv3[0][1] = 11;

                    // 좌표 이동이 끝났으면 화면을 갱신한다.
                    invalidate();
                }

                if (x > this.px2 && x < this.px2 + block[2].getWidth()
                        && y > this.py2 && y < this.py2 + block[2].getHeight()
                        && lv3[0][2] == 2) {

                    lv3[0][2] = 3;
                    invalidate();
                }


                if (x > this.px3 && x < this.px3 + block[2].getWidth()
                        && y > this.py3 && y < this.py3 + block[2].getHeight()
                        && lv3[0][3] == 10) {

                    lv3[0][3] = 11;

                    invalidate();

                }

                if (x > this.px5 && x < this.px5 + block[2].getWidth()
                        && y > this.py5 && y < this.py5 + block[2].getHeight()
                        && lv3[1][4] == 10) {

                    lv3[1][4] = 11;

                    invalidate();
                }

                if (x > this.px7 && x < this.px7 + block[2].getWidth()
                        && y > this.py7 && y < this.py7 + block[2].getHeight()
                        && lv3[2][4] == 2) {

                    lv3[2][4] = 3;

                    invalidate();
                }

                if (x > this.px8 && x < this.px8 + block[2].getWidth()
                        && y > this.py8 && y < this.py8 + block[2].getHeight()
                        && lv3[3][2] == 10) {

                    lv3[3][2] = 11;

                    invalidate();
                }

                if (x > 1600 && x < 1900
                        && y > 100 && y < 360) {

                    lv3 = lv3_copy;

                    invalidate();

                }

                break;


/////////////////////////////////////////////////////////////////////////////////////////////////////


            case MotionEvent.ACTION_UP:

                Stageload.click.start();
                if (x > this.px1 && x < this.px1 + block[2].getWidth()
                        && y > this.py1 && y < this.py1 + block[2].getHeight()
                        && lv3[0][1] == 11

                        && (lv3[2][1] == 1) && lv3[1][1] == 1) {


                    lv3[1][1] = 5;
                    lv3[2][1] = 5;

                    lv3[0][1] = 4;
                    Stageload.click.start();
                    // 좌표 이동이 끝났으면 화면을 갱신한다.
                    invalidate();
                }

                if (x > this.px2 && x < this.px2 + block[2].getWidth()
                        && y > this.py2 && y < this.py2 + block[2].getHeight()
                        && lv3[0][2] == 3

                        && (lv3[1][2] == 1 || lv3[2][2] == 1)) {


                    if (lv3[1][2] == 1)
                        lv3[1][2] = 5;
                    else
                        lv3[2][2] = 5;


                    lv3[0][2] = 4;

                    Stageload.click.start();
                    invalidate();
                }

                if (x > this.px3 && x < this.px3 + block[2].getWidth()
                        && y > this.py3 && y < this.py3 + block[2].getHeight()
                        && lv3[0][3] == 11

                        && (lv3[1][3] != 5 && lv3[2][3] != 5)) {

                    lv3[1][3] = 5;
                    lv3[2][3] = 5;

                    lv3[0][3] = 4;
                    Stageload.click.start();
                    invalidate();
                }

                if (x > this.px4 && x < this.px4 + block[2].getWidth()
                        && y > this.py4 && y < this.py4 + block[2].getHeight()
                        && lv3[1][0] == F) {


                    if (lv3[1][1] == 1)
                        lv3[1][1] = 7;
                    else if (lv3[1][1] != 1 && lv3[1][2] == 1) {
                        lv3[1][2] = 7;
                        lv3[1][1] = 1;
                    } else {
                        lv3[1][3] = 7;
                        lv3[1][1] = 1;
                        lv3[1][2] = 1;
                    }


                    lv3[1][0] = 4;
                    Stageload.click.start();
                    invalidate();
                }
                if (x > this.px5 && x < this.px5 + block[2].getWidth()
                        && y > this.py5 && y < this.py5 + block[2].getHeight()
                        && lv3[1][4] == 11
                        && ((lv3[1][1] == 1 && lv3[1][2] == 1) || (lv3[1][3] == 1 && lv3[1][1] == 1) || (lv3[1][3] == 1 && lv3[1][2] == 1))
                        && lv3[1][2] != 7 && lv3[1][3] != 7) {

                    if (lv3[1][3] != 1) {
                        lv3[1][2] = 5;
                        lv3[1][1] = 5;
                    } else if (lv3[1][2] != 1) {
                        lv3[1][3] = 5;
                        lv3[1][1] = 5;
                    } else {
                        lv3[1][3] = 5;
                        lv3[1][2] = 5;
                    }

                    lv3[1][4] = 4;

                    Stageload.click.start();
                    invalidate();
                }


                if (x > this.px6 && x < this.px6 + block[2].getWidth()
                        && y > this.py6 && y < this.py6 + block[2].getHeight()
                        && lv3[2][0] == F) {


                    if (lv3[2][1] == 1)
                        lv3[2][1] = 7;
                    else if (lv3[2][1] != 1 && lv3[2][2] == 1) {
                        lv3[2][2] = 7;
                        lv3[2][1] = 1;
                    } else {
                        lv3[2][3] = 7;
                        lv3[2][1] = 1;
                        lv3[2][2] = 1;
                    }

                    lv3[2][0] = 4;
                    Stageload.click.start();
                    invalidate();
                }
                if (x > this.px7 && x < this.px7 + block[2].getWidth()
                        && y > this.py7 && y < this.py7 + block[2].getHeight()
                        && lv3[2][4] == 3
                        && (lv3[2][1] == 1 || lv3[2][2] == 1 || lv3[2][3] == 1)
                        && lv3[2][3] != 7) {

                    if (lv3[2][3] == 1) {
                        lv3[2][3] = 5;

                    } else if (lv3[2][3] != 1 && lv3[2][2] == 1) {
                        lv3[2][2] = 5;

                    } else {

                        lv3[2][1] = 5;
                    }

                    lv3[2][4] = 4;
                    Stageload.click.start();
                    invalidate();
                }

                if (x > this.px8 && x < this.px8 + block[2].getWidth()
                        && y > this.py8 && y < this.py8 + block[2].getHeight()
                        && lv3[3][2] == 11
                        && (lv3[1][2] != 5 || lv3[2][2] != 5)) {


                    lv3[2][2] = 5;
                    lv3[1][2] = 5;
                    lv3[3][2] = 4;

                    Stageload.click.start();
                    invalidate();
                }


                break;
        }

        return true;
    }

}