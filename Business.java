//Pablo Mendoza 
//CPSC-39
//12/6/2024

public class Business{
	private String name;
	private int health;
	private int competitveness;

	//constructor

	public Business(String name, int health, int competitveness){
		this.name = name;
		this.health = health;
		this.competitveness = competitveness;
	}

	//get functions

	public String getName(){
		return name;
	}

	public int getHealth(){
		return health;
	}

	public int getCompetitveness(){
		return competitveness;
	}

	//set functions

    public void setHealth(int health) {
        this.health = health;
    }

    public void setCompetitveness(int competitveness) {
        this.competitveness = competitveness;
    }

	public String toString(){
		return "\nBusiness - \nName: " + name + "\nHealth: " + health + "\nCompetitvness: " + competitveness;
	}
}