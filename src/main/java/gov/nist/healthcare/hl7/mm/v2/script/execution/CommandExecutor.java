package gov.nist.healthcare.hl7.mm.v2.script.execution;

import java.io.IOException;

import gov.nist.healthcare.hl7.mm.v2.nathancode.CommandExecutionException;
import gov.nist.healthcare.hl7.mm.v2.nathancode.Issue;
import gov.nist.healthcare.hl7.mm.v2.nathancode.IssueType;
import gov.nist.healthcare.hl7.mm.v2.domain.Command;
import gov.nist.healthcare.hl7.mm.v2.domain.CommandType;

public interface CommandExecutor {
	
	/**
	 * This is a function that says whether or not a specific handler can handle this type of command.
	 * @param type
	 * @return boolean
	 */
	public boolean handles(CommandType type);
	/**
	 * This is the prototype of execute function that will execute a command accordingly to the type of the command given as @param
	 * @param context
	 * @param command
	 * @throws CommandExecutionException
	 * @throws IOException
	 */
	public <T extends Command> void execute(ExecutionContext context, T command) throws CommandExecutionException, IOException;
	
}
