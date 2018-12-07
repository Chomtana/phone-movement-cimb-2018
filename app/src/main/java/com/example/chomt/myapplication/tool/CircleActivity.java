package com.example.chomt.myapplication.tool;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.chomt.myapplication.R;

public class CircleActivity extends AppCompatActivity implements View.OnTouchListener {

    float downx = 0,downy = 0,upx = 0,upy = 0;
    ImageView imageView;

    float centerx = 300,centery = 300;
    int state = -1;
    String pw = "";

    private String resolveChar() {
        if (state==-1) {
            return "";
        } else if (state<10) {
            return String.valueOf(state);
        } else if (state==10) {
            return "A";
        } else if (state==11) {
            return "B";
        } else if (state==12) {
            return "C";
        }
        return "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        imageView = (ImageView) this.findViewById(R.id.circle_pin);
        imageView.setOnTouchListener(this);
        //centerx = imageView.getMeasuredWidth()/2;
        //centery = imageView.getMeasuredHeight()/2;
        Log.d("centerx",String.valueOf(centerx));
        Log.d("centery",String.valueOf(centery));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downx = event.getX();
                downy = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                upx = event.getX();
                upy = event.getY();
                //canvas.drawLine(downx, downy, upx, upy, paint);
                //imageView.invalidate();
                downx = upx;
                downy = upy;

                double d = Geo.d(centerx,centery,downx,downy);
                double angle = Geo.angle(centerx,centery,downx,downy);

                Log.d("x",String.valueOf(downx));
                Log.d("y",String.valueOf(downy));
                Log.d("dist",String.valueOf(d));
                Log.d("angle",String.valueOf(angle));

                for(int i = 15;i<=360;i+=30) {
                    if (angle > i && angle <= i+30) {
                        if (state != i) {
                            state = i;
                            pw += resolveChar();
                        }
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                upx = event.getX();
                upy = event.getY();
                if (pw=="123456") {
                    Log.d("ok","ok");
                }
                pw = "";
                //canvas.drawLine(downx, downy, upx, upy, paint);
                //imageView.invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                break;
        }
        return true;
    }
}
