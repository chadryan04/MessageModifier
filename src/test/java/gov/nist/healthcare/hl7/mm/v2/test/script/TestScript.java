package gov.nist.healthcare.hl7.mm.v2.test.script;

import java.io.IOException;
import java.util.List;



import gov.nist.healthcare.hl7.mm.v2.domain.CallCommand;
import gov.nist.healthcare.hl7.mm.v2.domain.Component;
import gov.nist.healthcare.hl7.mm.v2.domain.Field;
import gov.nist.healthcare.hl7.mm.v2.domain.HL7Path;
import gov.nist.healthcare.hl7.mm.v2.domain.MMScript;
import gov.nist.healthcare.hl7.mm.v2.domain.Reference;
import gov.nist.healthcare.hl7.mm.v2.domain.Segment;
import gov.nist.healthcare.hl7.mm.v2.generated.ParseException;
import gov.nist.healthcare.hl7.mm.v2.generated.Parser;
import gov.nist.healthcare.hl7.mm.v2.generated.TokenMgrError;
import gov.nist.healthcare.hl7.mm.v2.message.util.SyntaxChecker;
import gov.nist.healthcare.hl7.mm.v2.script.execution.MessageModifierService;
import gov.nist.healthcare.hl7.mm.v2.script.execution.ModificationResult;

public class TestScript {
	
	public static void main(String[] args) {
		MessageModifierService mms = new MessageModifierService();
		String message = "MSH|^~\\&|Test EHR Application|X68||NIST Test Iz Reg|20120701082240-0500||VXU^V04^VXU_V04|NIST-IZ-001.00|P|2.5.1|||ER|AL|||||Z22^CDCPHINVS\n" + 
    			"PID|1||D26376273^^^NIST MPI^MR||Snow^Madelynn^Ainsley^^^^L|Lam^Morgan^^^^^M|20070706|F||2076-8^Native Hawaiian or Other Pacific Islander^CDCREC|32 Prescott Street Ave^^Warwick^MA^02452^USA^L||^PRN^PH^^^657^5558563|||||||||2186-5^non Hispanic or Latino^CDCREC\n" + 
    			"PD1|||||||||||02^Reminder/Recall - any method^HL70215|||||A|20120701|20120701\n" + 
    			"NK1|1|Lam^Morgan^^^^^L|MTH^Mother^HL70063|32 Prescott Street Ave^^Warwick^MA^02452^USA^L|^PRN^PH^^^657^5558563\n" + 
    			"ORC|RE||IZ-783274^NDA|||||||I-23432^Burden^Donna^A^^^^^NIST-AA-1^^^^PRN||57422^RADON^NICHOLAS^^^^^^NIST-AA-1^L^^^MD\n" + 
    			"RXA|0|1|20120814||33332-0010-01^Influenza, seasonal, injectable, preservative free^NDC|0.5|mL^MilliLiter [SI Volume Units]^UCUM||00^New immunization record^NIP001|7832-1^Lemon^Mike^A^^^^^NIST-AA-1^^^^PRN|^^^X68||||Z0860BB|20121104|CSL^CSL Behring^MVX|||CP|A\n" + 
    			"RXR|C28161^Intramuscular^NCIT|LD^Left Arm^HL70163\n" + 
    			"OBX|1|CE|64994-7^Vaccine funding program eligibility category^LN|1|V05^VFC eligible - Federally Qualified Health Center Patient (under-insured)^HL70064||||||F|||20120701|||VXC40^Eligibility captured at the immunization level^CDCPHINVS\n" + 
    			"OBX|2|CE|30956-7^vaccine type^LN|2|88^Influenza, unspecified formulation^CVX||||||F\n" + 
    			"OBX|3|TS|29768-9^Date vaccine information statement published^LN|2|20120702||||||F\n" + 
    			"OBX|4|TS|29769-7^Date vaccine information statement presented^LN|2|20120814||||||F";
		
		String script = " call setupRandomData(\"PatientType\" => \"BABY\");" +"PID-1=[fatherName];";
		ModificationResult modificationResult = new ModificationResult();
		modificationResult = mms.modify(message, script);
		System.out.println(modificationResult.getModificationDetailsList(true));
		modificationResult.getModificationDetailsList(true);
		System.out.println(message);
		System.out.println("-------------------------------------------------------");
		System.out.println(modificationResult.getResultMessage());
		System.out.println("-------------------------------------------------------");
		System.out.println(modificationResult.getModificationDetailsList(true));

		
		
		
//		SyntaxChecker sc = new SyntaxChecker();
//		try {
//			System.out.println(sc.checkSyntax(script));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (TokenMgrError e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//		String script = "PID-5.1=Watson;";
//		String msg = "PID|||Q63W1^^^AIRA-TEST^MR||Holmes^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus&Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
//		String msg = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
//		modificationResult = mms.modify(msg,script);
//		System.out.println(modificationResult.getModificationDetailsList(true));
//		System.out.println(modificationResult.getResultMessage());
//		System.out.println(modificationResult.getNumberOfExecutedCommands());
//		String string1 = "PID|||Q63W1^^^AIRA-TEST^MR||^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
//		String string2 = "PID|||Q63W1^^^AIRA-TEST^MR||^Jeramiah^Z^IV^^^L|Monroe^Arden|20160626|M|||155 Lewis Cir^^Cadmus^MI^49221^USA^P||^PRN^PH^^^517^3004208|";
//		assert(string1.equals(string2));
//		System.out.println(modificationResult.getNumberOfExecutedCommands());
//
//		List<Issue> issues = modificationResult.getIssues();
//		for(Issue e:issues) {
//			System.out.println(e.toString());
//			
			
			
			
//		MessageModifierService mms = new MessageModifierService();
//		String script = "for $RXA-5.2 call map(\"PCV\" => \"ABC\", \"Default\" => \"Unknown\");";
//		ModificationResult modificationResult = new ModificationResult();
//		String msg = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
//		modificationResult = mms.modify(msg,script);
//		System.out.println(modificationResult.getResultMessage());
//		System.out.println(modificationResult.getNumberOfExecutedCommands());
//
//		List<Issue> issues = modificationResult.getIssues();
//		for(Issue e:issues) {
//			System.out.println(e.toString());
//			
//		}
		
//		MessageModifierService mms = new MessageModifierService();
//		String script = "for $RXA-9.2 call trunc(\"max\" => \"5\");";
//		ModificationResult modificationResult = new ModificationResult();
//		String msg = "RXA|0|1|20170104||133^PCV 13^CVX|0.5|mL^milliliters^UCUM||00^Administered^NIP001||||||Q8846RW||WAL^Wyeth^MVX||||A|";
//		modificationResult = mms.modify(msg,script);
//		System.out.println(modificationResult.getResultMessage());
//		System.out.println(modificationResult.getNumberOfExecutedCommands());
//
//		List<Issue> issues = modificationResult.getIssues();
//		for(Issue e:issues) {
//			System.out.println(e.toString());
				
				
				
		
			
			
//		MMScript mmScript;
//		try {
//			mmScript = Parser.parse("for $RXA-5.2 call map(\"PCV\" => \"ABC\", \"Default\" => \"Unknown\");");
//	
//
//		
//
//		CallCommand command = ((CallCommand) mmScript.getCommands().get(0));
//		System.out.println(command.getArgs().get(0).getName());
//			
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (TokenMgrError e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//			
//			
//		}

		
	


		
//		ReferenceParsed t = new ReferenceParsed();
//		t.setSegmentName("PID");
//		t.setFieldPos(5);
//		ModificationDetails modifyRequest = new ModificationDetails();
//		try {
//			String messageFinal = SetCommand.setValueInHL7("AAAAAAAAAAAAAAAAAAAAA",msg, t, modifyRequest);
//			System.out.println(messageFinal);
//
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (CommandExecutionException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

	}
	

	}

	

