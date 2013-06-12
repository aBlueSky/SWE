package networking;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.*;
import java.io.*;
import java.util.*;

public class GUI extends JFrame {
	Socket playerSocket = null;
	PrintWriter output = null;
	BufferedReader input = null;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		boolean valid = false;
		boolean uservalid = false;
		boolean passwordvalid = false;
		String userType;
		Socket socket;
		PrintWriter out;
		BufferedReader in;
		String answer = JOptionPane.showInputDialog("Are you a new user? Please enter yes or no.");
		while(!valid){
			if(answer.equals("no")|| answer.equals("No") || answer.equals("NO")){
				String username = JOptionPane.showInputDialog("Enter your Username");
				while(!uservalid){
					if(username.length()==0){
						username = JOptionPane.showInputDialog("Invalid Length. Please enter your username");
					}
					else{
						userType = "R, " + username;
						try{
						socket = new Socket("localhost", 2043);
						out = new PrintWriter(socket.getOutputStream(), true);
						in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						}
						catch(IOException e){
							System.err.println("Could not connect socket: " + e.getMessage());
						}
						uservalid = true;
					}
				}
				String password = JOptionPane.showInputDialog("Enter your Password");
				while(!passwordvalid){
					if(password.length()==0){
						password = JOptionPane.showInputDialog("Invalid Length. Please enter your password");
					}
					else{
						passwordvalid = true;
					}
				}
				valid = true;
			}
			else if(answer.equals("yes")|| answer.equals("Yes") || answer.equals("YES")){
				String newusername = JOptionPane.showInputDialog("Enter desired username");
				while(!uservalid){
					if(newusername.length()==0){
						newusername = JOptionPane.showInputDialog("Invalid Length. Please enter another username");
					}
					else{
						userType = "N, " + newusername;
						try{
							socket = new Socket("localhost", 2043);
							out = new PrintWriter(socket.getOutputStream(), true);
							in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						}
						catch(IOException e){
							System.err.println("Could not connect socket: " + e.getMessage());
						}
						uservalid = true;
					}
				}
				String newpassword = JOptionPane.showInputDialog("Enter desired password");
				while(!passwordvalid){
					if(newpassword.length()==0){
						newpassword = JOptionPane.showInputDialog("Invalid Length. Please enter another password");
					}
					else{
						passwordvalid = true;
					}
				}
				valid = true;
			}
			else{
				answer = JOptionPane.showInputDialog("Invalid answer. Please type in yes or no.");
			}
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		try{
			playerSocket = new Socket("localhost", 2043);
			output = new PrintWriter(playerSocket.getOutputStream(), true);
			input = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
		}
		catch(IOException e){
			System.err.println("Could not connect socket: " + e.getMessage());
		}
		setAlwaysOnTop(true);
		setFont(new Font("Orator Std", Font.PLAIN, 12));
		setTitle("Can't Stop");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 726, 599);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCantStop = new JLabel("CAN'T STOP");
		lblCantStop.setFont(new Font("Orator Std", Font.PLAIN, 26));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 102));
		
		JButton btnRoll = new JButton("Roll");
		btnRoll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.println("roll");
			}
		});
		btnRoll.setBackground(new Color(255, 127, 80));
		btnRoll.setFont(new Font("Orator Std", Font.PLAIN, 12));
		
		JButton btnBust = new JButton("Crap");
		btnBust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.println("crap");
			}
		});
		btnBust.setBackground(new Color(255, 127, 80));
		btnBust.setFont(new Font("Orator Std", Font.PLAIN, 12));
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				output.println("stop");
			}
		});
		btnStop.setBackground(new Color(255, 99, 71));
		btnStop.setFont(new Font("Orator Std", Font.PLAIN, 12));
		
		JButton btnGo = new JButton("Go");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				output.println("go");
			}
		});
		btnGo.setBackground(new Color(255, 127, 80));
		btnGo.setFont(new Font("Orator Std", Font.PLAIN, 11));
		JButton button_valid = new JButton(" ");
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 102));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(137)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 343, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(112, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(156)
					.addComponent(btnStop)
					.addGap(26)
					.addComponent(btnGo)
					.addGap(18)
					.addComponent(btnBust)
					.addGap(18)
					.addComponent(btnRoll)
					.addContainerGap(317, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(81, Short.MAX_VALUE)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 540, GroupLayout.PREFERRED_SIZE)
					.addGap(79))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(244)
					.addComponent(lblCantStop)
					.addContainerGap(296, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCantStop)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
					.addGap(59)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnStop)
						.addComponent(btnGo)
						.addComponent(btnBust)
						.addComponent(btnRoll))
					.addGap(18)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
					.addGap(25))
		);
		panel_1.setLayout(new MigLayout("", "[39px][39px][39px][39px][39px][39px][39px][39px][45px][45px][45px]", "[23px][23px][23px][23px][23px][23px][23px][]"));
		
		JButton button_5 = new JButton("7");
		panel_1.add(button_5, "cell 5 0,alignx left,aligny top");
		button_5.setEnabled(false);
		
		JButton button_4 = new JButton("6");
		panel_1.add(button_4, "cell 4 1,alignx left,aligny top");
		button_4.setEnabled(false);
		
		JButton button_16 = new JButton("   ");
		button_16.setEnabled(false);
		panel_1.add(button_16, "cell 5 1");
		
		JButton button_6 = new JButton("8");
		panel_1.add(button_6, "cell 6 1,alignx left,aligny top");
		button_6.setEnabled(false);
		
		JButton button_3 = new JButton("5");
		panel_1.add(button_3, "cell 3 2,alignx left,aligny top");
		button_3.setEnabled(false);
		
		JButton button_15 = new JButton("   ");
		button_15.setEnabled(false);
		panel_1.add(button_15, "cell 4 2");
		
		JButton button_27 = new JButton("   ");
		button_27.setEnabled(false);
		panel_1.add(button_27, "cell 5 2");
		
		JButton button_17 = new JButton("   ");
		button_17.setEnabled(false);
		panel_1.add(button_17, "cell 6 2");
		
		JButton button_7 = new JButton("9");
		panel_1.add(button_7, "cell 7 2,alignx left,aligny top");
		button_7.setEnabled(false);
		
		JButton button_2 = new JButton("4");
		panel_1.add(button_2, "cell 2 3,alignx left,aligny bottom");
		button_2.setEnabled(false);
		
		JButton button_14 = new JButton("   ");
		button_14.setEnabled(false);
		panel_1.add(button_14, "cell 3 3");
		
		JButton button_26 = new JButton("   ");
		button_26.setEnabled(false);
		panel_1.add(button_26, "cell 4 3");
		
		JButton button_37 = new JButton("   ");
		button_37.setEnabled(false);
		panel_1.add(button_37, "cell 5 3");
		
		JButton button_28 = new JButton("   ");
		button_28.setEnabled(false);
		panel_1.add(button_28, "cell 6 3");
		
		JButton button_18 = new JButton("   ");
		button_18.setEnabled(false);
		panel_1.add(button_18, "cell 7 3");
		
		JButton button_8 = new JButton("10");
		panel_1.add(button_8, "cell 8 3,alignx left,aligny bottom");
		button_8.setEnabled(false);
		
		JButton button_1 = new JButton("3");
		panel_1.add(button_1, "cell 1 4,alignx left,aligny top");
		button_1.setEnabled(false);
		
		JButton button_13 = new JButton("   ");
		button_13.setEnabled(false);
		panel_1.add(button_13, "cell 2 4");
		
		JButton button_25 = new JButton("   ");
		button_25.setEnabled(false);
		panel_1.add(button_25, "cell 3 4");
		
		JButton button_36 = new JButton("   ");
		button_36.setEnabled(false);
		panel_1.add(button_36, "cell 4 4");
		
		JButton button_51 = new JButton("   ");
		button_51.setEnabled(false);
		panel_1.add(button_51, "cell 5 4");
		
		JButton button_38 = new JButton("   ");
		button_38.setEnabled(false);
		panel_1.add(button_38, "cell 6 4");
		
		JButton button_29 = new JButton("   ");
		button_29.setEnabled(false);
		panel_1.add(button_29, "cell 7 4");
		
		JButton button_19 = new JButton("   ");
		button_19.setEnabled(false);
		panel_1.add(button_19, "cell 8 4");
		
		JButton button_9 = new JButton("11");
		panel_1.add(button_9, "cell 9 4,alignx right,aligny top");
		button_9.setEnabled(false);
		
		JButton button = new JButton("2");
		panel_1.add(button, "cell 0 5,alignx left,aligny top");
		button.setEnabled(false);
		
		JButton button_12 = new JButton("   ");
		button_12.setEnabled(false);
		panel_1.add(button_12, "cell 1 5");
		
		JButton button_24 = new JButton("   ");
		button_24.setEnabled(false);
		panel_1.add(button_24, "cell 2 5");
		
		JButton button_35 = new JButton("   ");
		button_35.setEnabled(false);
		panel_1.add(button_35, "cell 3 5");
		
		JButton button_50 = new JButton("   ");
		button_50.setEnabled(false);
		panel_1.add(button_50, "cell 4 5");
		
		JButton button_55 = new JButton("   ");
		button_55.setEnabled(false);
		panel_1.add(button_55, "cell 5 5");
		
		JButton button_52 = new JButton("   ");
		button_52.setEnabled(false);
		panel_1.add(button_52, "cell 6 5");
		
		JButton button_39 = new JButton("   ");
		button_39.setEnabled(false);
		panel_1.add(button_39, "cell 7 5");
		
		JButton button_30 = new JButton("   ");
		button_30.setEnabled(false);
		panel_1.add(button_30, "cell 8 5");
		
		JButton button_20 = new JButton("   ");
		button_20.setEnabled(false);
		panel_1.add(button_20, "cell 9 5");
		
		JButton button_10 = new JButton("12");
		panel_1.add(button_10, "cell 10 5,alignx left,aligny top");
		button_10.setEnabled(false);
		button_10.setBackground(new Color(245, 245, 245));
		
		JButton button_11 = new JButton("   ");
		button_11.setEnabled(false);
		panel_1.add(button_11, "cell 0 6");
		
		JButton button_23 = new JButton("   ");
		button_23.setEnabled(false);
		panel_1.add(button_23, "cell 1 6");
		
		JButton button_34 = new JButton("   ");
		button_34.setEnabled(false);
		panel_1.add(button_34, "cell 2 6");
		
		JButton button_49 = new JButton("   ");
		button_49.setEnabled(false);
		panel_1.add(button_49, "cell 3 6");
		
		JButton button_54 = new JButton("   ");
		button_54.setEnabled(false);
		panel_1.add(button_54, "cell 4 6");
		
		JButton button_56 = new JButton("   ");
		button_56.setEnabled(false);
		panel_1.add(button_56, "cell 5 6");
		
		JButton button_57 = new JButton("   ");
		button_57.setEnabled(false);
		panel_1.add(button_57, "cell 6 6");
		
		JButton button_53 = new JButton("   ");
		button_53.setEnabled(false);
		panel_1.add(button_53, "cell 7 6");
		
		JButton button_40 = new JButton("   ");
		button_40.setEnabled(false);
		panel_1.add(button_40, "cell 8 6");
		
		JButton button_31 = new JButton("   ");
		button_31.setEnabled(false);
		panel_1.add(button_31, "cell 9 6");
		
		JButton button_21 = new JButton("   ");
		button_21.setEnabled(false);
		panel_1.add(button_21, "cell 10 6");
		
		JButton button_22 = new JButton("   ");
		button_22.setEnabled(false);
		panel_1.add(button_22, "cell 0 7");
		
		JButton button_33 = new JButton("   ");
		button_33.setEnabled(false);
		panel_1.add(button_33, "cell 1 7");
		
		JButton button_48 = new JButton("   ");
		button_48.setEnabled(false);
		panel_1.add(button_48, "cell 2 7");
		
		JButton button_47 = new JButton("   ");
		button_47.setEnabled(false);
		panel_1.add(button_47, "cell 3 7");
		
		JButton button_46 = new JButton("   ");
		button_46.setEnabled(false);
		panel_1.add(button_46, "cell 4 7");
		
		JButton button_45 = new JButton("   ");
		button_45.setEnabled(false);
		panel_1.add(button_45, "cell 5 7");
		
		JButton button_44 = new JButton("   ");
		button_44.setEnabled(false);
		panel_1.add(button_44, "cell 6 7");
		
		JButton button_43 = new JButton("   ");
		button_43.setEnabled(false);
		panel_1.add(button_43, "cell 7 7");
		
		JButton button_42 = new JButton("   ");
		button_42.setEnabled(false);
		panel_1.add(button_42, "cell 8 7");
		
		JButton button_41 = new JButton("   ");
		button_41.setEnabled(false);
		panel_1.add(button_41, "cell 9 7");
		
		JButton button_32 = new JButton("   ");
		button_32.setEnabled(false);
		panel_1.add(button_32, "cell 10 7");
		
		
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}
}