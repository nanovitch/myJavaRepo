package application.model;

public class DAOFactory {
	
	public DAOFactory() {
		
	}
	
	public PersonDAO getPersonDAO() {
		return new PersonDAO();
	}
	
	// Add here other needed DAO classes...
	// ...for example, LogDAO for a Log table

}
