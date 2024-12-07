import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.LinkedList;
import java.util.Queue;
import java.io.*;

public class Main{

	static int money = 50000, market = 30, output = 0, competitveness = 30, week = 1;
	static Queue<Order> orders = new LinkedList<>();
	static ArrayList<Employee> employeeList = new ArrayList<>();
	static ArrayList<Business> businessList = new ArrayList<>();
	static Random random = new Random();

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		String filePath = "names.txt";
        NameProcessor nameProcessor = new NameProcessor(filePath);
        ArrayList<String> firstNames = nameProcessor.getFirstNames();
        ArrayList<String> lastNames = nameProcessor.getLastNames();

        NameGenerator nameGenerator = new NameGenerator(firstNames, lastNames);
        employeeList = nameGenerator.getEmployees(30);
        businessList = nameGenerator.getBusinesses(15);

        System.out.println("Welcome to business simulator game! Please enter your enterprises name - ");
		String userName = scanner.nextLine();
		orderChecker();

		while(money >= 0){

			System.out.println("\nWeek " + week + ": \n" + userName + " -\nMoney = " + money + "\nCompetitveness = " + competitveness + 
			"\nEnter 1 to pass week, 2 to check market, 3 to check employess, 4 to check orders, 5 for upgrades, 7 to exit game\n");
			String playerResponse = scanner.nextLine(); 
			switch (playerResponse) {
			case "1":
				updateVariables();
				weekPass(nameGenerator);
				orderChecker();
				break;
			case "2":
				for(Business business : businessList) {
    				System.out.println(business);
				}
				break;
			case "3":
				for(Employee employee : employeeList) {
    				System.out.println(employee);
				}
				break;
			case "4":
				for(Order order : orders) {
    				System.out.println(order);
				}
				break;
			case "5":

				System.out.println("\nWhich upgrade do you want? \n1 - Five More Employees (20000)\n2 - Proficiency Training (40000)\n Advertising Campgain (50000)");
				String upgradeResponse = scanner.nextLine(); 
				switch (upgradeResponse) {
				case "1":
					hireEmployees(nameGenerator);
					break;
				case "2":
					proficiencyTraining();
					break;
				case "3":
					advertisingCampgain();
					break;
				}
				break;

			case "7":
				System.out.println("Congratulations! You did not go broke!");
				return;
			default: 
				System.out.println("Incorrect input deteced, Enter 1 to pass week, 2 to check market, 3 to check employess, 7 to exit game");
			}
		}

		System.out.println("Game Over, you went broke!");
	}

	public static void updateVariables(){
		int proficiency = 0;
		for(int i = 0; i < employeeList.size(); i++){
			Employee employee = employeeList.get(i);
			int employeeProficiency = employee.getProficiency();
			proficiency += employeeProficiency;
			if(employeeProficiency < 700){
				employee.setProficiency(employeeProficiency + 1);
			}
		}

		for(int i = 0; i < businessList.size(); i++){
			Business business = businessList.get(i);
			int businessCompetitveness = business.getCompetitveness();
			int negOrPos = random.nextInt(2);
			int amount = random.nextInt(3);
			if(negOrPos == 1 && businessCompetitveness < 50){
				business.setCompetitveness(businessCompetitveness + amount);
			}else if(negOrPos == 0 && businessCompetitveness > 10){
				business.setCompetitveness(businessCompetitveness - amount);
			}
		}


		int negOrPos = random.nextInt(2);
		int amount = random.nextInt(3);
		if(negOrPos == 1 && market > 10){
			market += amount;
		}else if(negOrPos == 0 && market < 50){
			market -= amount;
		}

		output += (proficiency/5);
	}
	
	
	public static void weekPass(NameGenerator nameGenerator){
		int competition = 0; 
		for(int i = 0; i < businessList.size(); i++){
			competition += (businessList.get(i)).getCompetitveness();
			System.out.println((businessList.get(i)).getCompetitveness());
		}

		for(int i = 0; i < market; i++){
			if (random.nextInt(competition) < competitveness) {
            	orders.add(nameGenerator.getOrder());
        	}
		}

		for(int i = 0; i < employeeList.size(); i++){
			money -= (employeeList.get(i)).getSalary();
		}
		week ++;
	}
	
	public static void orderChecker(){
		while (!orders.isEmpty()){ 
	        Order order = orders.peek(); 

	        if (order != null && output > order.getRequest()) { 
	            money += order.getPayment();
	            output -= order.getRequest();
	            orders.remove(); 
        	} else {
        	    break; 
        	}
    	}

	    if (orders.isEmpty()) {
	        System.out.println("You have no more orders!");
	    }
	}

	public static void hireEmployees(NameGenerator nameGenerator){
		if(money >= 20000){
			ArrayList<Employee> newEmployees = nameGenerator.getEmployees(5);
			employeeList.addAll(newEmployees.subList(0, 5));
			money -= 20000;
			System.out.println("More employees successfully hired!");
		}else{
			System.out.println("Insufficient funds to hire new employees!");
		}
	}

	public static void proficiencyTraining(){
		if(money >= 40000){
			for(int i = 0; i < employeeList.size(); i++){
				Employee employee = employeeList.get(i);
				employee.setProficiency(employee.getProficiency() + 100);
			}
			money -= 40000;
			System.out.println("More employees were successfully trained!");
		}else{
			System.out.println("Insufficient funds to train employees!");
		}
	}

	public static void advertisingCampgain(){
		if(money >= 50000){
			competitveness += 10;
			money -= 50000;
			System.out.println("Advertising Campgain successfully lauched!");
		}else{
			System.out.println("Insufficient funds to launch an Advertising Campgain!");
		}
	}
}