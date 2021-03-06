package gov.nist.healthcare.hl7.mm.v2.procedure;


public class Patient {

    public String medicalRecordNumber = "";
    public String medicaidNumber = "";
    public String ssn = "";
    public String wic = "";
    public String boyName = "";
    public String girlName = "";
    public String motherName = "";
    public String motherMaidenName = "";
    public String motherDob = "";
    public String motherSsn = "";
    public String fatherName = "";
    public String lastName = "";
    public String differentLastName = "";
    public String middleNameBoy = "";
    public String middleNameGirl = "";
    public String aliasBoy = "";
    public String aliasGirl = "";
    public String[] datesAny = new String[4];
    public PatientType vaccineType = null;
    public String gender = "";
    public String[] vaccine1 = null;
    public String[] vaccine2 = null;
    public String[] vaccine3 = null;
    public String[] combo = null;
    public String[] race = null;
    public String[] ethnicity = null;
    public String[] language = null;
    public String[] address = null;
    public String[] vfc = null;
    public String suffix = "";
    public String street = "";
    public String street2 = "";
    public String city = "";
    public String state = "";
    public String zip = "";
    public String phone = "";
    public String phoneArea = "";
    public String phoneLocal = "";
    public String phoneAlt = "";
    public String phoneAltArea = "";
    public String phoneAltLocal = "";
    public int birthCount = 0;
    public String future = "";
    public String email = "";
    public String enteredByFirstName = "";
    public String enteredByLastName = "";
    public String enteredByMiddleName = "";
    public String enteredByNPI = "";
    public String orderedByFirstName = "";
    public String orderedByLastName = "";
    public String orderedByMiddleName = "";
    public String orderedByNPI = "";
    public String adminByFirstName = "";
    public String adminByLastName = "";
    public String adminByMiddleName = "";
    public String adminByNPI = "";
    public String[] responsibleOrg = null;
    public String[] adminOrg1 = null;
    public String[] adminOrg2 = null;
    
    public String getAliasBoy() {
      return aliasBoy;
    }

    public void setAliasBoy(String aliasBoy) {
      this.aliasBoy = aliasBoy;
    }

    public String getAliasGirl() {
      return aliasGirl;
    }

    public void setAliasGirl(String aliasGirl) {
      this.aliasGirl = aliasGirl;
    }

    public String getMotherSsn() {
      return motherSsn;
    }

    public void setMotherSsn(String motherSsn) {
      this.motherSsn = motherSsn;
    }

    public String getMotherDob() {
      return motherDob;
    }

    public void setMotherDob(String motherDob) {
      this.motherDob = motherDob;
    }

    public String getAdminByFirstName() {
      return adminByFirstName;
    }

    public void setAdminByFirstName(String adminByFirstName) {
      this.adminByFirstName = adminByFirstName;
    }

    public String getAdminByLastName() {
      return adminByLastName;
    }

    public void setAdminByLastName(String adminByLastName) {
      this.adminByLastName = adminByLastName;
    }

    public String getAdminByMiddleName() {
      return adminByMiddleName;
    }

    public void setAdminByMiddleName(String adminByMiddleName) {
      this.adminByMiddleName = adminByMiddleName;
    }

    public String getAdminByNPI() {
      return adminByNPI;
    }

    public void setAdminByNPI(String adminByNPI) {
      this.adminByNPI = adminByNPI;
    }

    public String[] getResponsibleOrg() {
      return responsibleOrg;
    }

    public void setResponsibleOrg(String[] responsibleOrg) {
      this.responsibleOrg = responsibleOrg;
    }

    public String[] getAdminOrg1() {
      return adminOrg1;
    }

    public void setAdminOrg1(String[] adminOrg1) {
      this.adminOrg1 = adminOrg1;
    }

    public String[] getAdminOrg2() {
      return adminOrg2;
    }

    public void setAdminOrg2(String[] adminOrg2) {
      this.adminOrg2 = adminOrg2;
    }

    public String getEnteredByFirstName() {
      return enteredByFirstName;
    }

    public void setEnteredByFirstName(String enteredByFirstName) {
      this.enteredByFirstName = enteredByFirstName;
    }

    public String getEnteredByLastName() {
      return enteredByLastName;
    }

    public void setEnteredByLastName(String enteredByLastName) {
      this.enteredByLastName = enteredByLastName;
    }

    public String getEnteredByMiddleName() {
      return enteredByMiddleName;
    }

    public void setEnteredByMiddleName(String enteredByMiddleName) {
      this.enteredByMiddleName = enteredByMiddleName;
    }

    public String getEnteredByNPI() {
      return enteredByNPI;
    }

    public void setEnteredByNPI(String enteredByNPI) {
      this.enteredByNPI = enteredByNPI;
    }

    public String getOrderedByFirstName() {
      return orderedByFirstName;
    }

    public void setOrderedByFirstName(String orderedByFirstName) {
      this.orderedByFirstName = orderedByFirstName;
    }

    public String getOrderedByLastName() {
      return orderedByLastName;
    }

    public void setOrderedByLastName(String orderedByLastName) {
      this.orderedByLastName = orderedByLastName;
    }

    public String getOrderedByMiddleName() {
      return orderedByMiddleName;
    }

    public void setOrderedByMiddleName(String orderedByMiddleName) {
      this.orderedByMiddleName = orderedByMiddleName;
    }

    public String getOrderedByNPI() {
      return orderedByNPI;
    }

    public void setOrderedByNPI(String orderedByNPI) {
      this.orderedByNPI = orderedByNPI;
    }

    public String getStreet2() {
      return street2;
    }

    public void setStreet2(String street2) {
      this.street2 = street2;
    }

    public String getPhoneAlt() {
      return phoneAlt;
    }

    public void setPhoneAlt(String phoneAlt) {
      this.phoneAlt = phoneAlt;
    }

    public String getPhoneAltArea() {
      return phoneAltArea;
    }

    public void setPhoneAltArea(String phoneAltArea) {
      this.phoneAltArea = phoneAltArea;
    }

    public String getPhoneAltLocal() {
      return phoneAltLocal;
    }

    public void setPhoneAltLocal(String phoneAltLocal) {
      this.phoneAltLocal = phoneAltLocal;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getWic() {
      return wic;
    }

    public void setWic(String wic) {
      this.wic = wic;
    }

    public String getSsn()
    {
      return ssn;
    }

    public void setSsn(String ssn)
    {
      this.ssn = ssn;
    }

    public String getFuture() {
        return future;
    }

    public void setFuture(String future) {
        this.future = future;
    }

    public String[] getAddress() {
        return address;
    }

    public void setAddress(String[] address) {
        this.address = address;
    }

    public int getBirthCount() {
        return birthCount;
    }

    public void setBirthCount(int birthCount) {
        this.birthCount = birthCount;
    }

    public String getBoyName() {
        return boyName;
    }

    public void setBoyName(String boyName) {
        this.boyName = boyName;
    }

    public String getMedicaidNumber() {
      return medicaidNumber;
    }

    public void setMedicaidNumber(String medicaidNumber) {
      this.medicaidNumber = medicaidNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String[] getDates() {
        return datesAny;
    }

    public void setDates(String[] dates) {
        this.datesAny = dates;
    }

    public String getDifferentLastName() {
        return differentLastName;
    }

    public void setDifferentLastName(String differentLastName) {
        this.differentLastName = differentLastName;
    }

    public String[] getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String[] ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGirlName() {
        return girlName;
    }

    public void setGirlName(String girlName) {
        this.girlName = girlName;
    }

    public String[] getLanguage() {
        return language;
    }

    public void setLanguage(String[] language) {
        this.language = language;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleNameBoy() {
        return middleNameBoy;
    }

    public void setMiddleNameBoy(String middleNameBoy) {
        this.middleNameBoy = middleNameBoy;
    }

    public String getMiddleNameGirl() {
        return middleNameGirl;
    }

    public void setMiddleNameGirl(String middleNameGirl) {
        this.middleNameGirl = middleNameGirl;
    }

    public String getMotherMaidenName() {
        return motherMaidenName;
    }

    public void setMotherMaidenName(String motherMaidenName) {
        this.motherMaidenName = motherMaidenName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneArea() {
        return phoneArea;
    }

    public void setPhoneArea(String phoneArea) {
        this.phoneArea = phoneArea;
    }

    public String getPhoneLocal() {
        return phoneLocal;
    }

    public void setPhoneLocal(String phoneLocal) {
        this.phoneLocal = phoneLocal;
    }

    public String[] getRace() {
        return race;
    }

    public void setRace(String[] race) {
        this.race = race;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String[] getVaccine1() {
        return vaccine1;
    }

    public void setVaccine1(String[] vaccine1) {
        this.vaccine1 = vaccine1;
    }

    public String[] getVaccine2() {
        return vaccine2;
    }

    public void setVaccine2(String[] vaccine2) {
        this.vaccine2 = vaccine2;
    }

    public String[] getVaccine3() {
        return vaccine3;
    }

    public void setVaccine3(String[] vaccine3) {
        this.vaccine3 = vaccine3;
    }

    public String[] getCombo() {
      return combo;
  }

  public void setCombo(String[] combo) {
      this.combo = combo;
  }

    public PatientType getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(PatientType vaccineType) {
        this.vaccineType = vaccineType;
    }

    public String[] getVfc() {
        return vfc;
    }

    public void setVfc(String[] vfc) {
        this.vfc = vfc;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getMedicalRecordNumber()
    {
        return medicalRecordNumber;
    }

    public void setMedicalRecordNumber(String medicalRecordNumber)
    {
        this.medicalRecordNumber = medicalRecordNumber;
    }

}
