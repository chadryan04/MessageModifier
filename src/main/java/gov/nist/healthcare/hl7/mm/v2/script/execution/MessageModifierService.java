package gov.nist.healthcare.hl7.mm.v2.script.execution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gov.nist.healthcare.hl7.mm.v2.nathancode.CommandExecutionException;
import gov.nist.healthcare.hl7.mm.v2.nathancode.Issue;
import gov.nist.healthcare.hl7.mm.v2.nathancode.IssueType;

import gov.nist.healthcare.hl7.mm.v2.domain.Command;
import gov.nist.healthcare.hl7.mm.v2.domain.MMScript;
import gov.nist.healthcare.hl7.mm.v2.generated.ParseException;
import gov.nist.healthcare.hl7.mm.v2.generated.Parser;
import gov.nist.healthcare.hl7.mm.v2.generated.TokenMgrError;
import gov.nist.healthcare.hl7.mm.v2.message.util.HL7MessageHandler;
import gov.nist.healthcare.hl7.mm.v2.message.util.SimpleHL7MessageHandler;

/**
 * @author Youssef
 * This is the class where all the logic of modifying a message happens
 */
public class MessageModifierService {
	
	List<CommandExecutor> commandExecutors = new ArrayList<CommandExecutor>();
	private HL7MessageHandler messageHandler;
	
	/**
	 * This is a constructor that create all type of CommandExecutor already implemented
	 */
	
	public MessageModifierService(){
		this.messageHandler = new SimpleHL7MessageHandler();
		this.commandExecutors.add(new UseCommandExecutor());
		this.commandExecutors.add(new CallCommandExecutor(messageHandler));
		this.commandExecutors.add(new AssignCommandExecutor(this.messageHandler));
		this.commandExecutors.add(new ConditionalCommandExcecutor(this.messageHandler));
	}
	
	/**
	 * This functions takes as @param an initial message and a
	 * script and performs a modification to return as a result a modificationResult object
	 * containing a final message which is the initial message after transformation and errors 
	 * concerning script parsing. Calling this method will result in parsing the script given as @param, 
	 * then storing the script's commands and executing then one by one. If any error is found, the method
	 *  will return errors, else the initial message will be modified and stored in the final message.
	 * 
	 * @param msg
	 * @param scr
	 * @return ModificationResult
	 */
	public ModificationResult modify(String msg, String scr){
		ExecutionContext context = new ExecutionContext();
		MMScript script = this.parseScript(context, scr);
		context.setMessageOriginal(msg);
		context.setMessage(msg);
		if(script != null){
			context.setScript(script);
//			Issue issue = new Issue(IssueType.Information,"The executed script is : " +"\n" + scr + "\n"+"\n");
//			context.issues.add(issue);
			for(Command c : script.getCommands()){
				try {
					this.execute(context, c);
//					Issue issueInformation = new Issue(IssueType.Information,"The resulted message of the execution of the command " + c.toString() + " is : " +"\n"+ context.getMessage()+"\n"+"\n");
//					context.issues.add(issueInformation);
				} 
				catch (CommandExecutionException e) {
					context.issues.add(e.getIssue());
				}
			}
//			Issue issueExecutedCommands = new Issue(IssueType.Information,"The number of executed commands is : " + context.getExecutedCommands() +"\n"+"\n");
//			context.issues.add(issueExecutedCommands);
		}

		return new ModificationResult(context.getIssues(), script != null, context.getExecutedCommands(), context.getMessage());		
	}
	
	/**
	 * This function is used in the modify function below, it hundles the parsing of the script
	 * @param context
	 * @param scr
	 * @return MMScript, an object containing the parsed script with all the commands 
	 */
	private MMScript parseScript(ExecutionContext context, String scr){
		try {
			return Parser.parse(scr);
		} catch (ParseException e) {
			Issue issue = new Issue(IssueType.Error,e.getMessage());
			context.issues.add(issue);
			e.printStackTrace();
			return null;
		} catch (TokenMgrError e) {		
			Issue issue = new Issue(IssueType.Error,e.getMessage());
			context.issues.add(issue);
			e.printStackTrace();

			return null;
		}
	}
	
	/**
	 * this function is used in the modify function below and it executes command
	 *  by command after cheking which CommandExecutor can execute which command
	 * @param context
	 * @param c
	 * @throws CommandExecutionException
	 */
	private void execute(ExecutionContext context, Command c) throws CommandExecutionException{
		for(CommandExecutor exec : commandExecutors){
			if(exec.handles(c.getType())){
				try {
					exec.execute(context, c);
				} catch (IOException e) {
					Issue issue = new Issue(IssueType.Warning,"Couldn't excecute commands in ModifierService :" + e.getMessage());
			 		throw new CommandExecutionException(issue);	
			 		
				}
				context.incExec();
			}
		}
	}
}
