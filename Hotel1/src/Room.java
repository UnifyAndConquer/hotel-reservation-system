
public class Room {

	//enum type{single,doubl ,triple};  we write "doubl" because "double" is an object
	private int nbOfpersons;
	private String bedType;
	private boolean smoking;
	String dateofreservation;
	String checkoutdate;
	private int id;
	int clientID;
	public Room(int nbOfpersons, String bedType, boolean smoking, int id) {
		this.nbOfpersons = nbOfpersons;
		this.bedType = bedType;
		this.smoking = smoking;
		this.dateofreservation = "00/00/0000";
		this.checkoutdate = "00/00/0000";
		this.id = id;
		this.clientID = 0;
	}
	
	

	
}
