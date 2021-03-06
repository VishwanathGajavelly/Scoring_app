package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import participant.Participant;

public class DbConnection {

	private static Connection conn;
	private static boolean hasData = false;

	public  ArrayList<Participant> displayScores(int chestNumber) throws SQLException, ClassNotFoundException {

		if (conn == null) {
			getConnection();
		}

		Statement state = conn.createStatement();
		ResultSet res = state.executeQuery("Select * from participants where chestNumber = " + chestNumber);
		
		ArrayList<Participant> list = populateListOfParticipants(res);
		return list;
	}

	private ArrayList<Participant> populateListOfParticipants(ResultSet rs ) throws SQLException {
		ArrayList<Participant> list = new ArrayList<Participant>();
		while(rs.next()) {
			
			Participant par = new Participant(rs.getString("firstName"), rs.getString("lastName"), rs.getInt("age"), rs.getInt("chestNumber"), rs.getString("state"));
			list.add(par);
		}
		return list;
		
	}

	private void getConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:SQLiteScoringApp.db");
		initialise();

	}

	private void initialise() throws SQLException {
		
		if (!hasData) {
			hasData = true;

			Statement state = conn.createStatement();
			ResultSet res = state.executeQuery("select * from participants");

			if (!res.next()) {
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

	public  boolean addParticipant(Participant participant) throws SQLException, ClassNotFoundException {

		if (conn == null) {
			getConnection();
		}

		PreparedStatement prep = conn.prepareStatement("insert into participants values(?,?,?,?,?);");
		prep.setInt(1, participant.getChestNumber());
		prep.setString(2, participant.getFirstName());
		prep.setString(3, participant.getLastName());
		prep.setInt(4, participant.getAge());
		prep.setString(5, participant.getState());
		
		if(prep.executeUpdate() >0)
			return true;
		return false; 
		
		
	}
	
	public  Integer getNextChestNumber() throws SQLException, ClassNotFoundException {

		if (conn == null) {
			getConnection();
		}

		Statement state = conn.createStatement();
		ResultSet res = state.executeQuery("select max(chestNumber) from participants");
		Integer chestNumber = res.getInt("max(chestNumber)");
		return (chestNumber+1); 
		
		
	}
	
	public void createTable() throws SQLException, ClassNotFoundException {

		if (conn == null) {
			getConnection();
		}

		System.out.println("TRYING TO CREATE TABLE");
		Statement state = conn.createStatement();
		state.execute("create table [IF NOT EXISTS] participants(chestNumber integer," +
						"firstName varchar(60), " + "lastName varchar(60)," + "age integer," +
						"state varchar(60), " + "primary key(chestNumber));");
		
	}

}
