package com.zq.smart_framework.test.thread;

/**
 * Created by zq on 2018/5/20.
 */
public class ClientThread extends Thread {

    private Sequence sequence;

    public ClientThread(Sequence sequence){
        this.sequence = sequence;
    }

    @Override
    public void run() {
         for(int i = 0 ; i < 3; i ++){
             System.out.println(Thread.currentThread().getName() + " => " + sequence.getNumber());

         }
    }
}
