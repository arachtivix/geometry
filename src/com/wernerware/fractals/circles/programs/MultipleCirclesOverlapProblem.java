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

public class MultipleCirclesOverlapProblem {

	public static void main(String[] args) {

		int numScenarios = 100;
		int numCircles = 5;
		double input[][] = new double[numScenarios][numCircles*3];
		double expected[][] = new double[numScenarios][1];

		for( int i = 0; i < numScenarios / 2; i++ ){
			System.out.println("Creating scenario " + i);
			Circle scenario[] = generateScenario(true,numCircles);
			for( int j = 0; j < numCircles; j++ ){
				input[i][j*3+0] = scenario[j].getRadius();
				input[i][j*3+1] = scenario[j].getCenter().getX();
				input[i][j*3+2] = scenario[j].getCenter().getY();
			}
			
			expected[i][0] = 1.0;
		}

		for( int i = numScenarios / 2; i < numScenarios; i++ ){
			System.out.println("Creating scenario " + i);
			Circle scenario[] = generateScenario(false,numCircles);
			for( int j = 0; j < numCircles; j++ ){
				input[i][j*3+0] = scenario[j].getRadius();
				input[i][j*3+1] = scenario[j].getCenter().getX();
				input[i][j*3+2] = scenario[j].getCenter().getY();
			}
			
			expected[i][0] = 0.0;
		}
		
		MLDataSet trainingSet = new BasicMLDataSet(input,expected);
		BasicNetwork network = new BasicNetwork();
		network.addLayer(new BasicLayer(null,true,numCircles*3));
		network.addLayer(new BasicLayer(new ActivationSigmoid(),true,numCircles*3));
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

	public static Circle[] generateScenario(boolean overlapConditionDesired, int numCircles){
		Circle retval[] = new Circle[numCircles];
		
		boolean overlaps;
		do{
			overlaps = false;
			for( int i = 0; i < numCircles; i++ ){
				retval[i] = new Circle(Math.random() * 2.0 - 1.0,Math.random() * 2.0 - 1.0,Math.random() + .05);
				for( int j = 0; j < i; j++ ){
					if( retval[i].overlaps(retval[j])){
						overlaps = true;
					}
				}
			}
		}while( overlaps != overlapConditionDesired );
		
		return retval;
	}
	
}
