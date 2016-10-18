package com.mathheals.meajuda.model;


public class School {
    private final String DEFAULT_STRING_MESSAGE = "initiated";
    private final int DEFAULT_INT_NUMBER = -1;

    private String name = DEFAULT_STRING_MESSAGE;
    private String classification = DEFAULT_STRING_MESSAGE;
    private String email = DEFAULT_STRING_MESSAGE;
    private String administrativeType = DEFAULT_STRING_MESSAGE;
    private String privateSchoolCategory = DEFAULT_STRING_MESSAGE;
    private String operatingCondition = DEFAULT_STRING_MESSAGE;
    private String zone = DEFAULT_STRING_MESSAGE;

    private Float latitude = 0F;
    private Float longitude = 0F;
    private char hasProfit = ' ';

    private Integer ifContractedPublicSector = DEFAULT_INT_NUMBER;
    private Integer numberOfRooms = DEFAULT_INT_NUMBER;
    private Integer numberComputers = DEFAULT_INT_NUMBER;
    private Integer numberComputersForStudent = DEFAULT_INT_NUMBER;
    private Integer numberStudent = DEFAULT_INT_NUMBER;


    public School(String name,
                  String classification,
                  String email,
                  String administrativeType,
                  String privateSchoolCategory,
                  String operatingCondition,
                  String zone,
                  Float latitude,
                  Float longitude,
                  char hasProfit,
                  Integer ifContractedPublicSector,
                  Integer numberOfRooms,
                  Integer numberComputers,
                  Integer numberComputersForStudent,
                  Integer numberStudent){

        setName(name);
        setClassification(classification);
        setEmail(email);
        setAdministrativeType(administrativeType);
        setPrivateSchoolCategory(privateSchoolCategory);
        setOperatingCondition(operatingCondition);
        setZone(zone);
        setLatitude(latitude);
        setLongitude(longitude);
        setHasProfit(hasProfit);
        setIfContractedPublicSector(ifContractedPublicSector);
        setNumberOfRooms(numberOfRooms);
        setNumberComputers(numberComputers);
        setNumberComputersForStudent(numberComputersForStudent);
        setNumberStudent(numberStudent);

    }

    public Float getLatitude() {
        return latitude;
    }

    private void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    private void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getClassification() {
        return classification;
    }

    private void setClassification(String classification) {
        this.classification = classification;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public String getAdministrativeType() {
        return administrativeType;
    }

    private void setAdministrativeType(String administrativeType) {
        this.administrativeType = administrativeType;
    }

    public String getPrivateSchoolCategory() {
        return privateSchoolCategory;
    }

    private void setPrivateSchoolCategory(String privateSchoolCategory) {
        this.privateSchoolCategory = privateSchoolCategory;
    }

    public String getOperatingCondition() {
        return operatingCondition;
    }

    private void setOperatingCondition(String operatingCondition) {
        this.operatingCondition = operatingCondition;
    }

    public String getZone() {
        return zone;
    }

    private void setZone(String zone) {
        this.zone = zone;
    }

    public char getHasProfit() {
        return hasProfit;
    }

    private void setHasProfit(char hasProfit) {
        this.hasProfit = hasProfit;
    }

    public Integer getIfContractedPublicSector() {
        return ifContractedPublicSector;
    }

    private void setIfContractedPublicSector(Integer ifContractedPublicSector) {
        this.ifContractedPublicSector = ifContractedPublicSector;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    private void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Integer getNumberComputers() {
        return numberComputers;
    }

    private void setNumberComputers(Integer numberComputers) {
        this.numberComputers = numberComputers;
    }

    public Integer getNumberComputersForStudent() {
        return numberComputersForStudent;
    }

    private void setNumberComputersForStudent(Integer numberComputersForStudent) {
        this.numberComputersForStudent = numberComputersForStudent;
    }

    public Integer getNumberStudent() {
        return numberStudent;
    }

    private void setNumberStudent(Integer numberStudent) {
        this.numberStudent = numberStudent;
    }
}
