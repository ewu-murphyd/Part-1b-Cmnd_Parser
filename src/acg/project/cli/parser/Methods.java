package acg.project.cli.parser;

import java.util.*;

import acg.project.action.command.miscellaneous.*;
import acg.project.action.command.behavioral.*;
import acg.project.action.command.creational.define.CommandCreationalDefineTrap;
import acg.project.action.command.ParameterAssignment;
import acg.project.action.command.creational.define.*;
//import acg.project.action.command.creational.*;
import acg.project.action.command.creational.create.*;
import acg.project.action.command.structural.*;
import acg.architecture.datatype.*;
public class Methods {

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
		if(A.length()!=3)
		{throw new IllegalArgumentException();}
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
	private Latitude Lat(String A)
	{
		int one = 0, two = 0;
		double three = 0;
		int start = 0;
		for(int i =0;i<A.length();i++)
		{
			if(A.charAt(i)=='*')
			{
				
				one =Integer.parseInt(A.substring(start,i-1));
				start = i-1;
			}
			else if(A.charAt(i)=='"')
			{
				two = Integer.parseInt(A.substring(start,i-1));
				start = i-1;
			}
			else if(A.charAt(i)=='"')
			{
				three = Double.parseDouble(A.substring(start,i-1));
				start = i-1;
			}
		}
		return new Latitude(one,two,three);
	}
	private Longitude Lon(String A)
	{
		int one = 0, two = 0, start=0;
		double three = 0;
		for(int i =0;i<A.length();i++)
		{
			if(A.charAt(i)=='*')
			{
				
				one =Integer.parseInt(A.substring(start,i-1));
				start = i-1;
			}
			else if(A.charAt(i)=='"')
			{
				two = Integer.parseInt(A.substring(start,i-1));
				start = i-1;
			}
			else if(A.charAt(i)=='"')
			{
				three = Double.parseDouble(A.substring(start,i-1));
				start = i-1;
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
}