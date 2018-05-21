package gov.nist.healthcare.hl7.mm.v2.functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import gov.nist.healthcare.hl7.mm.v2.domain.Reference;
import gov.nist.healthcare.hl7.mm.v2.message.util.HL7MessageHandler;
import gov.nist.healthcare.hl7.mm.v2.nathancode.CommandExecutionException;
import gov.nist.healthcare.hl7.mm.v2.nathancode.Issue;
import gov.nist.healthcare.hl7.mm.v2.nathancode.IssueType;
import gov.nist.healthcare.hl7.mm.v2.procedure.Patient;
import gov.nist.healthcare.hl7.mm.v2.procedure.PatientType;
import gov.nist.healthcare.hl7.mm.v2.procedure.Procedure;
import gov.nist.healthcare.hl7.mm.v2.procedure.TransformRequest;
import gov.nist.healthcare.hl7.mm.v2.procedure.Transformer;
import gov.nist.healthcare.hl7.mm.v2.script.execution.ExecutionContext;

public class AnonymizeAndUpdateRecordProcedure extends Function {
	
	private static final int MS_IN_DAY = 24 * 60 * 60 * 1000;

	  private Date messageDate;
	  private int daysToAdd;
	  private Date today;
	  private boolean isGirl = true;
	  public Transformer transformer;
	  private Patient patient;
	  private int orcCount = 0;
	  
	  protected HL7MessageHandler messageHandler;
	  private String name;

	  private static String asOfDate = null;

	public AnonymizeAndUpdateRecordProcedure(HL7MessageHandler messageHandler, String name, String asOfDate) {
		super(messageHandler, name);
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    try {
	      today = sdf.parse(asOfDate);
	    } catch (ParseException e) {
	      e.printStackTrace();
	    }
		// TODO Auto-generated constructor stub
	}

	public void setTransformer(Transformer transformer) {
	    this.transformer = transformer;
	  }
	
	
		  

		  public AnonymizeAndUpdateRecordProcedure(HL7MessageHandler messageHandler, String name) {
				super(messageHandler, name);
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		    try {
		      if (asOfDate == null) {
		        today = sdf.parse(sdf.format(new Date()));
		      } else {
		        today = sdf.parse(asOfDate);
		      }
		    } catch (ParseException e) {
		      e.printStackTrace();
		    }
		  }
	
	@Override
	public void execute(ExecutionContext context, Reference selector,Map<String, String> parameterMap) {
		List<String[]> fieldsList=null;
		if(parameterMap.containsKey("PATIENTTYPE")) {
			String patientType = parameterMap.get("PATIENTTYPE");
	    try {
	    	Transformer transformer = new Transformer();
			patient = transformer.setupPatient(PatientType.valueOf(patientType));	      

		  fieldsList = readMessage(context.getMessage());

	    } catch (Throwable e) {
	      e.printStackTrace();
	    }
	    {
	      init();
	      
	      for (String[] fields : fieldsList) {
	        String segmentName = fields[0];
	        if (segmentName.equals("MSH")) {
	          determineMessageDate(fields);
	          shiftDate(fields, 7);
	        } else if (segmentName.equals("PID")) {
	          shiftDate(fields, 7);
	          update(fields, 3, 1, patient.getMedicalRecordNumber());
	          isGirl = !readValue(fields, 8).equals("M");
	          updateName(fields, 5, 1, patient.getLastName());
	          updateName(fields, 5, 2, isGirl ? patient.getGirlName() : patient.getBoyName());
	          updateName(fields, 5, 3, isGirl ? patient.getMiddleNameGirl() : patient.getMiddleNameBoy());
	          updateName(fields, 6, 1, patient.getMotherMaidenName());
	          updateName(fields, 6, 2, patient.getMotherName());
	          update(fields, 11, 1, patient.getStreet());
	          update(fields, 11, 2, patient.getStreet2());
	          update(fields, 11, 3, patient.getCity());
	          update(fields, 11, 4, patient.getState());
	          update(fields, 11, 5, patient.getZip());
	          update(fields, 13, 6, patient.getPhoneArea());
	          update(fields, 13, 7, patient.getPhoneLocal());
	        } else if (segmentName.equals("PD1")) {
	          shiftDate(fields, 13);
	          shiftDate(fields, 17);
	          shiftDate(fields, 18);
	        } else if (segmentName.equals("NK1")) {
	          String relationship = readValue(fields, 3);
	          if (relationship.equals("MTH") || relationship.equals("GRD") || relationship.equals("")) {
	            updateName(fields, 2, 1, patient.getLastName());
	            updateName(fields, 2, 2, patient.getMotherName());
	            updateName(fields, 2, 2, patient.getMotherMaidenName());
	          } else if (relationship.equals("FTH")) {
	            updateName(fields, 2, 1, patient.getLastName());
	            updateName(fields, 2, 2, patient.getFatherName());
	            updateName(fields, 2, 2, patient.getDifferentLastName());
	          }
	          update(fields, 4, 1, patient.getStreet());
	          update(fields, 4, 2, patient.getStreet2());
	          update(fields, 4, 3, patient.getCity());
	          update(fields, 4, 4, patient.getState());
	          update(fields, 4, 5, patient.getZip());
	          update(fields, 5, 6, patient.getPhoneArea());
	          update(fields, 5, 7, patient.getPhoneLocal());
	        } else if (segmentName.equals("ORC")) {
	          orcCount++;
	          update(fields, 2, 1, "A" + patient.getMedicalRecordNumber() + "." + orcCount);
	          update(fields, 3, 1, "B" + patient.getMedicalRecordNumber() + "." + orcCount);
	        } else if (segmentName.equals("RXA")) {
	          shiftDate(fields, 3);
	          shiftDate(fields, 4);
	          shiftDate(fields, 16);
	        } else if (segmentName.equals("OBX")) {
	          shiftDate(fields, 14);
	          String obsType = readValue(fields, 3);
	          if (obsType.equals("29769-7")) {
	            shiftDate(fields, 5);
	          }
	        }

	      }
	    }

	    putMessageBackTogether(context, fieldsList);
	  		
	} else {
		
		Issue issueError = new Issue(IssueType.Warning,"Wrong arguments input for the command anonymizeAndUpdateRecordProcedure . The command will have no effect on the message. Choose the PatientType as an argument" + "\n" + "\n");
 		context.getIssues().add(issueError);
	}
	}
	
	public void update(String[] fields, int fieldPos, int subPos, String newName) {
	    String prevName = readValue(fields, fieldPos, subPos);
	    if (!prevName.equals("")) {
	      updateValue(newName, fields, fieldPos, subPos);
	    }
	  }

	  public void updateName(String[] fields, int fieldPos, int subPos, String newName) {
	    String prevName = readValue(fields, fieldPos, subPos);
	    if (!prevName.equals("")) {
	      if (prevName.length() < 3) {
	        if (newName.length() >= 3) {
	          newName = newName.substring(0, prevName.length());
	        }
	      }
	      updateValue(newName, fields, fieldPos, subPos);
	    }
	  }
	
	  List<String[]> readMessage(String message) throws IOException {
		    BufferedReader inResult = new BufferedReader(new StringReader(message));
		    String lineResult;
		    List<String[]> fieldsList = new ArrayList<String[]>();
		    while ((lineResult = inResult.readLine()) != null) {
		      lineResult = lineResult.trim();
		      String[] fields = lineResult.split("\\|");
		      if (fields.length > 0) {
		        fieldsList.add(fields);
		      }
		    }
		    inResult.close();
		    return fieldsList;
		  }

		  public void putMessageBackTogether(ExecutionContext context, List<String[]> fieldsList) {
		    String finalMessage = "";
		    for (String[] fields : fieldsList) {
		      if (fields[0].length() == 3) {
		        finalMessage += fields[0];
		        for (int i = 1; i < fields.length; i++) {
		          finalMessage += "|" + fields[i];
		        }
		        finalMessage += "\r";
		      }
		    }
		    context.setMessage(finalMessage);
		  }
		  
		 public  void init() {
			    messageDate = new Date();
			    daysToAdd = 0;
			  }
		  
		public  void shiftDate(String[] fields, int pos) {
			    if (fields.length > pos) {
			      updateValue(shiftDate(readValue(fields, pos)), fields, pos);
			    }
			  }

			  public String shiftDate(String originalDate) {
			    if (originalDate.length() >= 8) {
			      try {
			        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			        Date date = sdf.parse(originalDate.substring(0, 8));
			        Calendar calendar = Calendar.getInstance();
			        calendar.setTime(date);
			        calendar.add(Calendar.DAY_OF_MONTH, daysToAdd);
			        return sdf.format(calendar.getTime()) + originalDate.substring(8);
			      } catch (ParseException e) {
			        // ignore
			      }
			    }
			    return originalDate;
			  }
			  
			  public String readValue(String[] fields, int fieldPos) {
				    return readValue(fields, fieldPos, 1);
				  }

				  private String readValue(String[] fields, int fieldPos, int subPos) {
				    String value = "";
				    if (fields[0].equals("MSH") || fields[0].equals("FHS") || fields[0].equals("BHS")) {
				      fieldPos--;
				    }
				    if (fieldPos < fields.length) {
				      value = fields[fieldPos];
				      {
				        int posTilde = value.indexOf("~");
				        if (posTilde != -1) {
				          value = value.substring(0, posTilde);
				        }
				      }
				      while (subPos > 1) {
				        int posCaret = value.indexOf("^");
				        if (posCaret != -1) {
				          value = value.substring(posCaret + 1);
				        }
				        subPos--;
				      }
				      {
				        int posCaret = value.indexOf("^");
				        if (posCaret != -1) {
				          value = value.substring(0, posCaret);
				        }
				      }
				      {
				        int posAmpersand = value.indexOf("&");
				        if (posAmpersand != -1) {
				          value = value.substring(0, posAmpersand);
				        }
				      }
				    }
				    return value;
				  }
				  
				  public void updateValue(String updateValue, String[] fields, int fieldPos) {
					    updateValue(updateValue, fields, fieldPos, 1);
					  }

					  public void updateValue(String updateValue, String[] fields, int fieldPos, int subPos) {
					    if (fields[0].equals("MSH") || fields[0].equals("FHS") || fields[0].equals("BHS")) {
					      fieldPos--;
					    }
					    if (fieldPos < fields.length) {
					      String originalValue = fields[fieldPos];
					      int posStart = 0;
					      int posEnd = originalValue.length();
					      {
					        int posTilde = originalValue.indexOf("~");
					        if (posTilde != -1) {
					          posEnd = posTilde;
					        }
					      }
					      while (subPos > 1) {
					        int posCaret = originalValue.indexOf("^", posStart);
					        if (posCaret != -1) {
					          posStart = posCaret + 1;
					        } else {
					          originalValue += "^";
					          posStart = originalValue.length();
					        }
					        subPos--;
					      }
					      {
					        int posCaret = originalValue.indexOf("^", posStart);
					        if (posCaret != -1 && posCaret < posEnd) {
					          posEnd = posCaret;
					        }
					      }
					      {
					        int posAmpersand = originalValue.indexOf("&", posStart);
					        if (posAmpersand != -1 && posAmpersand < posEnd) {
					          posEnd = posAmpersand;
					        }
					      }
					      fields[fieldPos] = originalValue.substring(0, posStart) + updateValue + originalValue.substring(posEnd);
					    }
					  }
					  
					  public void determineMessageDate(String[] fields) {
						    String value = readValue(fields, 7);

						    if (value.length() >= 8) {
						      try {
						        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
						        messageDate = sdf.parse(value.substring(0, 8));

						        if (today.after(messageDate)) {
						          daysToAdd = (int) ((today.getTime() - messageDate.getTime()) / MS_IN_DAY);
						          daysToAdd--; // Don't set date to today, set it to yesterday so time
						                       // doesn't end in the future
						        }
						      } catch (ParseException e) {
						        // ignore
						      }
						    }
						  }

					public String showMeDocumentation() {
						// TODO Auto-generated method stub
						String doc = "The AnonymizeAndUpdateRecordProcedure is a function that allows to perform automatic modifications uppon a message. "
								+ "It takes as a parameter the patientType and changes the message accordingly by shiting date in specific fields "
								+ "and updating other fields containing names. Here is an example on how to use AnonymizeAndUpdateRecordProcedure :"
								+ "call AnonymizeAndUpdateRecordProcedure(\"PatientType\" => \"BABY\");";
						return doc;
					}

}
