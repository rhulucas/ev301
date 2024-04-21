package com.example.a09_bt;

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

        if (id.equals("MoveMotor_0xae_00")) {
            mf_makeMotorMoveCmd();
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
        else if (id.equals("MotorStart_0xa3")) {
            mf_makeMotorStopCmd();
        }
        else if (id.equals("ReadTouchSensor")) {
            mf_makeReadTouchSensorCmd();
        }
    }

    private void mf_makeMotorStartCmd() {
        msg = new CMDMsg(15, false, (byte) 0);
        msg.mv_setOPCODE((byte) 0xa4);
        msg.mv_setOPCMD((byte) 0x00);
        msg.mv_setLC0(9, (byte) 6);
        msg.mv_setLC1(10, (byte) 60);
        msg.mv_setLC0(12, (byte) 0xa6);
        msg.mv_setLC0(13, (byte) 0);
        msg.mv_setLC0(14, (byte) 6);
    }

    private void mf_makeMotorStopCmd() {
        msg = new CMDMsg(15, false, (byte) 0);
        msg.mv_setOPCODE((byte) 0xa3);
        msg.mv_setOPCMD((byte) 0x00);
        msg.mv_setLC0(9, (byte) 6);
        msg.mv_setLC0(10, (byte) 0);
    }

    private void mf_makeMotorMoveCmd() {
        msg = new CMDMsg(20, false, (byte) 0);
        msg.mv_setOPCODE((byte) 0xae);
        msg.mv_setOPCMD((byte) 0x00);
        msg.mv_setLC0(9, (byte) 6);
        msg.mv_setLC1(10, (byte) -50);
        msg.mv_setLC0(12, (byte) 0);
        msg.mv_setLC2(13, (short) 900);
        msg.mv_setLC2(16, (short) 180);
    }

    public void mf_makeMotorMoveContinuousCmd() {
        msg = new CMDMsg(16, false, (byte) 0);
        msg.mv_setOPCODE((byte) 0xa5);
        msg.mv_setOPCMD((byte) 0x00);
        msg.mv_setLC0(9, (byte) 0);
        msg.mv_setLC0(10, (byte) 6);
        msg.mv_setLC1(11, (byte) 50);
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
        //String str = "./ui/DownloadSuccess";
        //String str = "./ui/OverpowerAlert";
        //String str = "./ui/GeneralAlarm";
        //String str = "./ui/PowerDown";
        String str = "./ui/Startup";

        msg = new CMDMsg(12+str.length()+1, false, (byte) 0);
        msg.mv_setOPCODE((byte) 0x94);
        msg.mv_setOPCMD((byte) 0x02);
        msg.mv_setLC1(9, (byte) 50);
        msg.mv_setLCS(11, str);
    }

    public void mf_makePlayToneFileCmd_2() {
        //String str = "./ui/DownloadSuccess";
        //String str = "./ui/OverpowerAlert";
        String str = "./ui/GeneralAlarm";
        //String str = "./ui/PowerDown";
        //String str = "./ui/Startup";

        msg = new CMDMsg(12+str.length()+1, false, (byte) 0);
        msg.mv_setOPCODE((byte) 0x94);
        msg.mv_setOPCMD((byte) 0x02);
        msg.mv_setLC1(9, (byte) 50);
        msg.mv_setLCS(11, str);
    }

    public void mf_makePlayToneFileCmd_3() {
        //String str = "./ui/DownloadSuccess";
        //String str = "./ui/OverpowerAlert";
        //String str = "./ui/GeneralAlarm";
        String str = "./ui/PowerDown";
        //String str = "./ui/Startup";

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



}
