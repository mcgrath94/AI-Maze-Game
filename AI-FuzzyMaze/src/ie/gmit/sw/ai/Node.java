package ie.gmit.sw.ai;

import java.awt.Color;

public class Node {
	private static final int MAX_EXITS = 4;
	public enum NodeType{Wall, Passage};
	public enum NodePassage{North, South, East, West, None};
	private Node parent;
	private Color color = Color.BLACK;
	private NodeType type = NodeType.Wall;
	private NodePassage passage = NodePassage.None;
	public boolean visited =  false;
	public boolean goal;
	private int row = -1;
	private int col = -1;
	private int distance;
	private char feature;
	
	public Node(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setFeature(char feature){
		this.feature = feature;
	}

	public char getFeature(){
		return this.feature;
	}

	
	
	public Node[] children(Node[][] maze){
		
		Node[] children = new Node[MAX_EXITS];
		if (col - 1 >=0 && passage == NodePassage.West) children[0] = maze[row][col - 1]; //A West edge
		if (col + 1 < maze[row].length && maze[row][col + 1].getPassage() == NodePassage.West) children[1] = maze[row][col + 1]; //An East Edge
		if (row - 1 >= 0 && passage == NodePassage.North) children[2] = maze[row - 1][col]; //A North edge
		if (row + 1 < maze.length && maze[row + 1][col].getPassage() == NodePassage.North) children[3] = maze[row + 1][col]; //An South Edge
	
		int counter = 0;
		for (int i = 0; i < children.length; i++) {
			if (children[i] != null) counter++;
		}
		
		Node[] tmp = new Node[counter];
		int index = 0;
		for (int i = 0; i < children.length; i++) {
			if (children[i] != null){
				tmp[index] = children[i];
				index++;
			}
		}

		return tmp;
	}
	
	public NodeType getType() {
		return type;
	}
	
	public void setType(NodeType type) {
		this.type = type;
	}

	public NodePassage getPassage() {
		return passage;
	}

	public void setPassage(NodePassage passage) {
		this.passage = passage;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.color = Color.BLUE;
		this.visited = visited;
	}

	public boolean isGoalNode() {
		return goal;
	}

	public void setGoalNode(boolean goal) {
		this.goal = goal;
	}
	
	public int getHeuristic(Node goal){
		double x1 = this.col;
		double y1 = this.row;
		double x2 = goal.getCol();
		double y2 = goal.getRow();
		return (int) Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
	}

	
	public int getPathCost() {
		return distance;
	}

	public void setPathCost(int distance) {
		this.distance = distance;
	}

	public String toString() {
		if (passage == NodePassage.North){
			return "N ";
		}else{
			return "W ";
		}
	}

}

