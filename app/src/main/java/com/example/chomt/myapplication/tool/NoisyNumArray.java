package com.example.chomt.myapplication.tool;

public class NoisyNumArray {
    public double[] array;
    public double losslevel = 0.25;

    public class Compare {
        public double[] array1;
        public double[] array2;

        public double[] loss() {
            double[] res = new double[array1.length];
            for(int i = 0;i<array1.length;i++) {
                res[i] = (array1[i]-array2[i])*(array1[i]-array2[i]);
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
            return loss_sum()/((double)array1.length);
        }

        public double loss_max() {
            double max = -99999999;
            double[] L = loss();
            for(int i = 0;i<L.length;i++) {
                max = Math.max(max,L[i]);
            }
            return max;
        }

        public Compare(double[] a1,double[] a2) {
            array1 = a1;
            array2 = a2;
        }
    }

    public Compare comp(NoisyNumArray target) {
        return new Compare(array,target.array);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NoisyNumArray) {
            Compare c = this.comp((NoisyNumArray) obj);
            return c.loss_max()<losslevel;
        } else {
            return super.equals(obj);
        }
    }

    public NoisyNumArray(double[] a) {
        array = a;
    }
}
