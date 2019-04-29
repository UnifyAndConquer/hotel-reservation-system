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
		ArrayList<String> data = new ArrayList<String>();
		
		String[] com = input.split(";");
		command = com[0];
		
		String[] dat = com[1].split(",");
		
		for (int i = 0; i < dat.length; i++)
		{
			data.add(dat[i]);
		}
	}

	public String transition()
	{
		String out = "";
		
		switch (currentState)
		{
		case 1:

			login = false;
			
			if(command == "DONE")
			{
				if (!data.isEmpty())  // and SQL db returns OK
				{
					// write data to profile database
					
					out  = "2;";
					nextState = 2;   // go to "set date"
				}
//				else if data is not empty and SQL returns DUPLICATE RECORD
				
//					out = "500;";  				// ERROR: duplicate record
//					nextState = currentState;
				
				else
				{
					out = "400;";     			//ERROR: empty data after command
					nextState = currentState;
				}
			}
			else if(command == "LOGIN")
			{
				out = "6;";
				nextState = 6;
			}
			else
			{
				out = "401;";	  				//ERROR: invalid command
				nextState = currentState;
			}
			
		case 2:
			
			login = true;
			
			if (command == "SEARCH")
			{
				if(!data.isEmpty())  // there are rooms available
				{
					// access SQL records within set critera (check in, check out, preference, beds)
					// out = string containing available room numbers
					
					nextState = 3;
				}
				// else if data is not empty and there are no rooms available
					// out = "5;";
					// nextState = 5;
				
				else
				{
					out = "400;";   				//ERROR: empty data after command
					nextState = currentState;
				}
			}
			else if(command == "PROFILE")
			{
				out = "9;";
				nextState = 9;
			}
			else if (command == "LOGOUT")
			{
				out = "6;";
				nextState = 6;
			}
			else
			{
				out = "401;";	  				//ERROR: invalid command
				nextState = currentState;
			}
//			
//		case 3:
//			
//			login = true;
//			
//			if(command == "BACK")
//			{
//				out = "2;";
//				nextState = 2;
//			}
//			else if(command == "CHECKOUT")
//			{
//				if(!data.isEmpty())
//				{
//					// insert array of room numbers in reservations database with client id
//					out = "7;";
//					nextState = 7;
//				}
//				else
//				{
//					out = "400;";   				//ERROR: empty data after command
//					nextState = currentState;
//				}
//			}
//			else if(command == "LOGOUT")
//			{
//				out = "6;";
//				nextState = 6;
//			}
//			else
//			{
//				out = "401;";	  					//ERROR: invalid command
//				nextState = currentState;
//			}
//			
//		case 4:
//			
//			login = true;
//			
//			if(command == "BACK")
//			{
//				out = "9;";
//				nextState = 9;
//			}
//			else if(command == "DONE")
//			{
//				if(!data.isEmpty())
//				{
//					// insert profile data in profiles database
//					out = "9;";
//					nextState = 9;
//				}
//				else
//				{
//					out = "400;";   				//ERROR: empty data after command
//					nextState = currentState;
//				}
//			}
//			else
//			{
//				out = "401;";	  					//ERROR: invalid command
//				nextState = currentState;
//			}
//			
//		case 5:
//			
//			login = true;
//			
//			if(command == "BACK")
//			{
//				out = "2;";
//				nextState = 2;
//			}
//			else if(command == "LOGOUT")
//			{
//				out = "6;";
//				nextState = 6;
//			}
//			else
//			{
//				out = "401;";	  					//ERROR: invalid command
//				nextState = currentState;
//			}
//			
//		case 6:
//			
//			login = false;
//			
//			if(command == "REGISTER")
//			{
//				out = "1;";
//				nextState = 1;
//			}
//			else if(command == "DONE")
//			{
//				if(!data.isEmpty())  // and username and password correct
//				{
//					// check username and password in profiles database, get client id from database if ok
//					out = "2;";
//					nextState = 2;
//				}
//				else
//				{
//					out = "400;";   				//ERROR: empty data after command
//					nextState = currentState;
//				}
//			}
//			else
//			{
//				out = "401;";	  					//ERROR: invalid command
//				nextState = currentState;
//			}
//			
//		case 7:
//			
//			login = true;
//			
//			if(command == "BACK")
//			{
//				out = "1;";
//				nextState = 1;
//			}
//			else if(command == "DONE")
//			{
//				// calculate invoice from reservation database and return it in out
//			
//				nextState = 3;
//			}
//			else if(command == "LOGOUT")
//			{
//				out = "8;";
//				nextState = 8;
//			}
//			else
//			{
//				out = "401;";	  					//ERROR: invalid command
//				nextState = currentState;
//			}
//			
//		case 8:
//			
//			login = true;
//			
//			if(command == "BACK")
//			{
//				out = "7;";
//				nextState = 7;
//			}
//			else if(command == "BOOK")
//			{
//				out = "11;CONFIRMED";
//				nextState = 11;
//			}
//			else if(command == "LOGOUT")
//			{
//				out = "6;";
//				nextState = 6;
//			}
//			else
//			{
//				out = "401;";	  					//ERROR: invalid command
//				nextState = currentState;
//			}
//			
//		case 9:
//			
//			login = true;
//			
//			if(command == "NEWBOOKING")
//			{
//				out = "2;";
//				nextState = 2;
//			}
//			else if(command == "EDITRES")
//			{
//				if(!data.isEmpty())
//				{
//					// insert reservation number in reservations database, return reservation details in out
//					
//					nextState = 10;
//				}
//				else
//				{
//					out = "400;";   				//ERROR: empty data after command
//					nextState = currentState;
//				}
//			}
//			else if(command == "EDITPRO")
//			{
//				out = "4;";
//				nextState = 4;
//			}
//			else
//			{
//				out = "401;";	  					//ERROR: invalid command
//				nextState = currentState;
//			}
//			
//		case 10:
//			
//			login = true;
//			
//			if(command == "BACK")
//			{
//				out = "9;";
//				nextState = 9;
//			}
//			else if(command == "DONE")
//			{
//				if(!data.isEmpty())
//				{
//					// enter new reservation details to reservations database
//					out = "9;";
//					nextState = 9;
//				}
//				else
//				{
//					out = "400;";   				//ERROR: empty data after command
//					nextState = currentState;
//				}
//			}
//			else
//			{
//				out = "401;";	  					//ERROR: invalid command
//				nextState = currentState;
//			}
//			
//		case 11:
//			
//			login = true;
//			
//			if(command == "VIEWRES")
//			{
//				out = "9;";
//				nextState = 9;
//			}
//			else
//			{
//				out = "401;";	  					//ERROR: invalid command
//				nextState = currentState;
//			}
//			
		}
		
		output = out;
		return(out);
	}
}