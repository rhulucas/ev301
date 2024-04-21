package com.example.a09_bt;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import java.util.Timer;
import java.util.TimerTask;

public class EV3Service {
    private MainActivity cref_main;
    private Timer sensorTimer;
////    private ActivityMainBinding cref_binding;

    // BT Variables
    private final String MV_ROBOTNAME = "HUAHUA";
    private BluetoothAdapter mv_btInterface = null;
    private Set<BluetoothDevice> mv_pairedDevices = null;
    private BluetoothDevice mv_btDevice = null;
    private BluetoothSocket mv_btSocket = null;

    private ConnectedThread mv_connectedThread = null;

    public EV3Service(MainActivity m) {
        cref_main = m;
////        cref_binding = m.cf_getBinding();
    }

    public void startMotorMoveContinuous() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mf_EV3SendNoReplyCmd("forward");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void stopMotor() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mf_EV3SendNoReplyCmd("MotorStop_0xa3");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // How to remove view? not easy
    // Just one option - tuple (Pair in Java)
    public Pair<String, String> mf_checkBTPermissions() {
        String msg1, msg2;
        if (ContextCompat.checkSelfPermission(cref_main,
                Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED) {
            ////cref_binding.vvTvOut1.setText("BLUETOOTH_SCAN already granted.\n");
            msg1 = "BLUETOOTH_SCAN already granted.\n";
        }
        else {
            ////cref_binding.vvTvOut1.setText("BLUETOOTH_SCAN NOT granted.\n");
            msg1 = "BLUETOOTH_SCAN NOT granted.\n";
        }

        if (ContextCompat.checkSelfPermission(cref_main,
                Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
            ////cref_binding.vvTvOut2.setText("BLUETOOTH_CONNECT NOT granted.\n");
            msg2 = "BLUETOOTH_CONNECT NOT granted.\n";
        }
        else {
            ////cref_binding.vvTvOut2.setText("BLUETOOTH_CONNECT already granted.\n");
            msg2 = "BLUETOOTH_CONNECT already granted.\n";
        }
        Pair<String, String> msg = new Pair<>(msg1, msg2);
        return msg;
    }

    // https://www.geeksforgeeks.org/android-how-to-request-permissions-in-android-application/
    // https://stackoverflow.com/questions/67722950/android-12-new-bluetooth-permissions
    public void mf_requestBTPermissions() {
        // We can give any value but unique for each permission.
        final int BLUETOOTH_SCAN_CODE = 100;
        final int BLUETOOTH_CONNECT_CODE = 101;

        // Android version < 12, "android.permission.BLUETOOTH" just fine
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            Toast.makeText(cref_main,
                    "BLUETOOTH granted for earlier Android", Toast.LENGTH_SHORT) .show();
            return;
        }

        // Android 12+ has to go through the process
        if (ContextCompat.checkSelfPermission(cref_main,
                Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(cref_main,
                    new String[]{Manifest.permission.BLUETOOTH_SCAN},
                    BLUETOOTH_SCAN_CODE);
        }
        else {
            Toast.makeText(cref_main,
                    "BLUETOOTH_SCAN already granted", Toast.LENGTH_SHORT) .show();
        }

        if (ContextCompat.checkSelfPermission(cref_main,
                Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(cref_main,
                    new String[]{Manifest.permission.BLUETOOTH_CONNECT},
                    BLUETOOTH_CONNECT_CODE);
        }
        else {
            Toast.makeText(cref_main,
                    "BLUETOOTH_CONNECT already granted", Toast.LENGTH_SHORT) .show();
        }
    }

    // Modify from chap14, pp390 findRobot()
    ////public BluetoothDevice mf_locateInPairedBTList() {
    public String mf_locateInPairedBTList() {
        BluetoothDevice lv_bd = null;
        String msg = "";
        try {
            mv_btInterface = BluetoothAdapter.getDefaultAdapter();
            mv_pairedDevices = mv_btInterface.getBondedDevices();
            Iterator<BluetoothDevice> lv_it = mv_pairedDevices.iterator();
            while (lv_it.hasNext())  {
                lv_bd = lv_it.next();
                if (lv_bd.getName().equalsIgnoreCase(MV_ROBOTNAME)) {
                    ////cref_binding.vvTvOut1.setText(MV_ROBOTNAME + " is in paired list");
                    msg = MV_ROBOTNAME + " is in paired list";
                    ////return lv_bd;
                    mv_btDevice = lv_bd;
                    return msg;
                }
            }
            ////cref_binding.vvTvOut1.setText(MV_ROBOTNAME + " is NOT in paired list");
            msg = MV_ROBOTNAME + " is NOT in paired list";
        }
        catch (Exception e) {
            ////cref_binding.vvTvOut1.setText("Failed in findRobot() " + e.getMessage());
            msg = "Failed in findRobot() " + e.getMessage();
        }
        ////return null;
        return msg;
    }

    // Modify frmo chap14, pp391 connectToRobot()
    public String mf_connectToEV3() {
        BluetoothDevice bd = mv_btDevice;
        String msg = "";
        try  {
            mv_btSocket = bd.createRfcommSocketToServiceRecord
                    (UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
            mv_btSocket.connect();

            //// HERE
            ////cref_binding.vvTvOut2.setText("Connect to " + bd.getName() + " at " + bd.getAddress());
            msg = "Connect to " + bd.getName() + " at " + bd.getAddress();

            // Start the thread to connect with the given device
            mv_connectedThread = new ConnectedThread(mv_btSocket);
            mv_connectedThread.start();
        }
        catch (Exception e) {
            ////cref_binding.vvTvOut2.setText("Error interacting with remote device [" + e.getMessage() + "]");
            msg = "Error interacting with remote device [" + e.getMessage() + "]";
        }
        return msg;
    }

    public String mf_disconnFromEV3() {
        String msg = "";
        try {
            mv_btSocket.close();
            mv_connectedThread.cancel();
            ////cref_binding.vvTvOut2.setText(mv_btDevice.getName() + " is disconnect " );
            msg = mv_btDevice.getName() + " is disconnect ";
        } catch (Exception e) {
            ////cref_binding.vvTvOut2.setText("Error in disconnect -> " + e.getMessage());
            msg = "Error in disconnect -> " + e.getMessage();
        }
        return msg;
    }

    public void mf_EV3SendNoReplyCmd(String type)  throws  Exception {
        EV3CMD cmd = new EV3CMD(type);
        int DELAY = 10;
        try {
            mv_connectedThread.write(cmd.mf_getCmd().mf_getMsg(), DELAY);
        }
        catch (Exception e) {
            throw new Exception("Error in " + cmd.mf_getType() + "(" + e.getMessage() + ")");
        }

        mf_consoleOut(cmd.mf_getCmd().mf_getMsg(), "EV3 ==", false);
    }

    public byte[] mf_EV3SendReplyCmd(String type, byte ... byteArgs)  throws  Exception {
        // I/Choreographer: Skipped 82 frames!  The application may be doing too much work on its main thread.
        int DELAY = 10;
        int WAIT_BEFORE_READ = 200;
        int MSGCOUNTER = 0x1234;
        EV3CMD cmd = new EV3CMD(type, byteArgs);
        try {
            mv_connectedThread.write(cmd.mf_getCmd().mf_getMsg(), DELAY);
        } catch (Exception e) {
            Log.i("EV3 out ", e.getMessage());
            throw new Exception("Error in " + cmd.mf_getType() + "(" + e.getMessage() + ")");
        }

        mf_consoleOut(cmd.mf_getCmd().mf_getMsg(), "EV3 ==", false);

        // pause is needed between send cmd and read
        try {
            Thread.sleep(WAIT_BEFORE_READ);
        }
        catch (Exception e) {
            // Do nothing
        }

        int len = 15;
        byte[] buf = new byte[len];
        try {
            buf = mv_connectedThread.read(MSGCOUNTER, 50);
        } catch (Exception e) {
            throw new Exception("Error in " + cmd.mf_getType() + "(" + e.getMessage() + ")");
        }
        mf_consoleOut(buf, "EV3 ->", true);
        return buf;
    }
    private void mf_consoleOut(byte[] buf, String tag, boolean flag) {
        String str = "";
        for (int i=0; i<buf.length; i++) {
            str += String.format("%02X ", buf[i]);
        }
        Log.i(tag, str);
        if (flag) {
            Log.i("EV3 ->", new String(buf, StandardCharsets.UTF_8));
        }
    }

    private class ConnectedThread extends Thread {
        private String tag = "EV3 thread ";
        private InputStream mis = null;
        private OutputStream mos = null;

        public ConnectedThread(BluetoothSocket socket) {
            //Log.i(tag, "create ConnectedThread: ");

            // Get the BluetoothSocket input and output streams
            try {
                mis = socket.getInputStream();
                mos = socket.getOutputStream();
            } catch (IOException e) {
                Log.e(tag, "temp sockets not created", e);
            }
        }

        public void run() {
            //Log.i(tag, "\tBEGIN mConnectedThread");
////            mBatteryLevel  = mgetBatteryLevel();

            // repeat task -- ready
////            if (mpisPollBattery)
////                mpollBattery(5000);
            //mpollBattery(60000);
        }

        private byte[] read(int command, int delay) {
            // return bytes always 0xll, 0xhh as message counter 0x1234 here, 0x02(good) or 0x04(error msg), then returned values
            int NUMBER_OF_TRIES = 5;
            int INTERVAL = 50;
            try {
                int attempts = 0;
                int bytesReady = 0;
                byte[] sizeBuffer = new byte[2];
                if (delay > 0) {
                    try {
                        Thread.sleep(delay);
                    } catch (Exception e) {
                        // Do nothing
                    }
                }

                while (attempts < NUMBER_OF_TRIES) {
                    Thread.sleep(INTERVAL);
                    bytesReady = mis.available();

                    if (bytesReady == 0) {
                        //Log.i(tag,"\t\tNothing there, try again");
                        attempts++;
                    } else {
                        //Log.i(tag,"\tThere are [" + bytesReady + "] waiting for us!");
                        break;
                    }
                }

                if (bytesReady < 2) {
                    Log.e(tag, "No bytes ready " + bytesReady);
                    return null;
                }

                int bytesRead = mis.read(sizeBuffer, 0, 2);
                if (bytesRead != 2) {
                    Log.e(tag, "Bytes buf error " + bytesReady);
                    return null;
                }

                // calculate response size
                bytesReady = sizeBuffer[0] + (sizeBuffer[1] << 8);
                Log.i(tag, "Bytes to read mis [" + bytesReady + "]");

                byte[] retBuf = new byte[bytesReady];
                bytesRead = mis.read(retBuf);

                if (bytesReady != bytesRead) {
                    Log.e(tag, "Byte 0,1 size mismatch? " + bytesRead);
                    return null;
                }

                int ret = retBuf[0] + (retBuf[1] << 8);
                if (ret != command) {
                    Log.e(tag, "Byte 2,3 msg counter mismatch " + ret);
////                 return null;
                }

                if (retBuf[2] == 0x04) {
                    Log.e(tag, "Byte 4 is 0x04, error return");
////                 return null;
                }

                return retBuf;
            } catch (Exception e) {
                Log.e(tag, "Error in Read Response -> " + e.getMessage());
                return null;
            }
        }

        public void write(byte[] buffer, int delay) {
            //Log.i(tag, "\t\t\tBEGIN mthreadWrite");
            try {
                if (delay > 0) {
                    try {
                        Thread.sleep(delay);
                    }
                    catch (Exception e) {
                        // Do nothing
                    }
                }
                mos.write(buffer);
                mos.flush();
            }
            catch (IOException e) {
                Log.e(tag, "Exception during write", e);
            }
        }

        public void cancel() {
            try {
                mis.close();;
                mos.close();
                mis = null;
                mos = null;
                if (mv_btSocket != null && mv_btSocket.isConnected()) {
                    mv_btSocket.close();
                    ////Log.i(tag, "\tdisconnect (ed) to " + mNXTAddress);
                }
            }
            catch (IOException e) {
                Log.e(tag, "close() of connect socket failed", e);
            }
        }

        public void startTouchMonitor(byte port, byte type, byte mode) {
            if (sensorTimer != null) {
                sensorTimer.cancel();
            }
            sensorTimer = new Timer();
            sensorTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        byte[] response = mf_EV3SendReplyCmd("ReadTouchSensor");
                        boolean touchDetected = (response[3] != 0); // Assuming the touch status is in this byte
                        if (touchDetected) {
                            handleTouchDetected();
                        }
                    } catch (Exception e) {
                        Log.e("EV3Service", "Error reading touch sensor", e);
                    }
                }
            }, 0, 100); // Poll every 100ms
        }

        private void handleTouchDetected() throws Exception {
            // Commands to reverse the motor
            mf_EV3SendNoReplyCmd("MotorStart_0xa3"); // Assuming this is the command to reverse the motor
        }


    }
}
