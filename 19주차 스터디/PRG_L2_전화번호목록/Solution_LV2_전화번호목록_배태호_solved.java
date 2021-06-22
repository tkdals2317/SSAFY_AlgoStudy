package Algorithm;

import java.io.*;
import java.util.*;
public class Soulition_programmers_level2_전화번호목록 {
	public static void main(String[] args) {
    	
//    	String[] phone_book= {"119", "97674223", "1195524421"};
//    	String[] phone_book= {"123","456","789"};
    	String[] phone_book= {"1235","567","88"};
    	////////////////////////////////
        boolean answer = true;
        Arrays.sort(phone_book);

        for(int i = 0; i<phone_book.length-1; i++){
        	if(phone_book[i+1].length()>=phone_book[i].length() && phone_book[i].equals(phone_book[i+1].substring(0,phone_book[i].length()))){
                answer = false;
                break;
            }
        }

        System.out.println(answer);
    }
}