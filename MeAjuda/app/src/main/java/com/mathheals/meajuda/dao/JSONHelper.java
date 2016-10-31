package com.mathheals.meajuda.dao;

import android.util.Log;

import com.mathheals.meajuda.model.School;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class JSONHelper{
    static final String defaultStringValue = "";
    static final Double defaultDoubleValue = 0.0;
    static final Character defaultCharacterValue = '-';
    static final Integer defaultIntegerValue = 0;

    public static String getJSONObjectApi(final String URL) {
        String getApi = null;

        GetRequest request = new GetRequest();

        getApi = request.execute(URL).toString();

        try {
            getApi = request.get().toString();
        } catch (ExecutionException e){
            Log.d("ExecutionException", URL);
        } catch (InterruptedException e){
            Log.d("InterruptedException", URL);
        }
        return getApi;
    }

    public static List<School> schoolListFromName(String typedSchoolName, int desiredNumberSchools)
            throws JSONException {
        final String URL = "http://mobile-aceite.tcu.gov.br/nossaEscolaRS/rest/escolas?" +
                "nome="+ typedSchoolName +"&quantidadeDeItens="+ desiredNumberSchools +"";

        String schoolListApiString = getJSONObjectApi(URL);

        JSONArray schoolListJSONArray = new JSONArray(schoolListApiString);

        List<School> schoolList = populateSchoolList(schoolListJSONArray);

        return schoolList;
    }
    public static School getSchoolByCode( String schoolCode ) throws JSONException {

        final String URL = "http://mobile-aceite.tcu.gov.br/nossaEscolaRS/rest/escolas/" + schoolCode;

        String schoolApiString = getJSONObjectApi(URL);

        JSONObject schoolJSONObject = new JSONObject(schoolApiString);


        String name = getStringFromJSONObject(schoolJSONObject, "nome", defaultStringValue);

        Double latitude = getDoubleFromJSONObject(schoolJSONObject, "latitude", defaultDoubleValue);

        Double longitude = getDoubleFromJSONObject(schoolJSONObject, "longitude",
                defaultDoubleValue);

        String network = getStringFromJSONObject(schoolJSONObject, "rede", defaultStringValue);

        String email = getStringFromJSONObject(schoolJSONObject, "email", defaultStringValue);

        String administrativeType = getStringFromJSONObject(schoolJSONObject,
                "esferaAdministrativa", defaultStringValue);

        String privateSchoolCategory = getStringFromJSONObject(schoolJSONObject,
                "categoriaEscolaPrivada", defaultStringValue);

        String operatingCondition = getStringFromJSONObject(schoolJSONObject,
                "situacaoFuncionamento", defaultStringValue);

        String typeAgreementGovernment = getStringFromJSONObject(schoolJSONObject,
                "tipoConvenioPoderPublico", defaultStringValue);

        String CNPJ = getStringFromJSONObject(schoolJSONObject, "cnpj", defaultStringValue);

        String phoneNumber = getStringFromJSONObject(schoolJSONObject, "telefone",
                defaultStringValue);

        Character hasProfit = getCharacterFromJSONObject(schoolJSONObject, "seFimLucrativo",
                defaultCharacterValue);

        Character contractedPublicSector = getCharacterFromJSONObject(schoolJSONObject,
                "seConveniadaSetorPublico", defaultCharacterValue);

        Integer numberRooms = getIntegerFromJSONObject(schoolJSONObject, "qtdSalasExistentes",
                defaultIntegerValue);

        Integer numberUsedRooms = getIntegerFromJSONObject(schoolJSONObject, "qtdSalasUtilizadas",
                defaultIntegerValue);

        Integer numberEmployees = getIntegerFromJSONObject(schoolJSONObject, "qtdFuncionarios",
                defaultIntegerValue);

        Integer numberComputers = getIntegerFromJSONObject(schoolJSONObject, "qtdComputadores",
                defaultIntegerValue);

        Integer numberComputersByStudent = getIntegerFromJSONObject(schoolJSONObject,
                "qtdComputadoresPorAluno", defaultIntegerValue);

        Integer numberStudents = getIntegerFromJSONObject(schoolJSONObject, "qtdAlunos",
                defaultIntegerValue);

        String zone = getStringFromJSONObject(schoolJSONObject, "zona", defaultStringValue);

        School school = new School(schoolCode, name, latitude, longitude, network, email,
                administrativeType, privateSchoolCategory, operatingCondition,
                typeAgreementGovernment, CNPJ, phoneNumber, hasProfit, contractedPublicSector,
                numberRooms, numberUsedRooms, numberEmployees, numberComputers,
                numberComputersByStudent, numberStudents, zone);

        JSONObject addressJSONObject = schoolJSONObject.getJSONObject("endereco");
        addAddressToSchoolFromJSONObject(school, addressJSONObject);

        JSONObject infrastructureJSONObject = schoolJSONObject.getJSONObject("infraestrutura");
        addInfrastructureToSchoolFromJSONObject(school, infrastructureJSONObject);

        return  school;
    }
    private static List<School> populateSchoolList(JSONArray schoolListJSONArray) throws JSONException {
        List<School> schoolList = new ArrayList<>();

        for(int i = 0; i < schoolListJSONArray.length(); i++) {
            JSONObject schoolJSONObject = schoolListJSONArray.getJSONObject(i);

            School school = schoolFromJSONObject(schoolJSONObject);

            schoolList.add(school);
        }

        return schoolList;
    }

    private static School schoolFromJSONObject(JSONObject schoolJSONObject) throws JSONException{
        String schoolCode = getStringFromJSONObject(schoolJSONObject, "codEscola",
                defaultStringValue);
        String name = getStringFromJSONObject(schoolJSONObject, "nome", defaultStringValue);
        Double latitude = getDoubleFromJSONObject(schoolJSONObject, "latitude", defaultDoubleValue);
        Double longitude = getDoubleFromJSONObject(schoolJSONObject, "longitude",
                defaultDoubleValue);
        String network = getStringFromJSONObject(schoolJSONObject, "rede", defaultStringValue);
        String email = getStringFromJSONObject(schoolJSONObject, "email", defaultStringValue);
        String administrativeType = getStringFromJSONObject(schoolJSONObject,
                "esferaAdministrativa", defaultStringValue);
        String privateSchoolCategory = getStringFromJSONObject(schoolJSONObject,
                "categoriaEscolaPrivada", defaultStringValue);
        String operatingCondition = getStringFromJSONObject(schoolJSONObject,
                "situacaoFuncionamento", defaultStringValue);
        String typeAgreementGovernment = getStringFromJSONObject(schoolJSONObject,
                "tipoConvenioPoderPublico", defaultStringValue);
        String CNPJ = getStringFromJSONObject(schoolJSONObject, "cnpj", defaultStringValue);
        String phoneNumber = getStringFromJSONObject(schoolJSONObject, "telefone",
                defaultStringValue);
        Character hasProfit = getCharacterFromJSONObject(schoolJSONObject, "seFimLucrativo",
                defaultCharacterValue);
        Character contractedPublicSector = getCharacterFromJSONObject(schoolJSONObject,
                "seConveniadaSetorPublico", defaultCharacterValue);
        Integer numberRooms = getIntegerFromJSONObject(schoolJSONObject, "qtdSalasExistentes",
                defaultIntegerValue);
        Integer numberUsedRooms = getIntegerFromJSONObject(schoolJSONObject, "qtdSalasUtilizadas",
                defaultIntegerValue);
        Integer numberEmployees = getIntegerFromJSONObject(schoolJSONObject, "qtdFuncionarios",
                defaultIntegerValue);
        Integer numberComputers = getIntegerFromJSONObject(schoolJSONObject, "qtdComputadores",
                defaultIntegerValue);
        Integer numberComputersByStudent = getIntegerFromJSONObject(schoolJSONObject,
                "qtdComputadoresPorAluno", defaultIntegerValue);
        Integer numberStudents = getIntegerFromJSONObject(schoolJSONObject, "qtdAlunos",
                defaultIntegerValue);
        String zone = getStringFromJSONObject(schoolJSONObject, "zona", defaultStringValue);

        School school = new School(schoolCode, name, latitude, longitude, network, email,
                administrativeType, privateSchoolCategory, operatingCondition,
                typeAgreementGovernment, CNPJ, phoneNumber, hasProfit, contractedPublicSector,
                numberRooms, numberUsedRooms, numberEmployees, numberComputers,
                numberComputersByStudent, numberStudents, zone);

        JSONObject addressJSONObject = schoolJSONObject.getJSONObject("endereco");
        addAddressToSchoolFromJSONObject(school, addressJSONObject);

        JSONObject infrastructureJSONObject = schoolJSONObject.getJSONObject("infraestrutura");
        addInfrastructureToSchoolFromJSONObject(school, infrastructureJSONObject);

        return school;
    }

    private static void addAddressToSchoolFromJSONObject(School school,
                                                         JSONObject addressJSONObject)
            throws JSONException {
        String CEP = getStringFromJSONObject(addressJSONObject, "cep", defaultStringValue);
        String description = getStringFromJSONObject(addressJSONObject, "descricao",
                defaultStringValue);
        String district = getStringFromJSONObject(addressJSONObject, "bairro", defaultStringValue);
        String county = getStringFromJSONObject(addressJSONObject, "municipio", defaultStringValue);
        String state = getStringFromJSONObject(addressJSONObject, "uf", defaultStringValue);

        school.createAddress(CEP, description, district, county, state);
    }

    private static void addInfrastructureToSchoolFromJSONObject(School school,
                                                                JSONObject infrastructureJSONObject)
            throws JSONException {
        Character hasIndoorSportCourt = getCharacterFromJSONObject(infrastructureJSONObject,
                "temQuadraEsporteCoberta", defaultCharacterValue);
        Character hasDiscoverySportCourt = getCharacterFromJSONObject(infrastructureJSONObject,
                "temQuadraEsporteDescoberta", defaultCharacterValue);
        Character hasInternet = getCharacterFromJSONObject(infrastructureJSONObject, "temInternet",
                defaultCharacterValue);
        Character hasBroadband = getCharacterFromJSONObject(infrastructureJSONObject,
                "temBandaLarga", defaultCharacterValue);
        Character hasComputerLab = getCharacterFromJSONObject(infrastructureJSONObject,
                "temLaboratorioInformatica", defaultCharacterValue);
        Character hasScienceLab = getCharacterFromJSONObject(infrastructureJSONObject,
                "temLaboratorioCiencias", defaultCharacterValue);
        Character hasRefectory = getCharacterFromJSONObject(infrastructureJSONObject,
                "temRefeitorio", defaultCharacterValue);
        Character hasAuditory = getCharacterFromJSONObject(infrastructureJSONObject, "temAuditorio",
                defaultCharacterValue);
        Character hasPantry = getCharacterFromJSONObject(infrastructureJSONObject, "temDespensa",
                defaultCharacterValue);
        Character hasWareHouse = getCharacterFromJSONObject(infrastructureJSONObject,
                "temAlmoxarifado", defaultCharacterValue);
        Character hasCoveredPatio = getCharacterFromJSONObject(infrastructureJSONObject,
                "temPatioCoberto", defaultCharacterValue);
        Character hasDiscoveredPatio = getCharacterFromJSONObject(infrastructureJSONObject,
                "temPatioDescoberto", defaultCharacterValue);
        Character hasPlayground = getCharacterFromJSONObject(infrastructureJSONObject,
                "temParqueInfantil", defaultCharacterValue);
        Character hasKitchen = getCharacterFromJSONObject(infrastructureJSONObject, "temCozinha",
                defaultCharacterValue);
        Character hasLibrary = getCharacterFromJSONObject(infrastructureJSONObject, "temBiblioteca",
                defaultCharacterValue);
        Character hasNursery = getCharacterFromJSONObject(infrastructureJSONObject, "temBercario",
                defaultCharacterValue);
        Character hasBathroomInsideBuilding = getCharacterFromJSONObject(infrastructureJSONObject,
                "temSanitarioNoPredio", defaultCharacterValue);
        Character hasBathroomOutsideBuilding = getCharacterFromJSONObject(infrastructureJSONObject,
                "temSanitarioForaPredio", defaultCharacterValue);
        Character hasReadingRoom = getCharacterFromJSONObject(infrastructureJSONObject,
                "temSalaLeitura", defaultCharacterValue);
        Character hasGreenArea = getCharacterFromJSONObject(infrastructureJSONObject,
                "temAreaVerde", defaultCharacterValue);
        Character hasFilteredWater = getCharacterFromJSONObject(infrastructureJSONObject,
                "temAguaFiltrada", defaultCharacterValue);
        Character hasAccessibility = getCharacterFromJSONObject(infrastructureJSONObject,
                "temAcessibilidade", defaultCharacterValue);
        Character hasCreche = getCharacterFromJSONObject(infrastructureJSONObject, "temCreche",
                defaultCharacterValue);
        Character hasElementarySchool = getCharacterFromJSONObject(infrastructureJSONObject,
                "temEnsinoFundamental", defaultCharacterValue);
        Character hasHighSchool = getCharacterFromJSONObject(infrastructureJSONObject,
                "temEnsinoMedio", defaultCharacterValue);
        Character hasNormalHighSchool = getCharacterFromJSONObject(infrastructureJSONObject,
                "temEnsinoMedioNormal", defaultCharacterValue);
        Character hasProfessionalHighSchool = getCharacterFromJSONObject(infrastructureJSONObject,
                "temEnsinoMedioProfissional", defaultCharacterValue);
        Character hasIntegratedHighSchool = getCharacterFromJSONObject(infrastructureJSONObject,
                "temEnsinoMedioIntegrado", defaultCharacterValue);
        Character hasAdultEducation = getCharacterFromJSONObject(infrastructureJSONObject,
                "temEducacaoJovemAdulto", defaultCharacterValue);
        Character hasIndigenousEducation = getCharacterFromJSONObject(infrastructureJSONObject,
                "temEducacaoIndigena", defaultCharacterValue);
        Character hasBathroomShower = getCharacterFromJSONObject(infrastructureJSONObject,
                "banheiroTemChuveiro", defaultCharacterValue);
        Character hasOfferFood = getCharacterFromJSONObject(infrastructureJSONObject,
                "ofereceAlimentacao", defaultCharacterValue);
        Character hasServiceSpecializedEducation = getCharacterFromJSONObject(
                infrastructureJSONObject, "atendeEducacaoEspecializada", defaultCharacterValue);

        school.createInfrastructure(hasIndoorSportCourt,
                hasDiscoverySportCourt, hasInternet, hasBroadband, hasComputerLab, hasScienceLab,
                hasRefectory, hasAuditory, hasPantry, hasWareHouse, hasCoveredPatio,
                hasDiscoveredPatio, hasPlayground, hasKitchen, hasLibrary, hasNursery,
                hasBathroomInsideBuilding, hasBathroomOutsideBuilding, hasReadingRoom, hasGreenArea,
                hasFilteredWater, hasAccessibility, hasCreche, hasElementarySchool, hasHighSchool,
                hasNormalHighSchool, hasProfessionalHighSchool, hasIntegratedHighSchool,
                hasAdultEducation, hasIndigenousEducation, hasBathroomShower, hasOfferFood,
                hasServiceSpecializedEducation);
    }

    private static Double getDoubleFromJSONObject(JSONObject jsonObject, String field,
                                                  Double defaultValue) throws JSONException {
        Double returnValue = defaultValue;

        if(!jsonObject.isNull(field)) {
            returnValue = jsonObject.getDouble(field);
        }else {
            //Nothing to do
        }

        return returnValue;
    }

    private static String getStringFromJSONObject(JSONObject jsonObject, String field,
                                                  String defaultValue) throws JSONException {
        String returnValue = defaultValue;

        if(!jsonObject.isNull(field)) {
            returnValue = jsonObject.getString(field);
        }else {
            //Nothing to do
        }

        return returnValue;
    }

    private static Character getCharacterFromJSONObject(JSONObject jsonObject, String  field,
                                                        Character defaultValue)
            throws JSONException {
        Character returnValue = defaultValue;

        if(!jsonObject.isNull(field)) {
            returnValue = jsonObject.getString(field).charAt(0);
        }else {
            //Nothing to do
        }

        return returnValue;
    }

    private static Integer getIntegerFromJSONObject(JSONObject jsonObject, String field,
                                                    Integer defaultValue) throws JSONException {
        Integer returnValue = defaultValue;

        if(!jsonObject.isNull(field)) {
            returnValue = jsonObject.getInt(field);
        }else {
            //Nothing to do;
        }

        return returnValue;
    }
}
