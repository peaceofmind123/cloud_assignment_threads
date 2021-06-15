package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        double[][] matA = {{1,2,3},{3,4,5},{4,5,6}};
        double[][] matB = {{1,2,3},{3,4,5},{4,5,6}};
        System.out.println("Sequential version");
        try {
            double[][] result = SequentialMatProduct.multiply(matA, matB);
            SequentialMatProduct.printMat(result);
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("Naive Parallel version");

        try {
            ParallelizedMatProduct p = new ParallelizedMatProduct(matA, matB);
            double[][] result = p.naiveParallelCompute();
            SequentialMatProduct.printMat(result);
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
    }
}
