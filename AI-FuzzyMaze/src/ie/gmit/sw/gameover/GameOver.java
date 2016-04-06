package ie.gmit.sw.gameover;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ie.gmit.sw.ai.*;

public class GameOver extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public GameOver() {
				
		JPanel panel = new JPanel();

		JButton p = new JButton("Replay!");
		p.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == p){
					dispose();
					try {
						new GameRunner();
					} 
					catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		panel.add(p, BorderLayout.CENTER);
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
		g2.drawString("Game Over", 125, 100);
		
		if(GameRunner.won == true){
			g2.setFont(new Font("SansSerif", Font.ROMAN_BASELINE, 50));
			g2.drawString("You won!", 125, 250);
		}
	}

}

