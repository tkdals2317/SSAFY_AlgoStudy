import java.util.*;
import java.io.*;
public class Main_S1_13335_Ʈ��_�̻�� {
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
		System.setIn(new FileInputStream("res/input_13335_Ʈ��.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //Ʈ���� ����
		W = Integer.parseInt(st.nextToken()); //�ٸ��� ����
		L = Integer.parseInt(st.nextToken()); //�ٸ��� �ִ�����
		
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
			//�ٸ��� �ö�� ��� Ʈ���� �ð��� ���������ش�
			for(int i = 0; i < bridge.size(); i++) {
				bridge.get(i).time++;
			}
			//Ʈ���� �ٸ��� ���� �����Ѵٸ�
			//���� ������Ű�� �� �� ���� ���� �����(�� �ε����� 0��)Ʈ���� �ҿ�ð��� �ٸ����̿� �������ٸ�
			if(!bridge.isEmpty()&&bridge.get(0).time>W) {
				//�ٸ����� ���Կ��� ������ Ʈ���� ���Ը� ���ش�
				weightSum -= bridge.get(0).weight;
				//�ٸ������� Ʈ�� ����
				bridge.remove(0);
			}
			//���� �ٸ��� ������ Ʈ���� ���Կ� ���� �ٸ����� ���Ը� ���� ���� �ٸ��� �ִ����ߺ��� �۴ٸ�
			if(trucks.peek().weight+weightSum <= L && bridge.size()<W) {
				Truck current = trucks.poll();
				weightSum += current.weight;
			
				bridge.add(current);
			}
		}	
		//������ Ʈ���� �����ִ� ���
		if(bridge.size()>0) {
			//�ٸ����̸�ŭ �ѽð��� �����ش�
			totaltime+=W;
		}
		System.out.println(totaltime);
		
		br.close();
	}
}
