package ie.gmit.sw.ai.traversers;

import java.awt.Component;

import ie.gmit.sw.ai.*;

public class DepthLimitedDFSTraversator implements Traversator{
	private Node[][] maze;
	private Component viewer;
	private int limit;
	private boolean keepRunning = true;
	private long time = System.currentTimeMillis();
	private int visitCount = 0;

	public DepthLimitedDFSTraversator(int limit){
		this.limit = limit;
	}
	
	public void traverse(Node[][] maze, Node node, Component viewer) {
		this.maze = maze;
		this.viewer = viewer;
		
		dfs(node, 1);
		
		if (keepRunning){
			System.out.println("Failed to find goal node within a depth of " + limit);
		}
	}
	
	private void dfs(Node node, int depth){
		if (!keepRunning || depth > limit) return;
		
		node.setVisited(true);	
		visitCount++;
		viewer.repaint();
		
		if (node.isGoalNode()){
	        time = System.currentTimeMillis() - time; //Stop the clock
	        TraversatorStats.printStats(node, time, visitCount);
	        viewer.repaint();
	        keepRunning = false;
			return;
		}
		
		try { //Simulate processing each expanded node
			Thread.sleep(10);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Node[] children = node.children(maze);
		for (int i = 0; i < children.length; i++) {
			if (children[i] != null && !children[i].isVisited()){
				children[i].setParent(node);
				dfs(children[i], depth + 1);
			}
		}
	}
}