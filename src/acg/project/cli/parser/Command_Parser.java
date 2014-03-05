package acg.project.cli.parser;

import java.io.*;
import java.util.*;

public class Command_Parser {
	
	public static void main(String ... args)
	{
		Scanner kb = new Scanner(System.in);
		System.out.println("Command--> ");
		String s = kb.nextLine();
	
		parseString(s);
	
	}//end of main
	
	protected static void parseString(String str)
	{
		int x;
		if(str.substring(0,6).equals("DEFINE"))
		{
			defineString(str.substring(7));
		}
		
		else if(str.substring(0,6).equals("CREATE"))
		{
			createString(str.substring(7));
		}
		
		else if(str.substring(0,8).equals("POPULATE"))
		{
			//populateString(str.substring(9));
		}
		
		else if(str.substring(0,2).equals("DO"))
		{
			//doString(str.substring(3));
		}
		
		else if(str.substring(0,3).equals("@DO"))
		{
			//@doString(str.substring(4));
		}
		
		else if(str.substring(0,3).equals("SET"))
		{
			//@doString(str.substring(4));
		}
		
		else if(str.substring(0,3).equals("GET"))
		{
			//@doString(str.substring(4));
		}
	
		/**
		 * need miscellaneous cmnds
		 * and small cmnds implemented
		 **/
	}
	
	protected static void defineString(String str)
	{
		List<String> commandLine = new ArrayList<String>();
		String [] array=(str.split(" "));
		
		if(array[0].equals("TRAP"))
		{
			//work ID
			int counter = 1;
			counter =easyParse(commandLine,array,"ORIGIN",counter);
			counter =easyParse(commandLine,array,"AZIMUTH",counter);
			counter =easyParse(commandLine,array,"WIDTH",counter);
			counter =easyParse(commandLine,array,"LIMIT",counter);
			counter =easyParse(commandLine,array,"SPEED",(counter+1));
			counter =easyParse(commandLine,array,"MISS",counter);
			//add the last value
			String miss = array[counter];
			commandLine.add(miss);

			int x;
			for(x=0;x<commandLine.size();x++)
			{
				System.out.println(commandLine.get(x));
			}
		}//end of TRAP
		
		else if(array[0].equals("CATAPULT"))
		{
			//work ID
			int counter = 1;
			counter =easyParse(commandLine,array,"ORIGIN",counter);
			counter =easyParse(commandLine,array,"AZIMUTH",counter);
			counter =easyParse(commandLine,array,"LENGTH",counter);
			counter =easyParse(commandLine,array,"ACCELERATION",counter);
			counter =easyParse(commandLine,array,"LIMIT",counter);
			counter =easyParse(commandLine,array,"SPEED",(counter+1));
			counter =easyParse(commandLine,array,"RESET",counter);
			//add the last value
			String reset = array[counter];
			commandLine.add(reset);
		}
		
		else if(array[0].equals("OLS_XMT"))
		{
			//work ID
			int counter = 1;
			counter =easyParse(commandLine,array,"ORIGIN",counter);
			counter =easyParse(commandLine,array,"AZIMUTH",counter);
			counter =easyParse(commandLine,array,"ELEVATION",counter);
			counter =easyParse(commandLine,array,"RANGE",counter);
			counter =easyParse(commandLine,array,"DIAMETER",counter);
			
			//add the last value
			String diameter = array[counter];
			commandLine.add(diameter);
		}
		
		else if(array[0].equals("OLS_RCV"))
		{
			//work ID
			int counter = 1;
			counter =easyParse(commandLine,array,"DIAMETER",counter);
			
			//add the last value
			String diameter = array[counter];
			commandLine.add(diameter);
		}
		
		else if(array[0].equals("CARRIER"))
		{
			//work ID
			int counter = 1;
			counter =easyParse(commandLine,array,"SPEED",counter);
			counter =easyParse(commandLine,array,"DELTA",(counter+1));
			counter =easyParse(commandLine,array,"DECREASE",(counter+1));
			counter =easyParse(commandLine,array,"TURN",counter);
			counter =easyParse(commandLine,array,"LAYOUT",counter);
			
			//add the last value
			String layout = array[counter];
			commandLine.add(layout);
		}
		
		else if(array[0].equals("FIGHTER"))
		{
			//work ID
			int counter = 1;
			counter =easyParse(commandLine,array,"SPEED",counter);
			counter =easyParse(commandLine,array,"MAX",(counter+1));
			counter =easyParse(commandLine,array,"DELTA",counter);
			counter =easyParse(commandLine,array,"DECREASE",(counter+1));
			counter =easyParse(commandLine,array,"TURN",counter);
			counter =easyParse(commandLine,array,"CLIMB",counter);
			counter =easyParse(commandLine,array,"DESCENT",counter);
			counter =easyParse(commandLine,array,"EMPTY",counter);
			counter =easyParse(commandLine,array,"FUEL",(counter+1));
			counter =easyParse(commandLine,array,"DELTA",(counter+1));
			
			//add the last value
			String delta = array[counter];
			commandLine.add(delta);
		}
		
		else if(array[0].equals("TANKER"))
		{
			//work ID
			int counter = 1;
			counter =easyParse(commandLine,array,"SPEED",counter);
			counter =easyParse(commandLine,array,"MAX",(counter+1));
			counter =easyParse(commandLine,array,"DELTA",(counter));
			counter =easyParse(commandLine,array,"DECREASE",(counter+1));
			counter =easyParse(commandLine,array,"TURN",counter);
			counter =easyParse(commandLine,array,"CLIMB",counter);
			counter =easyParse(commandLine,array,"DESCENT",counter);
			
			//add the last value
			String tank = array[counter];
			commandLine.add(tank);
		}
		
		else if(array[0].equals("BOOM"))
		{
			if(array[1].equals("MALE"))
			{
				//work ID
				int counter = 2;
				counter =easyParse(commandLine,array,"LENGTH",counter);
				counter =easyParse(commandLine,array,"DIAMETER",counter);
				counter =easyParse(commandLine,array,"FLOW",counter);
				
				//add the last value
				String flow = array[counter];
				commandLine.add(flow);
			}
			
			else
			{
				//work ID
				int counter = 2;
				counter =easyParse(commandLine,array,"LENGTH",counter);
				counter =easyParse(commandLine,array,"DIAMETER",counter);
				counter =easyParse(commandLine,array,"ELEVATION",counter);
				counter =easyParse(commandLine,array,"FLOW",counter);
				
				//add the last value
				String flow = array[counter];
				commandLine.add(flow);
			}
			
		}
		
		else if(array[0].equals("TAILHOOK"))
		{
			//work ID
			int counter = 1;
			counter =easyParse(commandLine,array,"TIME",counter);
			
			//add the last value
			String time = array[counter];
			commandLine.add(time);
		}
		
		else if(array[0].equals("BARRIER"))
		{
			//work ID
			int counter = 1;
			counter =easyParse(commandLine,array,"ORIGIN",counter);
			counter =easyParse(commandLine,array,"AZIMUTH",(counter+1));
			counter =easyParse(commandLine,array,"WIDTH",(counter));
			counter =easyParse(commandLine,array,"TIME",(counter+1));
			
			//add the last value
			String time = array[counter];
			commandLine.add(time);
		}
		
		else if(array[0].equals("AUX_TANK"))
		{
			//work ID
			int counter = 1;
			counter =easyParse(commandLine,array,"AMOUNT",counter);
			
			//add the last value
			String amount = array[counter];
			commandLine.add(amount);
		}
	}//end of DEFINE'S
	
	protected static void createString(String str)
	{
		List<List<String>> lists = new ArrayList<List<String>>(4);
		List<String> commandLine = new ArrayList<String>();
		String [] array=(str.split(" "));
		
		if(array[0].equals("CARRIER"))
		{
			//work ID
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			counter =easyParse(commandLine,array,"WITH",counter);
			counter =easyParse(commandLine,array,"BARRIER",(counter+1));
			counter =easyParse(commandLine,array,"TRAP",counter);
			counter =easyParse(commandLine,array,"OLS",counter);
			counter =easyParse(commandLine,array,"AT",counter);
			counter =easyParse(commandLine,array,"HEADING",(counter+1));
			counter =easyParse(commandLine,array,"SPEED",counter);
			
			//add the last value
			String speed = array[counter];
			commandLine.add(speed);
		}
		
		else if(array[0].equals("FIGHTER"))
		{
			//work ID
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			counter =easyParse(commandLine,array,"WITH",counter);
			counter =easyParse(commandLine,array,"BOOM",(counter+1));
			counter =easyParse(commandLine,array,"TAILHOOK",counter);
			//add the last value
			String tailhook = array[counter];
			commandLine.add(tailhook);
			counter++;
			
			lists.add(commandLine);
			
			if(array.length-1>counter)
			{
				if(array[counter].equals("TANKS"))
				{
					List<String> tank = new ArrayList<String>();
					String tanks = array[counter];
					commandLine.add(tailhook);
					lists.add(1,tank);
				}
				
				else if(array[counter].equals("OVERRIDING"))
				{
					List<String> tank = new ArrayList<String>();
					counter = easyParse(tank,array,"WITH",counter);
					String with = array[counter];
					counter++;
					tank.add(with);
					lists.add(2,tank);
				}
				
				else if(array[counter].equals("AT"))
				{
					List<String> coord = new ArrayList<String>();
					counter = easyParse(coord,array,"ALTITUDE",(counter+1));
					counter = easyParse(coord,array,"HEADING",counter);
					counter = easyParse(coord,array,"SPEED",counter);
					String speed = array[counter];
					counter++;
					coord.add(speed);
					lists.add(3,coord);
				}
						
			}
		}
		
		else if(array[0].equals("TANKER"))
		{
			//work ID
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			counter =easyParse(commandLine,array,"WITH",counter);
			counter =easyParse(commandLine,array,"AT",(counter+1));
			counter =easyParse(commandLine,array,"ALTITUDE",(counter+1));
			counter =easyParse(commandLine,array,"HEADING",counter);
			counter =easyParse(commandLine,array,"SPEED",counter);
			
			//add the last value
			String speed = array[counter];
			commandLine.add(speed);
		}
		
		else if(array[0].equals("TRAP"))
		{
			//work ID
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			
			//add the last value
			String tid = array[counter];
			commandLine.add(tid);
		}
		
		else if(array[0].equals("BARRIER"))
		{
			//work ID
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			
			//add the last value
			String tid = array[counter];
			commandLine.add(tid);
		}
		
		else if(array[0].equals("AUX_TANK"))
		{
			//work ID
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			
			//add the last value
			String tid = array[counter];
			commandLine.add(tid);
		}
		
		else if(array[0].equals("CATAPULT"))
		{
			//work ID
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			
			//add the last value
			String tid = array[counter];
			commandLine.add(tid);
		}
		
		else if(array[0].equals("OLS_XMT"))
		{
			//work ID
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			
			//add the last value
			String tid = array[counter];
			commandLine.add(tid);
		}
		
		else if(array[0].equals("OLS_RVC"))
		{
			//work ID
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			
			//add the last value
			String tid = array[counter];
			commandLine.add(tid);
		}
		
		else if(array[0].equals("BOOM"))
		{
			//work ID
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			
			//add the last value
			String tid = array[counter];
			commandLine.add(tid);
		}
		
		else if(array[0].equals("TAILHOOK"))
		{
			//work ID
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			
			//add the last value
			String tid = array[counter];
			commandLine.add(tid);
		}
		
	}//end of CREATE'S
	
	protected static void populateString(String str)
	{
		List<String> commandLine = new ArrayList<String>();
		String [] array=(str.split(" "));
		
		if(array[0].equals("CARRIER"))
		{
			//work ID
			int counter = 1;
			counter =easyParse(commandLine,array,"WITH",counter);
			counter++;
			
			//single fighter
			if(array[counter].equals("FIGHTER"))
			{
				String tid = array[counter+1];
				commandLine.add(tid);
			}
			
			//multiple fighters
			else
			{
				counter++;
				while(array.length>counter)
				{
					String tid = array[counter];
					commandLine.add(tid);
					counter++;
				}
			}
		}
		
		else if(array[0].equals("WORLD"))
		{
			//work ID
			int counter = 2;
			
			//add all agent id's to be added
			while(array.length>counter)
			{
				String tid = array[counter];
				commandLine.add(tid);
				counter++;
			}
		}
	}
	
	protected static int easyParse(List commandLine,String [] array, String badWord,int counter)
	{
		String cmnd="";
		while(!array[counter].equals(badWord))
		{
			cmnd += array[counter];
			counter++;
		}
		counter++;
		commandLine.add(cmnd);
		return counter;
			
	}
}//end of class