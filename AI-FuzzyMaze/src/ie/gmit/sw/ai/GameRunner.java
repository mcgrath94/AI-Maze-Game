package ie.gmit.sw.ai;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ie.gmit.sw.ai.traversers.*;
import ie.gmit.sw.gameover.GameOver;

public class GameRunner implements KeyListener{
	private static final int MAZE_DIMENSION = 40;
	private Node[][] model;
	private GameView view;
	private int currentRow;
	private int currentCol;
	private int goalRow;
	private int goalCol;
	private int enemyRow;
	private int enemyCol;
	
	public static boolean won = false;

	
	public GameRunner() throws Exception{
		Maze m = new Maze(MAZE_DIMENSION, MAZE_DIMENSION);
		model = m.getMaze();
    	view = new GameView(model);
    	
    	placePlayer();
    	placeGoal();
    	placeEnemy();
    	
    	Dimension d = new Dimension(GameView.DEFAULT_VIEW_SIZE, GameView.DEFAULT_VIEW_SIZE);
    	view.setPreferredSize(d);
    	view.setMinimumSize(d);
    	view.setMaximumSize(d);
    	
    	JFrame f = new JFrame("G00299578 - AI Project");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addKeyListener(this);
        f.getContentPane().setLayout(new FlowLayout());
        f.add(view);
        f.setSize(1000,1000);
        f.setLocation(100,100);
        f.pack();
        f.setVisible(true);
	}
	
	
	private void placePlayer(){   	
		//places player at a random row and col in maze
    	currentRow = (int) (MAZE_DIMENSION * Math.random());
    	currentCol = (int) (MAZE_DIMENSION * Math.random());
    	model[currentRow][currentCol].setFeature('P');
    	updateView(); 		
	}
	
	private void placeGoal(){   	
		//places goal at a random row and col in maze
    	goalRow = (int) (MAZE_DIMENSION * Math.random());
    	goalCol = (int) (MAZE_DIMENSION * Math.random());
    	model[goalRow][goalCol].setFeature('G');
    	model[goalRow][goalCol].setGoalNode(true);
    	//model[goalRow][goalCol].isGoalNode();
	}
	
	private void placeEnemy(){   	
		//places player at a random row and col in maze
    	enemyRow = (int) (MAZE_DIMENSION * Math.random());
    	enemyCol = (int) (MAZE_DIMENSION * Math.random());
    	model[enemyRow][enemyCol].setFeature('S');
	}
	
	private void updateView(){
		//updates view so player is always centre
		view.setCurrentRow(currentRow);
		view.setCurrentCol(currentCol);
	}

	
    public void keyPressed(KeyEvent e) {
    	//if right is pressed - move right if valid move
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && currentCol < MAZE_DIMENSION - 1) {
        	if (isValidMove(currentRow, currentCol + 1)) 
        		currentCol++; // up a column 	      	
        }
        
        //if left is pressed - move left if valid move
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && currentCol > 0) {
        	if (isValidMove(currentRow, currentCol - 1)) 
        		currentCol--; // down a column         	
        }
        
        //if up is pressed - move up if valid move
        else if (e.getKeyCode() == KeyEvent.VK_UP && currentRow > 0) {
        	if (isValidMove(currentRow - 1, currentCol)) 
        		currentRow--; // down a row  		
        }
        
        //if down is pressed - move down if valid move
        else if (e.getKeyCode() == KeyEvent.VK_DOWN && currentRow < MAZE_DIMENSION - 1) {
        	if (isValidMove(currentRow + 1, currentCol)) 
        		currentRow++; //up a row   	
        }
        
        //if z is pressed, zooms out
        else if (e.getKeyCode() == KeyEvent.VK_Z){
        	view.toggleZoom();
        }
        else{
        	return;
        }
        //calls update view to the position moved to
        updateView();       
    }
    
    public void keyReleased(KeyEvent e) {} //Ignore
	public void keyTyped(KeyEvent e) {} //Ignore
	

    //checks if the move is valid - this is called when a key is pressed
	private boolean isValidMove(int r, int c){
		// if position to move to is empty(' '), and less than maze size, isValidMove returns true
		if (r <= model.length - 1 && c <= model[r].length - 1 && model[r][c].getFeature() == ' '){
			model[currentRow][currentCol].setFeature(' ');
			model[r][c].setFeature('P');
			return true;
		}
		
		//if goal node
		else if(r <= model.length - 1 && c <= model[r].length - 1 && model[r][c].getFeature() == 'G'){
			model[currentRow][currentCol].setFeature(' ');
			model[r][c].setFeature('P');
			gameWon();
			return true;
		}
		
		//if weapon
		else if(r <= model.length - 1 && c <= model[r].length - 1 && model[r][c].getFeature() == 'W'){
			model[currentRow][currentCol].setFeature('P');
			model[r][c].setFeature('X');
			gotWeapon();
			return false;
		}
		
		//if bomb
		else if(r <= model.length - 1 && c <= model[r].length - 1 && model[r][c].getFeature() == 'B'){
			model[currentRow][currentCol].setFeature('P');
			model[r][c].setFeature('X');			
			gotBomb();
			return false;
		}
		
		else{
			return false; //Can't move
		}
	}
	
	//when got weapon
	private void gotWeapon() {
		System.out.println("Test weapon");
		GameView.hasWeapon = true;
		
	}
	
	//when got normal bomb
	private void gotBomb() {
		System.out.println("Test bomb");
		
		
		
		//Traversator t = new DepthLimitedDFSTraversator(model.length / 2);
		//t.traverse(model, model[0][0], view);	
	}

	//when goal reached
	private void gameWon() {
		System.out.println("Test won");
		won = true;
		new GameOver();
	}

	
	public static void main(String[] args) throws Exception{
		new GameRunner();
	}
}