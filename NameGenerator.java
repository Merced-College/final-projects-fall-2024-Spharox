//Pablo Mendoza 
//CPSC-39
//12/6/2024

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;

public class NameGenerator{
	private ArrayList<String> firstNames;
	private ArrayList<String> lastNames;
	

	public NameGenerator(ArrayList<String> firstNames, ArrayList<String> lastNames){
		this.firstNames = firstNames;
		this.lastNames = lastNames;
	}

	// Generates an arraylist of employees with random names, salary, proficiency, and happiness
	public ArrayList<Employee> getEmployees(int amount){
		ArrayList<Employee> employees = new ArrayList<>();

		for(int i = 0; i < amount; i++){
			int[] rng = getRandomEmployeeNumbers(firstNames.size());
			employees.add(new Employee(firstNames.get(rng[0]) + " " + lastNames.get(rng[1]), 400, rng[2], 70));
		}
		return employees;
	}

	// Generates an arraylist of businesses with random names, healt, and competitveness
	public ArrayList<Business> getBusinesses(int amount){
		ArrayList<Business> businesses = new ArrayList<>();
		String[] businessNames= {"Works", "Industries", "Enterprises", "LLC", "Corp.", "Inc.", "Co.", "Dynamics"};
		for(int i = 0; i < amount; i++){
			int[] rng = getRandomBusinessNumbers(firstNames.size());
			businesses.add(new Business(lastNames.get(rng[0]) + " " + businessNames[rng[1]], 50, rng[2]));
		}
		return businesses;
	}

	// Generates an order with random name, request, and payment
	public Order getOrder(){
		int[] rng = getRandomOrderNumbers(firstNames.size());
		Order order = new Order(lastNames.get(rng[0]) + "'s order" , (750 * rng[1]), (4000 * rng[1]) + (1000 * (rng[1] - 1)));
		return order;
	}

	// Generates random numbers for the employee class
	private static int[] getRandomEmployeeNumbers(int size) {
		int[] rng = new int[3];
		rng[0] =  ThreadLocalRandom.current().nextInt(0, size);
        rng[1] =  ThreadLocalRandom.current().nextInt(0, size); 
    	rng[2] =  ThreadLocalRandom.current().nextInt(300, 500);
    	return rng;
    }

    // Generates random numbers for the business class
    private static int[] getRandomBusinessNumbers(int size) {
		int[] rng = new int[3];
		rng[0] =  ThreadLocalRandom.current().nextInt(0, size);
    	rng[1] =  ThreadLocalRandom.current().nextInt(0, 7);
    	rng[2] =  ThreadLocalRandom.current().nextInt(20, 30);
    	return rng;
    }

    // Generates random numbers for the order class
    private static int[] getRandomOrderNumbers(int size) {
		int[] rng = new int[2];
		rng[0] =  ThreadLocalRandom.current().nextInt(0, size);
    	rng[1] =  ThreadLocalRandom.current().nextInt(1, 4);
    	return rng;
    }

}