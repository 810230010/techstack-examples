package com.question;

import java.util.ArrayList;
import java.util.List;

/**
 * 杨辉三角形
 */
public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> allRows = new ArrayList();
        if(numRows == 0)
            return allRows;
        List<Integer> temp = new ArrayList();
        temp.add(1);
        allRows.add(temp);
        for(int i=1; i<numRows; i++){
            List<Integer> row = new ArrayList();
            row.add(1);
            for(int j=1; j<i; j++){
                List<Integer> lastRow = allRows.get(i-1);
                row.add(lastRow.get(j-1) + lastRow.get(j));
            }
            row.add(1);
            allRows.add(row);
        }
        return allRows;
    }
}
