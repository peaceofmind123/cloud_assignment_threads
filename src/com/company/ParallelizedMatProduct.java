package com.company;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelizedMatProduct {

    double[][] matA;
    double[][] matB;
    public double[][] result;

    int rowsA;
    int rowsB;
    int colsA;
    int colsB;
    private int i;
    private int j;
    private CountDownLatch l;

    ParallelizedMatProduct(double[][] matA, double[][] matB) throws Exception {
        this.matA = matA; // copy by reference
        this.matB = matB;
        this.rowsA = matA.length;
        this.rowsB = matB.length;
        this.colsA = matA[0].length;
        this.colsB = matB[0].length;

        if(this.colsA != this.rowsB) throw new Exception("Dimension error");

        this.result = new double[this.rowsA][this.colsB];

    }




    public double[][] naiveParallelCompute() throws InterruptedException {
        // each thread will reduce this by 1 once its operation is complete..
        // so, if all threads have completed the operation, we will be notified by await
        this.l = new CountDownLatch(rowsA*colsB);

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                try {
                    Thread thread = new Thread(new Worker(matA, matB,result, i,j, l));
                    thread.start();
                }
                catch(Exception e) {
                    System.out.println(e.toString());
                }

            }
        }
        this.l.await();
        return result;

    }

    public double[][] pooledParallelCompute(int numThreads) {
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                try {
                    Runnable worker = new Worker(matA, matB,result, i,j, new CountDownLatch(100));
                    executorService.execute(worker);
                }
                catch(Exception e) {
                    System.out.println(e.toString());
                }

            }
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {   }
        return result;
    }
}
