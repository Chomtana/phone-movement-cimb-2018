package com.example.chomt.myapplication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chomt.myapplication.tool.Href;
import com.example.chomt.myapplication.tool.NoisyNumSeries;

import java.util.List;

public class SensorTest extends AppCompatActivity implements SensorEventListener {

    private SensorManager sm;

    private Sensor ac;

    private final float[] mAccelerometerReading = new float[3];
    private final float[] mMagnetometerReading = new float[3];

    private final float[] mRotationMatrix = new float[9];
    private final float[] mOrientationAngles = new float[3];

    private boolean capture = false;
    private boolean capture_start = false;
    private boolean capture_test = false;
    private boolean capture_test_start = false;
    private static String Xts = "";
    private static String[] Yts = new String[] {"","","","","","","","",""};
    private static double[] Yts1 = new double[] {0,0,0,0,0,0,0,0,0};
    private String Xtc = "";
    private String[] Ytc = new String[] {"","","","","","","","",""};
    private double[] Ytc1 = new double[] {0,0,0,0,0,0,0,0,0};

    private long start_du = 999999999999999L;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        /*List<Sensor> sensorList  = sm.getSensorList(Sensor.TYPE_ALL);
        StringBuilder sensorText = new StringBuilder();

        for (Sensor currentSensor : sensorList ) {
            sensorText.append(currentSensor.getName()).append(
                    System.getProperty("line.separator"));
        }

        TextView sensorTextView = (TextView) findViewById(R.id.sensor_list);
        sensorTextView.setText(sensorText);*/

        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);

        Button capture_btn = (Button) findViewById(R.id.sensor_test_capture);
        capture_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView sensorTextView = (TextView) findViewById(R.id.sensor_list);
                Log.d("xxx","yyy");
                if (!capture_test) {
                    capture_test = true;
                    Xtc = "";
                    Ytc = new String[] {"","","","","","","","",""};
                    start_du = 999999999999999L;
                    sensorTextView.setText("DETECTING PATTERN...");
                } else {
                    capture_test = false;
                    capture_test_start = true;
                    Log.d("Xts",Xts);
                    Log.d("Yts0",Yts[1]);
                    //0,2 fail 3,5 pordai 1,4 perfect
                    sensorTextView.setText("");

                    //String xx = "0 20 40 53 61 81 101 120 140 160 180 200 220 246 252 260 279 300 320 340 360 379 400 420 440 458 460 480 500 520 540 560 580 600 620 639 657 660 680 700 720 740 760 780 799 819 839 857 860 879 900 920 940 960 980 1000 1021 1039 1057 1060 1080 1102 1120 1140 1160".trim();
                    //String yy = "-0.15229319 -0.14717388 -0.1439078 -0.14251652 -0.14647655 -0.14309944 -0.13026834 -0.121029906 -0.12082707 -0.117551185 -0.13037747 -0.120145015 -0.116469555 -0.11590991 -0.10977259 -0.10743626 -0.106229976 -0.11048394 -0.113377 -0.10604049 -0.11158529 -0.118140735 -0.12158251 -0.13757141 -0.15755802 -0.14941499 -0.16394894 -0.17836668 -0.19508362 -0.22363517 -0.25404385 -0.2881784 -0.31771147 -0.34725532 -0.38058814 -0.42380103 -0.43969348 -0.47858277 -0.5156763 -0.54942524 -0.58317345 -0.61012685 -0.6381559 -0.6575065 -0.6737946 -0.6838523 -0.7004255 -0.7181365 -0.7241451 -0.7292076 -0.73690987 -0.74391264 -0.74694836 -0.7533049 -0.7603569 -0.7593505 -0.7574228 -0.757364 -0.7385876 -0.7435196 -0.7472431 -0.75327766 -0.75443727 -0.75520533 -0.75326097".trim();

                    Xts = Xts.trim();
                    Yts[0] = Yts[0].trim();
                    Yts[1] = Yts[1].trim();
                    Yts[2] = Yts[2].trim();
                    Yts[3] = Yts[3].trim();
                    Yts[4] = Yts[4].trim();
                    Yts[5] = Yts[5].trim();
                    Yts[6] = Yts[6].trim();
                    Yts[7] = Yts[7].trim();
                    Yts[8] = Yts[8].trim();

                    Xtc = Xtc.trim();
                    Ytc[0] = Ytc[0].trim();
                    Ytc[1] = Ytc[1].trim();
                    Ytc[2] = Ytc[2].trim();
                    Ytc[3] = Ytc[3].trim();
                    Ytc[4] = Ytc[4].trim();
                    Ytc[5] = Ytc[5].trim();
                    Ytc[6] = Ytc[6].trim();
                    Ytc[7] = Ytc[7].trim();
                    Ytc[8] = Ytc[8].trim();

                    double[] x = resolve(Xtc);
                    double[] y0 = resolve(Ytc[0]);
                    double[] y1 = resolve(Ytc[1]);
                    double[] y2 = resolve(Ytc[2]);
                    double[] y3 = resolve(Ytc[3]);
                    double[] y4 = resolve(Ytc[4]);
                    double[] y5 = resolve(Ytc[5]);
                    double[] y6 = resolve(Ytc[6]);
                    double[] y7 = resolve(Ytc[7]);
                    double[] y8 = resolve(Ytc[8]);

                    double[] Xt = resolve(Xts);
                    double[] Yt0 = resolve(Yts[0]);
                    double[] Yt1 = resolve(Yts[1]);
                    double[] Yt2 = resolve(Yts[2]);
                    double[] Yt3 = resolve(Yts[3]);
                    double[] Yt4 = resolve(Yts[4]);
                    double[] Yt5 = resolve(Yts[5]);
                    double[] Yt6 = resolve(Yts[6]);
                    double[] Yt7 = resolve(Yts[7]);
                    double[] Yt8 = resolve(Yts[8]);

                    NoisyNumSeries as0 = new NoisyNumSeries(x,y0);
                    NoisyNumSeries as1 = new NoisyNumSeries(x,y1);
                    NoisyNumSeries as2 = new NoisyNumSeries(x,y2);
                    NoisyNumSeries as3 = new NoisyNumSeries(x,y3);
                    NoisyNumSeries as4 = new NoisyNumSeries(x,y4);
                    NoisyNumSeries as5 = new NoisyNumSeries(x,y5);
                    NoisyNumSeries as6 = new NoisyNumSeries(x,y6);
                    NoisyNumSeries as7 = new NoisyNumSeries(x,y7);
                    NoisyNumSeries as8 = new NoisyNumSeries(x,y8);
                    NoisyNumSeries bs0 = new NoisyNumSeries(Xt,Yt0);
                    NoisyNumSeries bs1 = new NoisyNumSeries(Xt,Yt1);
                    NoisyNumSeries bs2 = new NoisyNumSeries(Xt,Yt2);
                    NoisyNumSeries bs3 = new NoisyNumSeries(Xt,Yt3);
                    NoisyNumSeries bs4 = new NoisyNumSeries(Xt,Yt4);
                    NoisyNumSeries bs5 = new NoisyNumSeries(Xt,Yt5);
                    NoisyNumSeries bs6 = new NoisyNumSeries(Xt,Yt6);
                    NoisyNumSeries bs7 = new NoisyNumSeries(Xt,Yt7);
                    NoisyNumSeries bs8 = new NoisyNumSeries(Xt,Yt8);

                    NoisyNumSeries.Compare c0 = as0.comp(bs0);
                    NoisyNumSeries.Compare c1 = as1.comp(bs1);
                    NoisyNumSeries.Compare c2 = as2.comp(bs2);
                    NoisyNumSeries.Compare c3 = as3.comp(bs3);
                    NoisyNumSeries.Compare c4 = as4.comp(bs4);
                    NoisyNumSeries.Compare c5 = as5.comp(bs5);
                    NoisyNumSeries.Compare c6 = as5.comp(bs6);
                    NoisyNumSeries.Compare c7 = as5.comp(bs7);
                    NoisyNumSeries.Compare c8 = as5.comp(bs8);
                    /*double[] Y = c.loss();
                    for(int i = 0;i<Y.length;i++) {
                        Log.d("Loss",String.valueOf(Y[i]));
                    }*/
                    Log.d("Loss mean 0",String.valueOf(c0.loss_mean()));
                    Log.d("Loss mean 1",String.valueOf(c1.loss_mean()));
                    Log.d("Loss mean 2",String.valueOf(c2.loss_mean()));
                    //Log.d("Loss mean 3",String.valueOf(c3.loss_mean()));
                    //Log.d("Loss mean 4",String.valueOf(c4.loss_mean()));
                    //Log.d("Loss mean 5",String.valueOf(c5.loss_mean()));
                    //Log.d("Loss mean 6",String.valueOf(c6.loss_mean()));
                    //Log.d("Loss mean 7",String.valueOf(c7.loss_mean()));
                    //Log.d("Loss mean 8",String.valueOf(c8.loss_mean()));
                    //double totalloss = (c0.loss_mean()+c1.loss_mean()+c2.loss_mean()/*+c3.loss_mean()+c4.loss_mean()+c5.loss_mean()/*+c6.loss_mean()*//*+c7.loss_mean()*//*+c8.loss_mean()*/)/3;
                    //Log.d("Loss",String.valueOf( totalloss ));

                    if(c0.loss_mean()<0.2 && c1.loss_mean()<0.2 && c2.loss_mean()<0.2) {
                        //TextView sensorTextView = (TextView) findViewById(R.id.sensor_list);
                        //sensorTextView.setText("Passed");
                        sensorTextView.setText("");
                        Href.href(SensorTest.this,DashboardActivity.class);
                    } else {

                        sensorTextView.setText("Wrong pattern");
                        //sensorTextView.setTextColor(0xaa0000);
                    }
                }
            }
        });

        Button capture_test_btn = (Button) findViewById(R.id.sensor_set_capture);
        capture_test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView sensorTextView = (TextView) findViewById(R.id.sensor_list);
                if (!capture) {
                    capture = true;
                    Xts = "";
                    Yts = new String[]{"", "", "", "", "", "", "", "", ""};
                    start_du = 999999999999999L;
                    sensorTextView.setText("SETTING UP...");
                } else {
                    capture = false;
                    capture_start = true;
                    sensorTextView.setText("");
                }
            }
        });
    }

    // Get readings from accelerometer and magnetometer. To simplify calculations,
    // consider storing these readings as unit vectors.
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(event.values, 0, mAccelerometerReading,
                    0, mAccelerometerReading.length);
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(event.values, 0, mMagnetometerReading,
                    0, mMagnetometerReading.length);
        }
        updateOrientationAngles();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private double[] resolve(String s) {
        String[] a = s.split(" ");
        double[] res = new double[a.length];
        for(int i = 0;i<a.length;i++) {
            res[i] = Double.valueOf(a[i]);
        }
        return res;
    }

    // Compute the three orientation angles based on the most recent readings from
    // the device's accelerometer and magnetometer.
    public void updateOrientationAngles() {
        // Update rotation matrix, which is needed to update orientation angles.
        SensorManager.getRotationMatrix(mRotationMatrix, null,
                mAccelerometerReading, mMagnetometerReading);

        // "mRotationMatrix" now has up-to-date information.

        SensorManager.getOrientation(mRotationMatrix, mOrientationAngles);

        // "mOrientationAngles" now has up-to-date information.

        //String dbg = String.valueOf(mRotationMatrix[0])+" "+String.valueOf(mRotationMatrix[1])+" "+String.valueOf(mRotationMatrix[2])+" "+String.valueOf(mRotationMatrix[3])+" "+String.valueOf(mRotationMatrix[4])+" "+String.valueOf(mRotationMatrix[5])+" "+String.valueOf(mRotationMatrix[6])+" "+String.valueOf(mRotationMatrix[7])+" "+String.valueOf(mRotationMatrix[8]);
        String dbg = String.valueOf(mOrientationAngles[0]+Math.PI)+" "+String.valueOf(mOrientationAngles[1]+Math.PI)+" "+String.valueOf(mOrientationAngles[2]+Math.PI);
        //Log.d("xxx",dbg);

        if (capture) {
            long time = System.currentTimeMillis();
            start_du = Math.min(start_du,time);
            Xts += String.valueOf(time-start_du); Xts+=" ";
            if (capture_start) {

            }
            Yts[0] += String.valueOf(mOrientationAngles[0]+Math.PI); Yts[0]+=" ";
            Yts[1] += String.valueOf(mOrientationAngles[1]+Math.PI); Yts[1]+=" ";
            Yts[2] += String.valueOf(mOrientationAngles[2]+Math.PI); Yts[2]+=" ";
            Yts[3] += String.valueOf(mRotationMatrix[3]); Yts[3]+=" ";
            Yts[4] += String.valueOf(mRotationMatrix[4]); Yts[4]+=" ";
            Yts[5] += String.valueOf(mRotationMatrix[5]); Yts[5]+=" ";
            Yts[6] += String.valueOf(mRotationMatrix[6]); Yts[6]+=" ";
            Yts[7] += String.valueOf(mRotationMatrix[7]); Yts[7]+=" ";
            Yts[8] += String.valueOf(mRotationMatrix[8]); Yts[8]+=" ";
            capture_start = false;
        }

        if (capture_test) {
            long time = System.currentTimeMillis();
            start_du = Math.min(start_du,time);
            Xtc += String.valueOf(time-start_du); Xtc+=" ";
            Ytc[0] += String.valueOf(mOrientationAngles[0]+Math.PI); Ytc[0]+=" ";
            Ytc[1] += String.valueOf(mOrientationAngles[1]+Math.PI); Ytc[1]+=" ";
            Ytc[2] += String.valueOf(mOrientationAngles[2]+Math.PI); Ytc[2]+=" ";
            Ytc[3] += String.valueOf(mRotationMatrix[3]); Ytc[3]+=" ";
            Ytc[4] += String.valueOf(mRotationMatrix[4]); Ytc[4]+=" ";
            Ytc[5] += String.valueOf(mRotationMatrix[5]); Ytc[5]+=" ";
            Ytc[6] += String.valueOf(mRotationMatrix[6]); Ytc[6]+=" ";
            Ytc[7] += String.valueOf(mRotationMatrix[7]); Ytc[7]+=" ";
            Ytc[8] += String.valueOf(mRotationMatrix[8]); Ytc[8]+=" ";
            capture_test_start = false;
        }
    }

}
