package calculator;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class calc extends JFrame implements ActionListener, KeyListener {
	
	private static final long serialVersionUID = 1;

	private JTextField textField;
	public JButton buttons[][] = new JButton[5][4];
	double number1;
	double number2;
	boolean displayAnswer;
	String lastOperator;
	String answer;

	ArrayList<String> numbers = new ArrayList<String>();

	public static void main(String[] args) {
		new calc();
	}

	public calc() {
		Font font = new Font("Leelawadee UI Semilight", Font.PLAIN, 40);
		Color color = new Color(48, 48, 48);

		this.setTitle(" C A L C U L A T O R");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(620, 900));
		this.setLocationRelativeTo(null);
		JPanel main = new JPanel();

		main.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.weightx = 100;
		constraints.weighty = 100;
		constraints.gridwidth = 4;
		constraints.gridheight = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.BOTH;

		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setBorder(null);
		textField.setEditable(false);
		textField.addKeyListener(this);
		textField.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 93));
		main.add(textField, constraints);
		String[][] buttonNames = new String[][] { 
			{ "AC", "DEL", "%", "÷" }, 
			{ "7", "8", "9", "x" },
			{ "4", "5", "6", "-" }, 
			{ "1", "2", "3", "+" }, 
			{ "0", ".", "±", "=" } };
		constraints.gridwidth = 1;

		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {

				buttons[i][j] = new JButton(buttonNames[i][j]);
				buttons[i][j].setFocusable(false);
				buttons[i][j].setFont(font);
				buttons[i][j].setBackground(color);
				buttons[i][j].setBorder(null);
				buttons[i][j].setForeground(Color.WHITE);
				buttons[i][j].addActionListener(this);
				buttons[i][j].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				if (j == 3) {
					buttons[i][j].setBackground(new Color(111, 226, 205));
				}

				constraints.gridx = j;
				constraints.gridy = i + 1;
				main.add(buttons[i][j], constraints);
			}
		}

		this.add(main);
		this.setVisible(true);
		this.setSize(620, 900);
	}

	public void actionPerformed(ActionEvent e) {
		text(e.getActionCommand());
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		String key = String.valueOf(e.getKeyChar());
		text(key);
		System.out.println(String.valueOf(key));
		try {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				operations(key);
			}
		} catch (NumberFormatException e1) {
		}
		if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
			backSpace();
		}
	}

	public void addNum(String number) {
			if (displayAnswer) {
			textField.setText("");
			displayAnswer = false;
		}
		try{
			if (textField.getText().length() < 12) {
			textField.setText(textField.getText() + number);}
		}
			catch (Exception e){
				textField.setText(textField.getText());
			}
	}

	public void operations(String operator) {	
		try {
			if (number1 != 0) {
				number2 = Double.parseDouble(textField.getText());
				if (lastOperator.equals("+") && operator.equals("=")) {
					float ans = ((float) (number1 + number2));
					answer = String.valueOf(ans);
					displayAnswer = true;
				} else if (lastOperator.equals("-") && operator.equals("=")) {
					float ans = ((float) (number1 - number2));
					answer = String.valueOf(ans);
					displayAnswer = true;
				} else if (lastOperator.equals("x") || lastOperator.equals("*") && operator.equals("=")
						|| (lastOperator.equals("*") && operator.equals("="))) {
					float ans = ((float) (number1 * number2));
					answer = String.valueOf(ans);
					displayAnswer = true;
				} else if (lastOperator.equals("÷") || lastOperator.equals("/") && operator.equals("=")
						|| (lastOperator.equals("/") && operator.equals("="))) {
					float ans = ((float) (number1 / number2));
					answer = String.valueOf(ans);
					displayAnswer = true;
				} else if (lastOperator.equals("%") && operator.equals("=")) {
					float ans = ((float) (number1 % number2));
					answer = String.valueOf(ans);
					displayAnswer = true;
				}
			} else {
				number1 = Double.parseDouble(textField.getText());
			}
			if (displayAnswer) {
				number1 = Double.parseDouble(answer);
			}
			lastOperator = operator;
			textField.setText(answer);
		} catch (NumberFormatException e) {
		}
		
	}

	public void allClear() {
		textField.setText("");
		answer = "";
		number1 = 0;
	}
	public void backSpace() {
		if (textField.getText().length() > 0) {
			textField.setText(textField.getText().substring(0, textField.getText().length() - 1));
		}
	}
	public void plusM() {
		try {
			double ops = Double.parseDouble(String.valueOf(textField.getText()));
			ops = ops * -1;
			textField.setText(String.valueOf(ops));
		} catch (NumberFormatException e) {
		}
	}
	public void decimalP() {
		if (!textField.getText().contains(".")) {
			textField.setText(textField.getText() + ".");
		}
	}
	public void text(String numOp) {
		switch (numOp) {
		case "0":
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
			addNum(numOp);
			break;
		case ".":
			decimalP();
			break;
		case "x":
		case "*":
		case "+":
		case "-":
		case "/":
		case "÷":
		case "%":
		case "=":
			operations(numOp);
			break;
		case "DEL":
			backSpace();
			break;
		case "AC":
			allClear();
			break;
		case "±":
			plusM();
			break;
		}
	}	
}