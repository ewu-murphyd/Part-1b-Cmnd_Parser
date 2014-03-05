package acg.project.cli.parser;

public class PullInfo{

	//contructor initializes the scanner object
	public PullInfo(){
	}

	public String [] pullfromArra(String [] arra){
		String [] newArra = new String[0];
		for(int i = 0; i < arra.length; i++)
			newArra = pullfromString(arra[i]);
		return newArra;
	}

	public String [] pullfromString(String stringToPull){
		String [] arra = stringToPull.split(" (?=([^\']*\'[^\']*\')*[^\']*$)");
		String [] constructedArra = new String[0];
		if(arra[0].equalsIgnoreCase("DEFINE"))
			constructedArra = fillArrayDefine(arra);
		else if(arra[0].equalsIgnoreCase("UNDEFINE"))
			return new String [] {"UNDEFINE", arra[1]};
		else if(arra[0].equalsIgnoreCase("SHOW"))
			return new String [] {"SHOW_TEMPLATE", arra[2]};
		else if(arra[0].equalsIgnoreCase("LIST") && arra[1].equalsIgnoreCase("TEMPLATES"))
			return new String [] {"LIST_TEMPLATES"};
		else if(arra[0].equalsIgnoreCase("CREATE"))
			constructedArra = fillArrayCreate(arra);
		else if(arra[0].equalsIgnoreCase("UNCREATE"))
			return new String [] {"UNCREATE", arra[1]};
		else if(arra[0].equalsIgnoreCase("DESCRIBE"))
			return new String [] {"DESCRIBE", arra[1]};
		else if(arra[0].equalsIgnoreCase("LIST") && arra[1].equalsIgnoreCase("AGENTS"))
			return new String [] {"LIST_AGENTS"};
		//STRUCTURAL COMMANDS
		else if(arra[0].equalsIgnoreCase("POPULATE"))
			constructedArra = fillArrayPopulate(arra);
		else if(arra[0].equalsIgnoreCase("COMMIT"))
			return new String [] {"COMMIT"};
		else if(arra[0].equalsIgnoreCase("DO"))
			constructedArra = fillArrayDo(arra);
		else if(arra[0].equalsIgnoreCase("@DO"))
			constructedArra = fillArrayAtDo(arra);
		else if(arra[0].equalsIgnoreCase("SET") && arra[2].equalsIgnoreCase("DIRECTION"))
			return new String [] {"SET_WIND_DIRECTION", arra[3]};
		else if(arra[0].equalsIgnoreCase("SET") && arra[2].equalsIgnoreCase("SPEED"))
			return new String [] {"SET_WIND_SPEED", arra[3]};
		else if(arra[0].equalsIgnoreCase("GET"))
			return new String [] {"SET_WIND_CONDITIONS"};
		else if(arra[0].equalsIgnoreCase("@CLOCK") && arra.length == 1)
			return new String [] {"@CLOCK"};
		else if(arra[0].equalsIgnoreCase("@CLOCK"))
			return new String [] {"@CLOCK", arra[1]};
		else if(arra[0].equalsIgnoreCase("@RUN"))
			return new String [] {"@RUN", arra[1]};
		else if(arra[0].equalsIgnoreCase("@EXIT"))
			return new String [] {"@EXIT"};
		else if(arra[0].equalsIgnoreCase("@WAIT"))
			return new String [] {"@WAIT", arra[1]};
		return constructedArra;
	}

	private String [] fillArrayAtDo(String [] pullValues){
		if(pullValues[2].equalsIgnoreCase("FORCE") && pullValues[3].equalsIgnoreCase("COORDINATES"))
			return new String [] {"@DO", "FORCE_COORDINATES", pullValues[1], pullValues[4]};
		else if(pullValues[2].equalsIgnoreCase("FORCE") && pullValues[3].equalsIgnoreCase("ALTITUDE"))
			return new String [] {"@DO", "FORCE_ALTITUDE", pullValues[1], pullValues[4]};
		else if(pullValues[2].equalsIgnoreCase("FORCE") && pullValues[3].equalsIgnoreCase("HEADING"))
			return new String [] {"@DO", "FORCE_HEADING", pullValues[1], pullValues[4]};
		else if(pullValues[2].equalsIgnoreCase("FORCE") && pullValues[3].equalsIgnoreCase("SPEED"))
			return new String [] {"@DO", "FORCE_SPEED", pullValues[1], pullValues[4]};
		return null;
	}

	private String [] fillArrayDo(String [] pullValues){
		if(pullValues[2].equalsIgnoreCase("ASK"))
			return new String [] {"DO", "ASK", pullValues[1], pullValues[3]};
		else if(pullValues[2].equalsIgnoreCase("POSITION"))
			return new String [] {"DO", "POSITION", pullValues[1]};
		else if(pullValues[2].equalsIgnoreCase("BARRIER"))
			return new String [] {"DO", "BARRIER", pullValues[1], pullValues[3]};
		else if(pullValues[2].equalsIgnoreCase("CATAPULT"))
			return new String [] {"DO", "CATAPULT", pullValues[1], pullValues[6]};
		else if(pullValues[2].equalsIgnoreCase("SET") && pullValues[3].equalsIgnoreCase("SPEED"))
			return new String [] {"DO", "SET_SPEED", pullValues[1], pullValues[4]};
		else if(pullValues[2].equalsIgnoreCase("SET") && pullValues[3].equalsIgnoreCase("ALTITUDE"))
			return new String [] {"DO", "SET_ALTITUDE", pullValues[1], pullValues[4]};
		else if(pullValues[2].equalsIgnoreCase("SET") && pullValues[3].equalsIgnoreCase("HEADING") && pullValues.length == 5)
			return new String [] {"DO", "SET_HEADING", pullValues[1], pullValues[4]};
		else if(pullValues[2].equalsIgnoreCase("SET") && pullValues[3].equalsIgnoreCase("HEADING"))
			return new String [] {"DO", "SET_HEADING", pullValues[1], pullValues[4], pullValues[5]};
		else if(pullValues[2].equalsIgnoreCase("TAILHOOK"))
			return new String [] {"DO", "TAILHOOK", pullValues[1], pullValues[3]};
		else if(pullValues[2].equalsIgnoreCase("CAPTURE"))
			return new String [] {"DO", "CAPTURE_OLS", pullValues[1]};
		else if(pullValues[2].equalsIgnoreCase("BOOM"))
			return new String [] {"DO", "BOOM", pullValues[1], pullValues[3]};
		else if(pullValues[2].equalsIgnoreCase("TRANSFER"))
			return new String [] {"DO", "TRANSFER", pullValues[1], pullValues[3]};
		return null;
	}

	private String [] fillArrayPopulate(String [] pullValues){
		
		if(pullValues[1].equalsIgnoreCase("CARRIER")){
			String [] arra = new String [pullValues.length - 2];
			arra[0] = "POPULATE";
			arra[1] = "CARRIER";
			arra[2] = pullValues[2];
			for(int i = 5; i < pullValues.length; i++)
				arra[i-2] = pullValues[i];
			return arra;
		}
		else if(pullValues[1].equalsIgnoreCase("WORLD")){
			String [] arra = new String [pullValues.length - 1];
			arra[0] = "POPULATE";
			arra[1] = "WORLD";
			for(int i = 3; i < pullValues.length; i++)
				arra[i-1] = pullValues[i];
			return arra;
		}
		return null;
	}

	private String [] fillArrayCreate(String [] pullValues){
		if(pullValues[1].equalsIgnoreCase("CARRIER"))
			return new String [] {"CREATE", "CARRIER", pullValues[2], pullValues[4], pullValues[7], pullValues[9], pullValues[11], pullValues[13], pullValues[16], pullValues[18], pullValues[20]};
		if(pullValues[1].equalsIgnoreCase("FIGHTER"))
			return new String [] {"CREATE", "FIGHTER"};
		else if(pullValues[1].equalsIgnoreCase("TANKER"))
			return new String [] {"CREATE", "TANKER", pullValues[2], pullValues[4], pullValues[7], pullValues[10], pullValues[12], pullValues[14], pullValues[16]};
		else if(pullValues[1].equalsIgnoreCase("TRAP"))
			return new String [] {"CREATE", "TRAP", pullValues[2], pullValues[4]};
		else if(pullValues[1].equalsIgnoreCase("BARRIER"))
			return new String [] {"CREATE", "BARRIER", pullValues[2], pullValues[4]};
		else if(pullValues[1].equalsIgnoreCase("AUX_TANK"))
			return new String [] {"CREATE", "AUX_TANK", pullValues[2], pullValues[4]};
		else if(pullValues[1].equalsIgnoreCase("CATAPULT"))
			return new String [] {"CREATE", "CATAPULT", pullValues[2], pullValues[4]};
		else if(pullValues[1].equalsIgnoreCase("OLS_XMT"))
			return new String [] {"CREATE", "OLS_XMT", pullValues[2], pullValues[4]};
		else if(pullValues[1].equalsIgnoreCase("OLS_RCV"))
			return new String [] {"CREATE", "OLS_RCV", pullValues[2], pullValues[4]};
		else if(pullValues[1].equalsIgnoreCase("BOOM"))
			return new String [] {"CREATE", "BOOM", pullValues[2], pullValues[4]};
		else if(pullValues[1].equalsIgnoreCase("TAILHOOK"))
			return new String [] {"CREATE", "TAILHOOK", pullValues[2], pullValues[4]};
		System.out.println("df");
		return null;
	}

	private String [] fillArrayDefine(String [] pullValues){
		
		if(pullValues[1].equalsIgnoreCase("TRAP"))
			return new String [] {"DEFINE", "TRAP", pullValues[2], pullValues[4], pullValues[6], pullValues[8], pullValues[11], pullValues[13], pullValues[15]};
		else if(pullValues[1].equalsIgnoreCase("CATAPULT"))
			return new String [] {"DEFINE", "CATAPULT", pullValues[2], pullValues[4], pullValues[6], pullValues[8], pullValues[10], pullValues[13], pullValues[15], pullValues[17]};
		else if(pullValues[1].equalsIgnoreCase("OLS_XMT"))
			return new String [] {"DEFINE", "OLS_XMT", pullValues[2], pullValues[4], pullValues[6], pullValues[8], pullValues[10], pullValues[12]};
		else if(pullValues[1].equalsIgnoreCase("OLS_RCV"))
			return new String [] {"DEFINE", "OLS_RCV", pullValues[2], pullValues[4]};
		else if(pullValues[1].equalsIgnoreCase("CARRIER"))
			return new String [] {"DEFINE", "CARRIER", pullValues[2], pullValues[5], pullValues[8], pullValues[10], pullValues[12], pullValues[14]};
		else if(pullValues[1].equalsIgnoreCase("FIGHTER"))
			return new String [] {"DEFINE", "FIGHTER", pullValues[2], pullValues[5], pullValues[7], pullValues[10], pullValues[12], pullValues[14], pullValues[16], pullValues[18], pullValues[21], pullValues[24], pullValues[26]};
		else if(pullValues[1].equalsIgnoreCase("TANKER"))
			return new String [] {"DEFINE", "TANKER", pullValues[2], pullValues[5], pullValues[7], pullValues[10], pullValues[12], pullValues[14], pullValues[16], pullValues[18], pullValues[20]};
		else if(pullValues[1].equalsIgnoreCase("BOOM")){
			if(pullValues[2].equalsIgnoreCase("MALE"))
				return new String [] {"DEFINE", "MALE", pullValues[3], pullValues[5], pullValues[7], pullValues[9]};
			return new String [] {"DEFINE", "FEMALE", pullValues[3], pullValues[5], pullValues[7], pullValues[9], pullValues[11]};
		}
		else if(pullValues[1].equalsIgnoreCase("TAILHOOK"))
			return new String [] {"DEFINE", "TAILHOOK", pullValues[2], pullValues[4]};
		else if(pullValues[1].equalsIgnoreCase("BARRIER"))
			return new String [] {"DEFINE", "BARRIER", pullValues[2], pullValues[4], pullValues[6], pullValues[8], pullValues[10]};
		else if(pullValues[1].equalsIgnoreCase("AUX_TANK"))
			return new String [] {"DEFINE", "AUX_TANK", pullValues[2], pullValues[4]};
		return null;
	}

	public void printArray(String [] array){
		int arraySize = array.length;
		for(int i = 0; i < arraySize; i++)
			System.out.print(array[i] + " ");
		System.out.println();
	}

}