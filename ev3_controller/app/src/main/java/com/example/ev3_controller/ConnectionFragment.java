package com.example.ev3_controller;

import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ConnectionFragment extends Fragment {
    private EV3Service mref_ev3;
    private TextView vvTvOut1;
    private TextView vvBatteryText;
    private TextView vvTvOut2;
    private Button buttonConnect;
    private Button buttonDisconnect;
    private Button batteryButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_connection, container, false);
        vvTvOut1 = rootView.findViewById(R.id.vv_tvOut1);
        vvTvOut2 = rootView.findViewById(R.id.vv_tvOut2);
        buttonConnect = rootView.findViewById(R.id.button);
        buttonDisconnect = rootView.findViewById(R.id.buttonDisconnect);
        batteryButton = rootView.findViewById(R.id.batteryButton);
        vvBatteryText = rootView.findViewById(R.id.vv_batteryText);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mref_ev3 = ((MainActivity) getActivity()).getEV3Service();

        // Check Bluetooth permissions
        if (mref_ev3 != null) {
            Pair<String, String> p = mref_ev3.mf_checkBTPermissions();
            vvTvOut1.setText(p.first);
            vvTvOut2.setText(p.second);
        }

        // Set up button click listeners
        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    vvTvOut1.setText(mref_ev3.mf_locateInPairedBTList());
                    vvTvOut2.setText(mref_ev3.mf_connectToEV3());
                }
            }
        });

        buttonDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    vvTvOut2.setText(mref_ev3.mf_disconnFromEV3());
                }
            }
        });

        batteryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                         mref_ev3.mf_EV3SendReplyCmd("readBattery", (byte) 0x01, (byte) 0x10, (byte) 0x00);
                         byte[] reply = mref_ev3.mf_EV3SendReplyCmd("readBattery");
                         vvBatteryText.setText("Battery: " + reply[3] + "%");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}