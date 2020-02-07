package mainApplication;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.DbConnection;

public class MainApp {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		DbConnection dbConnection = new DbConnection();
		ResultSet rs;
		
		rs= dbConnection.displayScores();
		
		while(rs.next()) {
			System.out.println(rs.getString("firstName") + "  " + rs.getString("lastName"));
		}
		
	}

}
