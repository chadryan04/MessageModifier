package gov.nist.healthcare.hl7.mm.v2.procedure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;




public class Transformer {
	 private static final String REP_PAT_EMAIL = "[EMAIL]";
	  private static final String REP_PAT_PHONE = "[PHONE]";
	  private static final String REP_PAT_PHONE_AREA = "[PHONE_AREA]";
	  private static final String REP_PAT_PHONE_LOCAL = "[PHONE_LOCAL]";
	  private static final String REP_PAT_PHONE_ALT = "[PHONE_ALT]";
	  private static final String REP_PAT_PHONE_ALT_AREA = "[PHONE_ALT_AREA]";
	  private static final String REP_PAT_PHONE_ALT_LOCAL = "[PHONE_ALT_LOCAL]";
	  private static final String REP_PAT_VAC3_DATE = "[VAC3_DATE]";
	  private static final String REP_PAT_LANGUAGE = "[LANGUAGE]";
	  private static final String REP_PAT_ETHNICITY_LABEL = "[ETHNICITY_LABEL]";
	  private static final String REP_PAT_ETHNICITY = "[ETHNICITY]";
	  private static final String REP_PAT_RACE_LABEL = "[RACE_LABEL]";
	  private static final String REP_PAT_RACE = "[RACE]";
	  private static final String REP_PAT_SSN = "[SSN]";
	  private static final String REP_PAT_MRN = "[MRN]";
	  private static final String REP_PAT_WIC = "[WIC]";
	  private static final String REP_PAT_MEDICAID = "[MEDICAID]";
	  private static final String REP_PAT_BIRTH_ORDER = "[BIRTH_ORDER]";
	  private static final String REP_PAT_BIRTH_MULTIPLE = "[BIRTH_MULTIPLE]";
	  private static final String REP_PAT_DOB = "[DOB]";
	  private static final String REP_PAT_MOTHER_MAIDEN = "[MOTHER_MAIDEN]";
	  private static final String REP_PAT_MOTHER_DOB = "[MOTHER_DOB]";
	  private static final String REP_PAT_MOTHER_SSN = "[MOTHER_SSN]";
	  private static final String REP_PAT_MOTHER = "[MOTHER]";
	  private static final String REP_PAT_SUFFIX = "[SUFFIX]";
	  private static final String REP_PAT_FATHER = "[FATHER]";
	  private static final String REP_PAT_GENDER = "[GENDER]";
	  private static final String REP_PAT_BOY_OR_GIRL = "[BOY_OR_GIRL]";
	  private static final String REP_PAT_GIRL = "[GIRL]";
	  private static final String REP_PAT_BOY = "[BOY]";
	  private static final String REP_PAT_ALIAS_BOY_OR_GIRL = "[ALIAS_BOY_OR_GIRL]";
	  private static final String REP_PAT_ALIAS_GIRL = "[ALIAS_GIRL]";
	  private static final String REP_PAT_ALIAS_BOY = "[ALIAS_BOY]";

	  private static final String REP_ENTERED_BY_FIRST = "[ENTERED_BY_FIRST]";
	  private static final String REP_ENTERED_BY_LAST = "[ENTERED_BY_LAST]";
	  private static final String REP_ENTERED_BY_MIDDLE = "[ENTERED_BY_MIDDLE]";
	  private static final String REP_ENTERED_BY_NPI = "[ENTERED_BY_NPI]";
	  private static final String REP_ORDERED_BY_FIRST = "[ORDERED_BY_FIRST]";
	  private static final String REP_ORDERED_BY_LAST = "[ORDERED_BY_LAST]";
	  private static final String REP_ORDERED_BY_MIDDLE = "[ORDERED_BY_MIDDLE]";
	  private static final String REP_ORDERED_BY_NPI = "[ORDERED_BY_NPI]";
	  private static final String REP_ADMIN_BY_FIRST = "[ADMIN_BY_FIRST]";
	  private static final String REP_ADMIN_BY_LAST = "[ADMIN_BY_LAST]";
	  private static final String REP_ADMIN_BY_MIDDLE = "[ADMIN_BY_MIDDLE]";
	  private static final String REP_ADMIN_BY_NPI = "[ADMIN_BY_NPI]";
	  private static final String REP_RESPONSIBLE_ORG_NAME = "[RESPONSIBLE_ORG_NAME]";
	  private static final String REP_RESPONSIBLE_ORG_ID = "[RESPONSIBLE_ORG_ID]";
	  private static final String REP_ADMIN_ORG_1_NAME = "[ADMIN_ORG_1_NAME]";
	  private static final String REP_ADMIN_ORG_1_ID = "[ADMIN  Q_ORG_1_ID]";
	  private static final String REP_ADMIN_ORG_2_NAME = "[ADMIN_ORG_2_NAME]";
	  private static final String REP_ADMIN_ORG_2_ID = "[ADMIN_ORG_2_ID]";

	  private static final String REP_CON_USERID = "[USERID]";
	  private static final String REP_CON_PASSWORD = "[PASSWORD]";
	  private static final String REP_CON_FACILITYID = "[FACILITYID]";
	  private static final String REP_CON_FILENAME = "[FILENAME]";
	  private static final String REP_CON_OTHERID = "[OTHERID]";

	  private static final String INSERT_SEGMENT = "insert segment ";
	  private static final String INSERT_SEGMENT_FIRST = "first";
	  private static final String INSERT_SEGMENT_BEFORE = "before";
	  private static final String INSERT_SEGMENT_AFTER = "after";
	  private static final String INSERT_SEGMENT_LAST = "last";
	  private static final String INSERT_SEGMENT_IF_MISSING = "if missing";
	  private static final String INSERT_SEGMENT_IF_MISSING_FROM_MESSAGE = "if missing from message";

	  private static final String RUN_PROCEDURE = "run procedure";

	  private static final String REMOVE_REPEAT = "remove repeat"; // remove repeat
	                                                               // PID-5.5 valued
	                                                               // MA
	  private static final String REMOVE_SEGMENT = "remove segment ";
	  private static final String REMOVE_OBSERVATION = "remove observation ";
	  private static final String REMOVE_EMPTY_OBSERVATIONS = "remove empty observations";

	  private static final String CLEAR = "clear";

	  private static final String CLEAN = "clean";
	  private static final String CLEAN_NO_LAST_SLASH = "no last slash";
	  private static final String FIX = "fix";
	  private static final String FIX_AMPERSAND = "ampersand";
	  private static final String FIX_ESCAPE = "escape";
	  private static final String FIX_MISSING_MOTHER_MAIDEN_FIRST = "missing mother maiden first";

	  private static final String IF_18_PLUS = "if 18+";
	  private static final String IF_19_PLUS = "if 19+";
	  private static final String IF_18_MINUS = "if 18-";
	  private static final String IF_19_MINUS = "if 19-";

	  private static final int VACCINE_CVX = 0;
	  private static final int VACCINE_NAME = 1;
	  private static final int VACCINE_LOT = 2;
	  private static final int VACCINE_MVX = 3;
	  private static final int VACCINE_MANUFACTURER = 4;
	  private static final int VACCINE_TRADE_NAME = 5;
	  private static final int VACCINE_AMOUNT = 6;
	  private static final int VACCINE_ROUTE = 7;
	  private static final int VACCINE_SITE = 8;
	  private static final int VACCINE_VIS_PUB = 9;
	  private static final int VACCINE_VIS_PUB_CODE = 10;
	  private static final int VACCINE_VIS_PUB_DATE = 11;
	  private static final int VACCINE_VIS2_PUB = 12;
	  private static final int VACCINE_VIS2_PUB_CODE = 13;
	  private static final int VACCINE_VIS2_PUB_DATE = 14;
	  private static final int VACCINE_VIS3_PUB = 15;
	  private static final int VACCINE_VIS3_PUB_CODE = 16;
	  private static final int VACCINE_VIS3_PUB_DATE = 17;


	  private static int medicalRecordNumberInc = 0;
	  private static Random random = new Random();
	  private static Map<String, List<String[]>> conceptMap = null;
	  private static Map<String, List<String[]>> testDataMap = null;

	
	  public Patient setupPatient(PatientType patientType) {

		    Patient patient = new Patient();
		    if (patientType == PatientType.NONE) {
		      return patient;
		    }

		    medicalRecordNumberInc++;
		    patient.setMedicalRecordNumber("" + (char) (random.nextInt(26) + 'A') + random.nextInt(10)
		        + random.nextInt(10) + (char) (random.nextInt(26) + 'A') + medicalRecordNumberInc);
		    patient.setSsn("" + random.nextInt(10) + random.nextInt(10) + random.nextInt(10)
		        + random.nextInt(10) + random.nextInt(10) + random.nextInt(10) + random.nextInt(10)
		        + random.nextInt(10) + random.nextInt(10));
		    patient.setMotherSsn("" + random.nextInt(10) + random.nextInt(10) + random.nextInt(10)
		        + random.nextInt(10) + random.nextInt(10) + random.nextInt(10) + random.nextInt(10)
		        + random.nextInt(10) + random.nextInt(10));
		    patient.setMedicaidNumber("" + random.nextInt(10) + random.nextInt(10) + random.nextInt(10)
		        + random.nextInt(10) + random.nextInt(10) + random.nextInt(10) + random.nextInt(10)
		        + random.nextInt(10) + random.nextInt(10) + random.nextInt(10) + random.nextInt(10));
		    patient.setWic("" + random.nextInt(10) + random.nextInt(10) + random.nextInt(10)
		        + random.nextInt(10) + random.nextInt(10) + random.nextInt(10) + random.nextInt(10)
		        + random.nextInt(10) + random.nextInt(10));
		    patient.setBoyName(getRandomValue("BOY"));
		    patient.setGirlName(getRandomValue("GIRL"));
		    patient.setAliasBoy(getRandomValue("BOY"));
		    patient.setAliasGirl(getRandomValue("GIRL"));
		    patient.setMotherName(getRandomValue("GIRL"));
		    patient.setMotherMaidenName(getRandomValue("LAST_NAME"));
		    patient.setFatherName(getRandomValue("BOY"));
		    patient.setLastName(getRandomValue("LAST_NAME"));
		    patient.setDifferentLastName(getRandomValue("LAST_NAME"));
		    patient.setMiddleNameBoy(getRandomValue("BOY"));
		    patient.setMiddleNameGirl(getRandomValue("GIRL"));
		    String[] dates = new String[4];
		    patient.setDates(dates);
		    patient.setVaccineType(createDates(dates, patientType));
		    patient.setMotherDob(makeMotherDob(dates[0]));
		    patient.setGender(random.nextBoolean() ? "F" : "M");
		    patient.setVaccine1(
		        getValueArray("VACCINE_" + patient.getVaccineType(), VACCINE_VIS_PUB_DATE + 1));
		    patient.setVaccine2(
		        getValueArray("VACCINE_" + patient.getVaccineType(), VACCINE_VIS_PUB_DATE + 1));
		    patient.setVaccine3(
		        getValueArray("VACCINE_" + patient.getVaccineType(), VACCINE_VIS_PUB_DATE + 1));
		    patient.setCombo(getValueArray("VACCINE_COMBO", VACCINE_VIS3_PUB_DATE + 1));
		    patient.setRace(getValueArray("RACE", 2));
		    patient.setEthnicity(getValueArray("ETHNICITY", 2));
		    patient.setLanguage(getValueArray("LANGUAGE", 2));
		    patient.setAddress(getValueArray("ADDRESS", 4));
		    if (PatientType.ADULT == patientType) {
		      patient.setVfc(new String[] {"V01", "Not VFC eligible"});
		    } else {
		      patient.setVfc(getValueArray("VFC", 2));
		    }
		    patient.setSuffix(getRandomValue("SUFFIX"));
		    patient.setStreet((random.nextInt(400) + 1) + " " + getRandomValue("LAST_NAME") + " "
		        + getRandomValue("STREET_ABBREVIATION"));
		    patient.setStreet2("APT #" + (random.nextInt(400) + 1));
		    patient.setCity(patient.getAddress()[0]);
		    patient.setState(patient.getAddress()[1]);
		    patient.setZip(patient.getAddress()[2]);
		    patient.setPhoneArea(patient.getAddress()[3]);
		    patient.setPhoneLocal("" + (random.nextInt(8) + 2) + random.nextInt(10) + random.nextInt(10)
		        + random.nextInt(10) + random.nextInt(10) + random.nextInt(10) + random.nextInt(10));
		    patient.setPhone("(" + patient.getPhoneArea() + ")" + patient.getPhoneLocal());
		    patient.setPhoneAltArea(patient.getAddress()[3]);
		    patient.setPhoneAltLocal("" + (random.nextInt(8) + 2) + random.nextInt(10) + random.nextInt(10)
		        + random.nextInt(10) + random.nextInt(10) + random.nextInt(10) + random.nextInt(10));
		    patient.setPhoneAlt("(" + patient.getPhoneAltArea() + ")" + patient.getPhoneAltLocal());
		    if (PatientType.ADULT == patientType) {
		      if (patient.getGender().equals("M")) {
		        patient.setEmail(patient.getBoyName().toLowerCase() + "."
		            + patient.getLastName().toLowerCase() + "@madeupemailaddress.com");
		      } else {
		        patient.setEmail(patient.getGirlName().toLowerCase() + "."
		            + patient.getLastName().toLowerCase() + "@madeupemailaddress.com");
		      }
		    } else {
		      patient.setEmail(patient.getMotherName().toLowerCase() + "."
		          + patient.getLastName().toLowerCase() + "@madeupemailaddress.com");
		    }
		    patient.setBirthCount(makeBirthCount());
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		    Calendar calendar = Calendar.getInstance();
		    calendar.add(Calendar.YEAR, 1);
		    patient.setFuture(sdf.format(calendar.getTime()));
		    {
		      boolean enteredByBoy = random.nextBoolean();
		      patient.setEnteredByFirstName(getRandomValue(enteredByBoy ? "BOY" : "GIRL"));
		      patient.setEnteredByMiddleName(getRandomValue(enteredByBoy ? "BOY" : "GIRL"));
		      patient.setEnteredByLastName(getRandomValue("LAST_NAME"));
		      patient.setEnteredByNPI("" + random.nextInt(10) + random.nextInt(10) + random.nextInt(10)
		          + random.nextInt(10) + random.nextInt(10) + random.nextInt(10) + random.nextInt(10)
		          + random.nextInt(10) + random.nextInt(10) + random.nextInt(10));
		    }
		    {
		      boolean orderedByBoy = random.nextBoolean();
		      patient.setOrderedByFirstName(getRandomValue(orderedByBoy ? "BOY" : "GIRL"));
		      patient.setOrderedByMiddleName(getRandomValue(orderedByBoy ? "BOY" : "GIRL"));
		      patient.setOrderedByLastName(getRandomValue("LAST_NAME"));
		      patient.setOrderedByNPI("" + random.nextInt(10) + random.nextInt(10) + random.nextInt(10)
		          + random.nextInt(10) + random.nextInt(10) + random.nextInt(10) + random.nextInt(10)
		          + random.nextInt(10) + random.nextInt(10) + random.nextInt(10));
		    }
		    {
		      boolean adminByBoy = random.nextBoolean();
		      patient.setAdminByFirstName(getRandomValue(adminByBoy ? "BOY" : "GIRL"));
		      patient.setAdminByMiddleName(getRandomValue(adminByBoy ? "BOY" : "GIRL"));
		      patient.setAdminByLastName(getRandomValue("LAST_NAME"));
		      patient.setAdminByNPI("" + random.nextInt(10) + random.nextInt(10) + random.nextInt(10)
		          + random.nextInt(10) + random.nextInt(10) + random.nextInt(10) + random.nextInt(10)
		          + random.nextInt(10) + random.nextInt(10) + random.nextInt(10));
		    }
		    patient.setResponsibleOrg(getValueArray("RESPONSIBLE ORG", 2));
		    if (patient.getResponsibleOrg()[0].equals("") && patient.getResponsibleOrg()[1].equals("")) {
		      patient.getResponsibleOrg()[0] = "101";
		      patient.getResponsibleOrg()[1] =
		          getRandomValue("LAST_NAME") + (random.nextBoolean() ? " Family Clinic" : " Pediatrics");
		    }
		    patient.setAdminOrg1(getValueArray("ADMIN ORG 1", 2));
		    if (patient.getAdminOrg1()[0].equals("") && patient.getAdminOrg1()[1].equals("")) {
		      patient.getAdminOrg1()[0] = patient.getResponsibleOrg()[0] + "-" + "01";
		      patient.getAdminOrg1()[1] =
		          patient.getResponsibleOrg()[1] + " - " + getRandomValue("LAST_NAME");
		    }
		    patient.setAdminOrg2(getValueArray("ADMIN ORG 2", 2));
		    if (patient.getAdminOrg2()[0].equals("") && patient.getAdminOrg2()[1].equals("")) {
		      patient.getAdminOrg2()[0] = patient.getResponsibleOrg()[0] + "-" + "02";
		      patient.getAdminOrg2()[1] =
		          patient.getResponsibleOrg()[1] + " - " + getRandomValue("LAST_NAME");
		    }
		    return patient;
		  }
	  
	  public String getRandomValue(String concept) {
		    try {
		      return getValue(concept, 0);
		    } catch (IOException ioe) {
		      return "Unable to get value: " + ioe.getMessage();
		    }
		  }
	  public String getValue(String concept, int pos) throws IOException {
		    if (conceptMap == null) {
		      init();
		    }
		    if (testDataMap != null) {
		      List<String[]> valueList = testDataMap.get(concept);
		      if (valueList != null) {
		        return getRandomValue(pos, valueList);
		      }
		    }
		    List<String[]> valueList = conceptMap.get(concept);
		    if (valueList != null) {
		      return getRandomValue(pos, valueList);
		    }
		    return "";
		  }
	  
	  public String getRandomValue(int pos, List<String[]> valueList) {
		    String[] values = valueList.get(random.nextInt(valueList.size()));
		    if (pos < values.length) {
		      return values[pos];
		    }
		    return "";
		  }
	  
	  protected void init() {
		    try {
		      BufferedReader in = new BufferedReader(
		          new InputStreamReader(getClass().getResourceAsStream("transform.txt")));
		      conceptMap = readDataIn(in);
		    } catch (IOException e) {
		      e.printStackTrace();
		      conceptMap = new HashMap<String, List<String[]>>();
		    }
		  }
	  
	  public HashMap<String, List<String[]>> readDataIn(BufferedReader in) throws IOException {
		    HashMap<String, List<String[]>> map = new HashMap<String, List<String[]>>();
		    String line;
		    while ((line = in.readLine()) != null) {
		      int equals = line.indexOf("=");
		      if (equals != -1) {
		        String concept = line.substring(0, equals);
		        String[] values = line.substring(equals + 1).split("\\,");
		        List<String[]> valueList = map.get(concept);
		        if (valueList == null) {
		          valueList = new ArrayList<String[]>();
		          map.put(concept, valueList);
		        }
		        valueList.add(values);
		      }
		    }
		    return map;
		  }
	  
	  public String[] getValueArray(String concept, int size) {
		    if (conceptMap == null) {
		      init();
		    }
		    String[] valueSourceList = null;
		    List<String[]> valueList = null;
		    if (testDataMap != null) {
		      valueList = testDataMap.get(concept);
		    }
		    if (valueList == null) {
		      valueList = conceptMap.get(concept);
		    }
		    if (valueList != null) {
		      valueSourceList = valueList.get(random.nextInt(valueList.size()));
		    }
		    String[] values = new String[size];
		    for (int i = 0; i < values.length; i++) {
		      if (valueSourceList != null && i < valueSourceList.length) {
		        values[i] = valueSourceList[i];
		      } else {
		        values[i] = "";
		      }
		    }
		    return values;
		  }
	  
	  protected PatientType createDates(String[] dates, PatientType type) {
		    {
		      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		      if (type == PatientType.BABY || (type == PatientType.ANY_CHILD && random.nextBoolean())) {
		        // Setting up baby, 6 months old today
		        // 6 month appointment
		        Calendar cal6Month = Calendar.getInstance();
		        dates[3] = sdf.format(cal6Month.getTime());

		        // Born about 6 months before
		        Calendar calBorn = Calendar.getInstance();
		        calBorn.add(Calendar.MONTH, -6);
		        calBorn.add(Calendar.DAY_OF_MONTH, 3 - random.nextInt(17));
		        dates[0] = sdf.format(calBorn.getTime());

		        // 4 month appointment
		        Calendar cal4Month = Calendar.getInstance();
		        cal4Month.setTime(calBorn.getTime());
		        cal4Month.add(Calendar.MONTH, 4);
		        cal4Month.add(Calendar.DAY_OF_MONTH, random.nextInt(12) - 3);
		        dates[2] = sdf.format(cal4Month.getTime());

		        // 2 month appointment
		        Calendar cal2Month = Calendar.getInstance();
		        cal2Month.setTime(calBorn.getTime());
		        cal2Month.add(Calendar.MONTH, 2);
		        cal2Month.add(Calendar.DAY_OF_MONTH, random.nextInt(10) - 3);
		        if (cal2Month.after(cal6Month.getTime())) {
		          dates[1] = dates[3];
		        } else {
		          dates[1] = sdf.format(cal2Month.getTime());
		        }

		        return PatientType.BABY;
		      } else if (type == PatientType.TWO_MONTHS_OLD || type == PatientType.TWO_YEARS_OLD
		          || type == PatientType.FOUR_YEARS_OLD || type == PatientType.TWELVE_YEARS_OLD) {
		        // Setting up baby, 2 months old today
		        // 2 month appointment
		        // This type will always be at least two and the appointment will always
		        // be when the patient was at least two
		        Calendar calToday = Calendar.getInstance();
		        dates[3] = sdf.format(calToday.getTime());

		        int months = 0;
		        int years = 0;
		        if (type == PatientType.TWO_MONTHS_OLD) {
		          months = 2;
		        } else if (type == PatientType.TWO_YEARS_OLD) {
		          years = 2;
		        } else if (type == PatientType.FOUR_YEARS_OLD) {
		          years = 4;
		        } else if (type == PatientType.TWELVE_YEARS_OLD) {
		          years = 12;
		        }
		        // set birth date
		        Calendar calBorn = Calendar.getInstance();
		        calBorn.add(Calendar.MONTH, -months);
		        calBorn.add(Calendar.YEAR, -years);
		        calBorn.add(Calendar.DAY_OF_MONTH, -random.nextInt(17));
		        dates[0] = sdf.format(calBorn.getTime());

		        // 2 month appointment
		        Calendar cal2Month = Calendar.getInstance();
		        cal2Month.setTime(calBorn.getTime());
		        cal2Month.add(Calendar.MONTH, months);
		        cal2Month.add(Calendar.YEAR, years);
		        cal2Month.add(Calendar.DAY_OF_MONTH, random.nextInt(10));
		        if (cal2Month.getTime().after(calToday.getTime())) {
		          dates[1] = dates[3];
		          dates[2] = dates[3];
		        } else {
		          dates[1] = sdf.format(cal2Month.getTime());
		          dates[2] = dates[1];
		        }

		        if (type == PatientType.TWO_MONTHS_OLD) {
		          return PatientType.BABY;
		        } else if (type == PatientType.TWO_YEARS_OLD) {
		          return PatientType.TODDLER;
		        } else if (type == PatientType.FOUR_YEARS_OLD) {
		          return PatientType.TODDLER;
		        } else if (type == PatientType.TWELVE_YEARS_OLD) {
		          return PatientType.TWEEN;
		        }
		        return type;
		      } else {
		        if (type == PatientType.TODDLER
		            || (type == PatientType.ANY_CHILD && random.nextBoolean())) {
		          // Setting up toddler
		          Calendar calendar = Calendar.getInstance();
		          // 4 years (today) - 48 months
		          dates[3] = sdf.format(calendar.getTime());
		          // 19 months
		          calendar.add(Calendar.MONTH, 19 - 48);
		          calendar.add(Calendar.DAY_OF_MONTH, 7 - random.nextInt(15));
		          dates[2] = sdf.format(calendar.getTime());
		          // 12 months
		          calendar.add(Calendar.MONTH, 12 - 19);
		          calendar.add(Calendar.DAY_OF_MONTH, 7 - random.nextInt(15));
		          dates[1] = sdf.format(calendar.getTime());
		          // birth
		          calendar.add(Calendar.MONTH, -12);
		          calendar.add(Calendar.DAY_OF_MONTH, 7 - random.nextInt(15));
		          dates[0] = sdf.format(calendar.getTime());
		          return PatientType.TODDLER;
		        } else if (type == PatientType.ANY_CHILD || type == PatientType.TWEEN) {
		          // Setting up tween
		          Calendar calendar = Calendar.getInstance();
		          // 13 years (today)
		          dates[3] = sdf.format(calendar.getTime());
		          // 11 years
		          calendar.add(Calendar.YEAR, -2);
		          calendar.add(Calendar.DAY_OF_MONTH, 7 - random.nextInt(15));
		          dates[2] = sdf.format(calendar.getTime());
		          // 9 years
		          calendar.add(Calendar.YEAR, -2);
		          calendar.add(Calendar.DAY_OF_MONTH, 7 - random.nextInt(15));
		          dates[1] = sdf.format(calendar.getTime());
		          // birth
		          calendar.add(Calendar.YEAR, -9);
		          calendar.add(Calendar.DAY_OF_MONTH, 7 - random.nextInt(15));
		          dates[0] = sdf.format(calendar.getTime());
		          return PatientType.TWEEN;
		        } else {
		          // Setting up adult
		          Calendar calendar = Calendar.getInstance();
		          // 67 years (today)
		          dates[3] = sdf.format(calendar.getTime());
		          // last year
		          calendar.add(Calendar.YEAR, -1);
		          calendar.add(Calendar.DAY_OF_MONTH, 7 - random.nextInt(15));
		          dates[2] = sdf.format(calendar.getTime());
		          // two years before that
		          calendar.add(Calendar.YEAR, -2);
		          calendar.add(Calendar.DAY_OF_MONTH, 7 - random.nextInt(15));
		          dates[1] = sdf.format(calendar.getTime());
		          // birth
		          calendar.add(Calendar.YEAR, -64);
		          calendar.add(Calendar.DAY_OF_MONTH, 7 - random.nextInt(15));
		          dates[0] = sdf.format(calendar.getTime());
		          return PatientType.ADULT;

		        }
		      }
		    }
		  }
	  
	  protected int makeBirthCount() {
		    int birthCount = 1;
		    int hat = random.nextInt(100000);
		    if (hat < 3220 + 149) {
		      // chances for twin are 32.2 in 1,000 or 3220 in 100,000
		      birthCount = 2;
		      if (hat < 149) {
		        // chances for triplet or higher is is 148.9 in 100,000
		        birthCount = 3;
		        if (hat < 10) {
		          birthCount = 4;
		          if (hat < 2) {
		            birthCount = 5;
		          }
		        }
		      }
		    }
		    return birthCount;
		  }
	  
	  private static String makeMotherDob(String dob) {
		    try {
		      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		      Calendar calendar = Calendar.getInstance();
		      calendar.setTime(sdf.parse(dob));
		      calendar.add(Calendar.YEAR, -20 - random.nextInt(15));
		      calendar.add(Calendar.MONTH, -random.nextInt(12));
		      calendar.add(Calendar.DATE, -random.nextInt(30));
		      return sdf.format(calendar.getTime());
		    } catch (ParseException pe) {
		      return dob;
		    }
		  }
	  
}
