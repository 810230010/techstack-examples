package common;

import com.util.GetValueUtil;
import org.junit.Test;

import java.util.*;

public class CommonTest {
    @Test
    public void test1(){
        List<Map<String, Object>> detailList = new ArrayList<Map<String, Object>>();
        Map<String, Object> item1 = new HashMap<String, Object>();
        item1.put("sale_target_done", null);

        Map<String, Object> item2 = new HashMap<String, Object>();
        item2.put("sale_target_done", null);

        Map<String, Object> item3 = new HashMap<String, Object>();
        item3.put("sale_target_done", 0.1321);
        Map<String, Object> item4 = new HashMap<String, Object>();
        item4.put("sale_target_done", 0.2311);
        detailList.add(item1);
        detailList.add(item3);
        detailList.add(item2);
        detailList.add(item4);
        Collections.sort(detailList, (map1, map2)->{
                    Double saleTarget1 = GetValueUtil.GetDoubleValue(map1.get("sale_target_done"));
                    Double saleTarget2 = GetValueUtil.GetDoubleValue(map2.get("sale_target_done"));
                    saleTarget1 = saleTarget1 == null ? 0 : saleTarget1;
                    saleTarget2 = saleTarget2 == null ? 0 : saleTarget2;
                    return saleTarget2.compareTo(saleTarget1);
        });
        System.out.println(detailList);
    }
}
