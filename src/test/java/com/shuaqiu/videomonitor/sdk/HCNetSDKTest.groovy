package com.shuaqiu.videomonitor.sdk;

import org.junit.After
import org.junit.Assert;
import org.junit.Before
import org.junit.Test

/**
 * @author shuaqiu on 2013-07-02.
 */
class HCNetSDKTest {
    @Before
    void setUp() {
        init();
    }

    @After
    void tearDown() {
        cleanup();
    }

    void init() {
        def initResult = HCNetSDK.INSTANCE.NET_DVR_Init();
        Assert.assertTrue(initResult);
    }

    void cleanup() {
        def cleanupResult = HCNetSDK.INSTANCE.NET_DVR_Cleanup();
        Assert.assertTrue(cleanupResult);
    }

    @Test
    void testLogin() {
        def deviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30()
        def result = HCNetSDK.INSTANCE.NET_DVR_Login_V30("localhost", (short) 8080, "username", "password", deviceInfo)
        Assert.assertEquals(-1, result)
    }
}
