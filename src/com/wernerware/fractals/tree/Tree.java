package com.wernerware.fractals.tree;

import java.awt.Color;
import java.awt.Graphics2D;

import org.apache.commons.math3.geometry.euclidean.threed.Rotation;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Tree {
	
	private Vector3D trunkBase, trunkTop;
	private Tree child1, child2;
	private Color colors[];
	private int depth;
	
	public Tree(Vector3D trunkBase, Vector3D trunkTop, ReductionRatioGenerator reductionRatio, TreeBranchAngleGenerator anGen, Color colors[], int depth) {
		this.trunkBase = trunkBase;
		this.trunkTop = trunkTop;
		this.colors = colors;
		this.depth = depth;
		
		double reductionRatios[] = reductionRatio.getReductionRatios(depth);
		Vector3D diff1 = trunkTop.subtract(trunkBase);
		diff1 = diff1.scalarMultiply(reductionRatios[0]);
		Vector3D diff2 = trunkTop.subtract(trunkBase);
		diff2 = diff2.scalarMultiply(reductionRatios[1]);
		double angles[] = anGen.getAngles(depth);
		Rotation rot1 = new Rotation(new Vector3D(0,0,1),angles[0]);
		Rotation rot2 = new Rotation(new Vector3D(0,0,1),angles[1]);

		Vector3D rightBranchDisplacement = rot1.applyTo(diff1);
		Vector3D leftBranchDisplacement = rot2.applyTo(diff2);
		
		if( depth > 0 ){
			Vector3D rightBranchBase = trunkTop;
			Vector3D rightBranchTop = trunkTop.add(rightBranchDisplacement);
			Vector3D leftBranchBase = trunkTop;
			Vector3D leftBranchTop = trunkTop.add(leftBranchDisplacement);

			child1 = new Tree(rightBranchBase,rightBranchTop,reductionRatio,anGen,colors,depth-1);
			child2 = new Tree(leftBranchBase,leftBranchTop,reductionRatio,anGen,colors,depth-1);
		}
	}
	
	public void draw(Graphics2D gfx) {
		gfx.setColor(colors[depth]);
		gfx.drawLine((int)trunkBase.getX(), (int)trunkBase.getY(), (int)trunkTop.getX(), (int)trunkTop.getY());
		if( child1 != null && child2 != null ){
			child1.draw(gfx);
			child2.draw(gfx);
		}
	}
	
}
