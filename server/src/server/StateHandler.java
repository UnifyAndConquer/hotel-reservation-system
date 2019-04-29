package server;
import java.util.ArrayList;

public class StateHandler {
	
	int currentState, nextState;
	String input, output, command;
	Boolean login;
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
//		ArrayList<String> data = new ArrayList<String>();
		
		String[] com = input.split(";");
		command = com[0];
		
		String[] dat = com[1].split(",");
		
		System.out.println("parsing:");
		
		for (int i = 0; i < dat.length; i++)
		{
			data.add(dat[i]);
			System.out.println(data.get(i));
		}
	}

	public void transition()
	{
		System.out.println("data size: ");
		System.out.println(data.size());
		
		switch (currentState)
		{
		case 1:

			login = false;
			
			if(command.equals("DONE"))
			{
				Integer x = data.size();
				Integer y = 5;
				System.out.println("command = DONE");
				
				if (x.equals(y))  // all fields are filled
				{
					System.out.println("data is of length 5");
					if(db.insert_client(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4)))  // db returns OK
					{
						System.out.println("db insert successful");
						output  = "2;";
						nextState = 2;   // go to "set date"
					}
					else
					{
						System.out.println("db insert not successful");
						output = "500;";  				// ERROR: duplicate record
						nextState = currentState;
					}
				}
				else
				{
					System.out.println("there are only "+ data.size() + " fields");
					for (int i = 0; i < data.size(); i++ )
						{
							System.out.println(data.get(i));
						}
					
					output = "400;";     			//ERROR: empty data after command
					nextState = currentState;
				}
			}
			else if(command.equals("LOGIN"))
			{
				System.out.println("command = LOGIN");
				output = "6;";
				nextState = 6;
			}
			else
			{
				System.out.println("command = "+command);
				output = "401;";	  				//ERROR: invalid command
				nextState = currentState;
			}
			
//		case 2:
//			
//			login = true;
//			
//			if (command.equals("SEARCH"))
//			{
//				if(!data.isEmpty())  // there are rooms available
//				{
//					// access SQL records within set critera (check in, check output, preference, beds)
//					// output = string containing available room numbers
//					
//					nextState = 3;
//				}
//				// else if data is not empty and there are no rooms available
//					// output = "5;";
//					// nextState = 5;
//				
//				else
//				{
//					output = "400;";   				//ERROR: empty data after command
//					nextState = currentState;
//				}
//			}
//			else if(command == "PROFILE")
//			{
//				output = "9;";
//				nextState = 9;
//			}
//			else if (command == "LOGoutput")
//			{
//				output = "6;";
//				nextState = 6;
//			}
//			else
//			{
//				output = "401;";	  				//ERROR: invalid command
//				nextState = currentState;
//			}
//			
//		case 3:
//			
//			login = true;
//			
//			if(command == "BACK")
//			{
//				output = "2;";
//				nextState = 2;
//			}
//			else if(command == "CHECKoutput")
//			{
//				if(!data.isEmpty())
//				{
//					// insert array of room numbers in reservations database with client id
//					output = "7;";
//					nextState = 7;
//				}
//				else
//				{
//					output = "400;";   				//ERROR: empty data after command
//					nextState = currentState;
//				}
//			}
//			else if(command == "LOGoutput")
//			{
//				output = "6;";
//				nextState = 6;
//			}
//			else
//			{
//				output = "401;";	  					//ERROR: invalid command
//				nextState = currentState;
//			}
//			
//		case 4:
//			
//			login = true;
//			
//			if(command == "BACK")
//			{
//				output = "9;";
//				nextState = 9;
//			}
//			else if(command == "DONE")
//			{
//				if(!data.isEmpty())
//				{
//					// insert profile data in profiles database
//					output = "9;";
//					nextState = 9;
//				}
//				else
//				{
//					output = "400;";   				//ERROR: empty data after command
//					nextState = currentState;
//				}
//			}
//			else
//			{
//				output = "401;";	  					//ERROR: invalid command
//				nextState = currentState;
//			}
//			
//		case 5:
//			
//			login = true;
//			
//			if(command == "BACK")
//			{
//				output = "2;";
//				nextState = 2;
//			}
//			else if(command == "LOGoutput")
//			{
//				output = "6;";
//				nextState = 6;
//			}
//			else
//			{
//				output = "401;";	  					//ERROR: invalid command
//				nextState = currentState;
//			}
//			
//		case 6:
//			
//			login = false;
//			
//			if(command == "REGISTER")
//			{
//				output = "1;";
//				nextState = 1;
//			}
//			else if(command == "DONE")
//			{
//				if(!data.isEmpty())  // and username and password correct
//				{
//					// check username and password in profiles database, get client id from database if ok
//					output = "2;";
//					nextState = 2;
//				}
//				else
//				{
//					output = "400;";   				//ERROR: empty data after command
//					nextState = currentState;
//				}
//			}
//			else
//			{
//				output = "401;";	  					//ERROR: invalid command
//				nextState = currentState;
//			}
//			
//		case 7:
//			
//			login = true;
//			
//			if(command == "BACK")
//			{
//				output = "1;";
//				nextState = 1;
//			}
//			else if(command == "DONE")
//			{
//				// calculate invoice from reservation database and return it in output
//			
//				nextState = 3;
//			}
//			else if(command == "LOGoutput")
//			{
//				output = "8;";
//				nextState = 8;
//			}
//			else
//			{
//				output = "401;";	  					//ERROR: invalid command
//				nextState = currentState;
//			}
//			
//		case 8:
//			
//			login = true;
//			
//			if(command == "BACK")
//			{
//				output = "7;";
//				nextState = 7;
//			}
//			else if(command == "BOOK")
//			{
//				output = "11;CONFIRMED";
//				nextState = 11;
//			}
//			else if(command == "LOGoutput")
//			{
//				output = "6;";
//				nextState = 6;
//			}
//			else
//			{
//				output = "401;";	  					//ERROR: invalid command
//				nextState = currentState;
//			}
//			
//		case 9:
//			
//			login = true;
//			
//			if(command == "NEWBOOKING")
//			{
//				output = "2;";
//				nextState = 2;
//			}
//			else if(command == "EDITRES")
//			{
//				if(!data.isEmpty())
//				{
//					// insert reservation number in reservations database, return reservation details in output
//					
//					nextState = 10;
//				}
//				else
//				{
//					output = "400;";   				//ERROR: empty data after command
//					nextState = currentState;
//				}
//			}
//			else if(command == "EDITPRO")
//			{
//				output = "4;";
//				nextState = 4;
//			}
//			else
//			{
//				output = "401;";	  					//ERROR: invalid command
//				nextState = currentState;
//			}
//			
//		case 10:
//			
//			login = true;
//			
//			if(command == "BACK")
//			{
//				output = "9;";
//				nextState = 9;
//			}
//			else if(command == "DONE")
//			{
//				if(!data.isEmpty())
//				{
//					// enter new reservation details to reservations database
//					output = "9;";
//					nextState = 9;
//				}
//				else
//				{
//					output = "400;";   				//ERROR: empty data after command
//					nextState = currentState;
//				}
//			}
//			else
//			{
//				output = "401;";	  					//ERROR: invalid command
//				nextState = currentState;
//			}
//			
//		case 11:
//			
//			login = true;
//			
//			if(command == "VIEWRES")
//			{
//				output = "9;";
//				nextState = 9;
//			}
//			else
//			{
//				output = "401;";	  					//ERROR: invalid command
//				nextState = currentState;
//			}
//			
		}
	}
}