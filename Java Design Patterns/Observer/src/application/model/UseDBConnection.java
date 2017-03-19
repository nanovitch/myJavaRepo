package application.model;

import java.sql.Connection;
import java.sql.SQLException;

public interface UseDBConnection {
	
	public Connection getConnection(String DBServerType, String DBServerName, String DBInstanceName, String DBHostName) throws SQLException;

}
