package com.example.chomt.myapplication.tool;

public class Geo {
    public static double d(double x1,double y1,double x2,double y2) {
        return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }

    public static double angle(double xc,double yc,double x,double y) {
        double res = Math.toDegrees(Math.atan((yc-y)/(x-xc)));
        if (x>xc) {
            if (y<yc) {
                return 90-res;
            } else {
                return 90+Math.abs(res);
            }
        } else {
            if (y<yc) {
                return 270+Math.abs(res);
            } else {
                return 270-Math.abs(res);
            }
        }
    }

    public static double angled(double a,double b) {
        return Math.min(Math.abs(a-b),2*Math.PI+Math.min(a,b)-Math.max(a,b));
    }
}
