package algorithm.swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
 
class BC{
    int x;
    int y;
    int c;
    int p;
    BC(int x, int y, int c, int p){
        this.x = x;
        this.y = y;
        this.c = c;
        this.p = p;
    }
}
class People{
    int x;
    int y;
    People(int x, int y){
        this.x = x;
        this.y = y;
    }
}
 
public class Solution_swea_5644_무선충전_정태현_X{
    static int[][] map;
    static List<BC> list;
     
	// 이동방향에 따라 다음 좌표를 반환하는 함수
    static People moveNext(People ppl, int dir) {
         
        if(dir == 1) {
            ppl.x -= 1;
        }else if(dir == 2) {
            ppl.y += 1;
        }else if(dir == 3) {
            ppl.x += 1;
        }else if(dir == 4) {
            ppl.y -= 1;
        }
        return ppl;
    }
    
	// 해당 위치에서 최대로 얻을 수 있는 충전값을 반환하는 함수
    static int getCharge(People aa, People bb) {
         
        int getA = 0; // A로 부터 얻는 충전량 
        int getB = 0; // B로 부터 얻는 충전량 

		// 비트로 저장했기 때문에 꺼낼 때는 스트링으로 꺼내서
        String bc1 = Integer.toBinaryString(map[aa.x][aa.y]);
        String bc2 = Integer.toBinaryString(map[bb.x][bb.y]);
		// 현재 A위치에 적용되는 무선충전기의 종류를 저장 
        List<BC> alist = new ArrayList<>();
		// 현재 B위치에 적용되는 무선충전기의 종류를 저장 
        List<BC> blist = new ArrayList<>();
        int idx = 0;

		// 해당 비트 수가 1이면 그에 해당하는 충전기를 저장한다
        for(int i = bc1.length()-1 ; i >=0 ; i--) {
            if(bc1.charAt(i) == '1') {
                alist.add(list.get(idx));
            }
            idx++;
        }
        idx = 0;
        for(int i = bc2.length()-1 ; i >= 0 ; i--) {
            if(bc2.charAt(i) == '1') {
                blist.add(list.get(idx));
            }
            idx++;
        }
		
		// 저장된 충전기를 파워가 큰것부터 내림차순으로 정렬한다
        alist.sort(new Comparator<BC>() {
            @Override
            public int compare(BC o1, BC o2) {
                if(o1.p > o2.p) {
                    return -1;
                }else {
                    return 1;
                }
            }
        });
        blist.sort(new Comparator<BC>() {
            @Override
            public int compare(BC o1, BC o2) {
                if(o1.p > o2.p) {
                    return -1;
                }else {
                    return 1;
                }
            }
        });
		
		// 만약 A에 적용되는 무선충전기가 없다면 
        if(alist.size() == 0) {
			// 근데 B에 적용되는건 있다면 그거라도 적용 
            if(blist.size() > 0) {
                getB = blist.get(0).p;
            }
		// 만약 B에 적용되는 무선충전기가 없다면 
        }else if(blist.size() == 0) {
			// A라도 충전
            getA = alist.get(0).p;

		// 만약 둘다 적용되는 무선충전기가 있다면
		// 근데 하필 둘에 적용되는 충전기 중 가장 파워 쎈게 같은 거라면 
        }else if(alist.get(0).x == blist.get(0).x && alist.get(0).y == blist.get(0).y) {
			// 만약 둘다 그 충전기 외엔 적용되는게 없다면 반씩 나눠갖자
            if(alist.size() == 1 && blist.size() == 1) {
                getA = alist.get(0).p/2;
                getB = getA;
			// 그건 아니고 a만 아직 여분의 충전기가 있다면 A는 그걸로 충전시키고 젤 쎈건 B가 충전받자 
            }else if(alist.size() > 1 && blist.size() == 1){
                getB = blist.get(0).p;
                getA = alist.get(1).p;
			//그건 아니고 B만 아직 여분의 충전기가 있다면 B는 그걸로 충전시키고 젤 쎈건 A가 충전받자 
            }else if(alist.size() == 1 && blist.size() > 1) {
                getA = alist.get(0).p;
                getB = blist.get(1).p;
			// 둘다 여분의 충전기가 있다면
            }else {
				// 여분 중 쎈걸 가진 사람이 양보하자
                if(alist.get(1).p > blist.get(1).p) {
                    getB = blist.get(0).p;
                    getA = alist.get(1).p;
                }else {
                    getB = blist.get(1).p;
                    getA = alist.get(0).p;
                }
            }
		// 둘에 적용되는 충전기가 겹치지 않는다면 사이좋게 각자 충전
        }else {
            getA = alist.get(0).p;
            getB = blist.get(0).p;
        }
         
        return getA+getB;
    }
     
     public static void main(String []args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int idx = 1;
        StringBuilder sb = new StringBuilder();
        while(t-- > 0) {
             StringTokenizer st = new StringTokenizer(br.readLine());
             int m = Integer.parseInt(st.nextToken());
             int a = Integer.parseInt(st.nextToken());
             int[] track1 = new int[m];
             int[] track2 = new int[m];
             st = new StringTokenizer(br.readLine());
             for(int i = 0 ; i < m ; i++) {
                 track1[i] = Integer.parseInt(st.nextToken());
             }
             st = new StringTokenizer(br.readLine());
             for(int i = 0 ; i < m ; i++) {
                 track2[i] = Integer.parseInt(st.nextToken());
             }
             list = new ArrayList<BC>();
  
             map = new int[10][10];
              
             for(int i = 0 ; i < a ; i++) {
                 st = new StringTokenizer(br.readLine());

                 int y = Integer.parseInt(st.nextToken())-1;
                 int x = Integer.parseInt(st.nextToken())-1;
                 int c = Integer.parseInt(st.nextToken());
                 int p = Integer.parseInt(st.nextToken());
                 list.add(new BC(x, y, c, p));
                  
                  
                 int index = (int)Math.pow(2, list.size()); // 비트로 표현할것임
                  
                 for(int row = 0 ; row < 10 ; row++) {
                     for(int col = 0 ; col < 10 ; col++) {
						// 해당 좌표와 충전기 사이 거리가 c이하라면 맵에 표시
                         int d = Math.abs(row-x) + Math.abs(col-y);
                         if(d <= c) {
                             map[row][col] += index;
                         }
                          
                     }
                 }
             }
             
			// A,B의 좌표를 나타냄
             People aa = new People(0, 0);
             People bb = new People(9, 9);
             int ans = 0;
             for(int move = 0 ; move < m ; move++) {
                 ans += getCharge(aa, bb); // 충전받고
                 aa = moveNext(aa, track1[move]); // 위치 이동
                 bb = moveNext(bb, track2[move]);

             }
			// 마지막 위치 이동에 따른 충전 
             ans += getCharge(aa, bb);
              
             sb.append("#" + idx++ + " " + ans + "\n");
              
        }
        System.out.println(sb);
        
     }
}