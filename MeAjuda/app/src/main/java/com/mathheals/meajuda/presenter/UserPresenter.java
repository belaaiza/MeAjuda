package com.mathheals.meajuda.presenter;

import com.mathheals.meajuda.exception.UserException;
import com.mathheals.meajuda.model.User;

import java.text.ParseException;

public class UserPresenter {

    public String registerUser(String firstName, String lastName, String username, String mail,
                                         String mailConfirmation, String password,
                                         String passwordConfirmation){
        String message = "";

        try{
             User user = new User(firstName, lastName, username, mail, mailConfirmation,
                    password, passwordConfirmation);

            message = user.USER_SUCCESSFULLY_REGISTERED;
        }catch(UserException e){
            message = e.getMessage();
        }catch(ParseException e){
            e.printStackTrace();
        }

        return message;
    }

    public String authenticateUser(String email, String password){



    }
}
