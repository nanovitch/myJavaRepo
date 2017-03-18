package application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.data.Database;

/*
 * SQL code container
 */

public class PersonDAO {
	
	public int addPerson(Person person) throws SQLException {
		
		Connection con = Database.getInstance().getConnection();
		
		PreparedStatement p = con.prepareStatement("INSERT INTO AdventureWorksLT2012.dbo.People (name, password) VALUES (?, ?)");
		
		p.setString(1, person.getName());
		p.setString(2, person.getPassword());
		int inserted = p.executeUpdate();
		
		p.close();
		
		return inserted;
	}
	
	public int updatePerson(Person person) throws SQLException {
		
		Connection con = Database.getInstance().getConnection();
		
		PreparedStatement p = con.prepareStatement("UPDATE AdventureWorksLT2012.dbo.People SET name=?, password=? WHERE id=?");
		
		p.setString(1, person.getName());
		p.setString(2, person.getPassword());
		p.setInt(3, person.getId());
		int updated = p.executeUpdate();
		
		p.close();
		
		return updated;
		
	}
	
	public int deletePerson(int id) throws SQLException {
		
		Connection con = Database.getInstance().getConnection();
		
		PreparedStatement p = con.prepareStatement("DELETE FROM AdventureWorksLT2012.dbo.People WHERE id=?");
		
		p.setInt(1, id);
		int deleted = p.executeUpdate();
		
		p.close();
		
		return deleted;
		
	}
	
	public int deleteAll() throws SQLException {
		
		Connection con = Database.getInstance().getConnection();
		
		PreparedStatement p = con.prepareStatement("DELETE FROM AdventureWorksLT2012.dbo.People");
		
		int deleted = p.executeUpdate();
		
		p.close();
		
		return deleted;
		
	}
	
	public Person getPerson(int id) throws SQLException {
		
		Connection con = Database.getInstance().getConnection();
		
		String sql = "SELECT name, password FROM AdventureWorksLT2012.dbo.People WHERE id=?";
		
		PreparedStatement p = con.prepareStatement(sql);
		
		p.setInt(1, id);
		
		ResultSet result= p.executeQuery();
		
		Person person = null;
		
		if (result.next()) {
			String name = result.getString("name");
			String password = result.getString("password");
			person = new Person(id, name, password);
		}
				
		p.close();
		result.close();
		
		return person;
	}
	
	public List<Person> getPeople() throws SQLException {
		
		List<Person> people = new ArrayList<Person>();
		
		Connection con = Database.getInstance().getConnection();
		
		String sql = "SELECT id, name, password FROM AdventureWorksLT2012.dbo.People ORDER BY id";
		
		Statement selectStatement = con.createStatement();
		
		ResultSet results= selectStatement.executeQuery(sql);
		
		while (results.next()) {
			int id = results.getInt("id");
			String name = results.getString("name");
			String password = results.getString("password");
			people.add(new Person(id, name, password));
		}
		
		results.close();
		selectStatement.close();
		
		return people;
	}

}
