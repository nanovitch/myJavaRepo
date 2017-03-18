package application.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	private static Database database = new Database();
	private Connection con;
	
	
	private Database() {
		
	}
	
	public Connection getConnection() {
		return con;
	}

	public static Database getInstance() {
		
		return database;
			
	}
	
	//not thread safe singleton version
	/*
	private static Database database = null;
	
	private Database() {
		
	}
	
	public static Database getInstance() {
		
		if (database == null) database = new Database();
		return database;
			
	}
	*/
	
	/* Pour configurer des connexions SQL Server, il faut:
	 * 
	  1. Pointer sur sqljdbc4.jar
	  Suffisant pour les connexions du type  SQL Server authentication.
	  [Dans Eclipse, ajouter un jar exterieur en faisant:
	  Project/Properties/Java Build Path/Libraries/Add External JARs].
	  
	  2. Ajouter  le chemin vers sqljdbc_auth.dll comme argument VM. 
	  Necessaire pour les connexions du type  Window authentication
	  Par exemple, si sqljdbc_auth.dll est dans C:\Windows\System32:
	   Dans eclipse: dans [Run] / [run configurations] / [Arguments] / [VM Arguments]: ajouter
	   -Djava.library.path="C:\Windows\System32"
	 */
	
	public void connect() throws Exception {
		if (con != null)  return;
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}
		catch (ClassNotFoundException e) {
			throw new Exception("SQL driver not found!");
		}
		
		// For Window authentication you can do something like:
		String url = "jdbc:sqlserver://WIN-EU8KSLB95VS\\MSSQLSERVER;databaseName=AdventureWorksLT2012;integratedSecurity=true";
		con = DriverManager.getConnection(url);
		
		// For SQL Server authentication you can do something like:
		//String url = "jdbc:sqlserver://WIN-EU8KSLB95VS\\MSSQLSERVER;databaseName=AdventureWorksLT2012";
		//con = DriverManager.getConnection(url, "sa", "sa password");
		
		System.out.println("Connected to database");
	}
	
	public void disconnect() {
		if (con != null) {
			try {
				con.close();
			}
			catch (SQLException e) {
				System.out.println("Cannot close connection to database");
			}
		}
		con = null;
		System.out.println("Disconnected from database");
	}
	
	

}
