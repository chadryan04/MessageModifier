package gov.nist.healthcare.hl7.mm.v2.procedure;

import java.io.IOException;
import java.util.Map;

import gov.nist.healthcare.hl7.mm.v2.domain.Reference;
import gov.nist.healthcare.hl7.mm.v2.message.util.HL7MessageHandler;
import gov.nist.healthcare.hl7.mm.v2.nathancode.CommandExecutionException;
import gov.nist.healthcare.hl7.mm.v2.script.execution.ExecutionContext;

	
	public abstract class Procedure {
		protected HL7MessageHandler messageHandler;
		private String name;
		
		
	    public Procedure(HL7MessageHandler messageHandler, String name) {
			super();
			this.messageHandler = messageHandler;
			this.name = name;
		}
	    
		public abstract void execute(TransformRequest transformRequest, Map<String, String> parameterMap) throws IOException;


}