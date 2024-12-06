public class Order{
	private String name;
	private int request;
	private int payment;

	//constructor

	public Order(String name, int request, int payment){
		this.name = name;
		this.request = request;
		this.payment = payment;
	}

	//get functions

	public String getName(){
		return name;
	}

	public int getRequest(){
		return request;
	}

	public int getPayment(){
		return payment;
	}

	public String toString(){
		return "\nOrder - \nName: " + name + "\nRequest: " + request + "\nPayment: " + payment;
	}
}