package com.example.chomt.myapplication.tool;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class Href {
    public static void href(final Activity current,final Class target) {
        Intent i = new Intent(current,target);
        current.startActivity(i);
    }

    public static void btn(final Button btn,final Activity current,final Class target) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Href.href(current,target);
            }
        });
    }

    public static void btn(int btnid,final Activity current,final Class target) {
        Button btn = current.findViewById(btnid);
        Href.btn(btn,current,target);
    }
}



