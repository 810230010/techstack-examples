package com.usher;

import com.usher.rpc.util.NetUtils;
import org.junit.Test;


public class NetUtilsTest {
    @Test
    public void testGetAvailablePort(){
        int port = NetUtils.getAvailablePort(-1);
        System.out.println(port);
    }
}
