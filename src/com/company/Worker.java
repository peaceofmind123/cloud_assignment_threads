package com.company;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable{

    private double[][] matA;
    private double[][] matB;
    private double[][] result;
    private int i;
    private int j;
    private CountDownLatch latch;

    public Worker(double[][] matA, double[][] matB, double[][] result, int i, int j, CountDownLatch latch) {

        this.matA = matA;
        this.matB = matB;
        this.result = result;
        this.i = i;
        this.j = j;
        this.latch = latch;
    }
    public void run() {
        for (int k = 0; k < matB.length; k++) {
            result[i][j] += matA[i][k] * matB[k][j];
        }
        latch.countDown();
    }
}
