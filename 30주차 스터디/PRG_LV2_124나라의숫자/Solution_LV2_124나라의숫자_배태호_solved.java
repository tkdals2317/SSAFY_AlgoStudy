package PG;

import java.util.*;

public class Soulition_programmers_level2_124나라의숫자 {
	
	public static void main(String[] args) {

		int n =10 ;
		String answer="";
		/////////////////////////////////////////////////////
		
        while(n > 0){
            int remain = n % 3;
            n /= 3;
            
            if(remain == 0) {
                answer = "4" + answer;
                n--;
            }
            else if(remain==1) answer = "1" + answer;
            else if (remain==2) answer = "2" + answer;
        }
        
        System.out.println(answer);
//		return answer;
	}
}
