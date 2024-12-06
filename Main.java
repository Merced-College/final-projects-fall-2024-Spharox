import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.LinkedList;
import java.util.Queue;

public class Main{

	static int money = 10000, market = 50, output, competitveness = 30, week = 0;
	static Queue<Order> orders = new LinkedList<>();
	static Random random = new Random();

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		String filePath = "names.txt";
        NameProcessor nameProcessor = new NameProcessor(filePath);
        ArrayList<String> firstNames = nameProcessor.getFirstNames();
        ArrayList<String> lastNames = nameProcessor.getLastNames();

        NameGenerator nameGenerator = new NameGenerator(firstNames, lastNames);
        ArrayList<Employee> employeeList = nameGenerator.getEmployees(30);
        ArrayList<Business> businessList = nameGenerator.getBusinesses(15);

        System.out.println("Welcome to business simulator game! Please enter your enterprises name - ");
		String userName = scanner.nextLine(); 
		updateVariables(employeeList);
		weekPass(businessList, nameGenerator);

		while(true){

			System.out.println("\nWeek " + week + ": \n" + userName + " -\nMoney = 10000\nCompetitveness = " + competitveness + 
			"\nEnter 1 to pass week, 2 to check market, 3 to check employess, 4 to check orders, 7 to exit game\n");
			String playerResponse = scanner.nextLine(); 
			
			switch (playerResponse) {
			case "1":
				weekPass(businessList, nameGenerator);
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
			case "7":
				System.out.println("Congratulations! You did not go broke!");
				return;
			default: 
				System.out.println("Incorrect input deteced, Enter 1 to pass week, 2 to check market, 3 to check employess, 7 to exit game");
			}
		}
	}

	public static void updateVariables(ArrayList<Employee> employeeList){
		int proficiency = 0;
		for(int i = 0; i < employeeList.size(); i++){
			proficiency += (employeeList.get(i)).getProficiency();
		}
		output = proficiency;



	}
	
	public static void weekPass(ArrayList<Business> businessList, NameGenerator nameGenerator){
		int competition = 0; 
		for(int i = 0; i < businessList.size(); i++){
			competition += (businessList.get(i)).getCompetitveness();
		}
		
		for(int i = 0; i < market; i++){
			if (random.nextInt(competition) < competitveness) {
            	orders.add(nameGenerator.getOrder());
        	}
		}
		week ++;
	}
	
	public static void orderChecker(){
		
	}

}