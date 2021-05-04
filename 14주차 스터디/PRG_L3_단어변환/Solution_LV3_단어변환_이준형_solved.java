package programmers;

class Solution {
    
    static int wlen,blen,min;
    static String end;    
    
    public int solution(String begin, String target, String[] words) {
        
        wlen=words[0].length(); //문자하나길이
        blen=words.length;  //받은 문자 개수
        
        end=target;
        min=Integer.MAX_VALUE;
            
        search(0,begin,words);
        
        if(min==Integer.MAX_VALUE)
            return 0;
        else
            return min;
        
    }
    
    
    void search(int idx,String str,String[] words){
        if(str.equals(end)){    //같은지비교
            min=Math.min(min,idx);
            return;
        }
        if(idx==blen)
            return;
        if(idx>=min)    //가지치기
            return;
        
        for(int i=0;i<blen;i++){    //문자개수만큼
            int get=checkdiff(str,words[i]);
            if(get==-1)
                continue;
            char c=str.charAt(get);
            StringBuilder sb=new StringBuilder(str);
            sb.setCharAt(get,words[i].charAt(get)); //문자 하나 변환
            search(idx+1,sb.toString(),words);
        }
        
    }
    
    int checkdiff(String str,String tmp){   //문자 다른거 체크
        int get=-1;
        int count=0;
        for(int i=0;i<wlen;i++){
            if(str.charAt(i)!=tmp.charAt(i) &&str.charAt(i)!=end.charAt(i) ){   //문자 다른조건
                count++;
                get=i;
            }
        }
        if(count==1)
            return get;
        else
            return -1;
    }
    

}