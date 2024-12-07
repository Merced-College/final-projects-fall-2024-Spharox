//Pablo Mendoza 
//CPSC-39
//12/6/2024

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.LinkedList;
import java.util.Queue;
import java.io.*;

public class Main {

    // Game variables
    static int money = 50000, market = 30, output = 0, competitveness = 30, week = 1;
    static Queue<Order> orders = new LinkedList<>();
    static ArrayList<Employee> employeeList = new ArrayList<>();
    static ArrayList<Business> businessList = new ArrayList<>();
    static Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = "names.txt";

        // Initialize name processor and generator
        NameProcessor nameProcessor = new NameProcessor(filePath);
        ArrayList<String> firstNames = nameProcessor.getFirstNames();
        ArrayList<String> lastNames = nameProcessor.getLastNames();

        NameGenerator nameGenerator = new NameGenerator(firstNames, lastNames);
        employeeList = nameGenerator.getEmployees(30);
        businessList = nameGenerator.getBusinesses(15);

        // Get user's enterprise name
        System.out.println("Welcome to business simulator game! Please enter your enterprise's name: ");
        String userName = scanner.nextLine();
        orderChecker();

        // Main game loop
        while (money >= 0) {
            System.out.println("\nWeek " + week + ": \n" + userName + " -\nMoney = " + money + "\nCompetitveness = " + competitveness +
                    "\nEnter 1 to pass week, 2 to check market, 3 to check employees, 4 to check orders, 5 for upgrades, 7 to exit game\n");
            String playerResponse = scanner.nextLine();

            // Handle player's choices
            switch (playerResponse) {
                case "1":
                    updateVariables();
                    weekPass(nameGenerator);
                    orderChecker();
                    break;
                case "2":
                    businessList.forEach(System.out::println);
                    break;
                case "3":
                    employeeList.forEach(System.out::println);
                    break;
                case "4":
                    orders.forEach(System.out::println);
                    break;
                case "5":
                    handleUpgrades(scanner, nameGenerator);
                    break;
                case "7":
                    System.out.println("Congratulations! You did not go broke!");
                    return;
                default:
                    System.out.println("Incorrect input detected. Please enter a valid option.");
            }
        }

        System.out.println("Game Over, you went broke!");
    }

    // Updates variables such as market conditions and proficiency
    public static void updateVariables() {
        int proficiency = 0;
        for (Employee employee : employeeList) {
            int employeeProficiency = employee.getProficiency();
            proficiency += employeeProficiency;
            if (employeeProficiency < 700) {
                employee.setProficiency(employeeProficiency + 1);
            }
        }

        for (Business business : businessList) {
            int businessCompetitveness = business.getCompetitveness();
            int adjustment = random.nextInt(3);
            if (random.nextBoolean() && businessCompetitveness < 50) {
                business.setCompetitveness(businessCompetitveness + adjustment);
            } else if (!random.nextBoolean() && businessCompetitveness > 10) {
                business.setCompetitveness(businessCompetitveness - adjustment);
            }
        }

        int marketAdjustment = random.nextInt(3);
        if (random.nextBoolean() && market > 10) {
            market += marketAdjustment;
        } else if (!random.nextBoolean() && market < 50) {
            market -= marketAdjustment;
        }

        output += (proficiency / 5);
    }

    // Simulates passing a week
    public static void weekPass(NameGenerator nameGenerator){
		int competition = 0; 
		for(int i = 0; i < businessList.size(); i++){
			competition += (businessList.get(i)).getCompetitveness();
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

    // Processes completed orders
    public static void orderChecker() {
        while (!orders.isEmpty()) {
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

    // Handles hiring employees
    public static void hireEmployees(NameGenerator nameGenerator) {
        if (money >= 20000) {
            ArrayList<Employee> newEmployees = nameGenerator.getEmployees(5);
            employeeList.addAll(newEmployees);
            money -= 20000;
            System.out.println("More employees successfully hired!");
        } else {
            System.out.println("Insufficient funds to hire new employees!");
        }
    }

    // Handles proficiency training for employees
    public static void proficiencyTraining() {
        if (money >= 40000) {
            employeeList.forEach(employee -> employee.setProficiency(employee.getProficiency() + 100));
            money -= 40000;
            System.out.println("Employees successfully trained!");
        } else {
            System.out.println("Insufficient funds to train employees!");
        }
    }

    // Launches an advertising campaign
    public static void advertisingCampaign() {
        if (money >= 50000) {
            competitveness += 10;
            money -= 50000;
            System.out.println("Advertising campaign successfully launched!");
        } else {
            System.out.println("Insufficient funds to launch an advertising campaign!");
        }
    }

    // Handles upgrade menu and choices
    public static void handleUpgrades(Scanner scanner, NameGenerator nameGenerator) {
        System.out.println("\nWhich upgrade do you want? \n1 - Hire More Employees (20000)\n2 - Proficiency Training (40000)\n3 - Advertising Campaign (50000)");
        String upgradeResponse = scanner.nextLine();

        switch (upgradeResponse) {
            case "1":
                hireEmployees(nameGenerator);
                break;
            case "2":
                proficiencyTraining();
                break;
            case "3":
                advertisingCampaign();
                break;
            default:
                System.out.println("Invalid upgrade choice.");
        }
    }
}
