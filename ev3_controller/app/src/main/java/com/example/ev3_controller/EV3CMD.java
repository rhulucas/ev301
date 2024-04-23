package com.example.ev3_controller;

public class EV3CMD {
    int ind;
    String type;
    CMDMsg msg;

    String mf_getType() {
        return type;
    }
    CMDMsg mf_getCmd() {
        return msg;
    }

    public EV3CMD(String id, byte ... byteArgs) {
        int index = 1;
        byte port=0, type=0, mode=0;

        if (byteArgs.length == 1) {
            index = byteArgs[0];
        }
        else if (byteArgs.length == 3) {
            port = byteArgs[0];
            type = byteArgs[1];
            mode = byteArgs[2];
        }

        if (id.equals("forward")) {
            mf_makeMotorStartCmd();
        }
        else if (id.equals("backward")) {
            mf_makeMotorBackwardCmd();
        }
        else if (id.equals("up")) { // Medium Motor Port D
            mf_makeMotorUpCmd();
        }
        else if (id.equals("down")) {
            mf_makeMotorDownCmd();
        }
        else if (id.equals("left")) {
            mf_makeTurnLeftCmd();
        }
        else if (id.equals("right")) {
            mf_makeTurnRightCmd();
        }

        else if (id.equals("whiteKey1")) {
            mf_makePlayToneCmd(0xf7);
        }
        else if (id.equals("whiteKey2")) {
            mf_makePlayToneCmd(0x106);
        }
        else if (id.equals("whiteKey3")) {
            mf_makePlayToneCmd(0x126);
        }
        else if (id.equals("whiteKey4")) {
            mf_makePlayToneCmd(0x14a);
        }
        else if (id.equals("whiteKey5")) {
            mf_makePlayToneCmd(0x15d);
        }
        else if (id.equals("whiteKey6")) {
            mf_makePlayToneCmd(0x188);
        }
        else if (id.equals("whiteKey7")) {
            mf_makePlayToneCmd(0x1b8);
        }
        else if (id.equals("whiteKey8")) {
            mf_makePlayToneCmd(0x1ee);
        }
        else if (id.equals("whiteKey9")) {
            mf_makePlayToneCmd(0x20b);
        }
        else if (id.equals("whiteKey10")) {
            mf_makePlayToneCmd(0x24b);
        }
        else if (id.equals("whiteKey11")) {
            mf_makePlayToneCmd(0x293);
        }
        else if (id.equals("whiteKey12")) {
            mf_makePlayToneCmd(0x2DB);
        }
        else if (id.equals("whiteKey13")) {
            mf_makePlayToneCmd(0x32B);
        }
        else if (id.equals("whiteKey14")) {
            mf_makePlayToneCmd(0x383);
        }
        else if (id.equals("blackKey1")) {
            mf_makePlayToneCmd(0xe9);
        }
        else if (id.equals("blackKey2")) {
            mf_makePlayToneCmd(0x115);
        }
        else if (id.equals("blackKey3")) {
            mf_makePlayToneCmd(0x137);
        }
        else if (id.equals("blackKey4")) {
            mf_makePlayToneCmd(0x172);
        }
        else if (id.equals("blackKey5")) {
            mf_makePlayToneCmd(0x19f);
        }
        else if (id.equals("blackKey6")) {
            mf_makePlayToneCmd(0x1d2);
        }
        else if (id.equals("blackKey7")) {
            mf_makePlayToneCmd(0x22a);
        }
        else if (id.equals("blackKey8")) {
            mf_makePlayToneCmd(0x26e);
        }
        else if (id.equals("blackKey9")) {
            mf_makePlayToneCmd(0x2ff);
        }
        else if (id.equals("blackKey10")) {
            mf_makePlayToneCmd(0x353);
        }
        else if (id.equals("blackKey11")) {
            mf_makePlayToneCmd(0x37f);
        }

        else if (id.equals("PlayToneFile_0x94_sound1")) {
            mf_makePlayToneFileCmd_1();
        }
        else if (id.equals("PlayToneFile_0x94_sound2")) {
            mf_makePlayToneFileCmd_2();
        }
        else if (id.equals("PlayToneFile_0x94_sound3")) {
            mf_makePlayToneFileCmd_3();
        }
        else if (id.equals("MotorStart_0xa6")) {
            mf_makeMotorStartCmd();
        }
        else if (id.equals("MotorStop_0xa3")) {
            mf_makeMotorStopCmd();
        }
        else if (id.equals("ReadTouchSensor")) {
            mf_makeReadTouchSensorCmd();
        }
        else if(id.equals("readBattery")){
            mf_makeBatteryLevelCmd();
        }
        else if (id.equals("ReadMotionSensor_0x99_1c")) {
            mf_makeReadMotionSensorCmd();
        }
        else if (id.equals("spin_start")) {
            mf_makeTurnLeftCmd();
        }
    }

    private void mf_makeMotorStartCmd() { // Big Motor forward
        msg = new CMDMsg(15, false, (byte) 0);
        msg.mv_setOPCODE((byte) 0xa4);
        msg.mv_setOPCMD((byte) 0x00);
        msg.mv_setLC0(9, (byte) 6);// ports B and C
        msg.mv_setLC1(10, (byte) 60); // power

        msg.mv_setLC0(12, (byte) 0xa6);
        msg.mv_setLC0(13, (byte) 0);
        msg.mv_setLC0(14, (byte) 6);
    }

    private void mf_makeMotorDownCmd() { // medium motor down
        msg = new CMDMsg(15, false, (byte) 0);
        msg.mv_setOPCODE((byte) 0xa4);
        msg.mv_setOPCMD((byte) 0x00);
        msg.mv_setLC0(9, (byte) 8);
        msg.mv_setLC1(10, (byte) -60); // power
        msg.mv_setLC0(12, (byte) 0xa6);
        msg.mv_setLC0(13, (byte) 0);
        msg.mv_setLC0(14, (byte) 8);
    }

    private void mf_makeMotorUpCmd() { // medium motor up
        msg = new CMDMsg(15, false, (byte) 0);
        msg.mv_setOPCODE((byte) 0xa4);
        msg.mv_setOPCMD((byte) 0x00);
        msg.mv_setLC0(9, (byte) 8);// medium motor
        msg.mv_setLC1(10, (byte) 60); // power
        msg.mv_setLC0(12, (byte) 0xa6);
        msg.mv_setLC0(13, (byte) 0);
        msg.mv_setLC0(14, (byte) 0x08); // port D
    }

    private void mf_makeMotorBackwardCmd() { // Big Motor backward
        msg = new CMDMsg(15, false, (byte) 0);
        msg.mv_setOPCODE((byte) 0xa4);
        msg.mv_setOPCMD((byte) 0x00);
        msg.mv_setLC0(9, (byte) 6);// ports B and C
        msg.mv_setLC1(10, (byte) -60); // power
        msg.mv_setLC0(12, (byte) 0xa6);
        msg.mv_setLC0(13, (byte) 0);
        msg.mv_setLC0(14, (byte) 6);
    }

    private void mf_makeMotorBLeftCmd() { // Left turn for Port B
        msg = new CMDMsg(15, false, (byte) 0);
        msg.mv_setOPCODE((byte) 0xa4);
        msg.mv_setOPCMD((byte) 0x00);
        msg.mv_setLC0(9, (byte) 2);// ports B
        msg.mv_setLC1(10, (byte) -60); // power
        msg.mv_setLC0(12, (byte) 0xa6);
        msg.mv_setLC0(13, (byte) 0);
        msg.mv_setLC0(14, (byte) 2);
    }

    private void mf_makeMotorCLeftCmd() { // Left turn for Port C
        msg = new CMDMsg(15, false, (byte) 0);
        msg.mv_setOPCODE((byte) 0xa4);
        msg.mv_setOPCMD((byte) 0x00);
        msg.mv_setLC0(9, (byte) 4);// ports C
        msg.mv_setLC1(10, (byte) 60); // power
        msg.mv_setLC0(12, (byte) 0xa6);
        msg.mv_setLC0(13, (byte) 0);
        msg.mv_setLC0(14, (byte) 4);
    }

    public void mf_makeTurnLeftCmd() {
        mf_makeMotorBLeftCmd();
        mf_makeMotorCLeftCmd();
    }

    ////////////
    private void mf_makeMotorBRightCmd() { // Right turn for Port B
        msg = new CMDMsg(15, false, (byte) 0);
        msg.mv_setOPCODE((byte) 0xa4);
        msg.mv_setOPCMD((byte) 0x00);
        msg.mv_setLC0(9, (byte) 2);// ports B
        msg.mv_setLC1(10, (byte) 60); // power
        msg.mv_setLC0(12, (byte) 0xa6);
        msg.mv_setLC0(13, (byte) 0);
        msg.mv_setLC0(14, (byte) 2);
    }

    private void mf_makeMotorCRightCmd() { // Right turn for Port C
        msg = new CMDMsg(15, false, (byte) 0);
        msg.mv_setOPCODE((byte) 0xa4);
        msg.mv_setOPCMD((byte) 0x00);
        msg.mv_setLC0(9, (byte) 4);// ports C
        msg.mv_setLC1(10, (byte) -60); // power
        msg.mv_setLC0(12, (byte) 0xa6);
        msg.mv_setLC0(13, (byte) 0);
        msg.mv_setLC0(14, (byte) 4);
    }

    public void mf_makeTurnRightCmd() {
        mf_makeMotorBRightCmd();
        mf_makeMotorCRightCmd();
    }

    private void mf_makeMotorStopCmd() {
        msg = new CMDMsg(11, false, (byte) 0);
        msg.mv_setOPCODE((byte) 0xa3);
        msg.mv_setOPCMD((byte) 0x00);
        msg.mv_setLC0(9, (byte) 0x0f); // Stop all motors (ports A, B, C, and D)
    }

    public void mf_makePlayToneCmd(int frequency) {
        msg = new CMDMsg(16, false, (byte) 0);
        msg.mv_setOPCODE((byte) 0x94); // 7
        msg.mv_setOPCMD((byte) 0x01); // 8
        msg.mv_setLC0(9, (byte) 0x01); // 9
        msg.mv_setLC0(10, (byte) 0x82);
        msg.mv_setLC0(11, (byte) (frequency & 0xFF));
        msg.mv_setLC0(12, (byte) ((frequency >> 8) & 0xFF));
        msg.mv_setLC0(13, (byte) 0x82);
        msg.mv_setLC0(14, (byte) 0x23);
        msg.mv_setLC0(15, (byte) 0x01);
    }

    public void mf_makePlayToneFileCmd_1() {
        String str = "./ui/Startup";

        msg = new CMDMsg(12+str.length()+1, false, (byte) 0);
        msg.mv_setOPCODE((byte) 0x94);
        msg.mv_setOPCMD((byte) 0x02);
        msg.mv_setLC1(9, (byte) 50);
        msg.mv_setLCS(11, str);
    }

    public void mf_makePlayToneFileCmd_2() {
        String str = "./ui/GeneralAlarm";

        msg = new CMDMsg(12+str.length()+1, false, (byte) 0);
        msg.mv_setOPCODE((byte) 0x94);
        msg.mv_setOPCMD((byte) 0x02);
        msg.mv_setLC1(9, (byte) 50);
        msg.mv_setLCS(11, str);
    }

    public void mf_makePlayToneFileCmd_3() {
        String str = "./ui/PowerDown";

        msg = new CMDMsg(12+str.length()+1, false, (byte) 0);
        msg.mv_setOPCODE((byte) 0x94);
        msg.mv_setOPCMD((byte) 0x02);
        msg.mv_setLC1(9, (byte) 50);
        msg.mv_setLCS(11, str);
    }

    public void mf_makeReadTouchSensorCmd() {
        msg = new CMDMsg(15, true, (byte) 4);
        msg.mv_setOPCODE((byte) 0x99);
        msg.mv_setOPCMD((byte) 0x1b);
        msg.mv_setLC0(9, (byte) 0); // LAYER
        msg.mv_setLC0(10, (byte) 0x01); // Port 1
        msg.mv_setLC0(11, (byte) 0x10); // Touch sensor type
        msg.mv_setLC0(12, (byte) 0x00); // Touch sensor mode
        msg.mv_setLC0(13, (byte) 0x01); // Return 1 value
        msg.mv_setGV0(14, (byte) 0x60);
    }

    // port 1-4 0x00-0x03, port A-D 0x10-0x13
    // sensor
    //  7,0 EV3-Large-Motor-Degree
    //  7,1 EV3-Large-Motor-Rotation
    // 16,0 EV3-Touch
    // 29,0 EV3-Color-Reflected
    // 30,0 EV3-Ultrasonic-Cm
    public void mf_makeReadSensorsCmd(byte port, byte type, byte mode) {
        msg = new CMDMsg(15, true, (byte) 4);
        msg.mv_setOPCODE((byte) 0x99);
        msg.mv_setOPCMD((byte) 0x1b);
        msg.mv_setLC0(9, (byte) 0); // LAYER
        msg.mv_setLC0(10, port);
        msg.mv_setLC0(11, type);
        msg.mv_setLC0(12, mode);
        msg.mv_setLC0(13, (byte) 0x01); // return 1 value
        msg.mv_setGV0(14, (byte) 0x60);
    }

    public void mf_makeBatteryLevelCmd() {
        msg = new CMDMsg(10, true, (byte) 4);
        msg.mv_setOPCODE((byte) 0x81);
        msg.mv_setOPCMD((byte) 0x12);
        msg.mv_setGV0(9,(byte) 0x60);
    }

    public void mf_makeReadMotionSensorCmd() {
        msg = new CMDMsg(15, true, (byte) 4);
        msg.mv_setOPCODE((byte) 0x99);
        msg.mv_setOPCMD((byte) 0x1c);
        msg.mv_setLC0(9, (byte) 0);     // LAYER
        msg.mv_setLC0(10, (byte) 0x10); //
        msg.mv_setLC0(11, (byte) 0x07); // page 100
        msg.mv_setLC0(12, (byte) 0x00); // 7, 0 -> EV3-Large-Motor-Degree
        msg.mv_setLC0(13, (byte) 0x01); // return 1 value
        msg.mv_setGV0(14, (byte) 0x60);
    }

}
