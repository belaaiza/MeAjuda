package com.mathheals.meajuda.presenter;

import android.content.Context;

import com.mathheals.meajuda.dao.UserDAO;
import com.mathheals.meajuda.exception.UserException;
import com.mathheals.meajuda.model.User;
import com.mathheals.meajuda.view.users.UserList;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RankingFacade {
    private static RankingFacade instance;
    Context context;

    private RankingFacade(Context currentContext) {
        this.context = currentContext;
    }

    public static RankingFacade getInstance(Context context) {
        if (instance == null) {
            instance = new RankingFacade(context);
        }
        return instance;
    }

    public List<User> getUserRanking() throws JSONException, UserException {
        UserDAO userDAO = UserDAO.getInstance(context);

        List<Integer> userIdList = userDAO.getUserIdList();

        List<User> userRanking = new ArrayList<>();

        for (int i = 0; i < userIdList.size(); i++) {
            Integer idUser = userIdList.get(i);

            User user = userDAO.getUserById(idUser);

            userRanking.add(user);
        }

        Collections.sort(userRanking, new Comparator<User>() {
            @Override
            public int compare(User lhs, User rhs) {
                return lhs.getRating() > rhs.getRating() ? -1 : 1;
            }
        });

        return userRanking;
    }

}
