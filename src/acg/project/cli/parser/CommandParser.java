package acg.project.cli.parser;

import java.util.*;

import acg.architecture.datatype.Acceleration;
import acg.architecture.datatype.Altitude;
import acg.architecture.datatype.AngleNavigational;
import acg.architecture.datatype.AttitudePitch;
import acg.architecture.datatype.CoordinateCartesianRelative;
import acg.architecture.datatype.CoordinateWorld;
import acg.architecture.datatype.Distance;
import acg.architecture.datatype.Flow;
import acg.architecture.datatype.Identifier;
import acg.architecture.datatype.Latitude;
import acg.architecture.datatype.Longitude;
import acg.architecture.datatype.Percent;
import acg.architecture.datatype.Rate;
import acg.architecture.datatype.Speed;
import acg.architecture.datatype.Time;
import acg.architecture.datatype.Weight;
//import acg.architecture.view.glyph.loader.*;
import acg.project.action.ActionSet;
import acg.project.action.command.ParameterAssignment;
import acg.project.action.command.behavioral.*;
import acg.project.action.command.structural.*;
import acg.project.action.command.creational.create.*;
import acg.project.action.command.creational.define.*;
import acg.project.action.command.miscellaneous.*;
import acg.project.map.MapTemplate;

public class CommandParser {
	protected int commit = 0;
	private ActionSet actionSet;
	private String command;
	Methods method;

	protected CommandParser(ActionSet aS, String cmd)
	{
		this.actionSet = aS;
		this.command = cmd;
	}
	
	public void interpret()
	{
		try
		{
			System.out.println(this.command);
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
		
		else if(this.command.substring(0,3).equals("@DO"))
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
			String cmnd = this.command.substring(5);
			String [] array = cmnd.split(" ");
			if(array[0].equals("AGENTS"))
			{
				CommandCreationalListAgents ccLA = List_Agents();
				this.actionSet.getActionCreationalCreate().submit(ccLA);
			}
			
			else if(array[0].equals("TEMPLATES"))
			{
				CommandCreationalListTemplates ccLT = List_Templates();
				this.actionSet.getActionCreationalDefine().submit(ccLT);
			}
		}
		
		else if(this.command.substring(0,6).equals("COMMIT"))
		{
			CommandStructuralCommit cSc = Commit();
			this.actionSet.getActionStructural().submit(cSc);
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
				CommandMiscDoShowClock cmSC = new CommandMiscDoShowClock();
				this.actionSet.getActionMisc().submit(cmSC);
			}
		}
		
		else if(this.command.substring(0,4).equals("@RUN"))
		{
			runString(this.command.substring(5));
		}
		
		else if(this.command.substring(0,5).equals("@EXIT"))
		{
			//EXIT COMMAND
			CommandMiscDoExit cmDE = new CommandMiscDoExit();
			this.actionSet.getActionMisc().submit(cmDE);
		}
		
		else if(this.command.substring(0,5).equals("@WAIT"))
		{
			waitString(this.command.substring(6));
		}
		
		}
		
		catch(Exception e)
		{
			throw e;
		}

	}
	
	protected void defineString(String str)
	{
		List<String> commandLine = new ArrayList<String>();
		String [] array=(str.split(" "));
		//array = eraseSpace(array);
		
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
	
				CommandCreationalDefineTrap ccDT = Define_Trap(commandLine);
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
				CommandCreationalDefineCatapult ccDC = Define_Catapult(commandLine);
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
				
				CommandCreationalDefineOLSTransmitter ccDC = Define_OLS_XMT(commandLine);
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
				
				CommandCreationalDefineOLSReceiver ccDC = Define_OLS_RCV(commandLine);
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
				
				CommandCreationalDefineCarrier ccDC = Define_Carrier(commandLine);
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
				
				CommandCreationalDefineFighter ccDC = Define_Fighter(commandLine);
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
				counter =easyParse(commandLine,array,"TANK",counter);
				
				//add the last value
				String tank = array[counter];
				commandLine.add(tank);
				
				CommandCreationalDefineTanker ccDC = Define_Tanker(commandLine);
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
					CommandCreationalDefineBoomMale ccDC = DEFINE_BOOM_MALE(commandLine);
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
					CommandCreationalDefineBoomFemale ccDC = DEFINE_BOOM_Female(commandLine);
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
				
				CommandCreationalDefineTailhook ccDC = Define_Tailhook(commandLine);
				//submit
				this.actionSet.getActionCreationalDefine().submit(ccDC);
			}
			
			else if(array[0].equals("BARRIER"))
			{
				//parse through string and construct list
				int counter = 1;
				counter =easyParse(commandLine,array,"ORIGIN",counter);
				counter =easyParse(commandLine,array,"AZIMUTH",(counter));
				counter =easyParse(commandLine,array,"WIDTH",(counter));
				counter =easyParse(commandLine,array,"TIME",(counter));
				
				//add the last value
				String time = array[counter];
				commandLine.add(time);
				
				CommandCreationalDefineBarrier ccDC = Define_Barrier(commandLine);
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
				
				CommandCreationalDefineAuxiliaryTank ccDC = Define_Aux_Tank(commandLine);
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
		List<String> commandLine = new ArrayList<String>();
		String [] array=(str.split(" "));
		//array = eraseSpace(array);
		
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
			
			CommandCreationalCreateCarrier ccCC = Create_Carrier(commandLine);
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
					while((array.length>i) &&(!array[i].equals("OVERRIDING")&&!array[i].equals("AT")))
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
					if(array[counter].equals("OVERRIDING"))
						counter++;
					counter = easyParse(tank,array,"WITH",counter);
					String with = array[counter];
					with = with.substring(1,with.length()-1);
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
		
			
			CommandCreationalCreateFighter ccCC = Create_Fighter(lists);
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
			
			CommandCreationalCreateTanker ccCC = Create_Tanker(commandLine);
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
			
			CommandCreationalCreateTrap ccCC = Create_Trap(commandLine);
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
			
			CommandCreationalCreateBarrier ccCC = Create_Barrier(commandLine);
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
			
			CommandCreationalCreateAuxiliaryTank ccCC = Create_AUX_Tank(commandLine);
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
			
			CommandCreationalCreateCatapult ccCC = Create_CATAPULT(commandLine);
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
			
			CommandCreationalCreateOLSTransmitter ccCC = Create_OLS_XMT(commandLine);
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
			
			CommandCreationalCreateOLSReceiver ccCC = Create_OLS_RCV(commandLine);
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
				CommandCreationalCreateBoomMale ccCC = Create_Boom_Male(commandLine);
				this.actionSet.getActionCreationalCreate().submit(ccCC);

			}
			else
			{
				CommandCreationalCreateBoomFemale ccCC = Create_Boom_Female(commandLine);
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
			
			CommandCreationalCreateTailhook ccCC = Create_Tailhook(commandLine);
			//send object
			this.actionSet.getActionCreationalCreate().submit(ccCC);
		}

	}//end of CREATE'S
	
	protected void populateString(String str)
	{
		
		List<List<String>> lists = new ArrayList<List<String>>();
		for(int i = 0;i<4;i++)
		{
			lists.add(i,new ArrayList<String>(0));
		}
		List<String> commandLine = new ArrayList<String>();
		String [] array=(str.split(" "));
		int counter=0;
		
		if(array[0].equals("CARRIER"))
		{
			//parse through string and construct list
			counter = 1;
			counter =easyParse(commandLine,array,"WITH",counter);
			lists.set(0,commandLine);
			counter++;
			
			//single fighter
			List<String> fighter = new ArrayList<String>();
			if(array[counter].equals("FIGHTER"))
			{
				String tid = array[counter+1];
				fighter.add(tid);
				lists.set(1,fighter);
			}
			
			//multiple fighters
			else
			{
				counter++;
				while(array.length>counter)
				{
					String tid = array[counter];
					fighter.add(tid);
					counter++;
				}
				lists.set(1, fighter);
			}
			
			//wants list of list
			CommandStructuralPopulateCarrier csPC = Populate_Carrier(lists);
			this.actionSet.getActionStructural().submit(csPC);
		}
		
		else if(array[0].equals("WORLD"))
		{
			//parse through string and construct list

			counter = 2;
			
			//add all agent id's to be added
			while(array.length>counter)
			{
				String tid = array[counter];
				commandLine.add(tid);
				counter++;
			}
			
			CommandStructuralPopulateWorld csPC = Populate_World(commandLine);
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
			
			CommandBehavioralDoAsk cbDA = Do_Ask(commandLine);
			this.actionSet.getActionBehavioral().submit(cbDA);
		}
		
		else if(array[counter].equals("POSITION"))
		{
			//ask POSITION
			CommandBehavioralDoPosition cbDA = Do_Position(commandLine);
			this.actionSet.getActionBehavioral().submit(cbDA);
		}
		
		else if(array[counter].equals("BARRIER"))
		{
			//ask BARRIER
			counter++;
			commandLine.add(array[counter]);
			
			CommandBehavioralDoBarrier cbDA = Do_Barrier(commandLine);
			this.actionSet.getActionBehavioral().submit(cbDA);
		}
		
		else if(array[counter].equals("CATAPULT"))
		{
			//ask 
			counter+=4;
			commandLine.add(array[counter]);
			
			CommandBehavioralDoCatapult cbDA = Do_Catapult(commandLine);
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
				
				CommandBehavioralDoSetSpeed cbDA = Do_Set_Speed(commandLine);
				this.actionSet.getActionBehavioral().submit(cbDA);
			}
			
			else if(array[counter].equals("ALTITUDE"))
			{
				counter++;
				commandLine.add(array[counter]);
				
				CommandBehavioralDoSetAltitude cbDA = Do_Set_Altitude(commandLine);
				this.actionSet.getActionBehavioral().submit(cbDA);
			}
			
			else if(array[counter].equals("HEADING"))
			{
				counter++;
				commandLine.add(array[counter]);
				commandLine.add(array[(counter+1)]);
				
				CommandBehavioralDoSetHeading cbDA = Do_Set_Heading(commandLine);
				this.actionSet.getActionBehavioral().submit(cbDA);
			}

		}//end of SET
		
		else if(array[counter].equals("TAILHOOK"))
		{
			//ask 
			counter++;
			commandLine.add(array[counter]);
			
			CommandBehavioralDoTailhook cbDA = Do_Tailhook(commandLine);
			this.actionSet.getActionBehavioral().submit(cbDA);
		}
		
		else if(array[counter].equals("CATAPULT"))
		{
			//ask OLS
			CommandBehavioralDoCatapult cbDA = Do_Catapult(commandLine);
			this.actionSet.getActionBehavioral().submit(cbDA);
		}
		
		else if(array[counter].equals("BOOM"))
		{
			//ask BOOM
			counter++;
			commandLine.add(array[counter]);
			
			CommandBehavioralDoBoom cbDA = Do_Boom(commandLine);
			this.actionSet.getActionBehavioral().submit(cbDA);
		}
		
		else if(array[counter].equals("TRANSFER"))
		{
			//ask TRANSFER
			counter++;
			commandLine.add(array[counter]);
			
			CommandBehavioralDoTransfer cbDA = Do_Transfer(commandLine);
			this.actionSet.getActionBehavioral().submit(cbDA);
		}
		
		else if(array[counter].equals("CAPTURE"))
		{
			
			CommandBehavioralDoCaptureOLS cbDA = Do_Capture_Ols(commandLine);
			this.actionSet.getActionBehavioral().submit(cbDA);
		}

	}//end of doString
	
	protected void ATdoString(String str)
	{
		List<List<String>> lists = new ArrayList<List<String>>();
		for(int i = 0;i<4;i++)
		{
			lists.add(i,new ArrayList<String>(0));
		}
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
					
					CommandBehavioralDoForceCoordinates cbDF = Do_Force_Coordinates(commandLine);
					this.actionSet.getActionBehavioral().submit(cbDF);
				}
				
				else if(array[counter].equals("ALTITUDE"))
				{
					counter++;
					commandLine.add(array[counter]);
					
					CommandBehavioralDoForceAltitude cbDF = Do_Force_Altitude(commandLine);
					this.actionSet.getActionBehavioral().submit(cbDF);
				}
				
				else if(array[counter].equals("HEADING"))
				{
					counter++;
					commandLine.add(array[counter]);
					
					CommandBehavioralDoForceHeading cbDF = Do_Force_Heading(commandLine);
					this.actionSet.getActionBehavioral().submit(cbDF);
				}
				
				else if(array[counter].equals("SPEED"))
				{
					counter++;
					commandLine.add(array[counter]);
					
					CommandBehavioralDoForceSpeed cbDF = Do_Force_Speed(commandLine);
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
				
				//want list of list
				CommandBehavioralDoForceAll cbDF = Do_Force_All(lists);
				this.actionSet.getActionBehavioral().submit(cbDF);
			}
		}
	}//end of ATdoSting
	
	protected void getString(String str)
	{
		//List<String> commandLine = new ArrayList<String>();
		//String [] array=(str.split(" "));
		
		//get windConditions
		CommandBehavioralGetWindConditions cbDF = Get_Wind_Conditions();
		this.actionSet.getActionBehavioral().submit(cbDF);
	}//end of getString
	
	protected void setString(String str)
	{
		//set windConditions
		List<String> commandLine = new ArrayList<String>();
		String [] array = str.split(" ");
		int counter=1;
		
		if(array[counter].equals("DIRECTION"))
		{
			commandLine.add(array[counter+1]);
			//SEND DIRECTION
			CommandBehavioralSetWindDirection cbDF = Set_Wind_Direction(commandLine);
			this.actionSet.getActionBehavioral().submit(cbDF);
		}
		
		else if(array[counter].equals("SPEED"))
		{
			commandLine.add(array[counter+1]);
			//SEND SPEED
			CommandBehavioralSetWindSpeed cbDF = Set_Wind_Speed(commandLine);
			this.actionSet.getActionBehavioral().submit(cbDF);
		}
	}//end of getString
	
	public void uncreateString(String str)
	{
		List<String> commandLine = new ArrayList<String>();
		String [] array = str.split(" ");
		
		commandLine.add(array[0]);
		CommandCreationalUncreate ccU = Uncreate(commandLine);
		this.actionSet.getActionCreationalCreate().submit(ccU);
	}//end of uncreateString
	
	public void describeString(String str)
	{
		List<String> commandLine = new ArrayList<String>();
		String [] array = str.split(" ");
		commandLine.add(array[0]);
	
		//send tid which is remaining string
		CommandCreationalDescribe ccD = Discribe(commandLine);
		this.actionSet.getActionCreationalCreate().submit(ccD);
	}//end of describeString
	
	public void clockString(String str)
	{
		List<String> commandLine = new ArrayList<String>();
		String [] array = str.split(" ");
		commandLine.add(array[0]);
		
		if(array[0].equals("RESUME") || array[0].equals("PAUSE"))
		{
			CommandMiscDoSetClockRunning cmd = Clock_Resume(commandLine);
			this.actionSet.getActionMisc().submit(cmd);
		}
		
		else if(array[0].equals("UPDATE"))
		{
			//not sure which one
			CommandMiscDoClockUpdate cmSC = Clock_Update();
			this.actionSet.getActionMisc().submit(cmSC);
		}
		
		else
		{
			//set clock rate
			CommandMiscDoSetClockRate cmSC = Clock(commandLine);
			this.actionSet.getActionMisc().submit(cmSC);
		}
	}//end of clockString
	
	public void runString(String str)
	{
		try
		{
			List<String> commandLine = new ArrayList<String>();
			String [] array = str.split(" ");
			commandLine.add(array[0]);
			
			CommandMiscDoRun cmDR = Run(commandLine);
			this.actionSet.getActionMisc().submit(cmDR);
		}
		
		catch(Exception e)
		{
			throw e;
		}
	}//end of runString
	
	public void waitString(String str)
	{
		List<String> commandLine = new ArrayList<String>();
		String [] array = str.split(" ");
		commandLine.add(array[0]);
		//can't happen until after commit. commit is a global var keeping track of whether a commit was issued
		if(commit>0)
		{
			CommandMiscDoWait cmDW = Wait(commandLine);
			this.actionSet.getActionMisc().submit(cmDW);
		}
		
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
//++++++++++++++++++++++++++++++++++++++++++++++++++//
	
	public CommandCreationalDefineTrap Define_Trap(List<String> names)
	{
		return new CommandCreationalDefineTrap(id(names.get(0)),CCR(names.get(1)),getAN(names.get(2)),getDist(names.get(3)),
												weight(names.get(4)),GS(names.get(5)),percent(names.get(6)));
	}
	public CommandCreationalDefineCatapult Define_Catapult(List<String> names)
	{
		return new CommandCreationalDefineCatapult(id(names.get(0)),CCR(names.get(1)),getAN(names.get(2)),getDist(names.get(3)),
		                                           getACC(names.get(4)),weight(names.get(5)),GS(names.get(6)),time(names.get(7)));
	}
	//  <tid>  <origin>  <azimuth>  <elevation>  <distance1>  <distance2>
	public CommandCreationalDefineOLSTransmitter Define_OLS_XMT(List<String> names)
	{
		return new CommandCreationalDefineOLSTransmitter(id(names.get(0)),CCR(names.get(1)),getAN(names.get(2)),getATP(names.get(3)),
														 getDist(names.get(4)),getDist(names.get(5)));
	}
	 // <tid>  <distance> 
	 public CommandCreationalDefineOLSReceiver Define_OLS_RCV(List<String> names)
	{
		return new CommandCreationalDefineOLSReceiver(id(names.get(0)),getDist(names.get(1)));
	}
	// <tid>   <speed1>   <speed2>  <speed3>  <azimuth>  <string>
	 public CommandCreationalDefineCarrier Define_Carrier(List<String> names)
	 {
		return new CommandCreationalDefineCarrier(id(names.get(0)),GS(names.get(1)),GS(names.get(2)),
												  GS(names.get(3)),getAN(names.get(4)),names.get(5));
	 }
	 //<tid>  <speed1>  <speed2> <speed3><speed4><azimuth> <altitude1> <altitude2> <weight1><weight2> <weight3>
	 public CommandCreationalDefineFighter Define_Fighter(List<String> names)
	 {
		 return new CommandCreationalDefineFighter(id(names.get(0)),GS(names.get(1)),GS(names.get(2)),GS(names.get(3)),GS(names.get(4)),
				 									getAN(names.get(5)),getALT(names.get(6)),getALT(names.get(7)),weight(names.get(8))
				 									,weight(names.get(9)),weight(names.get(10)));
	 }
	 //  <tid>   <speed1>  <speed2>   <speed3>  <speed4>    <azimuth>  <altitude1>  <altitude2>  <weight>
	 public CommandCreationalDefineTanker Define_Tanker(List<String> names)
	 {
		 return new CommandCreationalDefineTanker(id(names.get(0)),GS(names.get(1)),GS(names.get(2)),GS(names.get(3)),GS(names.get(4)),
					getAN(names.get(5)),getALT(names.get(6)),getALT(names.get(7)),weight(names.get(8)));
	 }
	 //<tid>  <distance1>  <distance2>  <weight>
	 public CommandCreationalDefineBoomMale DEFINE_BOOM_MALE(List<String> names)
	 {
		 return new CommandCreationalDefineBoomMale(id(names.get(0)),getDist(names.get(1)),getDist(names.get(2)),FL(names.get(3)));
	 }
	//<tid>  <distance1>  <distance2> <elevation> <weight>
	 public CommandCreationalDefineBoomFemale DEFINE_BOOM_Female(List<String> names)
	 {
		 return new CommandCreationalDefineBoomFemale(id(names.get(0)),getDist(names.get(1)),getDist(names.get(2)),
				 									  getATP(names.get(3)),FL(names.get(4)));
	 }
	 // <tid>  <time>
	 public CommandCreationalDefineTailhook Define_Tailhook(List<String> names)
	 {
		 return new CommandCreationalDefineTailhook(id(names.get(0)),time(names.get(1)));
	 }
	 // <tid>  <origin>  <azimuth>  <distance>  <time>
	 public CommandCreationalDefineBarrier Define_Barrier(List<String> names)
	 {
		 return new CommandCreationalDefineBarrier(id(names.get(0)),CCR(names.get(1)),getAN(names.get(2)),getDist(names.get(3)),time(names.get(4)));
	 }
	 // <tid>  <weight>
	 public CommandCreationalDefineAuxiliaryTank Define_Aux_Tank(List<String> names)
	 {
		 return new CommandCreationalDefineAuxiliaryTank(id(names.get(0)),weight(names.get(1)));
	 }
	 // <tid>
	 public CommandCreationalUndefine undefine(List<String> names)
	 {
		 return new CommandCreationalUndefine(id(names.get(0)));
	 }
	 // <tid>
	 public CommandCreationalShowTemplate Show_Template(List<String> names)
	 {
		 return new CommandCreationalShowTemplate(id(names.get(0)));
	 }
	 // <tid>
	 public CommandCreationalListTemplates List_Templates()
	 {
		 return new CommandCreationalListTemplates();
	 }
	 // <aid1> <tid> <aid2> <aid3> <aid4> <aid5> <coordinates> <course>  <speed>
	 public CommandCreationalCreateCarrier Create_Carrier(List<String>names)
	 {
		 return new CommandCreationalCreateCarrier(id(names.get(0)),id(names.get(1)),id(names.get(2)),id(names.get(3)),id(names.get(4)),
				 									id(names.get(5)),getCORD(names.get(6)),getAN(names.get(7)),GS(names.get(8)));
	 }
	 // <aid1>  <tid>  <aid2>  <aid3>  <aid4> 
	 //[ <aidn>+) optional but needed for constructor
	 //[ (<aidm>.<argname> <string>)+) optional but needed for constructor
	// [ <coordinates>  <altitude>  <course>  <speed>) optional but needs second constructor
	 public CommandCreationalCreateFighter Create_Fighter(List<List<String>> LISTS)
	 {
		 for(int x=0;x<LISTS.size();x++)
			{
				for(int y=0;y<LISTS.get(x).size();y++)
				{
					System.out.println(LISTS.get(x).get(y));

				}
			}
		 List<String> names = LISTS.get(0);
		 List<String> Tanks = LISTS.get(1);
		 List<String> Params = LISTS.get(2);
		 List<String> AUX = LISTS.get(3);
		 ArrayList<Identifier> TANKS = new ArrayList<Identifier>();
		 ArrayList<ParameterAssignment> PARAMS = new ArrayList<ParameterAssignment>();
		 for(String A :Tanks)
		 {
			 TANKS.add(id(A));
		 }

		 for(int i =0;i<Params.size();i=i+2)
		 {
			 PARAMS.add(getParam(Params.get(i),Params.get(i+1)));
		 }
		 if(AUX.size()==0)
		 {
			return new CommandCreationalCreateFighter(id(names.get(0)),id(names.get(1)),id(names.get(2)),id(names.get(3))
					,id(names.get(4)),TANKS,PARAMS);
		 }
		 return new CommandCreationalCreateFighter(id(names.get(0)),id(names.get(1)),id(names.get(2)),id(names.get(3)),id(names.get(4)),
				 										TANKS,PARAMS,getCORD(AUX.get(0)),getALT(AUX.get(1)),
				 										getAN(AUX.get(2)),GS(AUX.get(3))); 
	 }
	 // <aid1> <tid>  <aid2>  <coordinates> <altitude> <course> <speed> 
	 public CommandCreationalCreateTanker Create_Tanker(List<String> names)
	 {
		 return new CommandCreationalCreateTanker(id(names.get(0)),id(names.get(1)),id(names.get(2)),getCORD(names.get(3)),
				 								  getALT(names.get(4)),getAN(names.get(5)),GS(names.get(6)));
	 }
	 public CommandCreationalCreateTrap Create_Trap(List<String> names)
	 {
		 return new CommandCreationalCreateTrap(id(names.get(0)),id(names.get(1)));
	 }
	 public CommandCreationalCreateBarrier Create_Barrier(List<String> names)
	 {
		 return new CommandCreationalCreateBarrier(id(names.get(0)),id(names.get(1)));
	 }
	 public CommandCreationalCreateAuxiliaryTank Create_AUX_Tank(List<String> names)
	 {
		 return new CommandCreationalCreateAuxiliaryTank(id(names.get(0)),id(names.get(1)));
	 }
	 public CommandCreationalCreateCatapult Create_CATAPULT(List<String> names)
	 {
		 return new CommandCreationalCreateCatapult(id(names.get(0)),id(names.get(1)));
	 }
	 public CommandCreationalCreateOLSTransmitter Create_OLS_XMT(List<String> names)
	 {
		 return new CommandCreationalCreateOLSTransmitter(id(names.get(0)),id(names.get(1)));
	 }

	 public CommandCreationalCreateOLSReceiver Create_OLS_RCV(List<String> names)
	 {
		 return new CommandCreationalCreateOLSReceiver(id(names.get(0)),id(names.get(1)));
	 }
	 public CommandCreationalCreateBoomMale Create_Boom_Male(List<String> names)
	 {
		 return new CommandCreationalCreateBoomMale(id(names.get(0)),id(names.get(1)));
	 }
	 public CommandCreationalCreateBoomFemale Create_Boom_Female(List<String> names)
	 {
		 return new CommandCreationalCreateBoomFemale(id(names.get(0)),id(names.get(1)));
	 }
	 
	 public CommandCreationalCreateTailhook Create_Tailhook(List<String> names)
	 {
		 return new CommandCreationalCreateTailhook(id(names.get(0)),id(names.get(1)));
	 }
	 public CommandCreationalUncreate Uncreate(List<String> names)
	 {
		 return new CommandCreationalUncreate(id(names.get(0)));
	 }
	 public CommandCreationalDescribe Discribe(List<String> names)
	 {
		 return new CommandCreationalDescribe(id(names.get(0)));
	 }
	 public CommandCreationalListAgents List_Agents()
	 {
		 return new CommandCreationalListAgents();
	 }
	 public CommandStructuralPopulateCarrier Populate_Carrier(List<List<String>> LISTS)
	 {
		 Identifier A = id(LISTS.get(0).get(0));
		 List<String> B = LISTS.get(1);
		 List<Identifier> D = new ArrayList< Identifier >();
		 for(String C : B)
		 {
			 D.add(id(C));
		 }
		 return new CommandStructuralPopulateCarrier(A,D);
	 }
	 public CommandStructuralPopulateWorld Populate_World(List<String> names)
	 { List<Identifier> D = new ArrayList< Identifier >();
		 for(String A : names)
		 {
			 D.add(id(A));
		 }
		 return new CommandStructuralPopulateWorld(D);
	 }
	 public CommandStructuralCommit Commit()
	 {
		 return new CommandStructuralCommit();
	 }
	 public CommandBehavioralDoAsk Do_Ask(List<String> names)
	 {
		 String A = names.get(1);
		if(A.equals("ALL")||A.equals("COORDINATES")||A.equals("ALTITUDE")||A.equals("HEADING")||
			A.equals("SPEED")||A.equals("WEIGHT")||A.equals("FUEL"))
			{
				return new CommandBehavioralDoAsk(id(names.get(0)),CommandBehavioralDoAsk.E_Parameter.valueOf(names.get(1)));
			}
			throw new IllegalArgumentException();
	 	}
	 
	 public CommandBehavioralDoPosition Do_Position(List<String> names)
	 {
		return new CommandBehavioralDoPosition(id(names.get(0))); 
	 }
	 
	 public CommandBehavioralDoBarrier Do_Barrier(List<String> names)
	 {
		 return new CommandBehavioralDoBarrier(id(names.get(0)),names.get(1).equals("UP"));
	 }
	 //done with 3 in behavioral
	 public CommandBehavioralDoCatapult Do_Catapult(List<String> names)
	 {
		 return new CommandBehavioralDoCatapult(id(names.get(0)),GS(names.get(1)));
	 }
	 public CommandBehavioralDoSetSpeed Do_Set_Speed(List<String> names)
	 {
		 return new CommandBehavioralDoSetSpeed(id(names.get(0)),GS(names.get(1)));
	 }
	 public CommandBehavioralDoSetAltitude Do_Set_Altitude(List<String> names)
	 {
		 return new CommandBehavioralDoSetAltitude(id(names.get(0)),getALT(names.get(1)));
	 }
	 public CommandBehavioralDoSetHeading Do_Set_Heading(List<String> names)
	 {
		 return new CommandBehavioralDoSetHeading(id(names.get(0)),getAN(names.get(1)),
				 		CommandBehavioralDoSetHeading.E_Direction.valueOf(names.get(2)));
	 }
	 public CommandBehavioralDoTailhook Do_Tailhook(List<String> names)
	 { 
		 String A = names.get(1);
		if(A.equals("UP")||A.equals("DOWN"))
		 return new CommandBehavioralDoTailhook(id(names.get(0)),names.get(1).equals("UP"));
		else{throw new IllegalArgumentException();}
	 }
	 public CommandBehavioralDoCaptureOLS Do_Capture_Ols(List<String> names)
	 {
		 return new CommandBehavioralDoCaptureOLS(id(names.get(0)));
	 }
	 public CommandBehavioralDoBoom Do_Boom(List<String>names)
	 {
		 String A = names.get(1);
		 if(A.equals("EXTEND")||A.equals("RETRACT"))
		 	return new CommandBehavioralDoBoom(id(names.get(0)),names.get(1).equals("EXTEND"));
		 else{throw new IllegalArgumentException();}
	 }
	 public CommandBehavioralDoTransfer Do_Transfer(List<String> names)
	 {
		 String A = names.get(1);
		 if(A.equals("START")||A.equals("STOP"))
			 return new CommandBehavioralDoTransfer(id(names.get(0)),names.get(1).equals("START"));
		 else{throw new IllegalArgumentException();}
	 }
	public CommandBehavioralDoForceCoordinates Do_Force_Coordinates(List<String> names)
	{
		return new CommandBehavioralDoForceCoordinates(id(names.get(0)),getCORD(names.get(1)));
	}
	public CommandBehavioralDoForceAltitude Do_Force_Altitude(List<String> names)
	{
		return new CommandBehavioralDoForceAltitude(id(names.get(0)),getALT(names.get(1)));
	}
	public CommandBehavioralDoForceHeading Do_Force_Heading(List<String> names)
	{
		return new CommandBehavioralDoForceHeading(id(names.get(0)),getAN(names.get(1)));
	}
	public CommandBehavioralDoForceSpeed Do_Force_Speed(List<String> names)
	{
		return new CommandBehavioralDoForceSpeed(id(names.get(0)),GS(names.get(1)));
	}
	public CommandBehavioralDoForceAll Do_Force_All(List<List<String>> names)
	{
		List<String> A = names.get(0);
		List<String> B = names.get(1);
		return new CommandBehavioralDoForceAll(id(A.get(0)),getCORD(A.get(1)),getALT(B.get(0)),getAN(A.get(2)),GS(A.get(3)));
	}
	public CommandBehavioralSetWindDirection Set_Wind_Direction(List<String> names)
	{
		return new CommandBehavioralSetWindDirection(getAN(names.get(0)));
	}
	public CommandBehavioralGetWindConditions Get_Wind_Conditions()
	{
		return new CommandBehavioralGetWindConditions();
	}
	public CommandBehavioralSetWindSpeed Set_Wind_Speed(List<String> names)
	{
		return new CommandBehavioralSetWindSpeed(GS(names.get(0)));
	}
	public CommandMiscDoSetClockRate Clock(List<String> names)
	{
		return new CommandMiscDoSetClockRate(rate(names.get(0)));
	}
	public CommandMiscDoSetClockRunning Clock_Resume(List<String> names)
	{String A = names.get(0);
		 if(A.equals("PAUSE")||A.equals("RESUME"))
		return new CommandMiscDoSetClockRunning(names.get(0).equals("RESUME"));
		 else{throw new IllegalArgumentException();}
		
	}
	public CommandMiscDoClockUpdate Clock_Update()
	{
		return new CommandMiscDoClockUpdate();	
	}
	public CommandMiscDoShowClock Clock()
	{
		return new CommandMiscDoShowClock();
	}
	public CommandMiscDoRun Run(List<String> names)
	{
		return new CommandMiscDoRun(names.get(0));
	}
	public CommandMiscDoExit Exit()
	{
		return new CommandMiscDoExit();
	}
	public CommandMiscDoWait Wait(List<String> names)
	{
		return new CommandMiscDoWait(rate(names.get(0)));
	}
	 //PARAMS
	 private ParameterAssignment getParam(String A,String B)
	 {
		 return new ParameterAssignment(id(A),B);
	 }
	 private CoordinateWorld getCORD(String A)
	{
		String[] B = A.split("/");
		Latitude C = Lat(B[0]);
		Longitude D = Lon(B[1]);
		return new CoordinateWorld(C,D);
	}
	private Acceleration getACC(String A)
	{
		return (new Acceleration(Integer.parseInt(A)));
	}
	private Altitude getALT(String A)
	{
		return (new Altitude(Double.parseDouble(A)));
	}
	private AngleNavigational getAN(String A)
	{
		//if(A.length()!=3)
		//{throw new IllegalArgumentException();}
		return (new AngleNavigational(Double.parseDouble(A)));
	}
	private Distance getDist(String A)
	{
		return (new Distance(Double.parseDouble(A)));
	}
	private AttitudePitch getATP(String A)
	{
		return (new AttitudePitch(Double.parseDouble(A)));
	}
	private Flow FL(String A)
	{
	return new Flow(Double.parseDouble(A));	
	}
	private static Identifier id(String A)
	{
		return new Identifier(A);
	}
	private static Latitude Lat(String A)
	{
		int one = 0, two = 0;
		double three = 0;
		int start = 0;
		for(int i =0;i<A.length();i++)
		{
			if(A.charAt(i)=='*')
			{

				one =Integer.parseInt(A.substring(start,i));
				start = i+1;
			}
			else if(A.charAt(i)=='\'')
			{
				two = Integer.parseInt(A.substring(start,i));
				start = i+1;
			}
			else if(A.charAt(i)=='"')
			{
				three = Double.parseDouble(A.substring(start,i));
			}
		}
		return new Latitude(one,two,three);
	}
	private static Longitude Lon(String A)
	{
		int one = 0, two = 0;
		double three = 0;
		int start = 0;
		for(int i =0;i<A.length();i++)
		{
			if(A.charAt(i)=='*')
			{

				one =Integer.parseInt(A.substring(start,i));
				start = i+1;
			}
			else if(A.charAt(i)=='\'')
			{
				two = Integer.parseInt(A.substring(start,i));
				start = i+1;
			}
			else if(A.charAt(i)=='"')
			{
				three = Double.parseDouble(A.substring(start,i));
			}
		}
		return new Longitude(one,two,three);
	}
	private CoordinateCartesianRelative CCR(String A)
	{
		String[] B = A.split(":");
		Double C = Double.parseDouble(B[0]);
		Double D = Double.parseDouble(B[1]);
		return new CoordinateCartesianRelative(C,D);
	}
	private Percent  percent(String A)
	{
		// should throw something if not between [0-100]
		int i =Integer.parseInt(A);
		return (new Percent(i));
	}
	private Rate rate(String A)
	{
		return (new Rate(Integer.parseInt(A)));
	}
	private Speed GS(String A)
	{
		return (new Speed(Integer.parseInt(A)));
	}
	private Time time(String A)
	{
		return new Time(Double.parseDouble(A));
	}
	private Weight weight(String A)
	{
		return (new Weight(Integer.parseInt(A)));
	}
}//end of class

