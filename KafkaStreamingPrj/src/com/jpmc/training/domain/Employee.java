package com.jpmc.training.domain;

public class Employee {

	private int id;
	private String name;
	private String designation;
	private double salary;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
    public Employee(int id, String name, String designation) {
        super();
        this.id = id;
        this.name = name;
        this.designation = designation;
    }
    public Employee() {
        super();
        // TODO Auto-generated constructor stub
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
	public Employee(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
    
    
}
