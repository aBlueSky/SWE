import javax.swing.*;

import java.awt.*;
public class MainBoard extends JFrame {
		private JLabel title;
	public MainBoard(){
		setTitle("CAN'T STOP!");
		setSize(400,700);
		setLocation(10,200);
		setLayout(new FlowLayout());
		title = new JLabel("CAN'T STOP!");
		add(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args){
		JFrame fra = new MainBoard();
		fra.show();
		String username = JOptionPane.showInputDialog("Enter your Username");
		String password = JOptionPane.showInputDialog("Enter your Password");
	}
}
