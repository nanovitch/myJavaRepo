package application.model;

import application.controller.CreateUserListener;
import application.view.CreateUserEvent;

public class Model implements CreateUserListener{
	
	private DAOFactory daoFactory = new DAOFactory();
	
	private String dbServerType = "MICROSOFT";
	private String dbServerName = "MSSQLSERVER";
	private String dbInstanceName = "AdventureWorksLT2012";
	private String dbHostName = "WIN-EU8KSLB95VS";

	@Override
	public void userCreated(CreateUserEvent event) {
		
		System.out.println("Model: Create user event received: " + event.getName() + " : " + event.getPassword());
		try {
			PersonDAO personDAO = ((PersonDAO) daoFactory.getDAOObject("PERSON", dbServerType, dbServerName, dbInstanceName, dbHostName));
			personDAO.addPerson(new Person(event.getName(), event.getPassword()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
