package gov.nist.healthcare.hl7.mm.v2.functions;

import java.util.List;
import java.util.Map;

import gov.nist.healthcare.hl7.mm.v2.domain.Reference;
import gov.nist.healthcare.hl7.mm.v2.message.util.HL7MessageHandler;
import gov.nist.healthcare.hl7.mm.v2.nathancode.CommandExecutionException;
import gov.nist.healthcare.hl7.mm.v2.nathancode.Issue;
import gov.nist.healthcare.hl7.mm.v2.nathancode.IssueType;
import gov.nist.healthcare.hl7.mm.v2.procedure.Patient;
import gov.nist.healthcare.hl7.mm.v2.procedure.PatientType;
import gov.nist.healthcare.hl7.mm.v2.procedure.Transformer;
import gov.nist.healthcare.hl7.mm.v2.script.execution.ExecutionContext;

public class ShiftDates extends Function {
	
	  protected HL7MessageHandler messageHandler;
	  private String name;
  	AnonymizeAndUpdateRecordProcedure aurp = new AnonymizeAndUpdateRecordProcedure(messageHandler, name);


	public ShiftDates(HL7MessageHandler messageHandler, String name) {
		super(messageHandler, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(ExecutionContext context, Reference selector, Map<String, String> parameterMap)
			throws CommandExecutionException {

		List<String[]> fieldsList=null;
	    try {
	    	Transformer transformer = new Transformer();
		  fieldsList = aurp.readMessage(context.getMessage());

	    } catch (Throwable e) {
	      e.printStackTrace();
	    }
	    {
	    	aurp.init();
	      
	      for (String[] fields : fieldsList) {
	        String segmentName = fields[0];
	        if (segmentName.equals("MSH")) {
	        	aurp.determineMessageDate(fields);
	        	aurp.shiftDate(fields, 7);
	        } else if (segmentName.equals("PID")) {
	        	aurp.shiftDate(fields, 7);
	        } else if (segmentName.equals("PD1")) {
	        	aurp.shiftDate(fields, 13);
	        	aurp.shiftDate(fields, 17);
	        	aurp.shiftDate(fields, 18);
	        } else if (segmentName.equals("NK1")) {

	        } else if (segmentName.equals("ORC")) {

	        } else if (segmentName.equals("RXA")) {
	        	aurp.shiftDate(fields, 3);
	        	aurp.shiftDate(fields, 4);
	        	aurp.shiftDate(fields, 16);
	        } else if (segmentName.equals("OBX")) {
	        	aurp.shiftDate(fields, 14);
	          String obsType = aurp.readValue(fields, 3);
	          if (obsType.equals("29769-7")) {
	        	  aurp.shiftDate(fields, 5);
	          }
	        }

	      }
	    }

	    aurp.putMessageBackTogether(context, fieldsList);
	  		
	} 
			
	}


