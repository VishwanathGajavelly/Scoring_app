package mainApplication;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;

import view.InsertDataForm;

public class MainApp {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InsertDataForm window = new InsertDataForm();
                    Dimension maxDim = new Dimension(650, 500);
                    window.setTitle("NATIONAL LEVEL COMPETITIONS");
            		window.setSize(maxDim);
            		window.setResizable(false);
            		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            		window.setVisible(true);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
	}
}
