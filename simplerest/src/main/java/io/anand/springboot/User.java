package io.anand.springboot;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	private String	name;
	private String	grade;
	@Id
	private long  	id;
	private long	graduated;

	public User () {
		
	}
	
	public User(String name, String grade, long id, long graduated) {
		super();
		this.name = name;
		this.grade = grade;
		this.id = id;
		this.graduated = graduated;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getGraduated() {
		return graduated;
	}
	public void setGraduated(long graduated) {
		this.graduated = graduated;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
}
