package com.company;

public class SequentialMatProduct {

    public static double[][] multiply(double[][] matA, double[][] matB) throws Exception {
        int rowsA = matA.length;
        int rowsB = matB.length;
        int colsA = matA[0].length;
        int colsB = matB[0].length;

        if(colsA != rowsB) throw new Exception("Dimension error");

        double[][] result = new double[rowsA][colsB];

        for(int i = 0; i < rowsA; i++) {
            for(int j = 0; j < colsB; j++) {
                result[i][j] = 0;
                for (int k =0; k < colsA; k++) {
                    result[i][j] += matA[i][k] * matB[k][j];
                }
            }
        }

        return result;
    }

    public static void printMat(double[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j] + "\t");
            }
            System.out.println(" ");
        }
    }
}
