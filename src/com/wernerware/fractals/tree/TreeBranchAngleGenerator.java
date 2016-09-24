package com.wernerware.fractals.tree;

public class TreeBranchAngleGenerator {

	private double max, angleConstrictionFactor;
	
	public TreeBranchAngleGenerator(double max, double angleConstrictionFactor){
		this.max = max;
		this.angleConstrictionFactor = angleConstrictionFactor;
	}
	
	public double[] getAngles(int depth){
		double retval[] = new double[2];
		
		retval[0] = Math.random()*max*Math.pow(angleConstrictionFactor, depth);
		retval[1] = Math.random()*-max*Math.pow(angleConstrictionFactor, depth);
		
		return retval;
	}
	
}
