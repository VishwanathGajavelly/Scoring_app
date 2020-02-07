package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteConnection;

public class DbConnection {
	
	private static Connection conn;
	private static boolean hasData = false;
	
	public ResultSet displayScores() throws SQLException, ClassNotFoundException {
		
		if(conn == null) {
			getConnection();
		}
		
		Statement state = conn.createStatement();
		ResultSet res = state.executeQuery("Select * from participants");
		return res;
	}

	private void getConnection() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:SQLiteScoringApp.db");
		initialise();
		
	}

	private void initialise() throws SQLException {
		// TODO Auto-generated method stub
		if(!hasData) {
			hasData = true;
			
			Statement state = conn.createStatement();
			
			ResultSet res = state.executeQuery("select * from participants");
			
			/* Create Table
			 * Statement state2 = conn.createStatement();
			 * state2.execute("create table participants(chestNumber integer," +
			 * "firstName varchar(60), " + "lastName varchar(60)," + "age integer," +
			 * "state varchar(60), " + "primary key(chestNumber));");
			 */
			
			if( !res.next()) {
				System.out.println("Building the participant table");
				
				PreparedStatement prep = conn.prepareStatement("insert into participants values(?,?,?,?,?);");
				prep.setInt(1, 500);
				prep.setString(2, "John");
				prep.setString(3, "Smith");
				prep.setInt(4, 30);
				prep.setString(5, "Telangana");
				prep.execute();
				
				
				PreparedStatement prep2 = conn.prepareStatement("insert into participants values(?,?,?,?,?);");
				
				prep2.setInt(1, 600);
				prep2.setString(2, "Max");
				prep2.setString(3, "Glenn");
				prep2.setInt(4, 40);
				prep2.setString(5, "Andhra");
				prep2.execute();
				
			}
		}
		
	}
	
	public void addParticipant(String firstName, String lastName, int chestNumber, int age, String state) throws SQLException, ClassNotFoundException {
		
		if(conn == null) {
			getConnection();
		}
		
		PreparedStatement prep = conn.prepareStatement("insert into participants values(?,?,?,?,?);");
		prep.setString(2, "John");
		prep.setString(3, "Smith");
		prep.setInt(4, 30);
		prep.setString(5, "Telangana");
		prep.execute();
	}
	
	

}
