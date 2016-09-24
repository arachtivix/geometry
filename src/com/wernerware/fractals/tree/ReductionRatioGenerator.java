package com.wernerware.fractals.tree;

public class ReductionRatioGenerator {

	private double standard, randomness;
	
	public ReductionRatioGenerator(double standard, double randomness) {
		this.standard = standard;
		this.randomness = randomness;
	}
	
	public double[] getReductionRatios(int depth){
		double retval[] = new double[2];

		retval[0] = standard + Math.random() * randomness - randomness/2.0;
		retval[1] = standard + Math.random() * randomness - randomness/2.0;
		
		return retval;
	}
}
