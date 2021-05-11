import java.util.*;
class Solution {
    public String solution(String s) {
        StringBuilder sb=new StringBuilder();
        boolean flag=true;
        for(int i=0;i<s.length();i++){
            char tmp=s.charAt(i);
            if(tmp==' '){   //공백일때
                sb.append(' ');
                flag=true;
            }else{  //공백아닐때
                if(flag==true){ //처음문자 일때
                    if(tmp>='a'&&tmp<='z'){
                        sb.append((char)(tmp-'a'+'A'));
                    }else{
                        sb.append(tmp);
                    }
                }else{
                    if(tmp>='A'&&tmp<='Z'){
                        sb.append((char)(tmp-'A'+'a'));
                    }else{
                        sb.append(tmp);
                    }
                }
                flag=false;
            }
            
        }
        
        System.out.println(sb);
        return sb.toString();
        
    }
}