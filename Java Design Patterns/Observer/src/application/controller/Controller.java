package application.controller;

import application.model.Model;
import application.view.CreateUserEvent;
import application.view.View;

public class Controller implements CreateUserListener{
	
	private Model model;
	private View view;
	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void userCreated(CreateUserEvent event) {
		
		model.userCreated(event);
		System.out.println("Controller: Create user event sended to Model: " + event.getName() + " : " + event.getPassword());
		
	}
	
	

}
