package gov.nist.healthcare.hl7.mm.v2.functions;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.jws.soap.SOAPBinding.ParameterStyle;

import gov.nist.healthcare.hl7.mm.v2.domain.PresetValue;
import gov.nist.healthcare.hl7.mm.v2.domain.Reference;
import gov.nist.healthcare.hl7.mm.v2.message.util.HL7MessageHandler;
import gov.nist.healthcare.hl7.mm.v2.nathancode.CommandExecutionException;
import gov.nist.healthcare.hl7.mm.v2.nathancode.Issue;
import gov.nist.healthcare.hl7.mm.v2.nathancode.IssueType;
import gov.nist.healthcare.hl7.mm.v2.procedure.Patient;
import gov.nist.healthcare.hl7.mm.v2.procedure.PatientType;
import gov.nist.healthcare.hl7.mm.v2.procedure.Transformer;
import gov.nist.healthcare.hl7.mm.v2.script.execution.ExecutionContext;

public class SetupRandomData extends Function {

	public SetupRandomData(HL7MessageHandler messageHandler, String name) {
		super(messageHandler, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(ExecutionContext context, Reference selector, Map<String, String> parameterMap)
			throws CommandExecutionException {
		Map<String, String> randomValues = new HashMap<>();
		Transformer transformer = new Transformer();
		Patient patient = new Patient();
		if(parameterMap.containsKey("PATIENTTYPE")) {
			String patientType = parameterMap.get("PATIENTTYPE");
			patient = transformer.setupPatient(PatientType.valueOf(patientType));

            for(Field f : Patient.class.getDeclaredFields()){
            	if(f.getType().isAssignableFrom(String.class)) {
            	System.out.println("REGARDE ICI D'ABORD " +f.getName());
            	String value="Failed try catch in SetupRandomDataFunction";
				try {
					value = (String) f.get(patient);

				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	randomValues.put(f.getName(),value);
            	context.setRandomValues(randomValues);
            }
            }        
            
            String s = "You have called SetupRandomData function. Here are the different rondomized data types that can be used :" + "\n";
            
            
            Issue informationSetupRandomData = new Issue(IssueType.Information,"You have called SetupRandomData function. Here are the different rondomized data types that can be used :"  + "\n" +randomValues.keySet().toString()  + "\n");
            context.getIssues().add(informationSetupRandomData);
//            System.out.println(informationSetupRandomData.getDescription());
            	
			
		}
		
	}

}
