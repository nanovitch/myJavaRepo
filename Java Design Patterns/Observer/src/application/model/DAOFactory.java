package application.model;

public class DAOFactory {
	
	public DAOFactory() {
		
	}
	
	// Generate here the needed DAO classes...
		
	public UseDBConnection getDAOObject(String daoType, String dBServerType, String dBServerName, String dBInstanceName, String dBHostName){
		
		if(daoType == null) return null;
	      		
		if(daoType.equalsIgnoreCase("PERSON")) return new PersonDAO(dBServerType, dBServerName, dBInstanceName, dBHostName);
		else if(daoType.equalsIgnoreCase("LOG")) return null; // return new LogDAO(dBServerType, dBServerName, dBInstanceName, dBHostName)
		else return null;
	 }

}
