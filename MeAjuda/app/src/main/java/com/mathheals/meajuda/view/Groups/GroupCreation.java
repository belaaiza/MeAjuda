package com.mathheals.meajuda.view.Groups;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.dao.GroupDAO;
import com.mathheals.meajuda.dao.GroupHasUserDAO;
import com.mathheals.meajuda.dao.UserDAO;
import com.mathheals.meajuda.exception.UserException;
import com.mathheals.meajuda.model.Group;
import com.mathheals.meajuda.model.User;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class GroupCreation extends Fragment implements View.OnClickListener {

    private EditText groupNameEditText;
    private EditText descriptionEditText;
    private AutoCompleteTextView membersAutoCompleteTextView;
    private ImageView addMemberImageView;
    private Button createGroupButton;

    private List<Integer> userIdList = new ArrayList<>();

    private UserDAO userDAO;

    public GroupCreation() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_group_creation, container, false);

        userDAO = UserDAO.getInstance(getContext());

        settingView(view);
        return view;
    }

    private void settingView(View view) {
        this.groupNameEditText = (EditText) view.findViewById(R.id.groupNameField);
        this.descriptionEditText = (EditText) view.findViewById(R.id.descriptionField);
        this.addMemberImageView = (ImageView) view.findViewById(R.id.addMemberImage);
        this.membersAutoCompleteTextView = (AutoCompleteTextView) view.findViewById(
                R.id.membersField);
        this.createGroupButton = (Button) view.findViewById(R.id.createGroupButton);

        createGroupButton.setOnClickListener(this);
        addMemberImageView.setOnClickListener(this);

        membersAutoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<User> userList = new ArrayList<>();
                try {
                    userList = userDAO.searchUserByLoginWithLike(s.toString(), 3);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UserException e) {
                    e.printStackTrace();
                }

                List<String> userLoginList = new ArrayList<>();

                for(int i = 0; i < userList.size(); i++) {
                    userLoginList.add(userList.get(i).getUsername());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                        android.R.layout.simple_list_item_1, userLoginList);

                membersAutoCompleteTextView.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.addMemberImage) {
            String typedUsername = membersAutoCompleteTextView.getText().toString();

            Integer idUser = userDAO.searchUserByLogin(typedUsername);

            if(idUser != null) {
                userIdList.add(idUser);

                Toast.makeText(getContext(), "Membro adicionado!", Toast.LENGTH_SHORT).show();

                membersAutoCompleteTextView.setText("");
            }
        }else if(view.getId() == R.id.createGroupButton) {
            GroupDAO groupDAO = GroupDAO.getInstance(getContext());

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences
                    (getContext());

            Integer idUserLoggedIn = sharedPreferences.getInt(getContext().getString
                    (R.string.key_id), -1);

            Group group = new Group(idUserLoggedIn, groupNameEditText.getText().toString());

            Integer idGroup = groupDAO.createGroup(group);

            GroupHasUserDAO groupHasUserDAO = GroupHasUserDAO.getInstance(getContext());

            groupHasUserDAO.addListOfMembers(idGroup, userIdList);

            Toast.makeText(getContext(), "Grupo criado com sucesso!", Toast.LENGTH_SHORT).show();
        }
    }
}
