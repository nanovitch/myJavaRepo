package application.model;

import java.sql.SQLException;

import application.controller.CreateUserListener;
import application.view.CreateUserEvent;

public class Model implements CreateUserListener{
	
	private PersonDAO personDAO = new PersonDAO();

	@Override
	public void userCreated(CreateUserEvent event) {
		
		System.out.println("Model: Create user event received: " + event.getName() + " : " + event.getPassword());
		try {
			personDAO.addPerson(new Person(event.getName(), event.getPassword()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
