import java.util.*;
import java.io.*;
/**
20 3 // 이동시간 20, 배터리의 개수 3개
// 0 : 이동안함, 1 : 상, 2 : 우, 3 : 하, 4 : 좌
2 2 3 2 2 2 2 3 3 4 4 3 2 2 3 3 3 2 2 3 //사용자1의 이동루트
4 4 1 4 4 1 4 4 1 1 1 4 1 4 3 3 3 3 3 3 //사용자2의 이동루트
4 4 1 100 //배터리 1 좌표 (4,4) 범위 1칸 충전량 100
7 10 3 40 //배터리 2 좌표 (7,3) 범위 3칸 충전량 40
6 3 2 70  //배터리 3 좌표 (6,3) 범위 2칸 충전량 70
[제약사항]

1. 지도의 가로, 세로 크기는 10이다.
2. 사용자는 총 2명이며, 사용자A는 지도의 (1, 1) 지점에서, 사용자B는 지도의 (10, 10) 지점에서 출발한다.
3. 총 이동 시간 M은 20이상 100이하의 정수이다. (20 ≤ M ≤ 100)
4. BC의 개수 A는 1이상 8이하의 정수이다. (1 ≤ A ≤ 8)
5. BC의 충전 범위 C는 1이상 4이하의 정수이다. (1 ≤ C ≤ 4)
6. BC의 성능 P는 10이상 500이하의 짝수이다. (10 ≤ P ≤ 500)
7. 사용자의 초기 위치(0초)부터 충전을 할 수 있다.
8. 같은 위치에 2개 이상의 BC가 설치된 경우는 없다. 그러나 사용자A, B가 동시에 같은 위치로 이동할 수는 있다. 사용자가 지도 밖으로 이동하는 경우는 없다.
*/
public class Solution_SWEA_5644_무선충전_이상민_solved {
	static int [] di = {0,-1, 0 , 1,  0}; //이동하지않음, 상, 우, 하, 좌
	static int [] dj = {0, 0, 1,  0, -1};
	static int Ax, Ay;
	static int Bx, By;
	static int[] bcA;
	static int[] bcB;
	static int time;
	static int bCnt;
	static BC [] bcArr;
	static int total;
	static class BC implements Comparable<BC>{
		int xpos;
		int ypos;
		int area;
		int power;
		public BC(int xpos, int ypos, int area, int power) {
			super();
			this.xpos = xpos;
			this.ypos = ypos;
			this.area = area;
			this.power = power;
		}
		@Override
		public int compareTo(BC o) {
			return -Integer.compare(this.power, o.power);
		}
		@Override
		public String toString() {
			return "BC [xpos=" + xpos + ", ypos=" + ypos + ", area=" + area + ", power=" + power + "]";
		}	
	}
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_swea_5644.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//입력
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			total = 0;
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			//이동시간
			time = Integer.parseInt(st.nextToken());
			//배터리 개수
			bCnt = Integer.parseInt(st.nextToken());
			//이동경로
			int pathArr[][] = new int [2][time];
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < time; j++) {
					pathArr[i][j] = Integer.parseInt(st.nextToken());
				}
				//System.out.println(Arrays.toString(pathArr[i]));
			}
			//배터리 정보를 담을 배열
			bcArr = new BC[bCnt+1];
			//충전영역이 아닐때 사용하는 0번 배터리 
			bcArr[0] = new BC(0,0,0,0);
			for (int i = 0; i < bCnt; i++) {
				st = new StringTokenizer(br.readLine()," ");
				int xpos = Integer.parseInt(st.nextToken());
				int ypos = Integer.parseInt(st.nextToken());
				int area = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());
				bcArr[i+1] = new BC(xpos, ypos, area, power);
			}
			//0번 배터리를 제외하고 배터리충전량으로 내림차순 정렬
			Arrays.sort(bcArr,1,bCnt+1);
			//System.out.println(Arrays.toString(bcArr));
			//A,B가 충전할 수 있는 배터리 배열
			bcA = new int[2];
			bcB = new int[2];
			Ax = 1;
			Ay = 1;
			Bx = 10;
			By = 10;
			//System.out.println("A ("+Ax+","+Ay+")");
			//System.out.println("B ("+Bx+","+By+")");
			findBattery();
			charge();
			//System.out.println(0+"번 :"+total);
			//System.out.println("===========");

			for (int t = 0; t < time; t++) {
				Ax = Ax+dj[pathArr[0][t]];
				Ay = Ay+di[pathArr[0][t]];
				
				Bx = Bx+dj[pathArr[1][t]];
				By = By+di[pathArr[1][t]];
				//System.out.println("A ("+Ax+","+Ay+")");
				//System.out.println("B ("+Bx+","+By+")");
				findBattery();
				charge();
				//System.out.println(t+1+"번 :"+total);
				//System.out.println("===========");
			}
			System.out.println("#"+tc+" "+total);
		}
		
			
		//두개 배터리 영역에 혼자 있는 경우 배터리 충전량이 큰 걸 선택은 어떻게?
		//한개의 배터리 영역에 두명이 있는 경우 절반을 나눔
		//두 사람의 위치 배터리 두 개 영역의 겹치는 곳에 있으면 반반 나누는거보다 한개씩 가지는게 낫다 
		//배터리저장할 클래스 배열 정렬해서 위에있는거부터 사용
		//좌표, 충전량, 범위, 사용하는 중인지 아닌지
		
		br.close();
	}
	static void charge() {
		if(bcA[0]==0&&bcB[0]==0) return;
		//A가 충전가능한 첫번째 배터리와 B가 충전가능한 첫번째 배터리가 같지 않다면
		if(bcA[0]!=bcB[0]) {
			//각각 total에 더해줌
			total += bcArr[bcA[0]].power+bcArr[bcB[0]].power;		
		}//A가 충전가능한 첫번째 배터리와 B가 충전가능한 첫번째 배터리가 같으면
		//충전불가능한 배터리(0번 배터리)가 아니어야함 
		else if(bcA[0]!=0&&bcA[0]==bcB[0]) {
			//어차피 둘중 하나는 가장 충전량이 가장 큰 배터리를 사용 (bcArr[bcA[0]].power)
			//나머지 두번째 배터리를 고를때는 A가 충전가능한 배터리와 B가 충전가능한 배터리 중 충전량이 큰걸로 사용 (Math.max(bcArr[bcA[1]].power, bcArr[bcB[1]].power);)

			total += bcArr[bcA[0]].power + Math.max(bcArr[bcA[1]].power, bcArr[bcB[1]].power);				

		}
	}
	static void findBattery() {
		int n = 0;
		int k = 1;
		Arrays.fill(bcA, 0);
		Arrays.fill(bcB, 0);
		//A가 배터리 범위 안에 있는지 체크
		while(n<2 && k<=bCnt) {
			//k번 배터리좌표와 A의 좌표 영역 검사 
			//A의 X좌표와 배터리 X좌표의 차이의 절대값+A의 Y좌표와 배터리 Y좌표의 차이의 절대값이 배터리 범위 안이라면 
			if(Math.abs(Ax-bcArr[k].xpos)+Math.abs(Ay-bcArr[k].ypos)<=bcArr[k].area) {
				//A가 충전가능한 배터리 배열에  k번 배터리를 추가
				bcA[n]=k;
				n++;
			}
			//다음 배터리 검사를 위해 k++
			k++;
		}
		n = 0;
		k = 1;
		//B가 배터리 범위 안에 있는지 체크
		while(n<2 && k<=bCnt) {
			//k번 배터리좌표와 A의 좌표 영역 검사 
			//B의 X좌표와 배터리 X좌표의 차이의 절대값+B의 Y좌표와 배터리 Y좌표의 차이의 절대값이 배터리 범위 안이라면 
			if(Math.abs(Bx-bcArr[k].xpos)+Math.abs(By-bcArr[k].ypos)<=bcArr[k].area) {
				//B가 충전가능한 배터리 배열에  k번 배터리를 추가
				bcB[n]=k;
				n++;
			}
			//다음 배터리 검사를 위해 k++
			k++;
		}
		
		//System.out.println(Arrays.toString(bcA));
		//System.out.println(Arrays.toString(bcB));
	}

}
