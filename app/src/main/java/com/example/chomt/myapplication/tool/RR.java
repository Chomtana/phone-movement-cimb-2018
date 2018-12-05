package com.example.chomt.myapplication.tool;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.lang.reflect.Field;

public class RR {
    public static int get(String pVariableName, String pResourcename, Activity activity)
    {
        try {
            return activity.getResources().getIdentifier(pVariableName, pResourcename, activity.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int id(String resName,Activity activity) {
        return get(resName, "id", activity);
    }

    public static View view(String resName, Activity activity) {
        return activity.findViewById(id(resName, activity));
    }

    public static int drawable_id(String resName,Activity activity) {
        return get(resName, "drawable", activity);
    }

    public static Drawable drawable(String resName, Activity activity) {
        return activity.getResources().getDrawable(drawable_id(resName, activity));
    }
}
