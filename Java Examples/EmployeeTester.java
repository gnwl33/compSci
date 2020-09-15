public class EmployeeTester 
{
	public static void main(String[] args) {
		Employee emp1 = new HourlyEmployee("John Doe", 10.50, 40);
//		HourlyEmployee emp2 = new HourlyEmployee("John Doe");

		emp1.setName("Jane Doe");
		System.out.println(emp1.getName());

		System.out.println(emp1);
		// System.out.println(emp2);
	}
}
