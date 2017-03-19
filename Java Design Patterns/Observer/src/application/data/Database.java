package application.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import application.model.UseDBConnection;

public class Database  implements UseDBConnection {
	
	private static Database database = new Database();
	private Connection con;
	
	
	private Database() {
		
	}
	
	public Connection getConnection(String DBServer, String DBInstance) {
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
	@Override
	public Connection getConnection(String DBServerType, String DBServerName, String DBInstanceName, String DBHostName) throws SQLException {
		
		String microsoftDriverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String oracleDriverName = "";
		String mysqlDriverName = "";
		String drivername, url;
		
		if(DBServerType.equalsIgnoreCase("MICROSOFT")) {
			drivername = microsoftDriverName;
			//url = "jdbc:sqlserver://" + "WIN-EU8KSLB95VS\\MSSQLSERVER;databaseName=AdventureWorksLT2012;integratedSecurity=true";
			url = "jdbc:sqlserver://" + DBHostName + "\\" + DBServerName + ";databaseName=" + DBInstanceName + ";integratedSecurity=true";
		}
		else if(DBServerType.equalsIgnoreCase("ORACLE")) {
			drivername = oracleDriverName;
			url = "";
		}
		else if(DBServerType.equalsIgnoreCase("MYSQL")) {
			drivername = mysqlDriverName;
			url = "";
		}
		else {
			drivername = "";
			url = "";
		}
		
		try{
			Class.forName(drivername);
		}
		catch (ClassNotFoundException e) {
			throw new SQLException("SQL driver not found!");
		}
		
		System.out.println("Connected to database");
		
		// For Window authentication you can do something like:
		return DriverManager.getConnection(url);
		
		// For SQL Server authentication you can do something like:
		//String url = "jdbc:sqlserver://WIN-EU8KSLB95VS\\MSSQLSERVER;databaseName=AdventureWorksLT2012";
		//con = DriverManager.getConnection(url, "sa", "sa password");
		
		
	}
	/*
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

	*/
}
