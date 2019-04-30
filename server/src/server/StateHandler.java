package server;
import java.util.ArrayList;

public class StateHandler
{
	int currentState, nextState;
	String input, output, command;
	Boolean login;
	int id;
	ArrayList<String> data = new ArrayList<String>();
	Db db;
	
	public StateHandler(int state, String textIn, Boolean log)
	{
		currentState = state;
		nextState = 0;
		input = textIn;
		output = "empty output";
		login = log;
		db = new Db();
		
		parseInput();
		transition();
	}
	
	public int getNextState()
	{
		return(nextState);
	}
	
	public int getId()
	{
		return(id);
	}
	
	public String getOutput()
	{
		return(output);
	}
	
	public Boolean getLogin()
	{
		return(login);
	}
	
	public void parseInput()  // chop input into command and data
	{	
		String[] com = input.split(";");
		command = com[0];
		
		String[] dat = com[1].split(",");
		
		System.out.println("PARSING AT SERVER:");
		System.out.println("Command: "+command);
		System.out.println("Data: ");
		for (int i = 0; i < dat.length; i++)
		{
			data.add(dat[i]);
			System.out.print(data.get(i) + " ");
		}
	}

	public void transition()
	{
		System.out.println("Data size: "+data.size());
		System.out.println("TRANSITION AT SERVER");
		
		switch (currentState)
		{
		case 1:
			System.out.println("Current state: "+currentState);

			login = false;
			
			if(command.equals("DONE"))
			{
				Integer x = data.size();
				Integer y = 5;
				System.out.println("command = DONE");
				
				if (x.equals(y))  // all fields are filled
				{
					System.out.println("data is of length 5");
					id = db.insert_client(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4));
					
					if(id>0)  // db returns OK
					{
						System.out.println("db insert successful");
						output  = "2;,";
						nextState = 2;   // go to "set date"
					}
					else
					{
						System.out.println("db insert not successful");
						output = "500;,";  				// ERROR: duplicate record
						nextState = currentState;
					}
				}
				else
				{
					System.out.println("there are only "+ data.size() + " data fields");
					for (int i = 0; i < data.size(); i++ )
						{
							System.out.println(data.get(i));
						}
					
					output = "400;,";     			//ERROR: empty data after command
					nextState = currentState;
				}
			}
			
			else if(command.equals("LOGIN"))
			{
				System.out.println("command = LOGIN ya man");
				output = "6;,";
				nextState = 6;
			}
			else
			{
				System.out.println("invalid command:"+command);
				output = "401;,";	  				//ERROR: invalid command
				nextState = currentState;
			}
			
			break;
			
		case 2:
			
			login = true;
			
			if (command.equals("SEARCH"))
			{
				Integer x = data.size();
				Integer y = 4;
				
				if(x.equals(y))  // no missing data after command
				{
					boolean rooms[] = db.preferences(Integer.parseInt(data.get(3)), data.get(0), data.get(1), data.get(2).equals("true"));
					System.out.println("room availability: ");
					
					output = "";
					
					int[] prices = new int[rooms.length];
					Boolean roomsAvailable = false;
					
					for (int i=0; i<rooms.length; i++)
					{
						
						if(rooms[i])
						{
							prices[i] = db.select_room_price(i+1);
							
							output = output + Integer.toString(i+1) + ","+prices[i]+",";   // write index of available rooms in output
							
							roomsAvailable = true;
						}
					}
					
					if(!roomsAvailable)
					{
						output = "502;,";
						nextState = currentState;
					}
					else
					{
						output = "3;" + output;
						nextState = 3;
					}
				}
				
				else
				{
					output = "400;,";   				//ERROR: empty data after command
					nextState = currentState;
				}
			}
			else if(command.equals("PROFILE"))
			{
				output = "9;,";
				nextState = 9;
			}
			else if (command.equals("LOGOUT"))
			{
				output = "6;,";
				nextState = 6;
			}
			else
			{
				output = "401;,";	  				//ERROR: invalid command
				nextState = currentState;
			}
			
			break;
			
		case 3:
			
			login = true;
			
			if(command.equals("BACK"))
			{
				output = "2;,";
				nextState = 2;
			}
			else if(command.equals("CHECKOUT"))
			{
				if(!data.isEmpty())
				{
					// insert array of room numbers in reservations database with client id
					output = "7;";
					nextState = 7;
				}
				else
				{
					output = "400;,";   				//ERROR: empty data after command
					nextState = currentState;
				}
			}
			else if(command.equals("LOGOUT"))
			{
				output = "6;,";
				nextState = 6;
			}
			else
			{
				output = "401;,";	  					//ERROR: invalid command
				nextState = currentState;
			}
			
			break;
			
		case 4:
			
			login = true;
			
			if(command == "BACK")
			{
				output = "9;,";
				nextState = 9;
			}
			else if(command == "DONE")
			{
				if(!data.isEmpty())
				{
					// insert profile data in profiles database
					output = "9;,";
					nextState = 9;
				}
				else
				{
					output = "400;,";   				//ERROR: empty data after command
					nextState = currentState;
				}
			}
			else
			{
				output = "401;,";	  					//ERROR: invalid command
				nextState = currentState;
			}
			
			break;
			
		case 5:
			
			login = true;
			
			if(command.equals("BACK"))
			{
				output = "2;";
				nextState = 2;
			}
			else if(command.equals("LOGOUT"))
			{
				output = "6;";
				nextState = 6;
			}
			else
			{
				output = "401;";	  					//ERROR: invalid command
				nextState = currentState;
			}
			
			break;
			
		case 6:
			System.out.println("Current state: "+currentState);
			System.out.println("Command (case 6): "+command);
			
			login = false;
			
			if(command.equals("REGISTER"))
			{
				System.out.println("inside register (6)");
				output = "1;,";
				nextState = 1;
			}
			else if(command.equals("DONE"))
			{
				System.out.println("inside done (6)");
				Integer x = data.size();
				Integer y = 2;
				
				if(x.equals(y))  // no data is missing
				{
					System.out.println("no missing data (6)");
					
					if(db.sign_in(data.get(1), data.get(0)) >= 0)   // this function returns user id
					{
						id = db.sign_in(data.get(1), data.get(0));
						System.out.println("login successful (6)");
						output = "2;,";
						nextState = 2;
					}
					else
					{
						System.out.println("incorrect password");
						output = "501;,";   	     //ERROR: incorrect password
						nextState = currentState;
					}
				}
				else
				{
					System.out.println("empty data after command");
					output = "400;,";   				//ERROR: empty data after command
					nextState = currentState;
				}
			}
			else
			{
				System.out.println("invalid command");
				output = "401;,";	  					//ERROR: invalid command
				nextState = currentState;
			}
			
			break;
			
		case 7:
			
			login = true;
			
			if(command.equals("BACK"))
			{
				output = "1;,";
				nextState = 1;
			}
			else if(command.equals("DONE"))
			{
				// calculate invoice from reservation database and return it in output
			
				nextState = 3;
			}
			else if(command.equals("LOGOUT"))
			{
				output = "8;,";
				nextState = 8;
			}
			else
			{
				output = "401;,";	  					//ERROR: invalid command
				nextState = currentState;
			}
			
			break;
			
		case 8:
			
			login = true;
			
			if(command.equals("BACK"))
			{
				output = "7;,";
				nextState = 7;
			}
			else if(command.equals("BOOK"))
			{
				output = "11;CONFIRMED";
				nextState = 11;
			}
			else if(command.equals("LOGOUT"))
			{
				output = "6;,";
				nextState = 6;
			}
			else
			{
				output = "401;,";	  					//ERROR: invalid command
				nextState = currentState;
			}
			
			break;
			
		case 9:
			
			login = true;
			
			if(command.equals("NEWBOOKING"))
			{
				output = "2;,";
				nextState = 2;
			}
			else if(command.equals("EDITRES"))
			{
				if(!data.isEmpty())
				{
					// insert reservation number in reservations database, return reservation details in output
					
					nextState = 10;
				}
				else
				{
					output = "400;,";   				//ERROR: empty data after command
					nextState = currentState;
				}
			}
			else if(command.equals("EDITPRO"))
			{
				output = "4;,";
				nextState = 4;
			}
			else
			{
				output = "401;,";	  					//ERROR: invalid command
				nextState = currentState;
			}
			
			break;
			
		case 10:
			
			login = true;
			
			if(command.equals("BACK"))
			{
				output = "9;,";
				nextState = 9;
			}
			else if(command.equals("DONE"))
			{
				if(!data.isEmpty())
				{
					// enter new reservation details to reservations database
					output = "9;,";
					nextState = 9;
				}
				else
				{
					output = "400;,";   				//ERROR: empty data after command
					nextState = currentState;
				}
			}
			else
			{
				output = "401;,";	  					//ERROR: invalid command
				nextState = currentState;
			}
			
			break;
			
		case 11:
			
			login = true;
			
			if(command.equals("VIEWRES"))
			{
				output = "9;,";
				nextState = 9;
			}
			else
			{
				output = "401;,";	  					//ERROR: invalid command
				nextState = currentState;
			}
			
			break;
			
			default:
			break;
		}
	}
}