import java.util.*;
import java.io.*;
public class Main_S1_13335_트럭_이상민_solved {
	static int N, W, L; 
	static public class Truck {
		int weight;
		int time;
		public Truck(int weight, int time) {
			this.weight = weight;
			this.time = time;
		}
		@Override
		public String toString() {
			return "Truck [weight=" + weight + ", time=" + time + "]";
		}
		
	}
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_13335_트럭.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //트럭의 갯수
		W = Integer.parseInt(st.nextToken()); //다리의 길이
		L = Integer.parseInt(st.nextToken()); //다리의 최대하중
		
		//Truck [] trucks = new Truck[N];
		ArrayDeque<Truck> trucks = new ArrayDeque<>();
		//ArrayDeque<Integer> trucks = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int weight = Integer.parseInt(st.nextToken());
			//trucks[i] = new Truck(weight, W, false);
			trucks.offer(new Truck(weight, 1));
			
		}
		
		int weightSum = 0;
		int totaltime = 0;
		ArrayList<Truck> bridge = new ArrayList<>();
		
		while(!trucks.isEmpty()) {
			totaltime++;
			//다리에 올라온 모든 트럭의 시간을 증가시켜준다
			for(int i = 0; i < bridge.size(); i++) {
				bridge.get(i).time++;
			}
			//트럭이 다리를 지나 도착한다면
			//만약 증가시키고 난 후 가장 먼저 들어은(즉 인덱스가 0인)트럭의 소요시간이 다리길이와 같아진다면
			if(!bridge.isEmpty()&&bridge.get(0).time>W) {
				//다리위의 무게에서 도착한 트럭의 무게를 빼준다
				weightSum -= bridge.get(0).weight;
				//다리위에서 트럭 제거
				bridge.remove(0);
			}
			//다음 다리에 진입할 트럭의 무게와 현재 다리위의 무게를 더한 값이 다리의 최대하중보다 작다면
			if(trucks.peek().weight+weightSum <= L && bridge.size()<W) {
				Truck current = trucks.poll();
				weightSum += current.weight;
			
				bridge.add(current);
			}
		}	
		//마지막 트럭이 남아있는 경우
		if(bridge.size()>0) {
			//다리길이만큼 총시간을 더해준다
			totaltime+=W;
		}
		System.out.println(totaltime);
		
		br.close();
	}
}