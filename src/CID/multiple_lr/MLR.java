package CID.multiple_lr;

public class MLR{
    // private static double[][] dataSet = {
    //     {41.9, 29.1, 251.3},
    //     {43.4, 29.3, 251.3},
    //     {43.9, 29.5, 248.3},
    //     {44.5, 29.7, 267.5},
    //     {47.3, 29.9, 273.0},
    //     {47.5, 30.3, 276.5},
    //     {47.9, 30.5, 270.3},
    //     {50.2, 30.7, 274.9},
    //     {52.8, 30.8, 285.0},
    //     {53.2, 30.9, 290.0},
    //     {56.7, 31.5, 297.0},
    //     {57.0, 31.7, 302.5},
    //     {63.5, 31.9, 304.5},
    //     {65.3, 32.0, 309.3},
    //     {71.1, 32.1, 321.7},
    //     {77.0, 32.5, 330.7},
    //     {77.8, 32.9, 349.0}
    // };

    private static double[][] dataSet ={
        {1,1,76},
        {2,3,78},
        {2,3,85},
        {4,5,88},
        {2,2,72},
        {1,2,69},
        {5,1,94},
        {4,1,94},
        {2,0,88},
        {4,3,92},
        {4,4,90},
        {3,3,75},
        {6,2,96},
        {5,4,90},
        {3,4,82},
        {4,4,85},
        {6,5,99},
        {2,1,83},
        {1,0,62},
        {2,1,76},
    };

    private static double n; 
        
    private static double sumX1;
    private static double sumX2;
    private static double sumY;
    private static double sumX1Y;
    private static double sumX2Y;       
    private static double sumX1X2;
    private static  double sumPowX1;
    private static double sumPowX2;
    
    private static double determinante;

    private static double b0;
    private static double b1;
    private static double b2;

    public static void main(String[] args) {
        MLR test = new MLR();
        if(args.length > 0){
            double X1 = Double.parseDouble(args[0]);
            double X2 = Double.parseDouble(args[1]);
            test.DoRegression(X1, X2);
        }
        else
            System.out.print("No params were included\n");
    }
    

    private static void PrintData(double[][] m){
        for(int i = 0; i < m.length; i++){
            for(int j = 0; j < m[0].length; j++){
                System.out.print("| " + m[i][j]);
            }
            System.out.println();
        }
    }

    private static void PrintData(double[] m){
        for(int i = 0; i < m.length; i++){
            System.out.println("| " + m[i]);    
        }
    }

    private static double Sumatory(int index){
        double res = 0;
        for(int i = 0; i < dataSet.length; i++){
            res += dataSet[i][index];
        }

        return res;
    }

    private static double Sumatory(int index1, int index2){
        double res = 0;
        for(int i = 0; i < dataSet.length; i++){
            res += dataSet[i][index1] * dataSet[i][index2];
        }

        return res;
    }

    private static double SumatoryPow(int index){
        double res = 0;
        for(int i = 0; i < dataSet.length; i++){
            res += Math.pow(dataSet[i][index], 2);
        }

        return res;
    }

    private static double Average(int index){
        return Sumatory(index) / dataSet.length;
    }

    private static void InitializeVariables(){
        n = (double) dataSet.length; 
        
        sumX1 = Sumatory(0);
        sumX2 = Sumatory(1);
        sumY = Sumatory(2);
        sumX1Y = Sumatory(0,2);
        sumX2Y = Sumatory(1,2);       
        sumX1X2 = Sumatory(0,1);
        sumPowX1 = SumatoryPow(0);
        sumPowX2 = SumatoryPow(1);

        determinante = CalculateDetermin();

        b1 = CalculateB1();
        b2 = CalculateB2();
        b0 = CalculateB0();
        
    }

    private static double CalculateDetermin(){
        return (n*sumPowX1*sumPowX2 + sumX1*sumX1X2*sumX2 + sumX2*sumX1*sumX1X2)-
                (sumX2*sumPowX1*sumX2 + n*sumX1X2*sumX1X2 + sumPowX2*sumX1*sumX1);

        
    }

    // private static double CalculateB0(){
    //     return Average(2) - CalculateB1()*Average(0) - CalculateB2()*Average(1);
    // }

    private static double CalculateB0(){
        return (
            ( sumY*sumPowX1*sumPowX2 + sumX1*sumX1X2*sumX2Y + sumX2*sumX1Y*sumX1X2 ) 
            - 
            ( sumX2Y*sumPowX1*sumX2 + sumX1X2*sumX1X2*sumY + sumPowX2*sumX1Y*sumX1 )
        ) / determinante;
    }

    private static double CalculateB1(){
        return ( (n*sumX1Y*sumPowX2 + sumX1*sumX2Y*sumX2 + sumX2*sumY*sumX1X2) - 
                (sumX2*sumX1Y*sumX2 + n*sumX2Y*sumX1X2 + sumPowX2*sumY*sumX1)
                ) / determinante;
    }

    private static double CalculateB2(){
        return ( (n*sumPowX1*sumX2Y + sumX1*sumX1X2*sumY + sumX2*sumX1*sumX1Y) - 
                (sumY*sumX2*sumPowX1 + n*sumX1Y*sumX1X2 + sumX2Y*sumX1*sumX1)
                ) / determinante;
    }

    private static void CalculateY(){
        for(int i = 0; i < dataSet.length; i++){            
            //b0 + (b1*x1) + (b2*x2)
            System.out.println(b0 + b1*dataSet[i][0] + b2*dataSet[i][1]);  
        }
    }

    private static double CalculateY(double _x1, double _x2){
        return b0 + b1*_x1 + b2*_x2;
    }

    public static void DoRegression(double X1, double X2){
        System.out.println("\n\n---------------------------------------");
        System.out.println("Data Set:");
        PrintData(dataSet);

        InitializeVariables();
        System.out.println("\n\n---------------------------------------");
        System.out.println("b0 = " + b0);
        System.out.println("b1 = " + b1);
        System.out.println("b2 = " + b2);
        System.out.println("\n\n---------------------------------------");
        System.out.print("Substituting on the formula:");
        String symbol = b2>0 ? " + " : " ";
        System.out.print("y = " + b0 + " + " + b1 + "*" + X1 + symbol + b2 + "*" + X2 +"\n");
        System.out.println("Y = " + CalculateY(X1, X2));

        
    }
}