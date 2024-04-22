package com.example.ev3_controller;

public class CMDMsg {
    byte[] mv_buffer;

    public CMDMsg(int len, boolean b, byte size) {
        mv_buffer = new byte[len];
        mv_buffer[0] = (byte) ((len-2) & 0x00FF);
        mv_buffer[1] = (byte) (((len-2) & 0xFF00) >> 8);
        mv_buffer[2] = (byte) 0x34;
        mv_buffer[3] = (byte) 0x12;
        mv_buffer[4] = b? (byte)0x00 : (byte)0x80;;
        mv_buffer[5] = b? size : (byte)0x00;
        mv_buffer[6] = (byte) 0x00;
    }

    public byte[] mf_getMsg() {
        return mv_buffer;   //返回mv_buffer数组，即消息的当前字节表示形式。
    }

    public void mv_setOPCODE(byte b) {
        mv_buffer[7] = b;   //设置操作码（OPCODE），这是控制特定操作的字节。
    }

    public void mv_setOPCMD(byte b) {
        mv_buffer[8] = b;  //设置操作命令（OPCMD），进一步定义了要执行的具体操作。
    }
    public void mv_setLC0(int index, byte b) {
        mv_buffer[index] = b;   //mv_setLC0 直接设置一个字节。
    }

    public void mv_setLC1(int index, byte value) {
        mv_buffer[index] = (byte) 0x81;
        mv_buffer[index+1] = value;
        //mv_setLC1 设置一个字节值，前面有0x81标志，表示这是一个字节长的数据。
    }
    //http://www.java2s.com/example/android/file-input-output/write-short-to-littleendian-byte-array.html
    public void mv_setLC2(int index, short value) {
        mv_buffer[index] = (byte) 0x82;
        mv_buffer[index+1] = (byte) (value & 0x00FF);
        mv_buffer[index+2] = (byte) ((value & 0xFF00) >> 8);
        //设置一个短整型（short）值，前面有0x82标志，表示这是两个字节长的数据，使用小端字节序。
    }
    public void mv_setLCS(int index, String str) {
        byte[] byteArrray = str.getBytes();
        mv_buffer[index] = (byte) 0x84;
        for (int i=0; i<str.length(); i++) {
            mv_buffer[index+1+i] = byteArrray[i];
        }
        mv_buffer[index+str.length()+1] = (byte) 0x00;
    }
    //设置一个字符串，前面有0x84标志，后面跟着字符串的字节表示和一个终止符0x00。
    public void mv_setGV0(int index, byte b) {
        mv_buffer[index] =  b;  //用于设置全局变量（Global Variable）的值。
    }
}
