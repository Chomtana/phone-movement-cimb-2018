package com.example.chomt.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.chomt.myapplication.tool.Href;
import com.example.chomt.myapplication.tool.RR;

public class PinActivity extends AppCompatActivity {

    private static final String TAG = "PinActivity";
    private String curr_pin = "";

    private void checkForPinOK() {
        if (curr_pin.length()==6) {
            if (curr_pin.equals("123456")) {
                Href.href(PinActivity.this,DashboardActivity.class);
            } else {
                curr_pin = "";
            }
        }
    }

    private void updateCircles() {
        for(int i = 1;i<=curr_pin.length();i++) {
            if (i>6) break;
            LinearLayout circle = (LinearLayout) RR.view("pin_circle_"+i,PinActivity.this);
            //LinearLayout circle = findViewById(R.id.pin_circle_6);
            circle.setBackground(RR.drawable("circle_solid",PinActivity.this));
        }
        for(int i = curr_pin.length()+1;i<=6;i++) {
            LinearLayout circle = (LinearLayout) RR.view("pin_circle_"+i,PinActivity.this);
            //LinearLayout circle = findViewById(R.id.pin_circle_6);
            circle.setBackground(RR.drawable("circle_blank",PinActivity.this));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        for(int i = 0;i<=9;i++) {
            final int num = i;
            Button btn = (Button) RR.view("pin_numpad_"+i,PinActivity.this);
            //Button btn = findViewById(R.id.pin_numpad_0);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.d(TAG,String.valueOf(num));
                    curr_pin += String.valueOf(num);
                    checkForPinOK();
                    updateCircles();
                }
            });
        }
    }

}
