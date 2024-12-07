//Pablo Mendoza 
//CPSC-39
//12/6/2024

public class Employee{
	private String name;
	private int salary;
	private int proficiency;
	private int happiness;

	//constructor

	public Employee(String name, int salary, int proficiency, int happiness){
		this.name = name;
		this.salary = salary;
		this.proficiency = proficiency;
		this.happiness = happiness;
	}

	//get functions

	public String getName(){
		return name;
	}

	public int getSalary(){
		return salary;
	}

	public int getProficiency(){
		return proficiency;
	}

	public int getHappiness(){
		return happiness;
	}

	//set functions


    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setProficiency(int proficiency) {
        this.proficiency = proficiency;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

	public String toString(){
		return "\nEmployee - \nName: " + name + "\nSalary: " + salary + "\nProficiency: " + proficiency + "\nHappiness: " + happiness;
	}
}