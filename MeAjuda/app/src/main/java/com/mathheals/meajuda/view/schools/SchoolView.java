package com.mathheals.meajuda.view.schools;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mathheals.meajuda.R;
import com.mathheals.meajuda.model.Address;
import com.mathheals.meajuda.model.School;

public class SchoolView extends Fragment {

    private School currentSchool;

    public SchoolView(School school){
        this.currentSchool = school;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View schoolView = inflater.inflate(R.layout.fragment_school_show, container, false);

        setViews(schoolView);

        return schoolView;
    }

    private void setViews(View view) {
        TextView schoolName = (TextView) view.findViewById(R.id.schoolName);
        schoolName.setText(currentSchool.getName());

        TextView schoolDescription = (TextView) view.findViewById(R.id.description);
        schoolDescription.setText(currentSchool.getAdministrativeType());

        TextView schoolPhone = (TextView) view.findViewById(R.id.phone);
        schoolPhone.setText(currentSchool.getPhoneNumber());

        TextView schoolEmail = (TextView) view.findViewById(R.id.email);
        schoolEmail.setText(currentSchool.getEmail());

        TextView schoolAddress = (TextView) view.findViewById(R.id.address);
        Address address = currentSchool.getAddress();
        schoolAddress.setText(address.getDescription().trim() + "\n" + address.getCounty().trim() +
                " - " + address.getState().trim() + "\n" + address.getCEP().trim());
    }

    @Override
    public void onDestroy(){
        if(this.getArguments().getBoolean("comeFromSearch")){
            getActivity().finish();
        }

        super.onDestroy();
    }
}
