package com.example.a09_bt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

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




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_move, container, false);
        vvForwardButton = rootView.findViewById(R.id.vv_forward);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mref_ev3 = ((MainActivity) getActivity()).getEV3Service();

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
    }
}