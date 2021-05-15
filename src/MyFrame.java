import java.awt.Color;

import javax.swing.JFrame;

public class MyFrame extends JFrame{
	public MyFrame() {
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.pink);
		setLayout(null);
		setVisible(true);
	}
}
