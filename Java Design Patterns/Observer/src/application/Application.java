package application;

import javax.swing.SwingUtilities;

import application.model.Model;
import application.view.View;
import application.controller.Controller;

public class Application {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				runApp();
			}
		});

	}
	
	public static void runApp() {
		Model model = new Model();
		View view = new View(model);
		Controller controller = new Controller(model, view);
		
		view.addCreateUserListener(controller);
	}

}
