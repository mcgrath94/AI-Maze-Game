package ie.gmit.sw.ai.traversers;

import java.util.ArrayList;
import java.util.List;

import ie.gmit.sw.ai.*;

public class DFSBomb {
	
	private Node[][] maze;
	private int limit;
	private boolean keepRunning = true;
	private List<Node> list = new ArrayList<Node>();
		
	public DFSBomb(int limit){
		this.limit = limit;
	}
		
	public void traverse(Node[][] maze, Node node) {
		this.maze = maze;
		dfs(node, 1);			
		}
		
	private void dfs(Node node, int depth){
		
		if (!keepRunning || depth > limit) return;
			
		node.setVisited(true);	
		ArrayList<Node> dfsList = node.dfsNodes(maze);
		
		for (Node children : dfsList) 
		{
			if(children.getFeature() != 'S')
			{
				if (children != null && !children.isVisited())
				{	
					children.setParent(node);
					list.add(children);
					dfs(children, limit+1);
				}
			}
		}
		for(Node children2 : list)
		{	
			if(children2.getFeature() == 'P'){
				children2.setFeature('P');
			}
			else if(children2.getFeature() == 'S' || children2.getFeature() == 'X'){
				children2.setFeature(' ');
			}
		}
		for (int i = 0; i < maze.length; i++)
		{
			for (int j = 0; j < maze[i].length; j++)
			{
				maze[i][j].setVisited(false);
				maze[i][j].setParent(null);
			}
		}
		list.clear();
	}
}

