import java.util.Scanner;

public class Main_G3_17386_선분교차1_변준형_solved {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double x1 = sc.nextInt();
		double y1 = sc.nextInt();
		double x2 = sc.nextInt();
		double y2 = sc.nextInt();
		double x3 = sc.nextInt();
		double y3 = sc.nextInt();
		double x4 = sc.nextInt();
		double y4 = sc.nextInt();
		double X = 0;
		double Y = 0;
		// y-y1 = (y2 - y1) / (x2 - x1) * (x - x1)
		// Ax + By +C = 0

		double A1 = y1 - y2;
		double B1 = x2 - x1;
		double C1 = x1 * y2 - x2 * y1;
		double A2 = y3 - y4;
		double B2 = x4 - x3;
		double C2 = x3 * y4 - x4 * y3;

		boolean isInteriorx1, isInteriory1, isInteriorx2, isInteriory2;

		isInteriorx1 = false;
		isInteriory1 = false;
		isInteriorx2 = false;
		isInteriory2 = false;

		// 교점 x좌표가 범위에 들어오는지 체크
		if (x1 < x2) {
			// A1 * x + B1 * y + C1 과 A2 * x + B2 * y + C2 식을
			// A1 * B2 * x + B1 * B2 * y + B2 * C1 과 A2 * B1 * x + B1 * B2 * y + B1 * C2 식으로
			// 변경
			// (A1 * B2 - A2 * B1) * x = B1 * C2 - B2 * C1
			// x = (B1 * C2 - B2 * C1) / (A1 * B2 - A2 * B1)
			if ((B1 * C2 - B2 * C1) * (A1 * B2 - A2 * B1) > x1 * (A1 * B2 - A2 * B1) * (A1 * B2 - A2 * B1)
					&& (B1 * C2 - B2 * C1) * (A1 * B2 - A2 * B1) < x2 * (A1 * B2 - A2 * B1) * (A1 * B2 - A2 * B1)) {
				isInteriorx1 = true;
			}
		} else if (x1 > x2) {
			if ((B1 * C2 - B2 * C1) * (A1 * B2 - A2 * B1) > x2 * (A1 * B2 - A2 * B1) * (A1 * B2 - A2 * B1)
					&& (B1 * C2 - B2 * C1) * (A1 * B2 - A2 * B1) < x1 * (A1 * B2 - A2 * B1) * (A1 * B2 - A2 * B1)) {
				isInteriorx1 = true;
			}
		} else { // x1==x2
			if ((B1 * C2 - B2 * C1) * (A1 * B2 - A2 * B1) == x1 * (A1 * B2 - A2 * B1) * (A1 * B2 - A2 * B1)) {
				isInteriorx1 = true;
			}
		}

		if (x3 < x4) {
			if ((B1 * C2 - B2 * C1) * (A1 * B2 - A2 * B1) > x3 * (A1 * B2 - A2 * B1) * (A1 * B2 - A2 * B1)
					&& (B1 * C2 - B2 * C1) * (A1 * B2 - A2 * B1) < x4 * (A1 * B2 - A2 * B1) * (A1 * B2 - A2 * B1)) {
				isInteriorx2 = true;
			}
		} else if (x3 > x4) {
			if ((B1 * C2 - B2 * C1) * (A1 * B2 - A2 * B1) > x4 * (A1 * B2 - A2 * B1) * (A1 * B2 - A2 * B1)
					&& (B1 * C2 - B2 * C1) * (A1 * B2 - A2 * B1) < x3 * (A1 * B2 - A2 * B1) * (A1 * B2 - A2 * B1)) {
				isInteriorx2 = true;
			}
		} else { // x3==x4
			if ((B1 * C2 - B2 * C1) * (A1 * B2 - A2 * B1) == x3 * (A1 * B2 - A2 * B1) * (A1 * B2 - A2 * B1)) {
				isInteriorx2 = true;
			}
		}

		// 교점 y좌표가 범위에 들어오는지 체크
		if (y1 < y2) {
			if ((A1 * C2 - A2 * C1) * (A2 * B1 - A1 * B2) > y1 * (A2 * B1 - A1 * B2) * (A2 * B1 - A1 * B2)
					&& (A1 * C2 - A2 * C1) * (A2 * B1 - A1 * B2) < y2 * (A2 * B1 - A1 * B2) * (A2 * B1 - A1 * B2)) {
				isInteriory1 = true;
			}
		} else if (y1 > y2) {
			if ((A1 * C2 - A2 * C1) * (A2 * B1 - A1 * B2) > y2 * (A2 * B1 - A1 * B2) * (A2 * B1 - A1 * B2)
					&& (A1 * C2 - A2 * C1) * (A2 * B1 - A1 * B2) < y1 * (A2 * B1 - A1 * B2) * (A2 * B1 - A1 * B2)) {
				isInteriory1 = true;
			}
		} else { // y1==y2
			if ((A1 * C2 - A2 * C1) * (A2 * B1 - A1 * B2) == y1 * (A2 * B1 - A1 * B2) * (A2 * B1 - A1 * B2)) {
				isInteriory1 = true;
			}
		}

		if (y3 < y4) {
			if ((A1 * C2 - A2 * C1) * (A2 * B1 - A1 * B2) > y3 * (A2 * B1 - A1 * B2) * (A2 * B1 - A1 * B2)
					&& (A1 * C2 - A2 * C1) * (A2 * B1 - A1 * B2) < y4 * (A2 * B1 - A1 * B2) * (A2 * B1 - A1 * B2)) {
				isInteriory2 = true;
			}
		} else if (y3 > y4) {
			if ((A1 * C2 - A2 * C1) * (A2 * B1 - A1 * B2) > y4 * (A2 * B1 - A1 * B2) * (A2 * B1 - A1 * B2)
					&& (A1 * C2 - A2 * C1) * (A2 * B1 - A1 * B2) < y3 * (A2 * B1 - A1 * B2) * (A2 * B1 - A1 * B2)) {
				isInteriory2 = true;
			}
		} else { // y3==y4
			if ((A1 * C2 - A2 * C1) * (A2 * B1 - A1 * B2) == y3 * (A2 * B1 - A1 * B2) * (A2 * B1 - A1 * B2)) {
				isInteriory2 = true;
			}
		}

		// 내부의 교점이라면 count+1
		if (isInteriorx1 && isInteriory1 && isInteriorx2 && isInteriory2) {
			System.out.println(1);
		} else
			System.out.println(0);

	}

}
