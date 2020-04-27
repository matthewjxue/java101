package com.mhisoft.matthew.learningjava.effectivejava;

public class ThreadDemo extends Thread {

    public ThreadDemo(String name) {
        super(name);
    }

    //run method of the thread
    public void run() {
        for(int i = 0; i < 5; i++) {
            compute();
        }
    }



    static ThreadLocal numcalls = new ThreadLocal();

    public void compute(){
        Integer n = (Integer) numcalls.get();
        if(n == null) n = new Integer(1);
        else n = new Integer(n.intValue() + 1);
        numcalls.set(n);
        System.out.println(Thread.currentThread().getName() + ": " + n);
        for (int i = 0, j = 0; i <100000 ; i++) {
            j+=i;
            try{
                Thread.sleep((int)(Math.random() * 100 + 1));
                System.out.println(Thread.currentThread().getName()+":I am awake now");

            }
            catch(InterruptedException e){}


            Thread.yield();
        }


    }




    public static void main(String[] args) {
        ThreadDemo thread1 = new ThreadDemo("thread A");
        ThreadDemo thread2 = new ThreadDemo("thread B");

//        Thread thread2 = new Thread(new Runnable() {
//            public void run() {
//                for (int i = 0; i < 5; i++) compute();
//            }
//        });
        if(args.length >= 1) thread1.setPriority(Integer.parseInt(args[0]));
        if(args.length >= 2) thread2.setPriority(Integer.parseInt(args[1]));
        thread1.start();
        thread2.start();




    }

}
