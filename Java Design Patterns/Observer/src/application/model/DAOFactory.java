package application.model;

public class DAOFactory {
	
	public DAOFactory() {
		
	}
	
	public PersonDAO getPersonDAO() {
		return new PersonDAO();
	}
	
	// Add here other needed DAO classes...
	// ...for example, LogDAO for a Log table
	
	public UseDBConnection getDAOObject(String daoType){
	      if(daoType == null) return null;
	      		
	      if(daoType.equalsIgnoreCase("PERSON")){
	         return new PersonDAO();
	         
	      } else if(daoType.equalsIgnoreCase("LOG")){
	         return null; // return new LogDAO
	         
	      } 
	      else return null;
	   }

}
