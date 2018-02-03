

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	
	public Window(int WIDTH, int HEIGHT, String title, Game game){
		JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
		frame.add(game);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
