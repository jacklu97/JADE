package CID.linear_regression;

import java.lang.Math;

public class SLR {
    private double b1;
    private double b0;
    private double xPredict;
    private double yPredict;
    private double[] obsX;
    private double[] obsY;


    public static void main(String[] args) {
        SLR test = new SLR();

        test.setObsX(new double[]{23,26,30,34,43,48,52,57,58});
        test.setObsY(new double[]{651,762,856,1063,1190,1298,1421,1440,1518});

        for(int i=0; i<args.length; i++){
            test.setXPredict(args[i]);
            test.StartLR();
            test.setYPredict();
            test.printSLRF();
            test.printPredict();
        }

    }

    public void printSLRF(){
        System.out.println("\nRegresión linear simple");
        System.out.println("Fórmula: y = B0 + B1x");

        System.out.println("\nB1 = " + getB1());
        System.out.println("B0 = " + getB0());
        System.out.println("x = "+ getXPredict());
        System.out.println("Sustituyendo en la fórmula\ny = " + getB0() + "+" + getB1() + "*" + getXPredict());
    }

    public void printPredict(){
        System.out.println("Predicción:");
        System.out.println("Y = " + getYPredict());
    }

    public void setXPredict(String data){
        this.xPredict = Double.parseDouble(data);
    }

    public double getXPredict(){
        return this.xPredict;
    }

    public void setYPredict(){
        this.yPredict = calcY(this.b1, this.b0, this.xPredict);
    }

    public double getYPredict(){
        return this.yPredict;
    }

    public void setB1(double b1){
        this.b1 = b1;
    }

    public double getB1(){
        return this.b1;
    }

    public void setB0(double b0){
        this.b0 = b0;
    }

    public double getB0(){
        return this.b0;
    }

    public void setObsX(double[] obsX){
        this.obsX = obsX;
    }

    public double[] getObsX(){
        return this.obsX;
    }

    public void setObsY(double[] obsY){
        this.obsY = obsY;
    }

    public double[] getObsY(){
        return this.obsY;
    }

    public void StartLR() {
        double[] obsX2 = powArray(getObsX(), 2);
        
        setB0(calcB0(getObsX(), getObsY(), getB1()));
        setB1(calcB1(getObsX(), getObsY(), obsX2));
    }

    public double calcY(double m, double b, double x){
        return b + m * x;
    }


    public double calcB1(double[] valoresX, double[] valoresY, double[] valoresX2) {
        int n = valoresX.length;

        double sumX = calcSum(valoresX);
        double sumY = calcSum(valoresY);

        return (
            ( n * calcSum(valoresX, valoresY) - ( sumX * sumY )) 
            / 
            ( n * calcSum(valoresX2) - ( sumX * sumX ) )
        );
    }
    

    public double calcB0(double[] valoresX, double[] valoresY, double b1){
        int n = valoresX.length;
        return (
            ( calcSum(valoresY) - ( b1 * calcSum(valoresX) ) ) / n
        );
    }

    public double calcSum(double[] valores){
        double res = 0;

        for(int i = 0; i<valores.length; i++){
            res += valores[i];
        }

        return res;
    }

    public double calcSum(double[] valoresX, double[] valoresY) {
        double res = 0;

        for(int i = 0; i<valoresX.length; i++){
            res += valoresX[i] * valoresY[i];
        }

        return res;
    }

    public double[] powArray(double[] valores, int potencia) {
        double[] nVal = new double[valores.length];

        for(int i =0; i<valores.length; i++){
            nVal[i] = Math.pow(valores[i], potencia);
        }

        return nVal;
    }
    
}