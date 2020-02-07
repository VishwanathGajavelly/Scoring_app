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
import java.awt.Component;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class InsertDataForm extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	
	public JTextField tb_firstName;
	public JTextField tb_lastName;
	public JTextField tb_state;
	public JTextField tb_age;
	public JTextField tb_chestNumber;
	
	public JTextField tb_firstName_scores;
	public JTextField tb_lastName_scores;
	public JTextField tb_state_scores;
	public JTextField tb_age_scores;
	public JTextField tb_chestNumber_scores;
	
	
	public DbConnection dbConnection;
	
    public InsertDataForm() {
	 	
    	JPanel participantInsertionPanel = getParticipantDetailsTabbedPane();
    	JPanel scoresInsertionPanel = getParticipantDetailsTabbedPane2();
		
	 	JTabbedPane finalTabbedPane = new JTabbedPane();
	 	
	 	finalTabbedPane.addTab("Insert Participant Details", participantInsertionPanel);
		finalTabbedPane.addTab("Insert Scores", scoresInsertionPanel);
		
		getContentPane().add(finalTabbedPane);
	
    }

	private JPanel getParticipantDetailsTabbedPane() {
		
		JLabel label_firstName = new JLabel("FIRST NAME");
		tb_firstName = new JTextField();
		tb_firstName.setToolTipText("FIRST NAME");
		tb_firstName.setText("firstName");
		tb_firstName.setColumns(10);
		JPanel firstNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 40, 10));
		firstNamePanel.add(Box.createHorizontalStrut(10));
		firstNamePanel.add(label_firstName);
		firstNamePanel.add(Box.createHorizontalStrut(40));
		firstNamePanel.add(tb_firstName);

		JLabel label_lastName= new JLabel("LAST NAME");
		tb_lastName = new JTextField();
		tb_lastName.setColumns(10);
		JPanel lastNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 40, 10));
		lastNamePanel.add(Box.createHorizontalStrut(10));
		lastNamePanel.add(label_lastName);
		lastNamePanel.add(Box.createHorizontalStrut(45));
		lastNamePanel.add(tb_lastName);

		JLabel label_state= new JLabel("STATE");
		tb_state = new JTextField();
		tb_state.setColumns(10);
		JPanel statePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 40, 10));
		statePanel.add(Box.createHorizontalStrut(10));
		statePanel.add(label_state);
		Component horizontalStrut = Box.createHorizontalStrut(73);
		statePanel.add(horizontalStrut);
		statePanel.add(tb_state);
		
		JLabel label_age= new JLabel("AGE");
		tb_age = new JTextField();
		tb_age.setColumns(10);
		JPanel agePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 40, 10));
		agePanel .add(Box.createHorizontalStrut(10));
		agePanel .add(label_age);
		agePanel .add(Box.createHorizontalStrut(88));
		agePanel .add(tb_age);
		
		JLabel label_chestNumber= new JLabel("CHEST NUMBER");
		tb_chestNumber = new JTextField();
		tb_chestNumber.setColumns(10);
		JPanel chestNumberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 40, 10));
		chestNumberPanel.add(Box.createHorizontalStrut(10));
		chestNumberPanel.add(label_chestNumber);
		chestNumberPanel.add(Box.createHorizontalStrut(20));
		chestNumberPanel.add(tb_chestNumber);

		JButton btn_addParticipant = new JButton("Insert Participant");
		btn_addParticipant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					if(tb_firstName.getText().isEmpty()||(tb_lastName.getText().isEmpty())||(tb_chestNumber.getText().isEmpty())||
							(tb_age.getText().isEmpty())||(tb_state.getText().isEmpty())) {
	                    JOptionPane.showMessageDialog(null, "Data Missing");
					}else {       
						String firstName =tb_firstName.getText();
	    				String lastName = tb_lastName.getText();
	    				int chestNumber = Integer.parseInt(tb_chestNumber.getText());
	    				int age = Integer.parseInt(tb_age.getText());
	    				String state = tb_state.getText();
	    				
	    				Participant newEntry = new Participant(firstName, lastName, chestNumber, age, state);
	    				dbConnection = new DbConnection();
	    				Boolean isInsertSuccess = dbConnection.addParticipant(newEntry);
	    				
	    				if(isInsertSuccess) {
		    				JOptionPane.showMessageDialog(null, "Data Submitted");	
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
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttonPanel.add(btn_addParticipant);
		
		
		JPanel generatorPanel = new JPanel();
		generatorPanel.setLayout(new BoxLayout(generatorPanel, BoxLayout.Y_AXIS));
		generatorPanel.add(Box.createVerticalStrut(5));
		generatorPanel.add(firstNamePanel);
		generatorPanel.add(Box.createVerticalStrut(5));
		generatorPanel.add(lastNamePanel);
		generatorPanel.add(Box.createVerticalStrut(5));
		generatorPanel.add(statePanel);
		
		generatorPanel.add(Box.createVerticalStrut(5));
		generatorPanel.add(agePanel);
		generatorPanel.add(Box.createVerticalStrut(5));
		generatorPanel.add(chestNumberPanel);
	
		generatorPanel.add(Box.createVerticalStrut(5));
		generatorPanel.add(buttonPanel);
	
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
