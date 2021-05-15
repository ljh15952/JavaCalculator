import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class MyFrame extends JFrame {
	private JTextField _inputField;
	private JButton[] _btn_num;
	private JButton[] _btn_sentence;
	private JButton _btn_equal;
	private JButton _btn_erase;
	private JButton _btn_dot;
	public MyFrame() {
		setSize(330, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.pink);

		InitFrame();

		setLayout(null);
		setVisible(true);
	}

	public void InitFrame() {
		_inputField = new JTextField();
		add(_inputField);
		_btn_num = new JButton[10];
		for (int i = 0; i < 10; i++) {
			_btn_num[i] = new JButton(Integer.toString(i));
			// _btn_num[i].addActionListener(myListener);
			add(_btn_num[i]);
		}
		_btn_sentence = new JButton[4];
		_btn_sentence[0] = new JButton("+");
		_btn_sentence[1] = new JButton("-");
		_btn_sentence[2] = new JButton("*");
		_btn_sentence[3] = new JButton("/");
		for (int i = 0; i < 4; i++) {
			// _btn_num[i].addActionListener(myListener);
			add(_btn_sentence[i]);
		}
		_btn_equal = new JButton("=");
		add(_btn_equal);
		_btn_erase = new JButton("C");
		add(_btn_erase);
		_btn_dot = new JButton(".");
		add(_btn_dot);
	}

	public void DrawFrame() {
		// Draw button and TextFeild
		int Side = 7;
		int btnWidth = (int) (getWidth() / 4 - Side);
		int btnHeight = (int) (getHeight() / 5) - 10;
		System.out.println(btnHeight);
		_inputField.setBounds(btnWidth, 0, btnWidth * 2 + Side, btnHeight);

		int num = 1;
		for (int y = 3; y >= 1; y--) {
			for (int x = 0; x <= 2; x++) {
				_btn_num[num++].setBounds(Side + btnWidth * x, btnHeight * y, btnWidth, btnHeight);
			}
		}
		_btn_dot.setBounds(Side + btnWidth * 0, btnHeight * 4, btnWidth, btnHeight);
		_btn_num[0].setBounds(Side + btnWidth * 1, btnHeight * 4, btnWidth, btnHeight);
		_btn_equal.setBounds(Side + btnWidth * 2, btnHeight * 4, btnWidth, btnHeight);
		
		_btn_erase.setBounds(Side + btnWidth * 0, 0, btnWidth - 30, btnHeight - 30);
		
		for(int i = 0; i < 4; i++) {
			_btn_sentence[i].setBounds(Side + btnWidth * 3, btnHeight * (i+1), btnWidth, btnHeight);
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		DrawFrame();
	}
}
