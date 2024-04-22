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

        else if (id.equals("PlayTone_0x94_01")) {
            mf_makePlayToneCmd();
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
    ////////////

    private void mf_makeMotorStopCmd() {
//        msg = new CMDMsg(15, false, (byte) 0);
//        msg.mv_setOPCODE((byte) 0xa3);
//        msg.mv_setOPCMD((byte) 0x00);
//        msg.mv_setLC0(9, (byte) 6); // ports B and C
//        msg.mv_setLC0(10, (byte) 0);

        msg = new CMDMsg(11, false, (byte) 0);
        msg.mv_setOPCODE((byte) 0xa3);
        msg.mv_setOPCMD((byte) 0x00);
        msg.mv_setLC0(9, (byte) 0x0f); // Stop all motors (ports A, B, C, and D)
    }

    private void mf_makeMotorMoveCmd() {
        msg = new CMDMsg(16, false, (byte) 0);
        msg.mv_setOPCODE((byte) 0xae);
        msg.mv_setOPCMD((byte) 0x00);
        msg.mv_setLC0(9, (byte) 6); // ports B and C
        msg.mv_setLC1(10, (byte) 50); //speed
        msg.mv_setLC0(12, (byte) 0); // ramp up steps, set to 0, meaning the motors will start at full speed immediately.
        msg.mv_setLC2(13, (short) 900); // duration of the motor movement in degrees, where 900 represents 2.5 rotations (900 degrees).
        msg.mv_setLC2(16, (short) 180); // specifies the ramp down steps, which is set to 180 degrees (0.5 rotations) in this case, allowing for a smooth stop at the end of the movement.
    }

    public void mf_makePlayToneCmd() {
        msg = new CMDMsg(17, false, (byte) 0);
        msg.mv_setOPCODE((byte) 0x94);
        msg.mv_setOPCMD((byte) 0x01);
        msg.mv_setLC1(9, (byte) 2);
        msg.mv_setLC2(11, (short) 1000);
        msg.mv_setLC2(14, (short) 1000);
    }

    //https://ev3-dc.readthedocs.io/en/latest/examples_filesystem.html
    // robot sound file             size (bytes)  md5-checksum
    //---------------------------------------------------------------------------
    //Startup.rsf                          3109  7BE0A201F57917BC0DDE508E63DD7AD8
    //PowerDown.rsf                        7939  2381EF46C5166BFF0B5852369E5A2CC7
    //OverpowerAlert.rsf                   8553  BE802DF67CBBC4E4A40C223BFFF4C14A
    //GeneralAlarm.rsf                     7300  A40C190AF86C8FA9A7FE9143B36B86EC
    //DownloadSucces.rsf                   6599  681C88B5930DE152C0BB096F890C492F
    //Click.rsf                             173  A16F9F1FDDACF56EDF81B4CD968826B4
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

    // page 50
    // (Data8) LAYER – Specify chain layer number [0-3]
    // (Data8) NO – Port number page 100
    // (Data8) TYPE – Specify device type (0 = Don’t change type)
    // (Data8) MODE – Device mode [0-7] (-1 = Don’t change mode)
    // (Data8) VALUES – Number of return values
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
