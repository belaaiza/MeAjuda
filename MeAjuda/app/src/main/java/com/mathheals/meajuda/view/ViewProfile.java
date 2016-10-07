package com.mathheals.meajuda.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mathheals.meajuda.R;

public class ViewProfile extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.material_design_profile_screen_xml_ui_design, container, false);
    }
}
