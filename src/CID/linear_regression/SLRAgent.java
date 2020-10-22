package CID.linear_regression;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

import java.util.Arrays;

public class SLRAgent extends Agent {

    protected void setup() {
        System.out.println("\n\n---------------------------------------");
        System.out.println("Hola, soy " + getLocalName());
        addBehaviour(new MyOneShotBehaviour());
    }

    private class MyOneShotBehaviour extends OneShotBehaviour {
        public void action(){
            SLR slr = new SLR();
            Object[] param = getArguments();
            String[] args = param[0].toString().split(" ");

            slr.setObsX(new double[]{23,26,30,34,43,48,52,57,58});
            slr.setObsY(new double[]{651,762,856,1063,1190,1298,1421,1440,1518});

            // slr.setObsX(new double[]{2,4,6,8});
            // slr.setObsY(new double[]{2,4,6,8});

            for(int i=0; i<args.length; i++){
                slr.setXPredict( args[i]);
                slr.StartLR();
                slr.setYPredict();
                slr.printSLRF();
                slr.printPredict();
            }
        }

        public int onEnd(){
            myAgent.doDelete();
            return super.onEnd();
        }

    }

}