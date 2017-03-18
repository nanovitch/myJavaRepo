package application.controller;

import application.view.CreateUserEvent;

public interface CreateUserListener {
	
	public void userCreated(CreateUserEvent event);

}
