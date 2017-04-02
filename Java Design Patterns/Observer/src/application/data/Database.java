package application.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import application.model.UseDBConnection;

/* Pour configurer des connexions SQL Server, il faut:
  
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
/*
  For Window authentication you can do something like:
  String url = "jdbc:sqlserver://WIN-EU8KSLB95VS\\MSSQLSERVER;databaseName=AdventureWorksLT2012";
  DriverManager.getConnection(url);
  
  For Microsoft SQL Server authentication you can do something like:
  String url = "jdbc:sqlserver://WIN-EU8KSLB95VS\\MSSQLSERVER;databaseName=AdventureWorksLT2012";
  DriverManager.getConnection(url, "sa", "sa password");
*/

public class Database  implements UseDBConnection {
	
	private String serverType;
	private String serverName;
	private String instanceName;
	private String hostName;
	
	private Connection con;
	
	private String microsoftDriverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	//TODO: Add Oracle and MySQL driver name
	private String oracleDriverName = "";
	private String mysqlDriverName = "";
		
	
	public Database(String serverType, String serverName, String instanceName, String hostName) throws SQLException {
		this.serverType = serverType;
		this.serverName = serverName;
		this.instanceName = instanceName;
		this.hostName = hostName;
		
		String drivername, url;
		
		if(serverType.equalsIgnoreCase("MICROSOFT")) {
			drivername = microsoftDriverName;
			url = "jdbc:sqlserver://" + hostName + "\\" + serverName + ";databaseName=" + instanceName + ";integratedSecurity=true";
		}
		else if(serverType.equalsIgnoreCase("ORACLE")) {
			drivername = oracleDriverName;
			url = "";
		}
		else if(serverType.equalsIgnoreCase("MYSQL")) {
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
		
		con = DriverManager.getConnection(url);
	}

	
	@Override
	public Connection getConnection() {
		
		return con;
		
		/*
		String drivername, url;
		
		if(serverType.equalsIgnoreCase("MICROSOFT")) {
			drivername = microsoftDriverName;
			url = "jdbc:sqlserver://" + hostName + "\\" + serverName + ";databaseName=" + instanceName + ";integratedSecurity=true";
		}
		else if(serverType.equalsIgnoreCase("ORACLE")) {
			drivername = oracleDriverName;
			url = "";
		}
		else if(serverType.equalsIgnoreCase("MYSQL")) {
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
		
		return DriverManager.getConnection(url);
		*/
		
		
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
	public String getServerType() {
		return serverType;
	}


	public void setServerType(String serverType) {
		this.serverType = serverType;
	}


	public String getServerName() {
		return serverName;
	}


	public void setServerName(String serverName) {
		this.serverName = serverName;
	}


	public String getInstanceName() {
		return instanceName;
	}


	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}


	public String getHostName() {
		return hostName;
	}


	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

}
