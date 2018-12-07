package com.example.chomt.myapplication.tool;

import android.util.Pair;

public class NoisyNumSeries {
    double[] X;
    double[] Y;

    public double losslevel = 0.25;
    public double movelevel = 0.1;
    public double stepsize = 0.01;

    public class Compare {
        public double[] Xs; //X static (saved in db)
        public double[] Ys; //Y static (saved in db)
        public double[] Xc; //X to check/compare (move)
        public double[] Yc; //Y to check/compare (move)

        public double[] Xt; //target X
        public double[] Yt; //target Y

        private double resolve(double xs,double ys,double xe,double ye,double xp) {
            double x = xe-xs;
            double y = ye-ys;
            double a = y/x;
            return ys+a*(xp-xs);
        }

        public Double[] resolveAllX() {
            Double[] y = new Double[Ys.length]; //y[i] = y of Xs[i]
            int ci = 0;
            for (int i = 0; i < Xs.length; i++) {
                //System.out.println(Xs[i]);
                if (ci >= Xc.length - 1) ci -= 1;
                if (Xs[i] > Xc[ci]) ci += 1;
                if (ci >= Xc.length - 1) {
                    //y[i] = Yc[Yc.length-1];
                    y[i] = null;
                    continue;
                }
                if (Xs[i] == Xc[ci] || Xs[i] == Xc[ci + 1]) {
                    if (Xs[i] == Xc[ci]) {
                        y[i] = Yc[ci];
                    } else if (Xs[i] == Xc[ci + 1]) {
                        y[i] = Yc[ci + 1];
                    }
                    continue;
                }

                y[i] = resolve(Xc[ci], Yc[ci], Xc[ci + 1], Yc[ci + 1], Xs[i]);
            }
            return y;
        }

        public void prep() {
            Xc = Xt.clone();
            Yc = Yt.clone();
          /*for(int i = 0;i<Xc.length;i++) {
            Xc[i] -= movelevel;
          }*/
        }

        public double[] loss() {
            double[] res = new double[Xs.length];
            for(int i = 0;i<res.length;i++) {
                res[i] = 99999999;
            }

            prep();
            for(double asd = 0;asd<=2*movelevel;asd+=stepsize) {
                for(int i = 0;i<Xc.length;i++) {
                    Xc[i] += stepsize;
                }
                //System.out.println(Xc[0]);
                Double[] y = resolveAllX();
                for(int i = 0;i<y.length;i++) {
                    if (y[i]!=null) {
                        res[i] = Math.min(res[i],(y[i]-Ys[i])*(y[i]-Ys[i]));
                    }
                }
            }

            return res;
        }

        public double loss_sum() {
            double sum = 0;
            double[] L = loss();
            for(int i = 0;i<L.length;i++) {
                sum += L[i];
            }
            return sum;
        }

        public double loss_mean() {
            return loss_sum()/((double)Xs.length);
        }

        public double loss_max() {
            double max = -99999999;
            double[] L = loss();
            for(int i = 0;i<L.length;i++) {
                max = Math.max(max,L[i]);
            }
            return max;
        }

        public Compare(double[] xs,double[] ys,double[] xt,double[] yt) {
            Xs = xs;
            Ys = ys;
            Xt = xt;
            Yt = yt;
        }
    }

    public Compare comp(NoisyNumSeries target) {
        return new Compare(X,Y,target.X,target.Y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NoisyNumSeries) {
            Compare c = this.comp((NoisyNumSeries) obj);
            return c.loss_max()<losslevel;
        } else {
            return super.equals(obj);
        }
    }

    public NoisyNumSeries(double[] x,double[] y) {
        X = x;
        Y = y;
    }
}
