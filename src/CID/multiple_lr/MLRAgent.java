package CID.multiple_lr;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class MLRAgent extends Agent{

    protected void setup(){
        System.out.println("Hi, I'm " + getLocalName());
        addBehaviour(new MyOneShotBehaviour());
    }

    private class MyOneShotBehaviour extends OneShotBehaviour{
        public void action(){
            MLR mlr = new MLR();
            Object[] param = getArguments();
            if(param != null && param.length > 0){
                String[] args = param[0].toString().split(" ");
                double X1 = Double.parseDouble(args[0]);
                double X2 = Double.parseDouble(args[1]);
                mlr.DoRegression(X1, X2);
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