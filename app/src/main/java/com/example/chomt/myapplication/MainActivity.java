package com.example.chomt.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.chomt.myapplication.tool.CircleActivity;
import com.example.chomt.myapplication.tool.Href;

public class MainActivity extends AppCompatActivity {

    public static String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /*Button login_btn = (Button) findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hrefVerifyPage = new Intent(MainActivity.this,PinActivity.class);
                startActivity(hrefVerifyPage);
            }
        });*/

        if (MainActivity.username == "") {
            Href.btn(R.id.login_btn, MainActivity.this, CircleActivity.class);
        } else {
            Href.btn(R.id.login_btn, MainActivity.this, PinActivity.class);
            //          button_id       fromClass.this          ToClass.class
        }

        Href.btn(R.id.sensor_list_href_btn, MainActivity.this, SensorTest.class);

        Pair<Integer,Integer> p = new Pair<>(3,5);
        Log.d("MainActivity",String.valueOf(p.first));
        Log.d("MainActivity",String.valueOf(p.second));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
