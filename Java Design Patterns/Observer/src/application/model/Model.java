package application.model;

import application.controller.CreateUserListener;
import application.view.CreateUserEvent;

public class Model implements CreateUserListener{
	
	private DAOFactory daoFactory = new DAOFactory();
	//private PersonDAO personDAO = new PersonDAO();

	@Override
	public void userCreated(CreateUserEvent event) {
		
		System.out.println("Model: Create user event received: " + event.getName() + " : " + event.getPassword());
		try {
			((PersonDAO) daoFactory.getDAOObject("PERSON")).addPerson(new Person(event.getName(), event.getPassword()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
