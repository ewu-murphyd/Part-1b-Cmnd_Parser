package acg.project.cli.parser;

import java.io.*;
import java.util.*;

//import acg.architecture.view.glyph.loader.*;
import acg.project.action.ActionSet;
import acg.project.action.command.behavioral.*;
import acg.project.action.command.structural.*;
import acg.project.action.command.creational.create.*;
import acg.project.action.command.creational.define.*;
import acg.project.action.command.miscellaneous.*;

public class CommandParser {
	
	private ActionSet actionSet;
	private String command;
	
	public static void main(String ... args)
	{
		Scanner kb = new Scanner(System.in);
		System.out.println("Command--> ");
		String s = kb.nextLine();
	
		//interpret(s);
	
	}//end of main

	protected void CommandParser(ActionSet aS, String cmd)
	{
		this.actionSet = aS;
		this.command = cmd;
		
	}
	
	public void interpret(String str)
	{
		if(command.substring(0,6).equals("DEFINE"))
		{
			defineString(command.substring(7));
		}
		
		else if(command.substring(0,6).equals("CREATE"))
		{
			createString(command.substring(7));
		}
		
		else if(command.substring(0,8).equals("POPULATE"))
		{
			populateString(command.substring(9));
		}
		
		else if(command.substring(0,2).equals("DO"))
		{
			doString(command.substring(3));
		}
		
		if(command.substring(0,3).equals("@DO"))
		{
			ATdoString(command.substring(4));
		}
		
		else if(command.substring(0,3).equals("SET"))
		{
			setString(command.substring(4));
		}
		
		else if(command.substring(0,3).equals("GET"))
		{
			getString(command.substring(4));
		}
		
		else if(command.substring(0,8).equals("UNCREATE"))
		{
			uncreateString(command.substring(9));
		}
		
		else if(command.substring(0,8).equals("DESCRIBE"))
		{
			describeString(command.substring(9));
		}
		
		else if(command.substring(0,4).equals("LIST"))
		{
			//call list agents
		}
		
		else if(command.substring(0,6).equals("COMMIT"))
		{
			//commitString(command.substring(7));
		}

		else if(command.substring(0,6).equals("@CLOCK"))
		{
			//send for rate/pause/resume...
			if(command.length()>6)
			{
				clockString(command.substring(7));
			}
			//string is just clock
			else
			{
				
			}
		}
		
		else if(command.substring(0,4).equals("@RUN"))
		{
			runString(command.substring(5));
		}
		
		else if(command.substring(0,5).equals("@EXIT"))
		{
			//EXIT COMMAND
		}
		
		else if(command.substring(0,5).equals("@WAIT"))
		{
			waitString(command.substring(6));
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
		array = eraseSpace(array);
		
		try
		{
			if(array[0].equals("TRAP"))
			{
				//parse through string and construct list
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
	
				/*Identifier [] trapIdent = define_Trap(commandLine);
				CommandCreationalDefineTrap ccDT = new CommandCreationalDefineTrap(trapIdent[0],trapIdent[1]);
				getActionCreationalDefine(CCDT);*/
			}//end of TRAP
			
			else if(array[0].equals("CATAPULT"))
			{
				//parse through string and construct list
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
				
				//make object
				
				//submit
			}
			
			else if(array[0].equals("OLS_XMT"))
			{
				//parse through string and construct list
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
				//parse through string and construct list
				int counter = 1;
				counter =easyParse(commandLine,array,"DIAMETER",counter);
				
				//add the last value
				String diameter = array[counter];
				commandLine.add(diameter);
			}
			
			else if(array[0].equals("CARRIER"))
			{
				//parse through string and construct list
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
				//parse through string and construct list
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
				
				//parse through string and construct list
				String delta = array[counter];
				commandLine.add(delta);
			}
			
			else if(array[0].equals("TANKER"))
			{
				//parse through string and construct list
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
					//parse through string and construct list
					int counter = 2;
					counter =easyParse(commandLine,array,"LENGTH",counter);
					counter =easyParse(commandLine,array,"DIAMETER",counter);
					counter =easyParse(commandLine,array,"FLOW",counter);
					
					//add the last value
					String flow = array[counter];
					commandLine.add(flow);
					
					//send male boom object
				}
				
				else
				{
					//parse through string and construct list
					int counter = 2;
					counter =easyParse(commandLine,array,"LENGTH",counter);
					counter =easyParse(commandLine,array,"DIAMETER",counter);
					counter =easyParse(commandLine,array,"ELEVATION",counter);
					counter =easyParse(commandLine,array,"FLOW",counter);
					
					//add the last value
					String flow = array[counter];
					commandLine.add(flow);
					
					//send female boom object
				}
				
			}
			
			else if(array[0].equals("TAILHOOK"))
			{
				//parse through string and construct list
				int counter = 1;
				counter =easyParse(commandLine,array,"TIME",counter);
				
				//add the last value
				String time = array[counter];
				commandLine.add(time);
			}
			
			else if(array[0].equals("BARRIER"))
			{
				//parse through string and construct list
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
				//parse through string and construct list
				int counter = 1;
				counter =easyParse(commandLine,array,"AMOUNT",counter);
				
				//add the last value
				String amount = array[counter];
				commandLine.add(amount);
			}
		}
		
		catch(Exception e)
		{
			System.out.println(e);
		}
	}//end of DEFINE'S
	
	protected static void createString(String str)
	{
		List<List<String>> lists = new ArrayList<List<String>>();
		List<String> decoy = new ArrayList<String>();
		decoy.add("NOTHING");
		
		//set decoys so indexes can be used
		lists.add(decoy);
		lists.add(decoy);
		lists.add(decoy);
		lists.add(decoy);
		
		List<String> commandLine = new ArrayList<String>();
		String [] array=(str.split(" "));
		array = eraseSpace(array);
		
		if(array[0].equals("CARRIER"))
		{
			//parse through string and construct list
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
			//parse through string and construct list
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			counter =easyParse(commandLine,array,"WITH",counter);
			counter =easyParse(commandLine,array,"BOOM",(counter+1));
			counter =easyParse(commandLine,array,"TAILHOOK",counter);
			//add the last value
			String tailhook = array[counter];
			commandLine.add(tailhook);
			counter++;
			
			//add commons to index 0
			lists.set(0,commandLine);
			
			if(array.length-1>counter)
			{
				if(array[counter].equals("TANKS"))
				{
					List<String> tank = new ArrayList<String>();
					counter++;
					//add all tanks
					while((array.length>counter) && (!array[counter].equals("OVERRIDING") && !array[counter].equals("AT")))
					{
						String tanks = array[counter];
						tank.add(tanks);
						counter++;
					}
					//add tanks to index 1
					lists.set(1,tank);
				}
				
				if((array.length>counter) && array[counter].equals("OVERRIDING"))
				{
					List<String> over = new ArrayList<String>();
					counter++;
					//get first aid
					counter = easyParse(over,array,"WITH",counter);
					//add all OVERRIDES
					while((array.length>counter) && !array[counter].equals("AT"))
					{
						String withs = array[counter];
						
						over.add(withs);
						counter++;
						//increase counter to the next index if it equals "WITH"
						if(array.length>counter && array[counter].equals("WITH"))
							counter++;
					}
					
					//add override to index 2
					lists.set(2,over);
				}
				
				if((array.length>counter) && array[counter].equals("AT"))
				{
					//parse through string and construct list
					List<String> coord = new ArrayList<String>();
					counter = easyParse(coord,array,"ALTITUDE",(counter+2));
					counter = easyParse(coord,array,"HEADING",counter);
					counter = easyParse(coord,array,"SPEED",counter);
					String speed = array[counter];
					counter++;
					coord.add(speed);
					
					//set at coordinates to index 3
					lists.set(3,coord);
				}
						
			}
		}
		
		else if(array[0].equals("TANKER"))
		{
			//parse through string and construct list
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
			//parse through string and construct list
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			
			//add the last value
			String tid = array[counter];
			commandLine.add(tid);
		}
		
		else if(array[0].equals("BARRIER"))
		{
			//parse through string and construct list
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			
			//add the last value
			String tid = array[counter];
			commandLine.add(tid);
		}
		
		else if(array[0].equals("AUX_TANK"))
		{
			//parse through string and construct list
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			
			//add the last value
			String tid = array[counter];
			commandLine.add(tid);
		}
		
		else if(array[0].equals("CATAPULT"))
		{
			//parse through string and construct list
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			
			//add the last value
			String tid = array[counter];
			commandLine.add(tid);
		}
		
		else if(array[0].equals("OLS_XMT"))
		{
			//parse through string and construct list
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			
			//add the last value
			String tid = array[counter];
			commandLine.add(tid);
		}
		
		else if(array[0].equals("OLS_RVC"))
		{
			//parse through string and construct list
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			
			//add the last value
			String tid = array[counter];
			commandLine.add(tid);
		}
		
		else if(array[0].equals("BOOM"))
		{
			//parse through string and construct list
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			
			//add the last value
			String tid = array[counter];
			commandLine.add(tid);
		}
		
		else if(array[0].equals("TAILHOOK"))
		{
			//parse through string and construct list
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			
			//add the last value
			String tid = array[counter];
			commandLine.add(tid);
		}
		System.out.println(commandLine.size());
		for(int x=0;x<lists.size();x++)
		{
			for(int y=0;y<lists.get(x).size();y++)
			{
				System.out.println(lists.get(x).get(y));

			}
		}
	}//end of CREATE'S
	
	protected static void populateString(String str)
	{
		List<String> commandLine = new ArrayList<String>();
		String [] array=(str.split(" "));
		array = eraseSpace(array);
		
		if(array[0].equals("CARRIER"))
		{
			//parse through string and construct list
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
			//parse through string and construct list

			int counter = 2;
			
			//add all agent id's to be added
			while(array.length>counter)
			{
				String tid = array[counter];
				commandLine.add(tid);
				counter++;
			}
		}
		
	}//end of populateString
	
	protected static void doString(String str)
	{
		List<String> commandLine = new ArrayList<String>();
		String [] array=(str.split(" "));
		array = eraseSpace(array);
		int counter=0;
		
		//get aid
		commandLine.add(array[0]);
		counter++;
		
		if(array[counter].equals("ASK"))
		{
			//ask all
			counter++;
			commandLine.add(array[counter]);
			
		}
		
		else if(array[counter].equals("POSITION"))
		{
			//ask POSITION
		}
		
		else if(array[counter].equals("BARRIER"))
		{
			//ask BARRIER
			counter++;
			commandLine.add(array[counter]);
		}
		
		else if(array[counter].equals("CATAPULT"))
		{
			//ask 
			counter+=4;
			commandLine.add(array[counter]);
		}
		
		else if(array[counter].equals("SET"))
		{
			counter++;
			if(array[counter].equals("SPEED"))
			{
				//ask 
				counter++;
				commandLine.add(array[counter]);
			}
			
			else if(array[counter].equals("ALTITUDE"))
			{
				counter++;
				commandLine.add(array[counter]);
			}
			
			else if(array[counter].equals("HEADING"))
			{
				counter++;
				commandLine.add(array[counter]);
				commandLine.add(array[(counter+1)]);
			}

		}//end of SET
		
		else if(array[counter].equals("TAILHOOK"))
		{
			//ask 
			counter++;
			commandLine.add(array[counter]);
		}
		
		else if(array[counter].equals("CATAPULT"))
		{
			//ask OLS
		}
		
		else if(array[counter].equals("BOOM"))
		{
			//ask BOOM
			counter++;
			commandLine.add(array[counter]);
		}
		
		else if(array[counter].equals("TRANSFER"))
		{
			//ask TRANSFER
			counter++;
			commandLine.add(array[counter]);
		}

	}//end of doString
	
	protected static void ATdoString(String str)
	{
		List<List<String>> lists = new ArrayList<List<String>>();
		List<String> decoy = new ArrayList<String>();
		decoy.add("NOTHING");
		lists.add(decoy);
		lists.add(decoy);
		lists.add(decoy);
		lists.add(decoy);
		lists.add(decoy);
		List<String> commandLine = new ArrayList<String>();
		String [] array=(str.split(" "));
		array = eraseSpace(array);
		int counter=0;
		
		//get aid
		commandLine.add(array[0]);
		lists.set(0,commandLine);
		counter++;
		
		if(array[counter].equals("FORCE"))
		{
			//NOT FOR ALL
			if(array.length==4)
			{
				counter++;
				if(array[counter].equals("COORDINATES"))
				{
					counter++;
					commandLine.add(array[counter]);
				}
				
				else if(array[counter].equals("ALTITUDE"))
				{
					counter++;
					commandLine.add(array[counter]);
				}
				
				else if(array[counter].equals("HEADING"))
				{
					counter++;
					commandLine.add(array[counter]);
				}
				
				else if(array[counter].equals("SPEED"))
				{
					counter++;
					commandLine.add(array[counter]);
				}
			}
			
			//for ALL
			else
			{
				counter++;
				if(array[counter].equals("COORDINATES"))
				{
					List<String> coord = new ArrayList<String>();

					counter++;
					coord.add(array[counter]);
					lists.set(1,coord);
					counter++;
				}
				
				if(array[counter].equals("ALTITUDE"))
				{
					List<String> alt = new ArrayList<String>();

					counter++;
					alt.add(array[counter]);
					lists.set(2,alt);
					counter++;
				}
				
				if(array[counter].equals("HEADING"))
				{
					List<String> heading = new ArrayList<String>();

					counter++;
					heading.add(array[counter]);
					lists.set(3,heading);
					counter++;
				}
				
				if(array[counter].equals("SPEED"))
				{
					List<String> speed = new ArrayList<String>();

					counter++;
					speed.add(array[counter]);
					lists.set(4,speed);
					counter++;
				}
				//send lists to be constructed
				for(int x = 0;x<lists.size();x++)
				{
					for(int y=0;y<lists.get(x).size();y++)
					{
						System.out.println(lists.get(x).get(y));
					}
				}
			}
		}
	}//end of ATdoSting
	
	protected static void getString(String str)
	{
		//get windConditions
	}//end of getString
	
	protected static void setString(String str)
	{
		//set windConditions
		String [] array = str.split(" ");
		int counter=1;
		
		if(array[counter].equals("DIRECTION"))
		{
			//SEND DIRECTION
		}
		
		else if(array[counter].equals("SPEED"))
		{
			//SEND SPEED
		}
	}//end of getString
	
	public static void uncreateString(String str)
	{
		//send tid which is remaining string
		//uncreate(str);
	}//end of uncreateString
	
	public static void describeString(String str)
	{
		//send tid which is remaining string
		//describe(str);
	}//end of describeString
	
	public static void clockString(String str)
	{
		String [] array=(str.split(" "));
		int counter=0;
		
		if(array[0].equals("RESUME"))
		{
			//CommandDoSetClockRunning cmd = new CommandDoSetClockRunning();
			//this.actionset
		}
			
		else if(array[0].equals("PAUSE"))
		{
			
		}
		
		else if(array[0].equals("UPDATE"))
		{
			
		}
		
		else
		{
			//set clock rate
		}
	}//end of clockString
	
	public static void runString(String str)
	{
		//send tid which is remaining string
		//describe(str);
	}//end of runString
	
	public static void waitString(String str)
	{
		//send tid which is remaining string
		//describe(str);
	}//end of waitString
	
	protected static int easyParse(List<String> commandLine,String [] array, String badWord,int counter)
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
	}//easyParse
	
	protected static String [] eraseSpace(String [] array)
	{
		List<String> list = new ArrayList<String>(Arrays.asList(array));
		
		for(int x=0;x<list.size();x++)
		{
			if(list.get(x).length()==0)
				list.remove(x);
		}
		
		String [] a = list.toArray(new String[0]);
		return a;
	}//eraseSpace
}//end of class
