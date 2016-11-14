package com.mathheals.meajuda.presenter;

import android.content.Context;

import com.mathheals.meajuda.dao.JSONHelper;
import com.mathheals.meajuda.dao.UserDAO;
import com.mathheals.meajuda.model.Address;
import com.mathheals.meajuda.model.School;
import com.mathheals.meajuda.model.User;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SchoolPresenter {

    private static SchoolPresenter instance;
    Context context;

    private SchoolPresenter(Context currentContext) {
        this.context = currentContext;
    }

    public static SchoolPresenter getInstance(Context context) {
        if (instance == null) {
            instance = new SchoolPresenter(context);
        }
        return instance;
    }

    public School showSchool ( String schooCode) throws JSONException {

        JSONHelper jsonHelper = new JSONHelper();

        School school = jsonHelper.getSchoolByCode(schooCode);

        return school;
    }

    public List<School> getAllSchools() throws JSONException {
        JSONHelper jsonHelper = JSONHelper.getInstance();
        List<School> listSchool = jsonHelper.getAllSchools();

        for(int i=0; i<listSchool.size(); i++){
            listSchool.get(i).setRating(getSchoolRating(listSchool.get(i).getSchoolCode()));
        }

        return listSchool;
    }

    public List<School> getSchoolRanking() throws JSONException {
        UserDAO userDAO = UserDAO.getInstance(context);

        List<String> schoolCodeList = userDAO.getSchoolCodeList();

        List<School> schoolRanking = new ArrayList<>();

        HashSet<String> schoolCodeSet = new HashSet<>();

        for(int i = 0; i < schoolCodeList.size(); i++) {
            String schoolCode = schoolCodeList.get(i);

            if(!schoolCodeSet.contains(schoolCode)) {
                schoolCodeSet.add(schoolCode);

                School school = JSONHelper.getSchoolByCode(schoolCode);

                Integer rating = getSchoolRating(school.getSchoolCode());

                String schoolName = school.getName();
                String state = school.getAddress().getState();
                String county = school.getAddress().getCounty();

                School schoolWithRating = new School(schoolCode, schoolName, rating);
                schoolWithRating.createAddress(state, county);

                schoolRanking.add(schoolWithRating);
            }
        }

        Collections.sort(schoolRanking, new Comparator<School>() {
            @Override
            public int compare(School lhs, School rhs) {
                return lhs.getRating() > rhs.getRating() ? -1 : 1;
            }
        });

        return schoolRanking;
    }

    public Integer getSchoolRating(String schoolCode) throws JSONException {
        UserDAO userDAO = UserDAO.getInstance(context);

        List<Integer> userIdList = userDAO.getUserIdListBySchoolCode(schoolCode);

        Integer rating = 0;

        for(int i = 0; i < userIdList.size(); i++) {
            Integer idUser = userIdList.get(i);

            rating += userDAO.getUserEvaluationById(idUser);
        }

        return rating;
    }
    
}
