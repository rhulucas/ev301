package com.example.a09_bt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class MoveFragment extends Fragment{
    private EV3Service mref_ev3;
    private ImageButton vvForwardButton;
    private ImageButton vvBackwardButton;
    private ImageButton vvLeft;
    private ImageButton vvRight;
    private ImageButton vvUp;
    private ImageButton vvDown;
    int currentSpeed = 60;
    Switch cv_Switch;
    boolean isspin = false;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_move, container, false);
        vvForwardButton = rootView.findViewById(R.id.vv_forward);
        vvBackwardButton = rootView.findViewById(R.id.vv_backward);
        vvLeft = rootView.findViewById(R.id.vv_left);
        vvRight = rootView.findViewById(R.id.vv_right);
        vvUp = rootView.findViewById(R.id.vv_up);
        vvDown = rootView.findViewById(R.id.vv_down);
        cv_Switch = rootView.findViewById(R.id.vv_switch);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mref_ev3 = ((MainActivity) getActivity()).getEV3Service();

        cv_Switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mref_ev3.startSpin();
                } else {
                    mref_ev3.stopMotor();
                }
            }
        });

        vvForwardButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Button pressed
                        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.button_pressed));
                        view.setScaleX(1.3f);
                        view.setScaleY(1.3f);
                        if (view.getId() == R.id.vv_forward) {
                            mref_ev3.startMotorMoveContinuous();
                        }
                        // Handle other button press events
                        return true;

                    case MotionEvent.ACTION_UP:
                        // Button released
                        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.button_released));
                        view.setScaleX(0.9f);
                        view.setScaleY(0.9f);
                        if (view.getId() == R.id.vv_forward) {
                            mref_ev3.stopMotor();
                        }
                        // Handle other button release events
                        return true;
                }
                return false;
            }
        });

        vvBackwardButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Button pressed
                        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.button_pressed));
                        view.setScaleX(1.3f);
                        view.setScaleY(1.3f);
                        if (view.getId() == R.id.vv_backward) {
                            mref_ev3.MotorBackwardContinuous();
                        }
                        // Handle other button press events
                        return true;

                    case MotionEvent.ACTION_UP:
                        // Button released
                        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.button_released));
                        view.setScaleX(0.9f);
                        view.setScaleY(0.9f);
                        if (view.getId() == R.id.vv_backward) {
                            mref_ev3.stopMotor();
                        }
                        // Handle other button release events
                        return true;
                }
                return false;
            }
        });

        vvLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Button pressed
                        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.button_pressed));
                        view.setScaleX(1.3f);
                        view.setScaleY(1.3f);
                        if (view.getId() == R.id.vv_left) {
                            mref_ev3.Left();
                        }
                        // Handle other button press events
                        return true;

                    case MotionEvent.ACTION_UP:
                        // Button released
                        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.button_released));
                        view.setScaleX(0.9f);
                        view.setScaleY(0.9f);
                        if (view.getId() == R.id.vv_left) {
                            mref_ev3.stopMotor();
                        }
                        // Handle other button release events
                        return true;
                }
                return false;
            }
        });

        vvUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Button pressed
                        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.button_pressed));
                        view.setScaleX(1.3f);
                        view.setScaleY(1.3f);
                        if (view.getId() == R.id.vv_up) {
                            mref_ev3.Up();
                        }
                        // Handle other button press events
                        return true;

                    case MotionEvent.ACTION_UP:
                        // Button released
                        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.button_released));
                        view.setScaleX(0.9f);
                        view.setScaleY(0.9f);
                        if (view.getId() == R.id.vv_up) {
                            mref_ev3.stopMotor();
                        }
                        // Handle other button release events
                        return true;
                }
                return false;
            }
        });

        vvDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Button pressed
                        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.button_pressed));
                        view.setScaleX(1.3f);
                        view.setScaleY(1.3f);
                        if (view.getId() == R.id.vv_down) {
                            mref_ev3.Down();
                        }
                        // Handle other button press events
                        return true;

                    case MotionEvent.ACTION_UP:
                        // Button released
                        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.button_released));
                        view.setScaleX(0.9f);
                        view.setScaleY(0.9f);
                        if (view.getId() == R.id.vv_down) {
                            mref_ev3.stopMotor();
                        }
                        // Handle other button release events
                        return true;
                }
                return false;
            }
        });

        vvRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Button pressed
                        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.button_pressed));
                        view.setScaleX(1.3f);
                        view.setScaleY(1.3f);
                        if (view.getId() == R.id.vv_right) {
                            mref_ev3.Right();
                        }
                        // Handle other button press events
                        return true;

                    case MotionEvent.ACTION_UP:
                        // Button released
                        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.button_released));
                        view.setScaleX(0.9f);
                        view.setScaleY(0.9f);
                        if (view.getId() == R.id.vv_right) {
                            mref_ev3.stopMotor();
                        }
                        // Handle other button release events
                        return true;
                }
                return false;
            }
        });
    }
}
