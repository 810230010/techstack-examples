package com.question;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * from code war
 * persistence(39) == 3 // because 3*9 = 27, 2*7 = 14, 1*4=4
 *                       // and 4 has only one digit
 */
public class PersistentBugger {
    public static int counter = 0;
    public static int persistence(long n) {
        // your code
        if(n < 10)
            return counter;
        String[] arr = String.valueOf(n).split("");
        int multiresult = 1;
        for(String i : arr){
            multiresult *= Integer.parseInt(i);
        }
        System.out.println(multiresult);
        counter++;
        return persistence(multiresult);
    }

    public static void main(String[] args) {
        System.out.println(persistence(398320));
    }
}
