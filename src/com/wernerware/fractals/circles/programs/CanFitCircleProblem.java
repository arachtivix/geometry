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

public class CanFitCircleProblem {

	public static void main(String[] args) {

		int maxSize = 0;
		int numTrainingScenarios = 1000;
		List<List<Circle>> trainingData = new LinkedList<List<Circle>>();
		for( int i = 0; i < numTrainingScenarios; i++ ){
			List<Circle> scenario = generateScenario(20,i%2==0);
			trainingData.add(scenario);
			maxSize = maxSize < scenario.size() ? scenario.size() : maxSize;
		}

		double input[][] = new double[numTrainingScenarios][maxSize*3];
		double expected[][] = new double[numTrainingScenarios][1];
		for( int i = 0; i < numTrainingScenarios; i++ ){
			List<Circle> scenario = trainingData.get(i);
			for( int j = 0; j < maxSize; j++ ){
				if( i < scenario.size() ){
					Circle c = scenario.get(i);
					input[i][j*3+0] = c.getRadius();
					input[i][j*3+1] = c.getCenter().getX();
					input[i][j*3+2] = c.getCenter().getY();
				} else {
					input[i][j*3+0] = 0;
					input[i][j*3+1] = 0;
					input[i][j*3+2] = 0;
				}
			}
			expected[i][0] = i%2==0 ? 1.0 : 0.0;
		}
		
		MLDataSet trainingSet = new BasicMLDataSet(input,expected);
		BasicNetwork network = new BasicNetwork();
		network.addLayer(new BasicLayer(null,true,maxSize*3));
		network.addLayer(new BasicLayer(new ActivationSigmoid(),true,maxSize*3));
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

	public static List<Circle> generateScenario(int numCircles, boolean canFitOneMore){
		List<Circle> retval;
		
		Circle c = new Circle(0,0,1);
		double minSub = .15;
		double maxSub = .5;
		double targetCoverage = .99;
		int maxTries = 100000;
		
		retval = NonOverlappingSubCircles.getNonOverlappingSubCircles(c, minSub, maxSub, targetCoverage, maxTries);
		
		Collections.sort(retval);
		
		if( canFitOneMore ) retval.remove(retval.size()-1);
		
		return retval;
	}
	
}
