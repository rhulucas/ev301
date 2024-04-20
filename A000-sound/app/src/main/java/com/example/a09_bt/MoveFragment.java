package com.example.a09_bt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class MoveFragment extends Fragment {
    private Button buttonSound1;
    private EV3Service mref_ev3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_move, container, false);
        // Initialize views and set up UI components
        buttonSound1 = rootView.findViewById(R.id.buttonSound1);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Set up event listeners and initialize EV3Service
        mref_ev3 = ((MainActivity) getActivity()).getEV3Service();

        // Set up button click listener for buttonSound1
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

        // Set up robot movement functionality
    }

    // Other methods and functionality specific to the move screen
}
