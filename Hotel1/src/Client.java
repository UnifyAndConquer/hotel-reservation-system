
public class Client {
	
	String name;
	
	String DoB; //Date of birth
	
	String nationality;
	
	String email;
	
	int PhoneNumber;
	
	String Password;
	
	private int ID;
	
	public Client(String name, String DoB, String nationality, String email, int PhoneNumber, String Password)
	{
		this.name=name;
		this.DoB=DoB;
		this.nationality=nationality;
		this.email=email;
		this.PhoneNumber=PhoneNumber;
		this.Password=Password;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
}
