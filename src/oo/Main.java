package oo;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.printf("프로그램 시작\n");
		
		Scanner In = new Scanner(System.in); // 스캐너 변수 생성
		System.out.printf("입력 : ");
		String Str = In.nextLine(); // Str 이라는 스트링 변수안에 값을 넣음 nextLine이 있어야 입력가능
		System.out.printf("출력 : %s\n", Str); // 출력
		In.close(); // 굳이 안닫아도 됨 근데 닫으면 좋음
		
		System.out.printf("프로그램 종료\n");
	}
}
