package com.example.a09_bt;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.a09_bt.databinding.ActivityMainBinding;

import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.bluetooth.BluetoothDevice;

// ver02 -- permission and connect
// ver03 -- connect to EV3
// ver04 -- sending Direct Command
// updated for Android 12 / under
// ver11 -- simple convert to MVC, many bad design issues
// ver12 -- clean MVC
// ver13 -- CMDMsg class
// ver21 -- no view in model
// ver22 -- play
// ver91 -- DEMO 1 split more, LCS, SoundFileName

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    private ActivityMainBinding binding;
    private EV3Service mref_ev3;

    // OOP get-set
    ActivityMainBinding cf_getBinding() {
        return binding;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mref_ev3 = new EV3Service(MainActivity.this);

        // Need grant permission once per install
        Pair<String, String> p = mref_ev3.mf_checkBTPermissions();
        binding.vvTvOut1.setText(p.first);
        binding.vvTvOut2.setText(p.second);

        binding.button.setOnTouchListener(this);

       binding.button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Context ctx =  MainActivity.this;
                        try {
                            mref_ev3.mf_EV3SendNoReplyCmd("MotorStart_0xa6");
                        }
                        catch(Exception e) {
                            binding.vvTvOut2.setText(e.getMessage());
                        }
                    }
                });

        binding.buttonSound1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Context ctx =  MainActivity.this;
                        try {
                            mref_ev3.mf_EV3SendNoReplyCmd("PlayToneFile_0x94_sound1");
                        }
                        catch(Exception e) {
                            binding.vvTvOut2.setText(e.getMessage());
                        }
                    }
                });
        binding.buttonSound2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Context ctx =  MainActivity.this;
                        try {
                            mref_ev3.mf_EV3SendNoReplyCmd("PlayToneFile_0x94_sound2");
                        }
                        catch(Exception e) {
                            binding.vvTvOut2.setText(e.getMessage());
                        }
                    }
                });
        binding.buttonSound3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Context ctx =  MainActivity.this;
                        try {
                            mref_ev3.mf_EV3SendNoReplyCmd("PlayToneFile_0x94_sound3");
                        }
                        catch(Exception e) {
                            binding.vvTvOut2.setText(e.getMessage());
                        }
                    }
                });


        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "FAB pressed", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch(motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
              try {
                  mref_ev3.mf_EV3SendNoReplyCmd("MotorStart_0xa6");
              }
              catch(Exception e) {
                  binding.vvTvOut2.setText(e.getMessage());
              }
              break;
            case MotionEvent.ACTION_UP:
                try {
                    mref_ev3.mf_EV3SendNoReplyCmd("MotorStop_0xa3");
                }
                catch(Exception e) {
                    binding.vvTvOut2.setText(e.getMessage());
                }
                break;
        }
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_first: mref_ev3.mf_requestBTPermissions();
                return true;
            case R.id.menu_second:
                binding.vvTvOut1.setText(mref_ev3.mf_locateInPairedBTList());
                return true;
            case R.id.menu_third:
                binding.vvTvOut2.setText(mref_ev3.mf_connectToEV3());
                return true;
            case R.id.menu_fourth:
                try {
                    mref_ev3.mf_EV3SendNoReplyCmd("MoveMotor_0xae_00");
                }
                catch(Exception e) {
                    binding.vvTvOut2.setText(e.getMessage());
                }
                return true;
            case R.id.menu_fifth:
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("PlayTone_0x94_01");
                    }
                    catch(Exception e) {
                        binding.vvTvOut2.setText(e.getMessage());
                    }
                    return true;
            case R.id.menu_sixth:
                try {
                    mref_ev3.mf_disconnFromEV3();
                }
                catch(Exception e) {
                    binding.vvTvOut2.setText(e.getMessage());
                }
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}