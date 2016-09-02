/**
 * File: RegisterFragment.java
 * Purpose: Register fragment of a new user in the database
 */

package com.example.geovanni.meajuda.presenter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.text.ParseException;
import com.example.geovanni.meajuda.R;
import com.example.geovanni.meajuda.exception.UserException;
import com.example.geovanni.meajuda.model.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserPresenter extends Fragment implements View.OnClickListener{

    private static final String STRING_EMPTY = "";

    private EditText nameField = null;
    private EditText mailField = null;
    private EditText mailConfirmationField = null;
    private EditText usernameField = null;
    private EditText passwordField = null;
    private EditText passwordConfirmField = null;

    private String name = STRING_EMPTY;
    private String username = STRING_EMPTY;
    private String mail = STRING_EMPTY;
    private String password = STRING_EMPTY;
    private String passwordConfirmation = STRING_EMPTY;
    private String mailConfirmation = STRING_EMPTY;


    /**
     * Creates and returns the view hierarchy associated with the fragment
     * @param inflater Object used to inflate any views in the fragment
     * @param container If non-null, is the parent view that the fragment should be attached to
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a
     *                             previous saved state as given here
     * @return View of the Register fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        assert inflater != null;
        assert container != null;
        assert savedInstanceState != null;

        View view = inflater.inflate(R.layout.register_user, container, false);
        assert view != null;

        Button register = (Button) view.findViewById(R.id.saveButton);
        assert register != null;

        this.settingEditText(view);

        register.setOnClickListener(this);


        return view;
    }

    /**
     * Registers new user in database
     * @param user Object that contain information of new user
     */
    private void registerUser(User user){
       //need implementation
    }

    /**
     * Starts login activity
     */
    private void startLoginActivity(){
       //need implementation
    }

    /**
     * Sets conditions to edit new user information
     * @param view Current view where the texts will be edited
     */
    private void settingEditText(View view){
        this.nameField = (EditText) view.findViewById(R.id.nameField);
        assert this.nameField != null;

        this.mailField = (EditText) view.findViewById(R.id.mailField);
        assert this.mailField != null;

        this.usernameField = (EditText) view.findViewById(R.id.loginField);
        assert this.usernameField != null;

        this.passwordField = (EditText) view.findViewById(R.id.passwordField);
        assert this.passwordField != null;

        this.mailConfirmationField = (EditText) view.findViewById(R.id.confirmMailField);
        assert this.mailConfirmationField != null;

        this.passwordConfirmField = (EditText) view.findViewById(R.id.confirmPasswordField);
        assert this.passwordConfirmField != null;

    }

    /**
     * Sets the previous typed text of the new user
     */
    private void settingTextTyped(){
        this.name = nameField.getText().toString();
        this.username = usernameField.getText().toString();
        this.mail = mailField.getText().toString();
        this.mailConfirmation = mailConfirmationField.getText().toString();
        this.password = passwordField.getText().toString();
        this.passwordConfirmation = passwordConfirmField.getText().toString();
    }

    /**
     * Sets the information typed on registration view
     * @param view Current view where the text was edited
     */
    @Override
    public void onClick(View view){
        assert view != null;

        Toast.makeText(getActivity(), "Usuário Cadastrado com Sucesso", Toast.LENGTH_LONG).show();
        settingTextTyped();

        validateUserInformation();
    }

    /**
     * Validates user information and registers if is valid
     */
    private void validateUserInformation(){
        try{
            User user = new User(name, username, mail, mailConfirmation,
                    password, passwordConfirmation);

            registerUser(user);

            String SUCCESSFUL_REGISTRATION_MESSAGE = "Bem-Vindo ao Aplicativo";
            Toast.makeText(getActivity().getBaseContext(),
                    SUCCESSFUL_REGISTRATION_MESSAGE, Toast.LENGTH_LONG).show();

            startLoginActivity();

        }catch(UserException e){
            String exceptionMessage = e.getMessage();
            assert exceptionMessage != null;

            switch(exceptionMessage){
                case User.NAME_CANT_BE_EMPTY_NAME:
                    nameField.requestFocus();
                    nameField.setError(exceptionMessage);
                    break;
                case User.NAME_CANT_BE_HIGHER_THAN_50:
                    nameField.requestFocus();
                    nameField.setError(exceptionMessage);
                    break;
                case User.EMAIL_CANT_BE_EMPTY_EMAIL:
                    mailField.requestFocus();
                    mailField.setError(exceptionMessage);
                    break;
                case User.EMAIL_CANT_BE_HIGHER_THAN_150:
                    mailField.requestFocus();
                    mailField.setError(exceptionMessage);
                    break;
                case User.INVALID_EMAIL:
                    mailField.requestFocus();
                    mailField.setError(exceptionMessage);
                    break;
                case User.EMAIL_ARE_NOT_EQUALS:
                    mailField.requestFocus();
                    mailField.setError(exceptionMessage);
                    break;
                case User.USERNAME_CANT_BE_EMPTY_USERNAME:
                    usernameField.requestFocus();
                    usernameField.setError(exceptionMessage);
                    break;
                case User.USERNAME_CANT_BE_HIGHER_THAN_100:
                    usernameField.requestFocus();
                    usernameField.setError(exceptionMessage);
                    break;
                case User.PASSWORD_CANT_BE_EMPTY_PASSWORD:
                    passwordField.requestFocus();
                    passwordField.setError(exceptionMessage);
                    break;
                case User.PASSWORD_CANT_BE_LESS_THAN_6:
                    passwordField.requestFocus();
                    passwordField.setError(exceptionMessage);
                    break;
                case User.PASSWORD_ARE_NOT_EQUALS:
                    passwordField.requestFocus();
                    passwordField.setError(exceptionMessage);
                    break;
                case User.USERNAME_EXISTENT:
                    usernameField.requestFocus();
                    usernameField.setError(exceptionMessage);
                    break;
                case User.CONFIRM_PASSWORD_CANT_BE_EMPTY:
                    passwordConfirmField.requestFocus();
                    passwordConfirmField.setError(exceptionMessage);
                    break;
                case User.EMAIL_CONFIRMATION_CANT_BE_EMPTY:
                    mailConfirmationField.requestFocus();
                    mailConfirmationField.setError(exceptionMessage);
                    break;
                default:
                    //nothing to do
                    break;
            }
        }catch(ParseException e){
            e.printStackTrace();
        }
    }
}
