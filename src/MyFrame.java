import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private String _showStr = "";
	private double _operand = 0;
	private Font _font;
	MyActionListener myListener = new MyActionListener();

	public MyFrame() {
		setSize(340, 370);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.pink);

		InitFrame();

		setLayout(null);
		setVisible(true);

		_font = new Font("굴림체", Font.BOLD, 25);
		setFont(_font);
	}

	public void InitFrame() {
		_inputField = new JTextField();

		add(_inputField);
		_btn_num = new JButton[10];
		for (int i = 0; i < 10; i++) {
			_btn_num[i] = new JButton(Integer.toString(i));
			_btn_num[i].addActionListener(myListener);
			add(_btn_num[i]);
		}
		_btn_sentence = new JButton[4];
		_btn_sentence[0] = new JButton("+");
		_btn_sentence[1] = new JButton("-");
		_btn_sentence[2] = new JButton("*");
		_btn_sentence[3] = new JButton("/");
		for (int i = 0; i < 4; i++) {
			_btn_sentence[i].addActionListener(myListener);
			add(_btn_sentence[i]);
		}
		_btn_equal = new JButton("=");
		_btn_equal.addActionListener(myListener);
		add(_btn_equal);
		_btn_erase = new JButton("C");
		_btn_erase.addActionListener(myListener);
		add(_btn_erase);
		_btn_dot = new JButton(".");
		_btn_dot.addActionListener(myListener);
		add(_btn_dot);
	}

	public void DrawFrame() {
		// Draw button and TextFeild
		int Side = 7;
		int btnWidth = (int) (getWidth() / 4 - Side);
		int btnHeight = (int) (getHeight() / 5) - 10;
		_inputField.setBounds(btnWidth, 0, btnWidth * 2 + Side, btnHeight);
		_inputField.setFont(_font);
		int num = 1;
		for (int y = 3; y >= 1; y--) {
			for (int x = 0; x <= 2; x++) {
				_btn_num[num].setFont(_font);
				_btn_num[num++].setBounds(Side + btnWidth * x, btnHeight * y, btnWidth, btnHeight);
			}
		}
		_btn_dot.setBounds(Side + btnWidth * 0, btnHeight * 4, btnWidth, btnHeight);
		_btn_dot.setFont(_font);
		_btn_num[0].setBounds(Side + btnWidth * 1, btnHeight * 4, btnWidth, btnHeight);
		_btn_num[0].setFont(_font);
		_btn_equal.setBounds(Side + btnWidth * 2, btnHeight * 4, btnWidth, btnHeight);
		_btn_equal.setFont(_font);

		_btn_erase.setBounds(Side + btnWidth * 0, 0, btnWidth - 30, btnHeight - 30);
		_btn_erase.setFont(_font);

		for (int i = 0; i < 4; i++) {
			_btn_sentence[i].setBounds(Side + btnWidth * 3, btnHeight * (i + 1), btnWidth, btnHeight);
			_btn_sentence[i].setFont(_font);
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		DrawFrame();
	}

	private int _sentenceNum = 0;
	private Boolean _isPushSentence = false;

	class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// Step 1
			// Make just output system
			// Step 2
			// Make Calculate System
			if (e.getSource() == _btn_erase) {
				_showStr = "";
				_operand = 0;
				_sentenceNum = 0;
				_isPushSentence = false;
			} else if (e.getSource() == _btn_dot) {
				_showStr += ".";
			} else if (e.getSource() == _btn_equal) {
				_showStr = Calculate();
				_sentenceNum = 0;
				_isPushSentence = false;
			} else {
				for (int i = 0; i < 10; i++) {
					if (e.getSource() == _btn_num[i]) {
						if (_isPushSentence) {
							// 연산자를 눌렀다면
							_showStr = "";
							_isPushSentence = false;
						}
						_showStr += Integer.toString(i);
						break;
					}
				}
				for (int i = 0; i < 4; i++) {
					if (e.getSource() == _btn_sentence[i]) {
						// 숫자 입력을 안했을 경우
						if (_showStr == "")
							break;
						_isPushSentence = true;

						if (_operand == 0) {
							// 피연산자 설정
							if (_showStr.contains(".")) {
								_operand = Double.parseDouble(_showStr);
							} else {
								_operand = Integer.parseInt(_showStr);
							}
							// 무슨 연산자를 눌렀나
							_sentenceNum = i + 1;
						} else {
							_showStr = Calculate();
							// 무슨 연산자를 눌렀나
							_sentenceNum = i + 1;
						}
						break;
					}
				}
			}
			_inputField.setText(_showStr);
		}
	}

	private String Calculate() {
		// 직접적인 계산
		double num = Double.parseDouble(_showStr);
		if (_sentenceNum == 1) {
			_operand += num;
		} else if (_sentenceNum == 2) {
			_operand -= num;
		} else if (_sentenceNum == 3) {
			_operand *= num;
		} else if (_sentenceNum == 4) {
			_operand /= num;
		}

		// 정수형으로 출력할 수 있으면 정수형 출력
		if (_operand == (int) _operand)
			return Integer.toString((int) _operand);
		else
			return Double.toString(Math.round(_operand*100)/100.0);
	}
}
