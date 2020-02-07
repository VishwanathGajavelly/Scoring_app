package participant;

public class Participant {
	
	private String firstName;
	private String lastName;
	private int chestNumber;
	private int age;
	private String state;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.firstName = lastName;
	}
	public int getChestNumber() {
		return chestNumber;
	}
	public void setChestNumber(int chestNumber) {
		this.chestNumber = chestNumber;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public Participant(String firstName, String lastName, int chestNumber, int age, String state) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.chestNumber = chestNumber;
		this.age = age;
		this.state = state;
	}
	

}
