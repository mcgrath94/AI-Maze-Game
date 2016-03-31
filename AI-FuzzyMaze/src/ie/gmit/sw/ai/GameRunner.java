package ie.gmit.sw.ai;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class GameRunner implements KeyListener{
	private static final int MAZE_DIMENSION = 60;
	private char[][] model;
	private GameView view;
	private int currentRow;
	private int currentCol;
	private int goalRow;
	private int goalCol;
	
	public GameRunner() throws Exception{
		Maze m = new Maze(MAZE_DIMENSION, MAZE_DIMENSION);
		model = m.getMaze();
    	view = new GameView(model);
    	
    	placePlayer();
    	placeGoal();
    	
    	Dimension d = new Dimension(GameView.DEFAULT_VIEW_SIZE, GameView.DEFAULT_VIEW_SIZE);
    	view.setPreferredSize(d);
    	view.setMinimumSize(d);
    	view.setMaximumSize(d);
    	
    	JFrame f = new JFrame("G00299578 - AI Project)");
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
    	model[currentRow][currentCol] = 'P';
    	updateView(); 		
	}
	
	private void placeGoal(){   	
		//places goal at a random row and col in maze
    	goalRow = (int) (MAZE_DIMENSION * Math.random());
    	goalCol = (int) (MAZE_DIMENSION * Math.random());
    	model[goalRow][goalCol] = 'G';		
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
		if (r <= model.length - 1 && c <= model[r].length - 1 && model[r][c] == ' '){
			model[currentRow][currentCol] = ' ';
			model[r][c] = 'P';
			return true;
		}else{
			return false; //Can't move
		}
	}
	
	public static void main(String[] args) throws Exception{
		new GameRunner();
	}
}