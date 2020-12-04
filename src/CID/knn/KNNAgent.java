package CID.knn;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class KNNAgent extends Agent{
    protected void setup(){
        System.out.println("Hi, I'm " + getLocalName());
        addBehaviour(new MyOneShotBehaviour());
    }

    private class MyOneShotBehaviour extends OneShotBehaviour{
        public void action(){
            KNN knn = new KNN();
            Object[] param = getArguments();
            if(param != null && param.length > 0){
                String[] args = param[0].toString().split(" ");
                int k = Integer.parseInt(args[0]);
                double newX = Double.parseDouble(args[1]);
                double newY = Double.parseDouble(args[2]);
                knn.doKNN(k, newX, newY);
            }
            else{
                System.out.print("No params were included");
            }
        }

        public int onEnd(){
            myAgent.doDelete();
            return super.onEnd();
        }
    }
}