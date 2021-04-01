package algorithm.boj;

import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class Member implements Comparable<Member>{ //priority Q ���� ���
	private int order;
	private int age;
	private String name;
	
	public Member() {}
	public Member(int order, int age, String name) {
		setOrder(order);
		setAge(age);
		setName(name);
	}
	
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int compareTo(Member o) { //A>B? ���:����
		if(getAge() > o.age) return 1; //�񱳴�� ���̺��� ������ �����ȯ (��������)
		else if(getAge() == o.age && getOrder() > o.order) return 1; //������ �������� �Ǵ�
		else return -1;
	}
	@Override
	public String toString() {
		return age+" "+name;
	}
	
}
public class Main_S5_10814_나이순정렬_정태현_solved {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("res/S5_10814_input2.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		Member[] mem = new Member[T]; 
		for(int tc=0; tc<T; tc++) {
			mem[tc] = new Member(tc, sc.nextInt(), sc.nextLine()); //��� ��ü ����
		}
		//Annonymous �迭 ���
		List<Member> mlist = Arrays.asList(mem); //sort�� ����ϱ� ���� List
		//asList = �迭�� ����Ʈ�� ��ȯ
		Collections.sort(mlist);
		for(int i=0; i<T; i++) {
			System.out.println(mlist.get(i).getAge()+mlist.get(i).getName());			
		}
		
		sc.close();
	}
}
