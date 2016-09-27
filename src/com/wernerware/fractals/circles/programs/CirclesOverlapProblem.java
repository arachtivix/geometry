package com.wernerware.fractals.circles.programs;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;

import com.wernerware.fractals.circles.Circle;

public class CirclesOverlapProblem {

	public static void main(String[] args) {

		int numScenarios = 100;
		double input[][] = new double[numScenarios][6];
		double expected[][] = new double[numScenarios][1];

		for( int i = 0; i < numScenarios / 2; i++ ){
			Circle scenario[] = generateScenario(true);
			input[i][0] = scenario[0].getRadius();
			input[i][1] = scenario[0].getCenter().getX();
			input[i][2] = scenario[0].getCenter().getY();
			input[i][3] = scenario[1].getRadius();
			input[i][4] = scenario[1].getCenter().getX();
			input[i][5] = scenario[1].getCenter().getY();
			
			expected[i][0] = 1.0;
		}

		for( int i = 0; i < numScenarios / 2; i++ ){
			Circle scenario[] = generateScenario(false);
			input[i][0] = scenario[0].getRadius();
			input[i][1] = scenario[0].getCenter().getX();
			input[i][2] = scenario[0].getCenter().getY();
			input[i][3] = scenario[1].getRadius();
			input[i][4] = scenario[1].getCenter().getX();
			input[i][5] = scenario[1].getCenter().getY();
			
			expected[i][0] = 0.0;
		}
		
		MLDataSet trainingSet = new BasicMLDataSet(input,expected);
		BasicNetwork network = new BasicNetwork();
		network.addLayer(new BasicLayer(null,true,6));
		network.addLayer(new BasicLayer(new ActivationSigmoid(),true,6));
		network.addLayer(new BasicLayer(new ActivationSigmoid(),false,1));
		network.getStructure().finalizeStructure();
		network.reset();
		MLTrain train = new ResilientPropagation(network,trainingSet);
		
		int epoc = 0;
		do{
			train.iteration();
			System.out.println("EPOC = " + epoc++ + " err = " + train.getError());
		} while(train.getError() > .01 && epoc < 100 );
		
	}

	public static Circle[] generateScenario(boolean overlap){
		Circle retval[] = new Circle[2];
		
		do{
			double x1 = Math.random() * 2.0 - 1.0;
			double y1 = Math.random() * 2.0 - 1.0;
			double r1 = Math.random() + .05;
			double x2 = Math.random() * 2.0 - 1.0;
			double y2 = Math.random() * 2.0 - 1.0;
			double r2 = Math.random() + .05;

			retval[0] = new Circle(x1,y1,r1);
			retval[1] = new Circle(x2,y2,r2);
		}while( retval[0].overlaps(retval[1]) ? !overlap : overlap );
		
		return retval;
	}
	
}
