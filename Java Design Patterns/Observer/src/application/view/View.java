package application.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import application.controller.CreateUserListener;
import application.data.Database;
import application.model.Model;

public class View extends JFrame implements ActionListener {
	
	private Model model;
	private ArrayList<CreateUserListener> createUserListeners;
	private Database db;
	
	private JButton createUser;
	private JTextField nameField;
	private JTextField passField;
	private JTextField repeatPassField;
	

	public View(Model model) {
		
		super("MVC Demo");
		
		this.model = model;
		createUserListeners = new ArrayList<CreateUserListener>();
		
		// singleton example: connect to a singleton database instance
		
		db = Database.getInstance();
		
		addWindowListener(new WindowAdapter() {
			
			public void windowOpened(WindowEvent e){
				try {
					db.connect();
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(View.this, "Unable to connect to database.", "Error", JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
				
			public void windowClosing(WindowEvent e){
				db.disconnect();
			}
		});
		
		// end singleton example
		
		nameField = new JTextField(10);
		passField = new JTextField(10);
		repeatPassField = new JTextField(10);
		createUser = new JButton("Create User");
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.anchor = GridBagConstraints.LAST_LINE_END;
		gc.gridx = 1;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(100, 0, 0, 10);
		gc.fill = GridBagConstraints.NONE;
		
		add(new JLabel("Name: "), gc);
		
		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.gridx = 2;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(100, 0, 0, 0);
		gc.fill = GridBagConstraints.NONE;
		
		add(nameField, gc);
		
		
		gc.anchor = GridBagConstraints.LAST_LINE_END;
		gc.gridx = 1;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 0, 0, 10);
		gc.fill = GridBagConstraints.NONE;
		
		add(new JLabel("Password: "), gc);
		
		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.gridx = 2;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.NONE;
		
		add(passField, gc);
		
		gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 1;
		gc.gridy = 3;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 0, 0, 10);
		gc.fill = GridBagConstraints.NONE;
		
		add(new JLabel("Repeat password: "), gc);
		
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 2;
		gc.gridy = 3;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 0, 0, 0);
		gc.fill = GridBagConstraints.NONE;
		
		add(repeatPassField, gc);
		
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 2;
		gc.gridy = 4;
		gc.weightx = 1;
		gc.weighty = 100;
		gc.fill = GridBagConstraints.NONE;
		
		add(createUser, gc);
		createUser.addActionListener(this);
				
		setSize(600, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String password = new String(passField.getText());
		String name = nameField.getText();
		
		fireCreateUserEvent(new CreateUserEvent(name, password));
		
	}
	
	public void fireCreateUserEvent(CreateUserEvent createUserEvent) {
		
		for (CreateUserListener createUserListener : createUserListeners) {
			if (createUserListener != null) createUserListener.userCreated(createUserEvent);
		}
		
	}

	public void addCreateUserListener(CreateUserListener createUserListener) {
		
		this.createUserListeners.add(createUserListener);
		
	}
	
	

}
