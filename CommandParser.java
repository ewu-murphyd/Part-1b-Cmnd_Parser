package acg.project.cli.parser;

import java.io.*;
import java.util.*;

import acg.architecture.datatype.Identifier;
//import acg.architecture.view.glyph.loader.*;
import acg.project.action.ActionSet;
import acg.project.action.command.behavioral.*;
import acg.project.action.command.structural.*;
import acg.project.action.command.creational.create.*;
import acg.project.action.command.creational.define.*;
import acg.project.action.command.miscellaneous.*;
import acg.project.map.MapTemplate;

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
		if(this.command.substring(0,6).equals("DEFINE"))
		{
			defineString(this.command.substring(7));
		}
		
		else if(this.command.substring(0,6).equals("CREATE"))
		{
			createString(this.command.substring(7));
		}
		
		else if(this.command.substring(0,8).equals("POPULATE"))
		{
			populateString(this.command.substring(9));
		}
		
		else if(this.command.substring(0,2).equals("DO"))
		{
			doString(this.command.substring(3));
		}
		
		if(this.command.substring(0,3).equals("@DO"))
		{
			ATdoString(this.command.substring(4));
		}
		
		else if(this.command.substring(0,3).equals("SET"))
		{
			setString(this.command.substring(4));
		}
		
		else if(this.command.substring(0,3).equals("GET"))
		{
			getString(this.command.substring(4));
		}
		
		else if(this.command.substring(0,8).equals("UNCREATE"))
		{
			uncreateString(this.command.substring(9));
		}
		
		else if(this.command.substring(0,8).equals("DESCRIBE"))
		{
			describeString(this.command.substring(9));
		}
		
		else if(this.command.substring(0,4).equals("LIST"))
		{
			//call list agents
		}
		
		else if(this.command.substring(0,6).equals("COMMIT"))
		{
			//commitString(command.substring(7));
		}

		else if(this.command.substring(0,6).equals("@CLOCK"))
		{
			//send for rate/pause/resume...
			if(this.command.length()>6)
			{
				clockString(this.command.substring(7));
			}
			//string is just clock
			else
			{
				
			}
		}
		
		else if(this.command.substring(0,4).equals("@RUN"))
		{
			runString(this.command.substring(5));
		}
		
		else if(this.command.substring(0,5).equals("@EXIT"))
		{
			//EXIT COMMAND
		}
		
		else if(this.command.substring(0,5).equals("@WAIT"))
		{
			waitString(this.command.substring(6));
		}
	
		/**
		 * need miscellaneous cmnds
		 * and small cmnds implemented
		 **/
	}
	
	protected void defineString(String str)
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
	
				CommandCreationalDefineTrap ccDT = define_Trap(commandLine);
				this.actionSet.getActionCreationalDefine().submit(ccDT);
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
				CommandCreationalDefineCatapult ccDC = define_Catapult(commandLine);
				//submit
				this.actionSet.getActionCreationalDefine().submit(ccDC);
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
				
				CommandCreationalDefineOLSTransmitter ccDC = define_OLS_XMT(commandLine);
				//submit
				this.actionSet.getActionCreationalDefine().submit(ccDC);
			}
			
			else if(array[0].equals("OLS_RCV"))
			{
				//parse through string and construct list
				int counter = 1;
				counter =easyParse(commandLine,array,"DIAMETER",counter);
				
				//add the last value
				String diameter = array[counter];
				commandLine.add(diameter);
				
				CommandCreationalDefineOLSReceiver ccDC = define_OLS_RCV(commandLine);
				//submit
				this.actionSet.getActionCreationalDefine().submit(ccDC);
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
				
				CommandCreationalDefineCarrier ccDC = define_Carrier(commandLine);
				//submit
				this.actionSet.getActionCreationalDefine().submit(ccDC);
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
				
				CommandCreationalDefineFighter ccDC = define_Fighter(commandLine);
				//submit
				this.actionSet.getActionCreationalDefine().submit(ccDC);
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
				
				CommandCreationalDefineTanker ccDC = define_Tanker(commandLine);
				//submit
				this.actionSet.getActionCreationalDefine().submit(ccDC);
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
					CommandCreationalDefineBoomMale ccDC = define_Boom_Male(commandLine);
					//submit
					this.actionSet.getActionCreationalDefine().submit(ccDC);
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
					CommandCreationalDefineBoomFemale ccDC = define_Boom_Female(commandLine);
					//submit
					this.actionSet.getActionCreationalDefine().submit(ccDC);
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
				
				CommandCreationalDefineTailhook ccDC = define_Tailhook(commandLine);
				//submit
				this.actionSet.getActionCreationalDefine().submit(ccDC);
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
				
				CommandCreationalDefineBarrier ccDC = define_Barrier(commandLine);
				//submit
				this.actionSet.getActionCreationalDefine().submit(ccDC);
			}
			
			else if(array[0].equals("AUX_TANK"))
			{
				//parse through string and construct list
				int counter = 1;
				counter =easyParse(commandLine,array,"AMOUNT",counter);
				
				//add the last value
				String amount = array[counter];
				commandLine.add(amount);
				
				CommandCreationalDefineAuxiliaryTank ccDC = define_Aux_Tank(commandLine);
				//submit
				this.actionSet.getActionCreationalDefine().submit(ccDC);
			}
		}
		
		catch(Exception e)
		{
			throw e;
		}
	}//end of DEFINE'S
	
	protected void createString(String str)
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
			
			CommandCreationalCreateCarrier ccCC = create_Carrier(commandLine);
			//send object
			this.actionSet.getActionCreationalCreate().submit(ccCC);
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
			for(int i = 0;i<4;i++)
			{
				lists.add(i,new ArrayList<String>(0));
			}
			String tailhook = array[counter];
			commandLine.add(0,tailhook);
			counter++;

			lists.set(0,commandLine);
			
			while(array.length-1>counter)
			{
				if(array[counter].equals("TANKS"))
				{
					int i = counter;
					while(!array[i].equals("OVERRIDING")||!array[i].equals("AT"))
					{
						if(i == array.length-i)
						{break;}
						i++;
					}
					List<String> tank = new ArrayList<String>();
					counter++;
					for(int x =counter ;x<i;x++)
					{
						String tanks = array[x];
						tank.add(tanks);	
					}
					counter++;
					lists.set(1,tank);
				}

				else if(array[counter].equals("OVERRIDING"))
				{
					int i = counter;
					while(!array[i].equals("AT"))
					{
						if(i == array.length-1)
						{break;}
						i++;
					}
					List<String> tank = new ArrayList<String>();
					counter++;
					for(int x =counter ;x<i;)
					{
					counter = easyParse(tank,array,"WITH",counter);
					String with = array[counter];
					tank.add(with);
					counter++;
					x=counter;
					}
					lists.set(2,tank);
				}

				else if(array[counter].equals("COORDINATES"))
				{
					List<String> coord = new ArrayList<String>();
					counter++;
					counter = easyParse(coord,array,"ALTITUDE",(counter));
					counter = easyParse(coord,array,"HEADING",counter);
					counter = easyParse(coord,array,"SPEED",counter);
					String speed = array[counter];
					counter++;
					coord.add(speed);
					lists.set(3,coord);
				}
				else{counter++;}
			}
		
			
			CommandCreationalCreateFighter ccCC = create_Fighter(lists);
			//send object
			this.actionSet.getActionCreationalCreate().submit(ccCC);
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
			
			CommandCreationalCreateTanker ccCC = create_Tanker(commandLine);
			//send object
			this.actionSet.getActionCreationalCreate().submit(ccCC);
		}
		
		else if(array[0].equals("TRAP"))
		{
			//parse through string and construct list
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			
			//add the last value
			String tid = array[counter];
			commandLine.add(tid);
			
			CommandCreationalCreateTrap ccCC = create_Tank(commandLine);
			//send object
			this.actionSet.getActionCreationalCreate().submit(ccCC);
		}
		
		else if(array[0].equals("BARRIER"))
		{
			//parse through string and construct list
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			
			//add the last value
			String tid = array[counter];
			commandLine.add(tid);
			
			CommandCreationalCreateBarrier ccCC = create_Barrier(commandLine);
			//send object
			this.actionSet.getActionCreationalCreate().submit(ccCC);
		}
		
		else if(array[0].equals("AUX_TANK"))
		{
			//parse through string and construct list
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			
			//add the last value
			String tid = array[counter];
			commandLine.add(tid);
			
			CommandCreationalCreateAuxiliaryTank ccCC = create_Aux_Tank(commandLine);
			//send object
			this.actionSet.getActionCreationalCreate().submit(ccCC);
		}
		
		else if(array[0].equals("CATAPULT"))
		{
			//parse through string and construct list
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			
			//add the last value
			String tid = array[counter];
			commandLine.add(tid);
			
			CommandCreationalCreateCatapult ccCC = create_Catapult(commandLine);
			//send object
			this.actionSet.getActionCreationalCreate().submit(ccCC);
		}
		
		else if(array[0].equals("OLS_XMT"))
		{
			//parse through string and construct list
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			
			//add the last value
			String tid = array[counter];
			commandLine.add(tid);
			
			CommandCreationalCreateOLSTransmitter ccCC = create_Ols_Xmt(commandLine);
			//send object
			this.actionSet.getActionCreationalCreate().submit(ccCC);
		}
		
		else if(array[0].equals("OLS_RVC"))
		{
			//parse through string and construct list
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			
			//add the last value
			String tid = array[counter];
			commandLine.add(tid);
			
			CommandCreationalCreateOLSReceiver ccCC = create_Ols_Rcv(commandLine);
			//send object
			this.actionSet.getActionCreationalCreate().submit(ccCC);
		}
		
		else if(array[0].equals("BOOM"))
		{
			//parse through string and construct list
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			
			//add the last value
			String tid = array[counter];
			commandLine.add(tid);
			//?NEED MALE AND FEMALE
			MapTemplate mP = this.actionSet.getMapTemplates();
			Identifier id = new Identifier(tid);
			
			A_CommandCreationalDefineBoom temp;
			try{
				temp = (A_CommandCreationalDefineBoom) mP.getCommand(id);
			}
			
			catch(Exception e)
			{
				throw e;
			}
			if(temp.isMale())
			{
				CommandCreationalCreateBoomMale ccCC = create_Boom_Male(commandLine);
				this.actionSet.getActionCreationalCreate().submit(ccCC);

			}
			else
			{
				CommandCreationalCreateBoomFemale ccCC = create_Boom_Female(commandLine);
				this.actionSet.getActionCreationalCreate().submit(ccCC);

			}

		}
		
		else if(array[0].equals("TAILHOOK"))
		{
			//parse through string and construct list
			int counter = 1;
			counter =easyParse(commandLine,array,"FROM",counter);
			
			//add the last value
			String tid = array[counter];
			commandLine.add(tid);
			
			CommandCreationalCreateTailhook ccCC = create_Tailhook(commandLine);
			//send object
			this.actionSet.getActionCreationalCreate().submit(ccCC);
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
	
	protected void populateString(String str)
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
			
			CommandStructuralPopulateCarrier csPC = populate_Carrier(commandLine);
			this.actionSet.getActionStructural().submit(csPC);
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
			
			CommandStructuralPopulateWorld csPC = populate_World(commandLine);
			this.actionSet.getActionStructural().submit(csPC);
		}
		
	}//end of populateString
	
	protected void doString(String str)
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
			
			CommandBehavioralDoAsk cbDA = do_Ask(commandLine);
			this.actionSet.getActionBehavioral().submit(cbDA);
		}
		
		else if(array[counter].equals("POSITION"))
		{
			//ask POSITION
			CommandBehavioralDoPosition cbDA = do_Position(commandLine);
			this.actionSet.getActionBehavioral().submit(cbDA);
		}
		
		else if(array[counter].equals("BARRIER"))
		{
			//ask BARRIER
			counter++;
			commandLine.add(array[counter]);
			
			CommandBehavioralDoBarrier cbDA = do_Barrier(commandLine);
			this.actionSet.getActionBehavioral().submit(cbDA);
		}
		
		else if(array[counter].equals("CATAPULT"))
		{
			//ask 
			counter+=4;
			commandLine.add(array[counter]);
			
			CommandBehavioralDoCatapult cbDA = do_Catapult(commandLine);
			this.actionSet.getActionBehavioral().submit(cbDA);
		}
		
		else if(array[counter].equals("SET"))
		{
			counter++;
			if(array[counter].equals("SPEED"))
			{
				//ask 
				counter++;
				commandLine.add(array[counter]);
				
				CommandBehavioralDoSetSpeed cbDA = do_Set_Speed(commandLine);
				this.actionSet.getActionBehavioral().submit(cbDA);
			}
			
			else if(array[counter].equals("ALTITUDE"))
			{
				counter++;
				commandLine.add(array[counter]);
				
				CommandBehavioralDoSetAltitude cbDA = do_Set_Altitude(commandLine);
				this.actionSet.getActionBehavioral().submit(cbDA);
			}
			
			else if(array[counter].equals("HEADING"))
			{
				counter++;
				commandLine.add(array[counter]);
				commandLine.add(array[(counter+1)]);
				
				CommandBehavioralDoSetHeading cbDA = do_Set_Heading(commandLine);
				this.actionSet.getActionBehavioral().submit(cbDA);
			}

		}//end of SET
		
		else if(array[counter].equals("TAILHOOK"))
		{
			//ask 
			counter++;
			commandLine.add(array[counter]);
			
			CommandBehavioralDoTailhook cbDA = do_Tailhook(commandLine);
			this.actionSet.getActionBehavioral().submit(cbDA);
		}
		
		else if(array[counter].equals("CATAPULT"))
		{
			//ask OLS
			CommandBehavioralDoCatapult cbDA = do_Catapult(commandLine);
			this.actionSet.getActionBehavioral().submit(cbDA);
		}
		
		else if(array[counter].equals("BOOM"))
		{
			//ask BOOM
			counter++;
			commandLine.add(array[counter]);
			
			CommandBehavioralDoBoom cbDA = do_Boom(commandLine);
			this.actionSet.getActionBehavioral().submit(cbDA);
		}
		
		else if(array[counter].equals("TRANSFER"))
		{
			//ask TRANSFER
			counter++;
			commandLine.add(array[counter]);
			
			CommandBehavioralDoTransfer cbDA = do_Transfer(commandLine);
			this.actionSet.getActionBehavioral().submit(cbDA);
		}
		
		else if(array[counter].equals("CAPTURE"))
		{
			
			CommandBehavioralDoCaptureOLS cbDA = do_Capture_Ols(commandLine);
			this.actionSet.getActionBehavioral().submit(cbDA);
		}

	}//end of doString
	
	protected void ATdoString(String str)
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
					
					CommandBehavioralDoForceCoordinates cbDF = do_Force_Coordinates(commandLine);
					this.actionSet.getActionBehavioral().submit(cbDF);
				}
				
				else if(array[counter].equals("ALTITUDE"))
				{
					counter++;
					commandLine.add(array[counter]);
					
					CommandBehavioralDoForceAltitude cbDF = do_Force_Altitude(commandLine);
					this.actionSet.getActionBehavioral().submit(cbDF);
				}
				
				else if(array[counter].equals("HEADING"))
				{
					counter++;
					commandLine.add(array[counter]);
					
					CommandBehavioralDoForceHeading cbDF = do_Force_Heading(commandLine);
					this.actionSet.getActionBehavioral().submit(cbDF);
				}
				
				else if(array[counter].equals("SPEED"))
				{
					counter++;
					commandLine.add(array[counter]);
					
					CommandBehavioralDoForceSpeed cbDF = do_Force_Speed(commandLine);
					this.actionSet.getActionBehavioral().submit(cbDF);
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
				
				CommandBehavioralDoForceAll cbDF = do_Force_All(commandLine);
				this.actionSet.getActionBehavioral().submit(cbDF);
			}
		}
	}//end of ATdoSting
	
	protected void getString(String str)
	{
		//List<String> commandLine = new ArrayList<String>();
		//String [] array=(str.split(" "));
		
		//get windConditions
		CommandBehavioralGetWindConditions cbDF = get_Wind();
		this.actionSet.getActionBehavioral().submit(cbDF);
	}//end of getString
	
	protected void setString(String str)
	{
		//set windConditions
		String [] array = str.split(" ");
		int counter=1;
		
		if(array[counter].equals("DIRECTION"))
		{
			//SEND DIRECTION
			CommandBehavioralSetWindDirection cbDF = set_Wind_Direction(commandLine);
			this.actionSet.getActionBehavioral().submit(cbDF);
		}
		
		else if(array[counter].equals("SPEED"))
		{
			//SEND SPEED
			CommandBehavioralSetWindSpeed cbDF = set_Wind_Speed(commandLine);
			this.actionSet.getActionBehavioral().submit(cbDF);
		}
	}//end of getString
	
	public void uncreateString(String str)
	{
		//CHECK IF PART OF AN AGENT
		
		//IF NOT PART THEN UNCREATE
		//uncreate(str);
	}//end of uncreateString
	
	public void describeString(String str)
	{
		//send tid which is remaining string
		//describe(str);
	}//end of describeString
	
	public void clockString(String str)
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
	
	public void runString(String str)
	{
		//send tid which is remaining string
		//describe(str);
	}//end of runString
	
	public void waitString(String str)
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
