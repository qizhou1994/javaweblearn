package com.zq.smart_framework.test.thread;

/**
 * Created by zq on 2018/5/20.
 */
public class SequenceA implements Sequence {

    private static int number = 0;

    @Override
    public int getNumber() {
        number = number +  1;
        return number ;
    }

    public static void main(String[] args ){
        Sequence sequence = new SequenceA();

        ClientThread clientThread1 = new ClientThread(sequence);
        ClientThread clientThread2 = new ClientThread(sequence);
        ClientThread clientThread3 = new ClientThread(sequence);

        clientThread1.start();
        clientThread2.start();
        clientThread3.start();
    }
}
