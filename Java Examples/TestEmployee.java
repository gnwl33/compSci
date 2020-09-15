 

public class TestEmployee {

	public static void main(String[] args) {
		
		HourlyEmployee x = new HourlyEmployee("Bob", 10.50, 40);
		SalariedEmployee y = new SalariedEmployee("Mary", 500);

		print(x);
 		print(y);
		
		// displayEarnings(x);
		// displayEarnings(y);

	}

	public static void print(Employee emp){
		if (emp instanceof HourlyEmployee)
			System.out.println(((HourlyEmployee)emp).earnings());
		else if (emp instanceof SalariedEmployee)
			System.out.println(((SalariedEmployee)emp).earnings());
	}


	// public static void print(HourlyEmployee emp){
	// 	System.out.println(emp);
	// }

	// public static void print(SalariedEmployee emp){
	// 	System.out.println(emp);
	// }


	public static void print(Object emp){
		System.out.println(emp);
	}

	// public static void displayEarnings(Object emp){
	// 	if (emp instanceof HourlyEmployee){
	// 		HourlyEmployee x = (HourlyEmployee) emp;
	// 		System.out.println(x.earnings());
	// 	}

	// 	else if (emp instanceof SalariedEmployee){
	// 		SalariedEmployee x = (SalariedEmployee) emp;
	// 		System.out.println(x.earnings());
	// 	}
	// }

}