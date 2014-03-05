package acg.project.cli.parser;

public class CheckInfo{

	//private String infoToCheck;
	private String [] allPossibles = {
		//Creational Template Commands
		/* 1*/"DEFINE TRAP & ORIGIN ^ AZIMUTH # WIDTH # LIMIT WEIGHT # SPEED # MISS # ",
		/* 2*/"DEFINE CATAPULT & ORIGIN ^ AZIMUTH # LENGTH # ACCELERATION # LIMIT WEIGHT # SPEED # RESET # ",
		/* 3*/"DEFINE OLS_XMT & ORIGIN ^ AZIMUTH # ELEVATION # RANGE # DIAMETER # ",
		/* 4*/"DEFINE OLS_RCV & DIAMETER # ",
		/* 5*/"DEFINE CARRIER & SPEED MAX # DELTA INCREASE # DECREASE # TURN # LAYOUT # ",
		/* 6*/"DEFINE FIGHTER & SPEED MIN # MAX # DELTA INCREASE # DECREASE # TURN # CLIMB # DESCENT # EMPTY WEIGHT # FUEL INITIAL # DELTA # ",
		/* 7*/"DEFINE TANKER & SPEED MIN # MAX # DELTA INCREASE # DECREASE # TURN # CLIMB # DESCENT # TANK # ",
		/* 8*/"DEFINE BOOM MALE & LENGTH # DIAMETER # FLOW # ",
		/* 9*/"DEFINE BOOM FEMALE & LENGTH # DIAMETER # ELEVATION # FLOW # ",
		/*10*/"DEFINE TAILHOOK & TIME # ",
		/*11*/"DEFINE BARRIER & ORIGIN ^ AZIMUTH # WIDTH # TIME # ",
		/*12*/"DEFINE AUX_TANK & AMOUNT # ",
		/*13*/"UNDEFINE & ",
		/*14*/"SHOW TEMPLATE & ",
		/*15*/"LIST TEMPLATES ",

		//Creational Agent Commands
		/* 1*/"CREATE CARRIER & FROM & WITH CATAPULT & BARRIER & TRAP & OLS & AT COORDINATES @ HEADING # SPEED # ",
		/* 2*/"CREATE FIGHTER & FROM & WITH OLS & BOOM & TAILHOOK & TANKS & OVERRIDING % AT COORDINATES @ ALTITUDE # HEADING # SPEED # ",
		/* 2*/"CREATE FIGHTER & FROM & WITH OLS & BOOM & TAILHOOK & TANKS & OVERRIDING % ",
		/* 2*/"CREATE FIGHTER & FROM & WITH OLS & BOOM & TAILHOOK & TANKS & AT COORDINATES @ ALTITUDE # HEADING # SPEED # ",
		/* 2*/"CREATE FIGHTER & FROM & WITH OLS & BOOM & TAILHOOK & TANKS & ",
		/* 2*/"CREATE FIGHTER & FROM & WITH OLS & BOOM & TAILHOOK & OVERRIDING % AT COORDINATES @ ALTITUDE # HEADING # SPEED # ",
		/* 2*/"CREATE FIGHTER & FROM & WITH OLS & BOOM & TAILHOOK & OVERRIDING % ",
		/* 2*/"CREATE FIGHTER & FROM & WITH OLS & BOOM & TAILHOOK & AT COORDINATES @ ALTITUDE # HEADING # SPEED # ",
		/* 2*/"CREATE FIGHTER & FROM & WITH OLS & BOOM & TAILHOOK & ",
		/* 3*/"CREATE TANKER & FROM & WITH BOOM & AT COORDINATES @ ALTITUDE # HEADING # SPEED # ",
		/* 4*/"CREATE TRAP & FROM & ",
		/* 5*/"CREATE BARRIER & FROM & ",
		/* 6*/"CREATE AUX_TANK & FROM & ",
		/* 7*/"CREATE CATAPULT & FROM & ",
		/* 8*/"CREATE OLS_XMT & FROM & ",
		/* 9*/"CREATE OLS_RCV & FROM & ",
		/*10*/"CREATE BOOM & FROM & ",
		/*11*/"CREATE TAILHOOK & FROM & ",
		/*12*/"UNCREATE & ",
		/*13*/"DESCRIBE & ",
		/*14*/"LIST AGENTS ",

		//Structural Commands
		/* 1*/"POPULATE CARRIER & WITH FIGHTER & ",
		/* 1*/"POPULATE CARRIER & WITH FIGHTERS & ",
		/* 2*/"POPULATE WORLD WITH & ",
		/* 3*/"COMMIT ",

		//Behavioral Commands
		/* 1*/"DO & ASK ALL ", //ask about format of all 1's
		/* 1*/"DO & ASK COORDINATES ",
		/* 1*/"DO & ASK ALTITUDE ",
		/* 1*/"DO & ASK HEADING ",
		/* 1*/"DO & ASK SPEED ",
		/* 1*/"DO & ASK WEIGHT ",
		/* 1*/"DO & ASK FUEL ",
		/* 2*/"DO & POSITION ",
		/* 3*/"DO & BARRIER UP ",
		/* 3*/"DO & BARRIER DOWN ",
		/* 4*/"DO & CATAPULT LAUNCH WITH SPEED # ",
		/* 5*/"DO & SET SPEED # ",
		/* 6*/"DO & SET ALTITUDE # ",
		/* 7*/"DO & SET HEADING # LEFT ",
		/* 7*/"DO & SET HEADING # RIGHT ",
		/* 7*/"DO & SET HEADING # ",
		/* 8*/"DO & TAILHOOK DOWN ",
		/* 8*/"DO & TAILHOOK UP ",
		/* 9*/"DO & CAPTURE OLS ",
		/*10*/"DO & BOOM EXTEND ",
		/*10*/"DO & BOOM RETRACT ",
		/*11*/"DO & TRANSFER START ",
		/*11*/"DO & TRANSFER STOP ",
		/*12*/"@DO & FORCE COORDINATES @ ",
		/*13*/"@DO & FORCE ALTITUDE # ",
		/*14*/"@DO & FORCE HEADING # ",
		/*15*/"@DO & FORCE SPEED # ",
		/*16*/"@DO & FORCE COORDINATES @ HEADING # SPEED # ",
		/*16*/"@DO & FORCE COORDINATES @ ALTITUDE # HEADING # SPEED # ",
		/*17*/"SET WIND DIRECTION # ",
		/*18*/"SET WIND SPEED # ",
		/*19*/"GET WIND CONDITIONS ",

		//Miscellaneous Commands
		/* 1*/"@CLOCK # ",
		/* 2*/"@CLOCK PAUSE ",
		/* 2*/"@CLOCK RESUME ",
		/* 2*/"@CLOCK UPDATE ",
		/* 3*/"@CLOCK ",
		/* 4*/"@RUN $ ",
		/* 5*/"@EXIT ",
		/* 6*/"@WAIT # "
	};
	private String [] reservedWords = {"@CLOCK", "@DO", "@EXIT", "@RUN", "@WAIT", "ACCELERATION", "AGENTS", "ALL", "ALTITUDE", "AMOUNT", "ASK", "AT", "AUX_TANK", "AZIMUTH", "BARRIER", "BOOM", "CAPTURE", "CARRIER", "CATAPULT", "CLIMB", "COMMIT", "CONDITIONS", "COORDINATES", "CREATE", "DECREASE", "DEFINE", "DELTA", "DESCENT", "DESCRIBE", "DIAMETER", "DIRECTION", "DO", "DOWN", "ELEVATION", "EMPTY", "EXTEND", "FEMALE", "FIGHTER", "FIGHTERS", "FLOW", "FORCE", "FROM", "FUEL", "GET", "HEADING", "INCREASE", "INITIAL", "LAUNCH", "LAYOUT", "LEFT", "LENGTH", "LIMIT", "LIST", "MALE", "MAX", "MIN", "MISS", "OLS", "OLS_RCV", "OLS_XMT", "ORIGIN", "OVERRIDING", "PAUSE", "POPULATE", "POSITION", "RANGE", "RESET", "RESUME", "RETRACT", "RIGHT", "SET", "SHOW", "SPEED", "START", "STOP", "TAILHOOK", "TANK", "TANKER", "TANKS", "TEMPLATE", "TEMPLATES", "TIME", "TRANSFER", "TRAP", "TURN", "UNCREATE", "UNDEFINE", "UP", "UPDATE", "WEIGHT", "WIDTH", "WIND", "WITH", "WORLD"};                                                                                 
	
	//contructor initializes the scanner object
	public CheckInfo(){
	}

	/*
		Sends each string from an array to the "check method".
		Once a string doesn't match the specified criteria it returns false. If all items
		in the array meet criteria then it sends true.
	 */
	public boolean checkArray(String [] arra){
		boolean bool = true;
		int index = 0, upper = arra.length;
		do{
			bool = this.check(arra[index]);
			index++;
		}while(bool == true && index < upper);
		return bool;
	}

	/*
		First the string is formatted. Number values are replaced with '-' and the spaces are removed
		Second the strings are compared to all possible string combinations
		If the string is valid input then true is returned otherwise false.
	 */
	public boolean check(String infoToCheck){
		infoToCheck = infoToCheck.toUpperCase();
		infoToCheck = combineString(infoToCheck);
		boolean bool = compareToKnownStrings(infoToCheck, allPossibles);
		//System.out.println("'" + infoToCheck + "' : " + bool);
		return bool;
	}

	/*
		Checks if a value in a string is a number value.
	 */
	private boolean checkIfNumber(String stringToCheck){
		return stringToCheck.matches("-?\\d+(\\.\\d+)?");
  	}

  	private boolean checkIfReserved(String stringToCheck){
  		for(int i = 0; i < reservedWords.length; i++){
  			if(stringToCheck.equals(reservedWords[i]))
  				return true;
  		}
  		return false;
  	}

  	private boolean checkIfCoordinates(String stringToCheck){
  		String pattern = "(\\d+)(\\*)(\\d+)(')(\\d+)(\")(\\/)(\\d+)(\\*)(\\d+)(')(\\d+)(\")";
  		return stringToCheck.matches(pattern);
  	}

  	private boolean checkIfOrigin(String stringToCheck){
		String flo="(([+-]?\\d*\\.\\d+)(?![-+0-9\\.])|([-+]\\d+)|(\\d+))";
		String colon="(:)";
  		String pattern = flo + colon + flo;
  		return stringToCheck.matches(pattern);
  	}

  	private boolean checkIfString(String stringToCheck){
  		String inQuotes = "'([^']*)'";
  		return stringToCheck.matches(inQuotes);
  	}

  	/*
  		Splits a string by space then combines it back together without spaces replaced number values with '-'
  	 */
	private String combineString(String stringToCombine){
		String inQuotes = "(?=([^\']*\'[^\']*\')*[^\']*$)", space = "( )";
		String pattern = "(\\()((?:[A-Z][A-Z]*[0-9]+[A-Z0-9]*))(\\.)((?:[A-Z][A-Z]+))( )(WITH)( )(\\'.*?\\')(\\))";
		//replaces <aidm>.<argname> WITH <string> with '%'
		stringToCombine = stringToCombine.replaceAll(pattern, "%");
		//splits by space and ignores spaces inside of single quotes
		String [] arra = stringToCombine.split(space + inQuotes);
		
		//reconstructs each string but replaces the variables to static values
		stringToCombine = "";
		for(int i = 0; i < arra.length; i++){
			if(checkIfCoordinates(arra[i]))
				stringToCombine += "@ ";//replaces coords with '@'
			else if(checkIfOrigin(arra[i]))
				stringToCombine += "^ ";//replaces origin with '^'
			else if(arra[i].equals("%"))
				stringToCombine += "% ";//replaces fighterOption with '%'
			else if(checkIfNumber(arra[i]))
				stringToCombine += "# ";//replaces number with '#'
			else if(checkIfString(arra[i]))
				stringToCombine += "$ ";//replaces string with '$'
			else if(!checkIfReserved(arra[i]))
				stringToCombine += "& ";//replaces ids with '&'
			else
				stringToCombine += arra[i] + " ";
		}

		//replaces <>+ values to single item for check purposes
		stringToCombine = stringToCombine.replaceAll("&\\s+", "&");
		stringToCombine = stringToCombine.replaceAll("&+", "& ");
		stringToCombine = stringToCombine.replaceAll("(OVERRIDING %)\\s+", "OVERRIDING %");
		stringToCombine = stringToCombine.replaceAll("(OVERRIDING %)+", "OVERRIDING % ");

		return stringToCombine;
	}

	/*
		compares a string to all known valid possibilites returning true if found and false otherwise.
	 */
	private boolean compareToKnownStrings(String infoToCheck, String [] arra){
		for(int i = 0; i < arra.length; i++){
			if(infoToCheck.equals(arra[i]))
				return true;
		}
		return false;
	}
}