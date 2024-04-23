package com.example.ev3_controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class SoundFragment extends Fragment {
    private EV3Service mref_ev3;
    private Button buttonSound2;
    private Button buttonSound1;
    private ImageButton whiteKey1;
    private ImageButton whiteKey2;
    private ImageButton whiteKey3;
    private ImageButton whiteKey4;
    private ImageButton whiteKey5;
    private ImageButton whiteKey6;
    private ImageButton whiteKey7;
    private ImageButton whiteKey8;
    private ImageButton whiteKey9;
    private ImageButton whiteKey10;
    private ImageButton whiteKey11;
    private ImageButton whiteKey12;
    private ImageButton whiteKey13;
    private ImageButton whiteKey14;
    private ImageButton blackKey1;
    private ImageButton blackKey2;
    private ImageButton blackKey3;
    private ImageButton blackKey4;
    private ImageButton blackKey5;
    private ImageButton blackKey6;
    private ImageButton blackKey7;
    private ImageButton blackKey8;
    private ImageButton blackKey9;
    private ImageButton blackKey10;
    private ImageButton blackKey11;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sound, container, false);
        // Initialize views and set up UI components
        buttonSound1 = rootView.findViewById(R.id.buttonSound1);
        buttonSound2 = rootView.findViewById(R.id.buttonSound2);
        whiteKey1 = rootView.findViewById(R.id.whiteKey1);
        whiteKey2 = rootView.findViewById(R.id.whiteKey2);
        whiteKey3 = rootView.findViewById(R.id.whiteKey3);
        whiteKey4 = rootView.findViewById(R.id.whiteKey4);
        whiteKey5 = rootView.findViewById(R.id.whiteKey5);
        whiteKey6 = rootView.findViewById(R.id.whiteKey6);
        whiteKey7 = rootView.findViewById(R.id.whiteKey7);
        whiteKey8 = rootView.findViewById(R.id.whiteKey8);
        whiteKey9 = rootView.findViewById(R.id.whiteKey9);
        whiteKey10 = rootView.findViewById(R.id.whiteKey10);
        whiteKey11 = rootView.findViewById(R.id.whiteKey11);
        whiteKey12 = rootView.findViewById(R.id.whiteKey12);
        whiteKey13 = rootView.findViewById(R.id.whiteKey13);
        whiteKey14 = rootView.findViewById(R.id.whiteKey14);
        blackKey1 = rootView.findViewById(R.id.blackKey1);
        blackKey2 = rootView.findViewById(R.id.blackKey2);
        blackKey3 = rootView.findViewById(R.id.blackKey3);
        blackKey4 = rootView.findViewById(R.id.blackKey4);
        blackKey5 = rootView.findViewById(R.id.blackKey5);
        blackKey6 = rootView.findViewById(R.id.blackKey6);
        blackKey7 = rootView.findViewById(R.id.blackKey7);
        blackKey8 = rootView.findViewById(R.id.blackKey8);
        blackKey9 = rootView.findViewById(R.id.blackKey9);
        blackKey10 = rootView.findViewById(R.id.blackKey10);
        blackKey11 = rootView.findViewById(R.id.blackKey11);

        return rootView;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Set up event listeners and initialize EV3Service
        mref_ev3 = ((MainActivity) getActivity()).getEV3Service();
        // Set up robot movement functionality

        // Setup the button listener for buttonSound2

        whiteKey1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("whiteKey1");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });
        whiteKey2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("whiteKey2");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });
        whiteKey3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("whiteKey3");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });
        whiteKey4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("whiteKey4");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });
        whiteKey5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("whiteKey5");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });
        whiteKey6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("whiteKey6");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });
        whiteKey7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("whiteKey7");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });
        whiteKey8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("whiteKey8");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });
        whiteKey9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("whiteKey9");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });
        whiteKey10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("whiteKey10");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });
        whiteKey11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("whiteKey11");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });whiteKey12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("whiteKey12");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });whiteKey13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("whiteKey13");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });
        whiteKey14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("whiteKey14");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });
        blackKey1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("blackKey1");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });
        blackKey2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("blackKey2");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });
        blackKey3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("blackKey3");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });
        blackKey4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("blackKey4");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });
        blackKey5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("blackKey5");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });
        blackKey6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("blackKey6");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });
        blackKey7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("blackKey7");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });
        blackKey8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("blackKey8");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });
        blackKey9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("blackKey9");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });
        blackKey10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("blackKey10");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });
        blackKey11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mref_ev3 != null) {
                    try {
                        mref_ev3.mf_EV3SendNoReplyCmd("blackKey11");
                    } catch (Exception e) {
                        // Handle the exception
                        e.printStackTrace();
                    }
                }
            }
        });


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

    }




}