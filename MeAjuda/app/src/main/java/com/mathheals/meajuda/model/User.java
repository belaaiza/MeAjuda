package com.mathheals.meajuda.model;

import android.util.Patterns;

import com.mathheals.meajuda.exception.UserException;
import java.text.ParseException;

public class User {
    public static final String USER_SUCCESSFULLY_REGISTERED = "Usuário cadastrado com sucesso";
    public static final String USER_SUCCESSFULLY_UPDATED = "Seu perfil foi atualizado com sucesso";
    public static final String FIRST_NAME_CANT_BE_EMPTY_NAME = "Acho que você está esquecendo de nos dizer seu nome.";
    public static final String FIRST_NAME_CANT_BE_HIGHER_THAN_50 = "O nome deve ter até 50 caracteres.";
    public static final String LAST_NAME_CANT_BE_EMPTY_NAME = "Acho que você está esquecendo de nos dizer seu último nome.";
    public static final String LAST_NAME_CANT_BE_HIGHER_THAN_50 = "O último nome deve ter até 50 caracteres.";
    public static final String EMAIL_CANT_BE_EMPTY_EMAIL = "Acho que você está esquecendo de nos dizer seu email.";
    public static final String EMAIL_CANT_BE_HIGHER_THAN_150 = "O e-mail deve ter até 150 caracteres.";
    public static final String INVALID_EMAIL = "Ops, esse e-mail é inválido.";
    public static final String CONFIRM_PASSWORD_CANT_BE_EMPTY = "Por favor, confirme sua senha";
    public static final String EMAIL_CONFIRMATION_CANT_BE_EMPTY = "Por favor, confirme seu e-mail";
    public static final String USERNAME_CANT_BE_EMPTY_USERNAME = "Acho que você está esquecendo de nos dizer seu login.";
    public static final String USERNAME_CANT_BE_HIGHER_THAN_100 = "O nome deve ter até 100 caracteres.";
    public static final String PASSWORD_CANT_BE_EMPTY_PASSWORD = "Acho que você está esquecendo de nos dizer sua senha.";
    public static final String PASSWORD_CANT_BE_LESS_THAN_6 = "A senha deve possuir no mínimo 6 caracteres.";
    public static final String EMAIL_ARE_NOT_EQUALS = "Ops, E-mails não conferem.";
    public static final String PASSWORD_ARE_NOT_EQUALS = "Ops, as senhas não conferem.";
    public static final String USERNAME_EXISTENT = "Ops, esse login já existe";
    private static final int MAX_LENGTH_NAME = 50;
    private static final int MAX_LENGTH_EMAIL = 150;
    private static final int MAX_LENGTH_USERNAME = 100;
    private static final int MIN_LENGTH_PASSWORD = 6;

    private Integer userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String classification;
    private Integer rating;
    private Integer idSchool;
    private Integer idClassification;

    //Complete constructor
    public User(String firstName, String lastName, String username, String email,
                String mailConfirmation, String password, String passwordConfirmation,
                Integer rating, Integer idSchool, Integer idClassification) throws UserException,
            ParseException{

        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setEmail(email);
        verifyEmailConfirmation(mailConfirmation);
        setPassword(password);
        verifyPasswordConfirmation(passwordConfirmation);
        setRating(rating);
        setClassification(rating);
        setIdSchool(idSchool);
        setIdClassification(rating);

    }

    //Update constructor
    public User(Integer userId, String firstName, String lastName, String username, String email,
                String mailConfirmation, String password, String passwordConfirmation)
            throws UserException, ParseException{

        setUserId(userId);
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setEmail(email);
        verifyEmailConfirmation(mailConfirmation);
        setPassword(password);
        verifyPasswordConfirmation(passwordConfirmation);
    }

    //Constructor without password and email confirmation
    public User(Integer userId, String firstName, String lastName, String username, String email,
                String password, Integer rating, Integer idSchool, Integer idClassification)
            throws UserException,
            ParseException{

        setUserId(userId);
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setRating(rating);
        setIdSchool(idSchool);
        setIdClassification(rating);
    }

    //Search user constructor
    public User(Integer userId, String firstName, String lastName, String email, String username,
                Integer rating, Integer idClassification) throws UserException{
        setUserId(userId);
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setEmail(email);
        setRating(rating);
        setIdClassification(rating);
    }

    public User(String firstName, String lastName, String username, Integer rating) throws UserException {
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setRating(rating);
        setIdClassification(rating);
        setClassification(rating);
    }

    public User(Integer userId, String firstName, String lastName, String email, String username,
                Integer rating) throws UserException {
        setUserId(userId);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setUsername(username);
        setRating(rating);
        setIdClassification(rating);
        setClassification(rating);
    }



    private void setClassification(Integer rating) {
        String classification;

        if(rating < 1) {
            classification = "Iniciante";
        } else if(rating < 2) {
            classification = "Aprendiz";
        } else if(rating < 3) {
            classification = "Nerd";
        } else if(rating < 4) {
            classification = "Especialista";
        } else if(rating < 5) {
            classification = "Mestre";
        } else {
            classification = "Gênio";
        }

        this.classification = classification;
    }

    private void setUserId(Integer id) {
        this.userId = id;
    }

    private void setRating (Integer rating){
        this.rating = rating;
    }

    private void setIdSchool (Integer idSchool){
        this.idSchool = idSchool;
    }

    //Revisar esse método se der tempo
    private void setIdClassification (Integer rating){
        Integer idClassification;

        if(rating < 1) {
            idClassification = 1;
        } else if(rating < 2) {
            idClassification = 2;
        } else if(rating < 3) {
            idClassification = 2;
        } else if(rating < 4) {
            idClassification = 3;
        } else if(rating < 5) {
            idClassification = 4;
        } else {
            idClassification = 5;
        }

        this.idClassification = idClassification;
    }

    private void setFirstName(String firstName) throws UserException{

        if(!firstName.isEmpty() && firstName!=null){
            if(firstName.length() <= MAX_LENGTH_NAME){
                this.firstName = firstName;
            }else{
                throw new UserException(FIRST_NAME_CANT_BE_HIGHER_THAN_50);
            }
        }else{
            throw new UserException(FIRST_NAME_CANT_BE_EMPTY_NAME);
        }
    }

    private void setLastName(String lastName) throws UserException{

        if(!lastName.isEmpty() && lastName!=null){
            if(lastName.length() <= MAX_LENGTH_NAME){
                this.lastName = lastName;
            }else{
                throw new UserException(LAST_NAME_CANT_BE_HIGHER_THAN_50);
            }
        }else{
            throw new UserException(LAST_NAME_CANT_BE_EMPTY_NAME);
        }
    }

    private  void  setEmail(String email) throws UserException{

        if (!email.isEmpty() && email!=null) {
            if(email.length() <= MAX_LENGTH_EMAIL){
                CharSequence emailCharSequence = email;
                if(Patterns.EMAIL_ADDRESS.matcher(emailCharSequence.toString().trim()).matches()){
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

    public String getClassification() {
        return classification;
    }

    public Integer getUserId() {return this.userId; }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getUsername(){
        return this.username;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public Integer getRating(){
        return this.rating;
    }

    public Integer getIdSchool(){
        return this.idSchool;
    }

    public Integer getIdClassification(){
        return this.idClassification;
    }
}
