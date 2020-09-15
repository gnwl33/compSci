package examples;

public class Employee
{
	private String name; // name of the employee

	public Employee (String n) { name = n; }
	public Employee () { name = "Unknown"; }

	public String getName() { return name; }

	public void setName(String name){
		this.name = name;
	}


	// public String toString() { return name;}
}

