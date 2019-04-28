package server;

import java.util.ArrayList;

public class Test {
	
	public static void main(String args[])
	{
		String str;
		int val = 4;
		
		str = Integer.toString(val);
		System.out.print(str + str);

	}
}


//String input = "DONE;Tarek,Tohme,21";
//String command, rest;
//ArrayList<String> data = new ArrayList<String>();
//
//String[] com = input.split(";");
//command = com[0];
//rest = com[1];
//String[] dat = rest.split(",");
//
//System.out.println(command);
//
//for (int i = 0; i < dat.length; i++ )
//{
//	data.add(dat[i]);
//	System.out.println(data.get(i));
//}