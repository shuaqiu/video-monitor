package com.shuaqiu.videomonitor.sdk;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Structure;

/**
 * @author shuaqiu on 2013-7-2.
 */
public interface HCNetSDK extends Library {
    HCNetSDK INSTANCE = (HCNetSDK) Native.loadLibrary("HCNetSDK", HCNetSDK.class);

    int SERIALNO_LEN = 48;   //序列号长度

    /**
     * initialize the sdk
     *
     * @return true if success while false on failure. call {@link #NET_DVR_GetLastError} to get the error code
     * @see #NET_DVR_Cleanup
     */
    boolean NET_DVR_Init();

    /**
     * Release the sdk
     *
     * @return true if success while false on failure. call {@link #NET_DVR_GetLastError} to get the error code
     * @see #NET_DVR_Init
     */
    boolean NET_DVR_Cleanup();

    /**
     * login to the device
     *
     * @param sDVRIP       the ip address of the device
     * @param wDVRPort     the port of the device
     * @param sUserName    user name to login
     * @param sPassword    password
     * @param lpDeviceInfo [out] the information of device
     * @return the user id if login success, or -1 if login failed, call {@link #NET_DVR_GetLastError} to get the error code
     * @see #NET_DVR_Logout_V30(NativeLong)
     */
    NativeLong NET_DVR_Login_V30(String sDVRIP, short wDVRPort, String sUserName, String sPassword, NET_DVR_DEVICEINFO_V30 lpDeviceInfo);

    /**
     * logout
     *
     * @param lUserID user id to logout.
     * @return true if success while false if failure.
     * @see #NET_DVR_Login_V30(String, short, String, String, NET_DVR_DEVICEINFO_V30)
     */
    boolean NET_DVR_Logout_V30(NativeLong lUserID);

    /**
     * get the error code for the last operation.
     *
     * @return the error code
     */
    int NET_DVR_GetLastError();

    /**
     * NET_DVR_Login_V30()参数结构
     */
    public static class NET_DVR_DEVICEINFO_V30 extends Structure {
        public byte[] sSerialNumber = new byte[SERIALNO_LEN];  //序列号
        public byte byAlarmInPortNum;                //报警输入个数
        public byte byAlarmOutPortNum;                //报警输出个数
        public byte byDiskNum;                    //硬盘个数
        public byte byDVRType;                    //设备类型, 1:DVR 2:ATM DVR 3:DVS ......
        public byte byChanNum;                    //模拟通道个数
        public byte byStartChan;                    //起始通道号,例如DVS-1,DVR - 1
        public byte byAudioChanNum;                //语音通道数
        public byte byIPChanNum;                    //最大数字通道个数
        public byte[] byRes1 = new byte[24];                    //保留
    }
}
