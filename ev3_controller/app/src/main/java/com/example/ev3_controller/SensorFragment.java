package com.example.ev3_controller;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class SensorFragment extends Fragment {
    private EV3Service mref_ev3;

    private long startTime;
    private ImageButton upButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sensor, container, false);
        upButton = rootView.findViewById(R.id.vv_forward);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mref_ev3 = ((MainActivity) getActivity()).getEV3Service();

        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Change background color to yellow when clicked
                upButton.setBackgroundColor(Color.YELLOW);
                if (mref_ev3 != null) {
                    new Thread(() -> {
                        try {
                            mref_ev3.mf_EV3SendNoReplyCmd("forward");
                            startTime = System.currentTimeMillis(); // Record start time

                            while (true) {
                                byte[] response = mref_ev3.mf_EV3SendReplyCmd("ReadTouchSensor", (byte) 0x01, (byte) 0x10, (byte) 0x00);
                                if (response[3] != 0) { // Check if touch sensor is pressed
                                    long forwardDuration = System.currentTimeMillis() - startTime;
                                    mref_ev3.mf_EV3SendNoReplyCmd("backward");
                                    Thread.sleep(forwardDuration); // Move backward for the same duration
                                    mref_ev3.mf_EV3SendNoReplyCmd("MotorStop_0xa3"); // Stop the motor
                                    break;
                                }
                                Thread.sleep(100); // Check every 100 milliseconds
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }finally {
                            // Restore original background color
                            getActivity().runOnUiThread(() -> upButton.setBackgroundColor(getResources().getColor(R.color.white)));
                        }
                    }).start();
                }
            }
        });
    }
}
