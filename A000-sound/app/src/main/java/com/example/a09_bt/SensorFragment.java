package com.example.a09_bt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class SensorFragment extends Fragment {
    private EV3Service mref_ev3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_move, container, false);
        // Initialize views and set up UI components
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Set up event listeners and initialize EV3Service
        mref_ev3 = ((MainActivity) getActivity()).getEV3Service();
        // Set up robot movement functionality
    }

    // Other methods and functionality specific to the move screen
}