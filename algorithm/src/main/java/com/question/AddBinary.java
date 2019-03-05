package com.question;

/**
 * 10001 + 10000
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        int sum = 0;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while(i >= 0 || j >= 0){
            int bit1 = (i >= 0) ? Integer.parseInt(a.charAt(i)+"") : 0;
            int bit2 = (j >= 0) ? Integer.parseInt(b.charAt(j) + "") : 0;
            sum = bit1 ^ bit2 ^ carry;
            carry = (bit1 + bit2 + carry) > 1 ? 1: 0;
            sb.append(sum + "");
            i--;
            j--;
        }
        if(carry > 0){
            sb.append(carry+"");
        }
        System.out.println(sb.reverse().toString());
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        new AddBinary().addBinary("1001", "1000");
    }
}
