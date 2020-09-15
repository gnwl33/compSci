package examples;

public class SalariedEmployee extends Employee 
{
	private double weeklySalary;
	
	public SalariedEmployee(String n, double salary) {
		super(n);
		weeklySalary = salary;
	}
	
	public double earnings() {
		return weeklySalary;
	}

	@Override
	public String toString() {
		return getName() + ", " + earnings();
 	}

}


