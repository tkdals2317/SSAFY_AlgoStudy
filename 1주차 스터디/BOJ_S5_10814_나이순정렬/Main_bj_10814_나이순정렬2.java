package baekjun;
import java.io.*;
import java.util.*;

class Person implements Comparable<Person>{
	int num;
	String name;
	
	@Override
	public int compareTo(Person p) {
		int res= this.num-p.num;
		if(res==0) {		//조건 여러가지 비교하는 방법
			res=this.name.compareTo(p.name);
		}
		return res;
	}
}

public class Main_bj_10814_나이순정렬2 {

	public static void main(String[] args) throws Exception{

		System.setIn(new FileInputStream("res/input_bj_10814.txt"));
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		
		Person[] persons = new Person[N];
		//입력
		for(int i=0;i<N;i++) {
			persons[i]=new Person();
			persons[i].num=sc.nextInt();
			persons[i].name=sc.next();
		}
		
//		Arrays.sort(persons);
		
		Arrays.sort(persons, new Comparator<Person>() {

			@Override
			public int compare(Person p1, Person p2) {
				int res=p1.num-p2.num;
				return res;
			}
		});
		
		for(int i=0;i<N;i++) {
			System.out.println(persons[i].num+" "+persons[i].name);
		}
		
		sc.close();
	}


}