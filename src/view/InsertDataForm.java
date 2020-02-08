package view;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import model.DbConnection;
import participant.Participant;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class InsertDataForm extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	
	public JTextField tb_firstName;
	public JTextField tb_lastName;
	public JTextField tb_state;
	public JTextField tb_age;
	
	public JTextField tb_firstName_scores;
	public JTextField tb_lastName_scores;
	public JTextField tb_state_scores;
	public JTextField tb_age_scores;
	public JTextField tb_chestNumber_scores;
	
	
	public DbConnection dbConnection;
	
	//repaint();
	
    public InsertDataForm() throws ClassNotFoundException, SQLException {
	 	
    	JPanel participantInsertionPanel = getParticipantDetailsTabbedPane();
    	JPanel scoresInsertionPanel = getParticipantDetailsTabbedPane2();
		
	 	JTabbedPane finalTabbedPane = new JTabbedPane();
	 	
	 	finalTabbedPane.addTab("Insert Participant Details", participantInsertionPanel);
		finalTabbedPane.addTab("Insert Scores", scoresInsertionPanel);
		
		getContentPane().add(finalTabbedPane);
	
    }

	private JPanel getParticipantDetailsTabbedPane() throws ClassNotFoundException, SQLException {
		
		dbConnection = new DbConnection();
		
		JPanel generatorPanel = new JPanel();
		generatorPanel.setBackground(new Color(238, 232, 170));
		
		JLabel label_firstName = new JLabel("FIRST NAME");
		JLabel label_lastName= new JLabel("LAST NAME");
		JLabel label_chestNumber= new JLabel("CHEST NUMBER");
		JLabel label_state= new JLabel("STATE");
		JLabel label_age= new JLabel("AGE");
		JLabel chestNumber_label= new JLabel(dbConnection.getNextChestNumber().toString());
		chestNumber_label.setForeground(new Color(128, 0, 0));
		
		JButton btn_addParticipant = new JButton("Insert Participant");
		
		tb_firstName = new JTextField();
		tb_lastName = new JTextField();
		tb_state = new JTextField();
		tb_age = new JTextField();
		
		chestNumber_label.setFont(new Font("Cambria", Font.PLAIN, 50));
		label_firstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_lastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_state.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_age.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_chestNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_addParticipant.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		label_chestNumber.setBounds(150, 50, 250, 20);
		label_firstName.setBounds(150, 100, 150, 20);
		label_lastName.setBounds(150, 150, 150, 20);
		label_state.setBounds(150, 200, 150, 20);
		label_age.setBounds(150, 250, 150, 20);
		btn_addParticipant.setBounds(200, 400, 200, 50);
		
		chestNumber_label.setBounds(300, 30, 150, 50);
		tb_firstName.setBounds(300, 100, 150, 30);
		tb_lastName.setBounds(300, 150, 150,30);
		tb_state.setBounds(300, 200, 150, 30);
		tb_age.setBounds(300, 250, 150,30);
		
		tb_firstName.setToolTipText("FIRST NAME");
		tb_lastName.setToolTipText("LAST NAME");
		tb_state.setToolTipText("STATE");
		tb_age.setToolTipText("AGE");
		
		tb_firstName.setColumns(10);
		tb_lastName.setColumns(10);
		tb_state.setColumns(10);
		tb_age.setColumns(10);
		
		
		btn_addParticipant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					if(tb_firstName.getText().isEmpty()||(tb_lastName.getText().isEmpty())||
							(tb_age.getText().isEmpty())||(tb_state.getText().isEmpty())) {
	                    JOptionPane.showMessageDialog(null, "Data Missing");
					}else {       
						String firstName =tb_firstName.getText();
	    				String lastName = tb_lastName.getText();
	    				int chestNumber = Integer.parseInt(chestNumber_label.getText());
	    				int age = Integer.parseInt(tb_age.getText());
	    				String state = tb_state.getText();
	    				
	    				Participant newEntry = new Participant(firstName, lastName, chestNumber, age, state);
	    				Boolean isInsertSuccess = dbConnection.addParticipant(newEntry);
	    				
	    				if(isInsertSuccess) {
		    				JOptionPane.showMessageDialog(null, "Data Submitted");	
		    				chestNumber_label.setText(dbConnection.getNextChestNumber().toString());
		    				tb_firstName.setText(null);
		    				tb_lastName.setText(null);
		    				tb_age.setText(null);
		    				tb_state.setText(null);
	    				}else {
	    					 JOptionPane.showMessageDialog(null, "INSERTION FAILED");
	    				}

					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Invalid Data- check Age");
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Data Entry Failed");
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Data Entry Failed");
					e.printStackTrace();
				}
            	
			}
		});

		
		
		
		generatorPanel.setLayout(null);
		generatorPanel.add(label_firstName);
		generatorPanel.add(tb_firstName);
		generatorPanel.add(label_lastName);
		generatorPanel.add(tb_lastName);
		generatorPanel.add(label_state);
		generatorPanel.add(tb_state);
		generatorPanel.add(label_age);
		generatorPanel.add(tb_age);
		generatorPanel.add(label_chestNumber);
		generatorPanel.add(btn_addParticipant);
		generatorPanel.add(chestNumber_label);
		
		
		return generatorPanel;
    }
	
	private JPanel getParticipantDetailsTabbedPane2() {
		JLabel label_firstName_scores = new JLabel("FIRST NAME");
		JLabel label_lastName= new JLabel("LAST NAME");
		
		tb_firstName_scores = new JTextField();
		tb_firstName_scores.setColumns(10);
		tb_lastName_scores = new JTextField();
		tb_lastName_scores.setColumns(10);
		
		JButton btn_addParticipant_scores = new JButton("Insert Participant");
		
		JPanel buttonPanel_scores = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttonPanel_scores.add(btn_addParticipant_scores);
		
		JPanel firstNamePanel_scores = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		firstNamePanel_scores.add(Box.createHorizontalStrut(10));
		firstNamePanel_scores.add(label_firstName_scores);
		firstNamePanel_scores.add(Box.createHorizontalStrut(40));
		firstNamePanel_scores.add(tb_firstName_scores);
		
		JPanel lastNamePanel_scores = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		lastNamePanel_scores.add(Box.createHorizontalStrut(10));
		lastNamePanel_scores.add(label_lastName);
		lastNamePanel_scores.add(Box.createHorizontalStrut(50));
		lastNamePanel_scores.add(tb_lastName_scores);
		
		JPanel generatorPanel_scores = new JPanel();
		
		generatorPanel_scores.setLayout(new BoxLayout(generatorPanel_scores, BoxLayout.Y_AXIS));
		generatorPanel_scores.setBackground(Color.WHITE);
		generatorPanel_scores.add(Box.createVerticalStrut(20));
		generatorPanel_scores.add(firstNamePanel_scores);
		generatorPanel_scores.add(Box.createVerticalStrut(10));
		generatorPanel_scores.add(lastNamePanel_scores);
		generatorPanel_scores.add(Box.createVerticalStrut(10));
		generatorPanel_scores.add(buttonPanel_scores);
		generatorPanel_scores.add(Box.createVerticalStrut(200));
		
		return generatorPanel_scores;
	
	}
	
}
