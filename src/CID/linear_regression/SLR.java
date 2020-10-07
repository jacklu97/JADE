package CID.linear_regression;

import java.lang.Math;

public class SLR {
    public static void main(String[] args) {
        SLR test = new SLR();
        test.StartLR(Double.parseDouble(args[0]));
    }
    

    public void StartLR(double xPredict) {
        double[] obsX = new double[]{23,26,30,34,43,48,52,57,58};
        double[] obsY = new double[]{651,762,856,1063,1190,1298,1421,1440,1518};
        double[] obsX2 = powArray(obsX, 2);
        
        System.out.println("Regresión linear simple");
        System.out.println("Fórmula: y = B0 + B1x");

        double b1 = calcB1(obsX, obsY, obsX2);
        double b0 = calcB0(obsX, obsY, b1);

        double yPredict = calcY(b1, b0, xPredict);

        System.out.println("\nB1 = " + b1);
        System.out.println("B0 = " + b0);
        System.out.println("x = "+ xPredict);
        System.out.println("Sustituyendo en la fórmula\ny = " + b0 + "+" + b1 + "*" + xPredict);
        System.out.println("Predicción:");
        System.out.println("Y = " + yPredict);
    }

    public static double calcY(double m, double b, double x){
        return b + m * x;
    }


    public static double calcB1(double[] valoresX, double[] valoresY, double[] valoresX2) {
        int n = valoresX.length;

        double sumX = calcSum(valoresX);
        double sumY = calcSum(valoresY);

        return (
            ( n * calcSum(valoresX, valoresY) - ( sumX * sumY )) 
            / 
            ( n * calcSum(valoresX2) - ( sumX * sumX ) )
        );
    }
    

    public static double calcB0(double[] valoresX, double[] valoresY, double b1){
        int n = valoresX.length;
        return (
            ( calcSum(valoresY) - ( b1 * calcSum(valoresX) ) ) / n
        );
    }

    public static double calcSum(double[] valores){
        double res = 0;

        for(int i = 0; i<valores.length; i++){
            res += valores[i];
        }

        return res;
    }

    public static double calcSum(double[] valoresX, double[] valoresY) {
        double res = 0;

        for(int i = 0; i<valoresX.length; i++){
            res += valoresX[i] * valoresY[i];
        }

        return res;
    }

    public static double[] powArray(double[] valores, int potencia) {
        double[] nVal = new double[valores.length];

        for(int i =0; i<valores.length; i++){
            nVal[i] = Math.pow(valores[i], potencia);
        }

        return nVal;
    }
    
    
}