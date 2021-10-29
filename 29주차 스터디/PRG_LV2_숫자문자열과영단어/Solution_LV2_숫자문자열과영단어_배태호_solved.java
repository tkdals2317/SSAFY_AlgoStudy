package PG;

import java.util.*;
public class Soulition_programmers_level2_숫자문자열과영단어 {
	
	public static void main(String[] args) {
    	String s = "23four5six7";
        Stack<Character> st = new Stack();
        for(int i=0; i<s.length(); i++) {
        	st.push(s.charAt(i));
        	
        	if(st.size()>=3 && st.get(st.size()-1).equals('e') && st.get(st.size()-2).equals('n') && st.get(st.size()-3).equals('o')) {
        		for(int j=0; j<3; j++)
        			st.pop();
        		st.add('1');
        	}
        	if(st.size()>=3 && st.get(st.size()-1).equals('o') && st.get(st.size()-2).equals('w') && st.get(st.size()-3).equals('t')) {
        		for(int j=0; j<3; j++)
        			st.pop();
        		st.add('2');
        	}
        	if(st.size()>=3 && st.get(st.size()-1).equals('e') && st.get(st.size()-2).equals('e') && st.get(st.size()-3).equals('r') && st.get(st.size()-4).equals('h') && st.get(st.size()-5).equals('t')) {
        		for(int j=0; j<5; j++)
        			st.pop();
        		st.add('3');
        	}
        	if(st.size()>=4 && st.get(st.size()-1).equals('r') && st.get(st.size()-2).equals('u') && st.get(st.size()-3).equals('o') && st.get(st.size()-4).equals('f')) {
        		for(int j=0; j<4; j++)
        			st.pop();
        		st.add('4');
        	}
        	if(st.size()>=4 && st.get(st.size()-1).equals('e') && st.get(st.size()-2).equals('v') && st.get(st.size()-3).equals('i') && st.get(st.size()-4).equals('f')) {
        		for(int j=0; j<4; j++)
        			st.pop();
        		st.add('5');
        	}
        	if(st.size()>=3 && st.get(st.size()-1).equals('x') && st.get(st.size()-2).equals('i') && st.get(st.size()-3).equals('s')) {
        		for(int j=0; j<3; j++)
        			st.pop();
        		st.add('6');
        	}
        	if(st.size()>=5 && st.get(st.size()-1).equals('n') && st.get(st.size()-2).equals('e') && st.get(st.size()-3).equals('v') && st.get(st.size()-4).equals('e') && st.get(st.size()-5).equals('s')) {
        		for(int j=0; j<5; j++)
        			st.pop();
        		st.add('7');
        	}
        	if(st.size()>=5 && st.get(st.size()-1).equals('t') && st.get(st.size()-2).equals('h') && st.get(st.size()-3).equals('g') && st.get(st.size()-4).equals('i') && st.get(st.size()-5).equals('e')) {
        		for(int j=0; j<5; j++)
        			st.pop();
        		st.add('8');
        	}
        	if(st.size()>=4 && st.get(st.size()-1).equals('e') && st.get(st.size()-2).equals('n') && st.get(st.size()-3).equals('i') && st.get(st.size()-4).equals('n')) {
        		for(int j=0; j<4; j++)
        			st.pop();
        		st.add('9');
        	}
        	if(st.size()>=4 && st.get(st.size()-1).equals('o') && st.get(st.size()-2).equals('r') && st.get(st.size()-3).equals('e') && st.get(st.size()-4).equals('z')) {
        		for(int j=0; j<4; j++)
        			st.pop();
        		st.add('0');
        	}
        }
        String answer = "";
        int size = st.size();
        for(int i=0; i<size; i++ ) {
        	answer= (st.pop()-'0')+answer;
        }
        System.out.print(Integer.parseInt(answer));
    }

}