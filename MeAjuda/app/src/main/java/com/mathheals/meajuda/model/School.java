package com.mathheals.meajuda.model;

import java.io.Serializable;

public class School implements Serializable {
    private String schoolCode;
    private String name;
    private Double latitude;
    private Double longitude;
    private String state;
    private String county;
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
    private Integer rating;
    private String zone;
    private Address address;
    private Infrastructure infrastructure;

    public School(String schoolCode, String name, Integer rating) {
        setSchoolCode(schoolCode);
        setName(name);
        setRating(rating);
    }

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
        setEmail(email);
        setAdministrativeType(administrativeType);
        setPrivateSchoolCategory(privateSchoolCategory);
        setOperatingCondition(operatingCondition);
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

    public void createAddress(String state, String county) {
        Address address = new Address(state, county);

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
    }

    public String getName() {
        return name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getNetwork() {
        return network;
    }

    public String getEmail() {
        return email;
    }

    public String getAdministrativeType() {
        return administrativeType;
    }

    public String getPrivateSchoolCategory() {
        return privateSchoolCategory;
    }

    public String getOperatingCondition() {
        return operatingCondition;
    }

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
    }

    public String getZone() {
        return zone;
    }

    public Address getAddress() {
        return address;
    }

    public Infrastructure getInfrastructure() {
        return infrastructure;
    }

    public String getState() {
        return state;
    }

    public String getCounty() {
        return county;
    }

    public Integer getRating() {
        return rating;
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
    }

    private void setNumberComputers(Integer numberComputers) {
        this.numberComputers = numberComputers;
    }

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
    }

    private void setState(String state) {
        this.state = state;
    }

    private void setCounty(String county) {
        this.county = county;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

}
