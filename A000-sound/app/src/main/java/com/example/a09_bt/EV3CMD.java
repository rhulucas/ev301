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

    public EV3CMD(String type, int ind) {
        if (type.equals("MoveMotor_0xae_00")) {
            mf_makeMotorMoveCmd();
        }
        else if (type.equals("PlayTone_0x94_01")) {
            mf_makePlayToneCmd();
        }
        else if (type.equals("PlayToneFile_0x94_sound1")) {
            mf_makePlayToneFileCmd_1();
        }
        else if (type.equals("PlayToneFile_0x94_sound2")) {
            mf_makePlayToneFileCmd_2();
        }
        else if (type.equals("PlayToneFile_0x94_sound3")) {
            mf_makePlayToneFileCmd_3();
        }
        else if (type.equals("MotorStart_0xa6")) {
            mf_makeMotorStartCmd();
        }
        else if (type.equals("MotorStart_0xa3")) {
            mf_makeMotorStopCmd();
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



}
