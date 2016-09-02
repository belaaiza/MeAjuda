package com.example.geovanni.meajuda.model;

import android.util.Patterns;
import com.example.geovanni.meajuda.exception.UserException;
import java.text.ParseException;

public class User{

    private static final String ID_IS_INVALID = "Id inválido";
    public static final String NAME_CANT_BE_EMPTY_NAME = "Hey, acho que você está esquecendo de nos dizer seu nome.";
    public static final String NAME_CANT_BE_HIGHER_THAN_50 = "Hey, acho que você ultrapassou o número de caracteres permitido para o nome, tente novamente.";
    public static final String EMAIL_CANT_BE_EMPTY_EMAIL = "Hey, acho que você está esquecendo de nos dizer seu email.";
    public static final String EMAIL_CANT_BE_HIGHER_THAN_150 = "Hey, acho que você ultrapassou o número de caracteres permitido para email, tente novamente.";
    public static final String INVALID_EMAIL = "Ops, esse e-mail é inválido.";
    public static final String CONFIRM_PASSWORD_CANT_BE_EMPTY = "Hey, confirme sua senha";
    public static final String EMAIL_CONFIRMATION_CANT_BE_EMPTY = "Hey, confirme seu e-mail";
    public static final String USERNAME_CANT_BE_EMPTY_USERNAME = "Hey, acho que você está esquecendo de nos dizer seu login.";
    public static final String USERNAME_CANT_BE_HIGHER_THAN_100 = "Hey, acho que você ultrapassou o número de caracteres permitido para o login, tente novamente.";
    public static final String PASSWORD_CANT_BE_EMPTY_PASSWORD = "Hey, acho que você está esquecendo de nos dizer sua senha.";
    public static final String PASSWORD_CANT_BE_LESS_THAN_6 = "Hey, acho que vocẽ não atingiu o número mínimo de caracteres.";
    public static final String EMAIL_ARE_NOT_EQUALS = "Ops, E-mails não conferem.";
    public static final String PASSWORD_ARE_NOT_EQUALS = "Ops, as senhas não conferem.";
    public static final String USERNAME_EXISTENT = "Ops, esse login já existe";
    private static final int MAX_LENGTH_NAME = 50;
    private static final int MAX_LENGTH_EMAIL = 150;
    private static final int MAX_LENGTH_USERNAME = 100;
    private static final int MIN_LENGTH_PASSWORD = 6;

    private int idUser;
    private String name;
    private String username;
    private String email;
    private String password;

    /**
     * Verifies if two user's are the same
     * @param user
     * @return boolean - If the users are the same, return true, else return false
     */
    public boolean equals(User user){
        return this.name.equals(user.getName()) &&
                this.username.equals(user.getUsername()) &&
                this.email.equals(user.getEmail()) &&
                this.password.equals(user.getPassword());
    }

    /**
     * Constructs user with given information
     * @param idUser - User ID
     * @param name - User name
     * @param username - Username of the user
     * @param email - User email
     * @param mailConfirmation - User confirmation email
     * @param password - User password
     * @param passwordConfirmation - User password confirmation
     * @throws UserException
     * @throws ParseException
     */
    public User(int idUser, String name, String username, String email, String mailConfirmation, String password, String passwordConfirmation) throws UserException, ParseException{
        setIdUser(idUser);
        setName(name);
        setUsername(username);
        setEmail(email);
        verifyEmailConfirmation(mailConfirmation);
        setPassword(password);
        verifyPasswordConfirmation(passwordConfirmation);

    }

    /**
     * Constructs user with given information
     * @param name - User name
     * @param username - Username of the user
     * @param email - User email
     * @param mailConfirmation - User confirmation email
     * @param password - User password
     * @param passwordConfirmation - User password confirmation
     * @throws UserException
     * @throws ParseException
     */
    public User(String name, String username, String email, String mailConfirmation, String password, String passwordConfirmation) throws UserException, ParseException{
        setIdUser(idUser);
        setName(name);
        setUsername(username);
        setEmail(email);
        verifyEmailConfirmation(mailConfirmation);
        setPassword(password);
        verifyPasswordConfirmation(passwordConfirmation);

    }

    /**
     * Sets the ID of the user
     * @param idUser - User ID
     * @throws UserException
     */
    private void setIdUser(int idUser) throws UserException{

        if(idUser <= Integer.MAX_VALUE && idUser >= 1){
            this.idUser =idUser;
        }else{
            throw new UserException(ID_IS_INVALID);
        }
    }

    /**
     * Sets the name of the user
     * @param name - User name
     * @throws UserException
     */
    private void setName(String name) throws UserException{

        if(!name.isEmpty() && name!=null){
            if(name.length() <= MAX_LENGTH_NAME){
                this.name = name;
            }else{
                throw new UserException(NAME_CANT_BE_HIGHER_THAN_50);
            }
        }else{
            throw new UserException(NAME_CANT_BE_EMPTY_NAME);
        }
    }

    /**
     * Sets the email of the user
     * @param email - User email
     * @throws UserException
     */
    private  void  setEmail(String email) throws UserException{

        if (!email.isEmpty() && email!=null) {
            if(email.length() <= MAX_LENGTH_EMAIL){
                CharSequence emailCharSequence = email;
                if(Patterns.EMAIL_ADDRESS.matcher(emailCharSequence).matches()) {
                    this.email = email;
                }else{
                    throw new UserException(INVALID_EMAIL);
                }
            }else{
                throw  new UserException(EMAIL_CANT_BE_HIGHER_THAN_150);
            }
        }else{
            throw  new UserException(EMAIL_CANT_BE_EMPTY_EMAIL);
        }
    }

    /**
     * Verify email confirmation
     * @param confirmationMail - User confirmation email
     * @throws UserException
     */
    private void verifyEmailConfirmation(String confirmationMail) throws UserException{
        if(confirmationMail!=null && !confirmationMail.isEmpty()) {
            if (!email.equals(confirmationMail)) {
                throw new UserException(EMAIL_ARE_NOT_EQUALS);
            }else{
                // Nothing to do
            }
        }
        else{
            throw new UserException(EMAIL_CONFIRMATION_CANT_BE_EMPTY);
        }
    }

    /**
     * Sets the username of the user
     * @param username - Username of the user
     * @throws UserException
     */
    private  void  setUsername (String username) throws UserException{

        if (username!=null && !username.isEmpty()) {
            if(username.length() <= MAX_LENGTH_USERNAME){
                this.username = username;
            }else{
                throw  new UserException(USERNAME_CANT_BE_HIGHER_THAN_100);
            }
        }else{
            throw  new UserException(USERNAME_CANT_BE_EMPTY_USERNAME);
        }

    }

    /**
     * Sets the password of the user
     * @param password - User password
     * @throws UserException
     */
    private  void  setPassword (String password) throws UserException{

        if (!password.isEmpty() && password!=null){
            if(password.length() >= MIN_LENGTH_PASSWORD){
                this.password = password;
            }else{
                throw  new UserException(PASSWORD_CANT_BE_LESS_THAN_6);
            }
        }else{
            throw  new UserException(PASSWORD_CANT_BE_EMPTY_PASSWORD);
        }

    }

    /**
     * Verify password confirmation
     * @param confirmationPassword - User password confirmation
     * @throws UserException
     */
    private void verifyPasswordConfirmation(String confirmationPassword) throws UserException{
        if(confirmationPassword!=null && !confirmationPassword.isEmpty()){
            if (!password.equals(confirmationPassword)){
                throw new UserException(PASSWORD_ARE_NOT_EQUALS);
            }else{
                // Nothing to do
            }
        }
        else{
            throw new UserException(CONFIRM_PASSWORD_CANT_BE_EMPTY);
        }
    }

    /**
     * Gets the ID of the user
     * @return int - User ID
     */
    public int getIdUser(){
        return idUser;
    }

    /**
     * Gets the name of the user
     * @return String - User name
     */
    public String getName(){
        return name;
    }

    /**
     * Gets the username of the user
     * @return String - Username of the user
     */
    public String getUsername(){
        return username;
    }

    /**
     * Gets the email of the user
     * @return String - User email
     */
    public String getEmail(){
        return email;
    }

    /**
     * Gets the password of the user
     * @return String - User password
     */
    public String getPassword(){
        return password;
    }
}
