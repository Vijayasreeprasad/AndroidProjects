package com.microsoft.projectoxford.emotionsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by Viji on 5/8/2017.
 */

public class RiddleFragment extends Fragment {

    private static final String ARG_RIDDLE_ID = "riddle_id";

    private Riddle mRiddle;
    private EditText mTitleField;
    private EditText mRiddleField;
    //private Button mDateButton;
    private CheckBox mSolvedCheckBox;


    public static RiddleFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_RIDDLE_ID, crimeId);

        RiddleFragment fragment = new RiddleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID riddleId = (UUID) getArguments().getSerializable(ARG_RIDDLE_ID);
        mRiddle = RiddleLab.get(getActivity()).getCrime(riddleId);
    }

    @Override
    public void onPause() {
        super.onPause();
        RiddleLab.get(getActivity())
                .updateCrime(mRiddle);
    }


    //This method is where you inflate the layout for the fragment’s view and return the
    // inflated View to the hosting activity. The LayoutInflater and ViewGroup parameters
    // are necessary to inflate the layout.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_riddle, container, false);
    //end: The Bundle will contain data that this method can use to re-create the view from a saved state.


        //////// get title
        mTitleField = (EditText) v.findViewById(R.id.riddle_title);
        mTitleField.setText(mRiddle.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mRiddle.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });
///////////end get title/////////////////////////////

        //Edit field
        mTitleField = (EditText) v.findViewById(R.id.riddle_field);
        mTitleField.setText(mRiddle.getField());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mRiddle.setField(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        //--------------------

      /*  mDateButton = (Button) v.findViewById(R.id.crime_date);
        mDateButton.setText(mRiddle.getDate().toString());
        mDateButton.setEnabled(false); */

        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.riddle_solved);
        mSolvedCheckBox.setChecked(mRiddle.isUsed());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                mRiddle.setUsed(isChecked);
            }
        });
        return v;
    }



}
