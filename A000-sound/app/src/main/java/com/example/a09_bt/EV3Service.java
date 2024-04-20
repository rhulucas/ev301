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
import com.example.a09_bt.databinding.ActivityMainBinding;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

public class EV3Service {
    private MainActivity cref_main;
////    private ActivityMainBinding cref_binding;

    // BT Variables
    private final String MV_ROBOTNAME = "HUAHUA";
    private BluetoothAdapter mv_btInterface = null;
    private Set<BluetoothDevice> mv_pairedDevices = null;
    private BluetoothDevice mv_btDevice = null;
    private BluetoothSocket mv_btSocket = null;

    //// HERE
    // Data stream to/from NXT bluetooth
    private InputStream mv_is = null;
    private OutputStream mv_os = null;

    public EV3Service(MainActivity m) {
        cref_main = m;
////        cref_binding = m.cf_getBinding();
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
            mv_is = mv_btSocket.getInputStream();
            mv_os = mv_btSocket.getOutputStream();
            ////cref_binding.vvTvOut2.setText("Connect to " + bd.getName() + " at " + bd.getAddress());
            msg = "Connect to " + bd.getName() + " at " + bd.getAddress();
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
            mv_is.close();
            mv_os.close();
            ////cref_binding.vvTvOut2.setText(mv_btDevice.getName() + " is disconnect " );
            msg = mv_btDevice.getName() + " is disconnect ";
        } catch (Exception e) {
            ////cref_binding.vvTvOut2.setText("Error in disconnect -> " + e.getMessage());
            msg = "Error in disconnect -> " + e.getMessage();
        }
        return msg;
    }

    public void mf_EV3SendNoReplyCmd(String type)  throws  Exception {
        EV3CMD cmd = new EV3CMD(type, 0);
        try {
            mv_os.write(cmd.mf_getCmd().mf_getMsg());
            mv_os.flush();
        }
        catch (Exception e) {
            throw new Exception("Error in " + cmd.mf_getType() + "(" + e.getMessage() + ")");
        }
        mf_consoleOut(cmd.mf_getCmd().mf_getMsg(), "EV3 ==", false);
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
}
