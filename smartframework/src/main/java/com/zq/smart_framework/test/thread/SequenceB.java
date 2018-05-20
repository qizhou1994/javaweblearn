package com.zq.smart_framework.test.thread;

/**
 * Created by zq on 2018/5/20.
 */
public class SequenceB implements  Sequence {


    private static ThreadLocal<Integer> numberContainer = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };


    @Override
    public int getNumber() {
        numberContainer.set(numberContainer.get()+1);
        return numberContainer.get();
    }

    public static void main(String[] args ){
        Sequence sequence = new SequenceB();

        ClientThread clientThread1 = new ClientThread(sequence);
        ClientThread clientThread2 = new ClientThread(sequence);
        ClientThread clientThread3 = new ClientThread(sequence);

        clientThread1.start();
        clientThread2.start();
        clientThread3.start();
    }
}
