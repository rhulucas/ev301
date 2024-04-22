package com.example.ev3_controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class SoundFragment extends Fragment {
    private EV3Service mref_ev3;
    private Button buttonSound2;
    private Button buttonSound3;
    private Button buttonSound1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sound, container, false);
        // Initialize views and set up UI components
        buttonSound1 = rootView.findViewById(R.id.buttonSound1);
        buttonSound2 = rootView.findViewById(R.id.buttonSound2);
        buttonSound3 = rootView.findViewById(R.id.buttonSound3);
        return rootView;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Set up event listeners and initialize EV3Service
        mref_ev3 = ((MainActivity) getActivity()).getEV3Service();
        // Set up robot movement functionality

        // Setup the button listener for buttonSound2


        buttonSound1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("PlayToneFile_0x94_sound1");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });


        buttonSound2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("PlayToneFile_0x94_sound2");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });

        buttonSound3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("PlayToneFile_0x94_sound3");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });

    }




}