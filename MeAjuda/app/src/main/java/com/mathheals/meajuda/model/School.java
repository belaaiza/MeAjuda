package com.mathheals.meajuda.model;

<<<<<<< HEAD

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
=======
public class School {
    private String schoolCode;
    private String name;
    private Double latitude;
    private Double longitude;
    private String network;
    private String email;
    private String administrativeType;
    private String privateSchoolCategory;
    private String operatingCondition;
    private String typeAgreementGovernment;
    private String CNPJ;
    private String phoneNumber;
    private Character hasProfit;
    private Character contractedPublicSector;
    private Integer numberRooms;
    private Integer numberUsedRooms;
    private Integer numberEmployees;
    private Integer numberComputers;
    private Integer numberComputersByStudent;
    private Integer numberStudents;
    private String zone;
    private Address address;
    private Infrastructure infrastructure;

    public School(String schoolCode, String name, Double latitude, Double longitude, String network,
                  String email, String administrativeType, String privateSchoolCategory,
                  String operatingCondition, String typeAgreementGovernment, String CNPJ,
                  String phoneNumber, Character hasProfit, Character contractedPublicSector,
                  Integer numberRooms, Integer numberUsedRooms, Integer numberEmployees,
                  Integer numberComputers, Integer numberComputersByStudent, Integer numberStudents,
                  String zone){
        setSchoolCode(schoolCode);
        setName(name);
        setLatitude(latitude);
        setLongitude(longitude);
        setNetwork(network);
>>>>>>> 5ed80b6c1446c045e6d9d7ec9861c6baadfe14b3
        setEmail(email);
        setAdministrativeType(administrativeType);
        setPrivateSchoolCategory(privateSchoolCategory);
        setOperatingCondition(operatingCondition);
<<<<<<< HEAD
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
=======
        setTypeAgreementGovernment(typeAgreementGovernment);
        setCNPJ(CNPJ);
        setPhoneNumber(phoneNumber);
        setHasProfit(hasProfit);
        setContractedPublicSector(contractedPublicSector);
        setNumberRooms(numberRooms);
        setNumberUsedRooms(numberUsedRooms);
        setNumberEmployees(numberEmployees);
        setNumberComputers(numberComputers);
        setNumberComputersByStudent(numberComputersByStudent);
        setNumberStudents(numberStudents);
        setZone(zone);
    }

    public void createAddress(String CEP, String description, String district,
                                        String county, String state) {
        Address address = new Address(CEP, description, district, county, state);

        setAddress(address);
    }

    public void createInfrastructure(Character hasIndoorSportCourt,
                                     Character hasDiscoverySportCourt,
                                     Character hasInternet, Character hasBroadband,
                                     Character hasComputerLab,
                                     Character hasScienceLab, Character hasRefectory,
                                     Character hasAuditory,
                                     Character hasPantry, Character hasWareHouse,
                                     Character hasCoveredPatio,
                                     Character hasDiscoveredPatio, Character hasPlayground,
                                     Character hasKitchen, Character hasLibrary,
                                     Character hasNursery,
                                     Character hasBathroomInsideBuilding,
                                     Character hasBathroomOutsideBuilding,
                                     Character hasReadingRoom, Character hasGreenArea,
                                     Character hasFilteredWater, Character hasAccessibility,
                                     Character hasCreche, Character hasElementarySchool,
                                     Character hasHighSchool, Character hasNormalHighSchool,
                                     Character hasProfessionalHighSchool,
                                     Character hasIntegratedHighSchool,
                                     Character hasAdultEducation, Character hasIndigenousEducation,
                                     Character hasBathroomShower, Character hasOfferFood,
                                     Character hasServiceSpecializedEducation) {
        Infrastructure infrastructure = new Infrastructure(hasIndoorSportCourt,
                hasDiscoverySportCourt, hasInternet, hasBroadband, hasComputerLab, hasScienceLab,
                hasRefectory, hasAuditory, hasPantry, hasWareHouse, hasCoveredPatio,
                hasDiscoveredPatio, hasPlayground, hasKitchen, hasLibrary, hasNursery,
                hasBathroomInsideBuilding, hasBathroomOutsideBuilding, hasReadingRoom, hasGreenArea,
                hasFilteredWater, hasAccessibility, hasCreche, hasElementarySchool, hasHighSchool,
                hasNormalHighSchool, hasProfessionalHighSchool, hasIntegratedHighSchool,
                hasAdultEducation, hasIndigenousEducation, hasBathroomShower, hasOfferFood,
                hasServiceSpecializedEducation);

        setInfrastructure(infrastructure);
    }

    public String getSchoolCode() {
        return schoolCode;
>>>>>>> 5ed80b6c1446c045e6d9d7ec9861c6baadfe14b3
    }

    public String getName() {
        return name;
    }

<<<<<<< HEAD
    private void setName(String name) {
        this.name = name;
    }

    public String getClassification() {
        return classification;
    }

    private void setClassification(String classification) {
        this.classification = classification;
=======
    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getNetwork() {
        return network;
>>>>>>> 5ed80b6c1446c045e6d9d7ec9861c6baadfe14b3
    }

    public String getEmail() {
        return email;
    }

<<<<<<< HEAD
    private void setEmail(String email) {
        this.email = email;
    }

=======
>>>>>>> 5ed80b6c1446c045e6d9d7ec9861c6baadfe14b3
    public String getAdministrativeType() {
        return administrativeType;
    }

<<<<<<< HEAD
    private void setAdministrativeType(String administrativeType) {
        this.administrativeType = administrativeType;
    }

=======
>>>>>>> 5ed80b6c1446c045e6d9d7ec9861c6baadfe14b3
    public String getPrivateSchoolCategory() {
        return privateSchoolCategory;
    }

<<<<<<< HEAD
    private void setPrivateSchoolCategory(String privateSchoolCategory) {
        this.privateSchoolCategory = privateSchoolCategory;
    }

=======
>>>>>>> 5ed80b6c1446c045e6d9d7ec9861c6baadfe14b3
    public String getOperatingCondition() {
        return operatingCondition;
    }

<<<<<<< HEAD
    private void setOperatingCondition(String operatingCondition) {
        this.operatingCondition = operatingCondition;
=======
    public String getTypeAgreementGovernment() {
        return typeAgreementGovernment;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Character getHasProfit() {
        return hasProfit;
    }

    public Character getContractedPublicSector() {
        return contractedPublicSector;
    }

    public Integer getNumberRooms() {
        return numberRooms;
    }

    public Integer getNumberUsedRooms() {
        return numberUsedRooms;
    }

    public Integer getNumberEmployees() {
        return numberEmployees;
    }

    public Integer getNumberComputers() {
        return numberComputers;
    }

    public Integer getNumberComputersByStudent() {
        return numberComputersByStudent;
    }

    public Integer getNumberStudents() {
        return numberStudents;
>>>>>>> 5ed80b6c1446c045e6d9d7ec9861c6baadfe14b3
    }

    public String getZone() {
        return zone;
    }

<<<<<<< HEAD
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
=======
    public Address getAddress() {
        return address;
    }

    public Infrastructure getInfrastructure() {
        return infrastructure;
    }

    private void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    private void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    private void setNetwork(String network) {
        this.network = network;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private void setAdministrativeType(String administrativeType) {
        this.administrativeType = administrativeType;
    }

    private void setPrivateSchoolCategory(String privateSchoolCategory) {
        this.privateSchoolCategory = privateSchoolCategory;
    }

    private void setOperatingCondition(String operatingCondition) {
        this.operatingCondition = operatingCondition;
    }

    private void setTypeAgreementGovernment(String typeAgreementGovernment) {
        this.typeAgreementGovernment = typeAgreementGovernment;
    }

    private void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    private void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private void setHasProfit(Character hasProfit) {
        this.hasProfit = hasProfit;
    }

    private void setContractedPublicSector(Character contractedPublicSector) {
        this.contractedPublicSector = contractedPublicSector;
    }

    private void setNumberRooms(Integer numberRooms) {
        this.numberRooms = numberRooms;
    }

    private void setNumberUsedRooms(Integer numberUsedRooms) {
        this.numberUsedRooms = numberUsedRooms;
    }

    private void setNumberEmployees(Integer numberEmployees) {
        this.numberEmployees = numberEmployees;
>>>>>>> 5ed80b6c1446c045e6d9d7ec9861c6baadfe14b3
    }

    private void setNumberComputers(Integer numberComputers) {
        this.numberComputers = numberComputers;
    }

<<<<<<< HEAD
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
=======
    private void setNumberComputersByStudent(Integer numberComputersByStudent) {
        this.numberComputersByStudent = numberComputersByStudent;
    }

    private void setNumberStudents(Integer numberStudents) {
        this.numberStudents = numberStudents;
    }

    private void setZone(String zone) {
        this.zone = zone;
    }

    private void setAddress(Address address) {
        this.address = address;
    }

    private void setInfrastructure(Infrastructure infrastructure) {
        this.infrastructure = infrastructure;
>>>>>>> 5ed80b6c1446c045e6d9d7ec9861c6baadfe14b3
    }
}
