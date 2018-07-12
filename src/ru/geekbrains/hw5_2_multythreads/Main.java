package ru.geekbrains.hw5_2_multythreads;

public class Main {

    static final int size = 10000000;
    static final int h=size/2;
    static final int number= 4;


    public static void main(String[] args) throws InterruptedException {

        float arr[] = new float[size];
        float a1[] = new float[h];
        float a2[] = new float[h];
        float a_1[] = new float[h/2];
        float a_2[] = new float[h/2];
        float a_3[] = new float[h/2];
        float a_4[] = new float[h/2];
        for (int i = 0; i <arr.length ; i++) {
            arr[i]=1;
        }

        Thread[] threads = new Thread[number];
        int part = size/number;
        float a[][] = new float[number][part];
        System.arraycopy(arr,0,a1,0,h);
        System.arraycopy(arr,h,a2,0,h);


        long a_th_th = System.currentTimeMillis();
        System.arraycopy(arr,0,a_1,0,h/2);
        System.arraycopy(arr,h/2,a_2,0,h/2);
        System.arraycopy(arr,h,a_3,0,h/2);
        System.arraycopy(arr,3*h/2,a_4,0,h/2);
        a_th_th=System.currentTimeMillis()-a_th_th;
        System.out.println("4 threads separ : "+a_th_th);

        long threadTimer = System.currentTimeMillis();
        for (int i = 0; i <number ; i++) {
            System.arraycopy(arr,i*part,a[i],0,part);
        }
        for (int i = 0; i <number ; i++) {
            int k = i;
            threads[k] = new Thread(new Runnable() {
                @Override
                public void run() {

                    for (int j = 0; j < part; j++) {
                        a[k][j] = (float) (a[k][j] * Math.sin(0.2f + (j + part * k) / 5) * Math.cos(0.2f + (j + part *k) / 5)* Math.cos(0.4f + (j + part * k) / 2));
                    }
                }
            });
        }
        for (int i = 0; i <number ; i++) {
            threads[i].start();
        }
        for (int i = 0; i <number ; i++) {
            threads[i].join();
        }

        for (int i = 0; i <number ; i++) {
            System.arraycopy(a[i],0,arr,i*part,part);
        }
        System.out.println("time: "+ (System.currentTimeMillis()-threadTimer));

        long startTimer = System.currentTimeMillis();

        for (int i = 0; i <arr.length ; i++) {
            arr[i]=(float)(arr[i]*Math.sin(0.2f+i/5)*Math.cos(0.2f+i/5)*Math.cos(0.4f+i/2));
        }
        System.out.println("One thread time: "+ (System.currentTimeMillis()-startTimer));


        for (int i = 0; i <number ; i++) {

        }

        long a_th2=System.currentTimeMillis();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <a1.length ; i++) {
                    a1[i]=(float)(a1[i]*Math.sin(0.2f+i/5)*Math.cos(0.2f+i/5)*Math.cos(0.4f+i/2));
                }
            }
        });

                Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <a2.length ; i++) {
                    a2[i]=(float)(a2[i]*Math.sin(0.2f+(i+h)/5)*Math.cos(0.2f+(i+h)/5)*Math.cos(0.4f+(i+h)/2));
                }
            }
        });
                t1.start();
                t2.start();
                try {
                    t1.join();
                    t2.join();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                System.arraycopy(a1,0,arr,0,h);
                System.arraycopy(a2,0,arr,h,h);

        System.out.println("Two threads result: "+(System.currentTimeMillis()-a_th2));
        //////////////////////////////

        long a_th_th2=System.currentTimeMillis();
        Thread t_1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <a_1.length ; i++) {
                    a_1[i]=(float)(a_1[i]*Math.sin(0.2f+i/5)*Math.cos(0.2f+i/5)*Math.cos(0.4f+i/2));
                }
            }
        });

        Thread t_2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <a_2.length ; i++) {
                    a_2[i]=(float)(a_2[i]*Math.sin(0.2f+(i+h/2)/5)*Math.cos(0.2f+(i+h/2)/5)*Math.cos(0.4f+(i+h/2)/2));
                }
            }
        });

        Thread t_3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <a_3.length ; i++) {
                    a_3[i]=(float)(a_3[i]*Math.sin(0.2f+(i+h)/5)*Math.cos(0.2f+(i+h)/5)*Math.cos(0.4f+(i+h)/2));
                }
            }
        });

        Thread t_4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <a_4.length ; i++) {
                    a_4[i]=(float)(a_4[i]*Math.sin(0.2f+(i+3*h/2)/5)*Math.cos(0.2f+(i+3*h/2)/5)
                            *Math.cos(0.4f+(i+3*h/2)/2));
                }
            }
        });





        t_1.start();
        t_2.start();
        t_3.start();
        t_4.start();
        try {
            t_1.join();
            t_2.join();
            t_3.join();
            t_4.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.arraycopy(a_1,0,arr,0,h/2);
        System.arraycopy(a_2,0,arr,h/2,h/2);
        System.arraycopy(a_3,0,arr,h,h/2);
        System.arraycopy(a_4,0,arr,3*h/2,h/2);

        System.out.println("Four threads result: "+(System.currentTimeMillis()-a_th_th2));





    }
}
