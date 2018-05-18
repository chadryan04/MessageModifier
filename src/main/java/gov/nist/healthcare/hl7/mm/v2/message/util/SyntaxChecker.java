package gov.nist.healthcare.hl7.mm.v2.message.util;

import gov.nist.healthcare.hl7.mm.v2.domain.MMScript;
import gov.nist.healthcare.hl7.mm.v2.generated.ParseException;
import gov.nist.healthcare.hl7.mm.v2.generated.Parser;
import gov.nist.healthcare.hl7.mm.v2.generated.TokenMgrError;
import gov.nist.healthcare.hl7.mm.v2.nathancode.Issue;

public class SyntaxChecker {
	
	
	/**
	 * This function is used to checkSyntax. Takes a script as param and returns errors.
	 * The checkSyntax function will use the parser line by line to parse the script given as @param. 
	 * Once the script parsed, it will return errors that were found in the script. The errors will be
	 * displayed showing which column and line each error was found, what error was found and what token was expected
	 * 
	 * @param script
	 * @return String, errors found in the script
	 * @throws ParseException
	 * @throws TokenMgrError
	 */
	public String checkSyntax(String script) throws ParseException, TokenMgrError {
		MMScript mmScript = Parser.parseLineByLine(script);
		String result="";
		System.out.println("LOOOOK HEEEEERE : " + mmScript.getCommands().toString());
		for(Issue i : mmScript.getSyntax()) {
		    result = result + i.toString() +"\n";
		}
		if(mmScript.getSyntax().isEmpty()) {
			return "The syntax is correct";
		} else {
			return result;

		}
		
		

		
	}
	

}
