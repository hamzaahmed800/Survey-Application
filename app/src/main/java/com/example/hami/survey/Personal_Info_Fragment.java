package com.example.hami.survey;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hami.survey.Globals.CommonCalls;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

/**
 * Created by HAMI on 06/10/2017.
 */

public class Personal_Info_Fragment extends Fragment implements View.OnClickListener {

    View view;
    Toolbar mToolbar;
    Button done;
    EditText name, fname, age, dob, cnic_no, cnin_p, cnin_ex, cast, mobile, skill, remark;
    Spinner edu;
    Spinner martial;
    Spinner availabel;
    EditText no_female_child,no_male_child;
    Spinner gender;
    Spinner religon;
    Spinner emp_status;
    Spinner emp_will;
    String education, availability, status_martial, no_of_childern,gen,religon_text,emp_text,emp_will_text;
    SharedPreferences preferences;
    public static final int Dilouge_ID = 0;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_personal_info, container, false);

        initView();

        return view;
    }



    private void initView() {

        mToolbar = (Toolbar) view.findViewById(R.id.main_bar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Personal Information");
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        mToolbar.setTitleTextColor(Color.WHITE);
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(),HomeActivity.class);
//                startActivity(intent);
//            }
//        });

        name = (EditText) view.findViewById(R.id.name_text);
        fname = (EditText) view.findViewById(R.id.fname_text);
        dob = (EditText) view.findViewById(R.id.date_of_birth);
        dob.addTextChangedListener(new MaskTextWatcher(dob, new SimpleMaskFormatter("NN-NN-NNNN")));
        cnic_no = (EditText) view.findViewById(R.id.cnic_text);
        cnin_p = (EditText) view.findViewById(R.id.cnic_place_text);
        cnin_ex = (EditText) view.findViewById(R.id.cnic_expiry);
        cnin_ex.addTextChangedListener(new MaskTextWatcher(cnin_ex, new SimpleMaskFormatter("NN-NN-NNNN")));
        cast = (EditText) view.findViewById(R.id.cast_text);
        mobile = (EditText) view.findViewById(R.id.cell_no);
        mobile.addTextChangedListener(new MaskTextWatcher(mobile, new SimpleMaskFormatter("NNNN-NNNNNNNNNNNNNNN")));
        skill = (EditText) view.findViewById(R.id.skill_text);
        remark = (EditText) view.findViewById(R.id.remarks);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().showDialog(Dilouge_ID);
            }
        });



        //Setting up the spinner

        emp_will = (Spinner) view.findViewById(R.id.emp_will);
        ArrayAdapter<CharSequence> arrayAdapter0011 = ArrayAdapter.createFromResource(getContext(), R.array.emp_status, android.R.layout.simple_list_item_1);
        emp_will.setAdapter(arrayAdapter0011);
        emp_will.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                emp_will_text = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        emp_status = (Spinner) view.findViewById(R.id.emp_status);
        ArrayAdapter<CharSequence> arrayAdapter001 = ArrayAdapter.createFromResource(getContext(), R.array.emp_status, android.R.layout.simple_list_item_1);
        emp_status.setAdapter(arrayAdapter001);
        emp_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                emp_text = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        religon = (Spinner) view.findViewById(R.id.religon_next);
        ArrayAdapter<CharSequence> arrayAdapter01 = ArrayAdapter.createFromResource(getContext(), R.array.religon, android.R.layout.simple_list_item_1);
        religon.setAdapter(arrayAdapter01);
        religon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                religon_text = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        gender = (Spinner) view.findViewById(R.id.gender_spinner);
        ArrayAdapter<CharSequence> arrayAdapter0 = ArrayAdapter.createFromResource(getContext(), R.array.gender, android.R.layout.simple_list_item_1);
        gender.setAdapter(arrayAdapter0);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gen = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        edu = (Spinner) view.findViewById(R.id.edu_spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.qualification, android.R.layout.simple_list_item_1);
        edu.setAdapter(arrayAdapter);
        edu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                education = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        martial = (Spinner) view.findViewById(R.id.spinner_marital);
        ArrayAdapter<CharSequence> arrayAdapter1 = ArrayAdapter.createFromResource(getContext(), R.array.martial_status, android.R.layout.simple_list_item_1);
        martial.setAdapter(arrayAdapter1);
        martial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                status_martial = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        availabel = (Spinner) view.findViewById(R.id.available);
        ArrayAdapter<CharSequence> arrayAdapter2 = ArrayAdapter.createFromResource(getContext(), R.array.availability, android.R.layout.simple_list_item_1);
        availabel.setAdapter(arrayAdapter2);
        availabel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                availability = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        no_male_child = (EditText) view.findViewById(R.id.no_male_child);
        no_female_child = (EditText) view.findViewById(R.id.no_female_child);
        //ArrayAdapter<CharSequence> arrayAdapter3 = ArrayAdapter.createFromResource(getContext(), R.array.family_member, android.R.layout.simple_list_item_1);
        //no_child1.setAdapter(arrayAdapter3);
        /*no_child1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                no_of_childern = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/



        done = (Button) view.findViewById(R.id.done_personal_button);
        done.setOnClickListener(this);


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.done_personal_button:
                if (!TextUtils.isEmpty(name.getText().toString()) && !TextUtils.isEmpty(fname.getText().toString()) &&
                        !TextUtils.isEmpty(cnic_no.getText().toString())
                        && !TextUtils.isEmpty(dob.getText().toString()) && !TextUtils.isEmpty(cnin_p.getText().toString())
                        && !TextUtils.isEmpty(cnin_ex.getText().toString())
                        ) {

                    done_personal_info();

                } else {

                    Toast.makeText(getContext(), "Name, father name, age, date of birth , CNIC Data, religon  required!", Toast.LENGTH_LONG).show();
                }

                break;
        }
    }

    private void done_personal_info() {

        storeData();
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);

    }


    public void storeData() {

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CommonCalls.name, name.getText().toString());
        editor.putString(CommonCalls.fname, fname.getText().toString());
        editor.putString(CommonCalls.age, null);
        editor.putString(CommonCalls.dob, dob.getText().toString());
        editor.putString(CommonCalls.cnic_no, cnic_no.getText().toString());
        editor.putString(CommonCalls.cnic_place, cnin_p.getText().toString());
        editor.putString(CommonCalls.cnic_expi, cnin_ex.getText().toString());
        editor.putString(CommonCalls.gender,gen);
        editor.putString(CommonCalls.cast, cast.getText().toString());
        editor.putString(CommonCalls.religon, religon_text);
        editor.putString(CommonCalls.contact, mobile.getText().toString());
        editor.putString(CommonCalls.skills, skill.getText().toString());
        editor.putString(CommonCalls.emp_status, emp_text);
        editor.putString(CommonCalls.emp_will, emp_will_text);
        editor.putString(CommonCalls.remarks, remark.getText().toString());
        editor.putString(CommonCalls.education, education);
        editor.putString(CommonCalls.martial_Status, status_martial);
        editor.putString(CommonCalls.availabilit, availability);

        int no_of_male_child = 0, no_of_female_child = 0;
        if(!no_male_child.getText().toString().equals("")) {
            no_of_male_child = Integer.parseInt(no_male_child.getText().toString());
        }

        if(!no_female_child.getText().toString().equals("")) {
            no_of_female_child = Integer.parseInt(no_female_child.getText().toString());
        }

        editor.putInt(CommonCalls.no_male_child, no_of_male_child);
        editor.putInt(CommonCalls.no_female_child, no_of_female_child);


        editor.apply();

    }

}
