package common;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * guava use case
 */
public class GuavaTest {
    @Test
    public void testList(){
        //新建列表
        //
        List<String> strList = Lists.asList(null, new String[]{"b", "c"});
        System.out.println(strList);

        List<String> strList2 = Lists.newArrayList();
    }
}
