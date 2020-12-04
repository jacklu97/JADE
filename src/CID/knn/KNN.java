package CID.knn;

import java.util.Collections;
import java.util.Arrays;
import java.lang.Math;

public class KNN{
    private static double[][] dataSet = {
        {158, 58, 1},
        {158, 59, 1},
        {158, 63, 1},
        {160, 59, 1},
        {160, 60, 1},
        {163, 60, 1},
        {163, 61, 1},
        {160, 64, 2},
        {163, 64, 2},
        {165, 61, 2},
        {165, 62, 2},
        {165, 65, 2},
        {168, 62, 2},
        {168, 63, 2},
        {168, 66, 2},
        {170, 63, 2},
        {170, 64, 2},
        {170, 68, 2},
    };

    // private static int k;
    private static double xMean;
    private static double yMean;
    private static double [] xCoordinates;
    private static double [] yCoordinates;
    private static double [][] ecludianDistance;

    public static double[] getCoordinatesX(double[][] m) {
        double[] res = new double[m.length];

        for(int i = 0; i < m.length; i++){
            res[i] += m[i][1];
        }

        // for(double param : res){
        //     System.out.print(param);
        //     System.out.print("\n");
        // }

        return res;
    }

    public static double[] getCoordinatesY(double[][] m) {
        double[] res = new double[m.length];

        for(int i = 0; i < m.length; i++){
            res[i] += m[i][0];
        }

        // for(double param : res){
        //     System.out.print(param);
        //     System.out.print("\n");
        // }

        return res;
    }

    public static double getMean(double[] m){
        double res = 0;

        for(int i = 0; i < m.length; i++){
            res+=m[i];
        }

        return res/m.length;
    }

    public static double[] standardization(double[] m, double mean){
        double[] newArr = new double[m.length];

        double maxInArr = Arrays.stream(m).max().getAsDouble();
        double minInArr = Arrays.stream(m).min().getAsDouble();

        for(int i = 0; i < m.length; i++){
            newArr[i] = (m[i] - mean) / (maxInArr - minInArr);
        }

        // for(double param: newArr){
        //     System.out.print(param);
        //     System.out.print("\n");
        // }

        return newArr;
    }

    public static double standardization(double m, double[] coord , double mean){
        double minInArr = Arrays.stream(coord).min().getAsDouble();
        double maxInArr = Arrays.stream(coord).max().getAsDouble();

        return (m - mean) / (maxInArr - minInArr);
    }

    public static double[][] getEuclidianDistance(double newX, double newY,double[] coordX, double[] coordY){
        double[][] res = new double[coordX.length][2];

        for(int i = 0; i < coordX.length; i++){
            res[i][0] = i;
            res[i][1] = Math.sqrt( Math.pow( newX - coordX[i], 2 ) + Math.pow( newY - coordY[i], 2 ) );
        }

        // for(double param : res){
        //     System.out.print(param);
        //     System.out.print("\n");
        // }

        return res;
    }

    public static void print2dArray(double[][] m){
        for(int i = 0; i<m.length; i++){
            System.out.print("\n" + m[i][0] + " " + m[i][1]);
        }
    }

    public static void getKNeighbor(int k){
        double[] sizes = new double[k];

        for(int i = 0; i<k; i++){
            sizes[i] = dataSet[(int) ecludianDistance[i][0]][2];
        }

        int maxInArr =(int) Arrays.stream(sizes).max().getAsDouble();

        if(maxInArr == 1){
            System.out.print("T Shirt size: M");
        }
        else{
            System.out.print("T Shirt size: L");
        }
    }

    public static void doKNN(int k, double newX, double newY){
        xCoordinates = getCoordinatesX(dataSet);
        yCoordinates = getCoordinatesY(dataSet);

        xMean = getMean(xCoordinates);
        // System.out.print(xMean);
        yMean = getMean(yCoordinates);
        // System.out.print(yMean);

        xCoordinates = standardization(xCoordinates, xMean);
        yCoordinates = standardization(yCoordinates, yMean);

        newX = standardization(newX, xCoordinates, xMean);
        newY = standardization(newY, yCoordinates, yMean);

        ecludianDistance = getEuclidianDistance(newX, newY, xCoordinates, yCoordinates);
        // print2dArray(ecludianDistance);
        // System.out.print("\n");
        Arrays.sort(ecludianDistance, (a, b) -> Double.compare(a[1], b[1]));
        // print2dArray(ecludianDistance);
        // System.out.print("\n");
        getKNeighbor(k);
    }
    

    public static void main(String[] args){
        doKNN(5, 61, 161);
    }
}