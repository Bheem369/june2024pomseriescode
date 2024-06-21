public class selenium {
	
	String name;
	int age;
	String city;
	double salary;
	
	public static void main(String[] args)
	{
		Employee e = new Employee();
		e.name = "shankar";
		e.age = 32;
		e.city = "Hyderabad";
		e.salary = 15.63;
		
		System.out.println("Name:"+e.name + " "+"Age:"+e.age+" "+"city:"+e.city +" "+"Salary:"+e.salary );
	}

}
