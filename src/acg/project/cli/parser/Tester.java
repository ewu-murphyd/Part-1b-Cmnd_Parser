package acg.project.cli.parser;
import java.util.*;
import java.io.*;

import acg.project.action.ActionSet;
import acg.project.cli.CommandLineInterface;

public class Tester {	
	public static void main(String [] args) throws java.io.FileNotFoundException{
		String [] arra;
		// This is only used as a tester since all possible values are included
		Scanner sc = new Scanner(new File("testerInfo.txt"));
		CommandCleaner cCL = new CommandCleaner();
		CheckInfo checker = new CheckInfo();
		CommandLineInterface cli = new CommandLineInterface();
		ActionSet as = new ActionSet(cli);
		
		boolean checkInput = true;
		while(sc.hasNextLine() && checkInput){
			//an alternative for this line would be 'arra = cCL.getCommands()' which grabs the command by line
			arra = cCL.getCommands(sc.nextLine());
			checkInput = checker.checkArray(arra);
			
			for(int i = 0; i < arra.length; i++){
				CommandParser cp = new CommandParser(as, arra[i]);
				cp.interpret();
			}
		}

		cCL.close();
	//	sc.close();
	}
}
