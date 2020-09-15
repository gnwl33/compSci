 

public class Circle  {
	private double radius = 1;

	public Circle(double radius){
		this.radius = radius;
	}

	public Circle(){
		
	}

	public double getArea(){
		return 3.14 * radius * radius;
	}

	public double getRadius(){
		return radius;
	}

	public void setRadius(double r){
		radius = r;
	}
}