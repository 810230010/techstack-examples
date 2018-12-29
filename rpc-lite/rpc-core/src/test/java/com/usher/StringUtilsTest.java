package com.usher;

import com.usher.rpc.util.StringUtils;
import org.junit.Test;

public class StringUtilsTest {
    @Test
    public void test1(){
        String str = "&aaa&";
        System.out.println(StringUtils.trimEdgeStr(str, "&"));
    }
}
