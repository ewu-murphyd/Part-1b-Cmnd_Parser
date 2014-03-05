package acg.project.cli.parser;

import java.util.Scanner;

//wraps the scanner object and returns an array of strings
public class CommandCleaner{

	private Scanner input;
	
	//contructor initializes the scanner object
	public CommandCleaner(){
		input = new Scanner(System.in);
	}

	//method returns all commands on line in an array
	public String [] getCommands(){
		String s = "";
		s = input.nextLine();
		return getArra(s);
	}

	public String [] getCommands(String str){
		return getArra(str);
	}

	//closes the scanner object
	public void close(){
		input.close();
	}

	/*
	* Removes all additional whitespace
	* then splits by semicolon
	* then trims each array to remove beginning and trailing whitespace
	* the array is then returned
	*/
	private String [] getArra(String stringToParse){
		stringToParse = stringToParse.replaceAll("\\s+", " ");
		stringToParse = removeComment(stringToParse);
		String [] stringArray = stringToParse.split(";");
		int arraySize = stringArray.length;
		for(int i = 0; i < arraySize; i++){
			stringArray[i] = stringArray[i].trim();
		}

		//if a command is just a comment then null is returned
		if(stringArray[0].length() == 0)
				return null;
		return stringArray;
	}

	// removes comment
	private String removeComment(String stringtoRemoveFrom){
		int offset = stringtoRemoveFrom.indexOf("//");
		if(-1 != offset)
    		return stringtoRemoveFrom.substring(0, offset);
    	return stringtoRemoveFrom;
	}
}