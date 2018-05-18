package gov.nist.healthcare.hl7.mm.v2.script.execution;

import java.io.IOException;



import gov.nist.healthcare.hl7.mm.v2.domain.AssignmentCommand;
import gov.nist.healthcare.hl7.mm.v2.domain.Command;
import gov.nist.healthcare.hl7.mm.v2.domain.CommandType;
import gov.nist.healthcare.hl7.mm.v2.domain.ConstantValue;
import gov.nist.healthcare.hl7.mm.v2.domain.PresetValue;
import gov.nist.healthcare.hl7.mm.v2.domain.ReferenceValue;
import gov.nist.healthcare.hl7.mm.v2.domain.Value;
import gov.nist.healthcare.hl7.mm.v2.message.util.HL7MessageHandler;
import gov.nist.healthcare.hl7.mm.v2.nathancode.CommandExecutionException;
import gov.nist.healthcare.hl7.mm.v2.nathancode.Issue;
import gov.nist.healthcare.hl7.mm.v2.nathancode.IssueType;
import gov.nist.healthcare.hl7.mm.v2.nathancode.ModificationDetails;
import gov.nist.healthcare.hl7.mm.v2.nathancode.ReferenceParsed;
import gov.nist.healthcare.hl7.mm.v2.nathancode.SetCommand;

/**
 * @author Youssef
 *
 */
public class AssignCommandExecutor implements CommandExecutor {

	private HL7MessageHandler messageHandler;
	
	public AssignCommandExecutor(HL7MessageHandler messageHandler) {
		super();
		this.messageHandler = messageHandler;
	}

	
	public boolean handles(CommandType type) {
		return type == CommandType.ASSIGNMENT;
	}

	public <T extends Command> void execute(ExecutionContext context, T command) throws CommandExecutionException, IOException {
		AssignmentCommand assignmentCommand = (AssignmentCommand) command;
		String message;
		ModificationDetails modifyRequest = new ModificationDetails();

		try {
			Value value = assignmentCommand.getValue();
			String newValue = "";
			
			if(value instanceof ConstantValue) {
				newValue = ((ConstantValue) value).getConstant();	

			}
			else if(value instanceof ReferenceValue) {	
					ReferenceParsed ValueReferenced = ((ReferenceValue) value).getReference().toRefParsed();
					try {
						newValue = SetCommand.getValueFromHL7(context.getMessage(), ValueReferenced, modifyRequest);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (CommandExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		
				}

			
			else if (value instanceof PresetValue) {	
				if(context.getRandomValues().containsKey(((PresetValue) value).getId())) {
					newValue = context.getRandomValues().get(((PresetValue) value).getId());

				} else {
					Issue issueError = new Issue(IssueType.Error,"Unrecognized Preset Value :" + ((PresetValue) value).getId() + "\n" + "\n");
					throw new CommandExecutionException(issueError);
				}
			}

			
			
			
			message = messageHandler.set(assignmentCommand.getReference(), context.getMessage(), newValue);
			context.setMessage(message);
		} catch (CommandExecutionException e) {

	 		throw e;	
		}
		//message = modified
		//context.getMessage = original
		
	}

}
