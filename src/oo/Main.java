package oo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

public class Main {
	public static void main(String[] args) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		List<String> dateList = new ArrayList<>();
		List<String> dateListRE = new ArrayList<>();
		List<String> titleList = new ArrayList<>();
		List<String> textList = new ArrayList<>();
		List<Integer> viewList = new ArrayList<>();

		System.out.printf("프로그램 시작\n");

		Scanner In = new Scanner(System.in);
		while (true) {
			System.out.printf("명령어 입력 : "); // 명령어 입력
			String Cmd = In.nextLine().trim();

			if (Cmd.equals("article list")) { // 게시물 확인
				if (titleList.size() == 0) {
					System.out.println("게시글이 없습니다.");
				} else if (titleList.size() > 0) {
					System.out.printf("총 %d개의 게시물이 있습니다.\n", titleList.size());
					for (int i = 0; i < titleList.size(); i++) {
						System.out.printf("%d. %s | View : %d\n", i + 1, titleList.get(i), viewList.get(i));
					}
				}
			}

			else if (Cmd.equals("article write")) { // 게시물 작성
				while (true) {
					System.out.printf("제목 : "); // 제목입력
					String Title = In.nextLine().trim();
					if (Title.equals("exit")) {
						System.out.println("글 작성을 취소합니다.");
						break;
					} else if (Title.equals("")) {
						System.out.println("제목을 입력하세요.");
						continue;
					} else if (Title.length() > 0) {
						titleList.add(Title);
						System.out.printf("내용 : "); // 내용입력
						String Text = In.nextLine().trim();
						if (Text.equals("exit")) {
							titleList.remove(titleList.size() - 1);
							System.out.println("글 작성을 취소합니다.");
							break;
						}
						System.out.printf("%d번 글이 작성되었습니다.\n", titleList.size());
						textList.add(Text);
						long curTime = System.currentTimeMillis();
						dateList.add(dateFormatter.format(new Date(curTime)));
						dateListRE.add("0");
						viewList.add(0);
						break;
					}
				}
			}

			else if (Cmd.equals("article read")) { // 게시물 읽기
				int Num;
				while (true) {
					if (titleList.size() == 0) {
						System.out.println("게시물이 존재하지 않습니다.");
						break;
					}
					System.out.printf("읽을 게시물 번호 : ");
					String Enter = In.nextLine().trim();
					if (Enter.equals("exit")) {
						break;
					}
					try {
						Num = Integer.valueOf(Enter).intValue();
					} catch (NumberFormatException Char) {
						System.out.println("올바른 게시물 번호를 입력해 주세요.");
						continue;
					}
					if (Num <= titleList.size() && Num > 0) { // 게시물 출력 내용
						System.out.printf("번 호 : %d\n", Num);
						System.out.printf("날 짜 : %s\n", dateList.get(Num - 1));
						if (dateListRE.get(Num - 1) != "0") {
							System.out.printf("수 정 : %s\n", dateListRE.get(Num - 1));
						}
						System.out.printf("제 목 : %s\n", titleList.get(Num - 1));
						System.out.printf("내 용 : %s\n", textList.get(Num - 1));
						System.out.printf("조 회 : %d\n", viewList.get(Num - 1));
						int viewRE = viewList.get(Num - 1);
						viewList.set(Num - 1, viewRE + 1);
					} else if (Num > titleList.size()) {
						System.out.printf("%d번의 게시물은 존재하지 않습니다.\n", Num);
					} else {
						System.out.println("올바른 게시물 번호를 입력해 주세요.");
					}
				}
			}

			else if (Cmd.equals("article delete")) { // 게시물 삭제
				int Num;
				while (true) {
					if (titleList.size() == 0) {
						System.out.println("게시물이 존재하지 않습니다.");
						break;
					}
					System.out.printf("삭제할 게시물 번호 : ");
					String Enter = In.nextLine().trim();
					if (Enter.equals("exit")) {
						break;
					}
					try {
						Num = Integer.valueOf(Enter).intValue();
					} catch (NumberFormatException Char) {
						System.out.println("올바른 게시물 번호를 입력해 주세요.");
						continue;
					}
					if (Num <= titleList.size() && Num > 0) {
						titleList.remove(Num - 1);
						textList.remove(Num - 1);
						dateList.remove(Num - 1);
						System.out.printf("%d번의 게시물을 삭제했습니다.\n", Num);
						break;
					} else if (Num > titleList.size()) {
						System.out.printf("%d번의 게시물은 존재하지 않습니다.\n", Num);
					} else {
						System.out.println("올바른 게시물 번호를 입력해 주세요.");
					}
				}
			}

			else if (Cmd.equals("article modify")) { // 글 수정
				int Num;
				while (true) {
					if (titleList.size() == 0) {
						System.out.println("게시물이 존재하지 않습니다.");
						break;
					}
					System.out.printf("수정할 게시물 번호 : ");
					String Enter = In.nextLine().trim();
					if (Enter.equals("exit")) {
						break;
					}
					try {
						Num = Integer.valueOf(Enter).intValue();
					} catch (NumberFormatException Char) {
						System.out.println("올바른 게시물 번호를 입력해 주세요.");
						continue;
					}
					if (Num <= titleList.size() && Num > 0) {
						System.out.printf("제목 : %s -> ", titleList.get(Num - 1));
						String reTitle = In.nextLine().trim();
						String pastTitle = titleList.get(Num - 1);
						System.out.printf("%d번 게시물 수정을 취소합니다.\n", Num);
						if (reTitle.equals("exit")) {
							break;
						}
						if (reTitle.equals("") || reTitle.length() == 0) {
							System.out.println("제목을 입력하세요.");
							continue;
						} else if (reTitle.length() > 0) {
							titleList.set(Num - 1, reTitle);
							System.out.printf("내용 : %s -> ", textList.get(Num - 1));
							String reText = In.nextLine().trim();
							if (reText.equals("exit")) {
								titleList.set(Num - 1, pastTitle);
								System.out.printf("%d번 게시물 수정을 취소합니다.\n", Num);
								break;
							}
							textList.set(Num - 1, reText);
							long curTime = System.currentTimeMillis();
							dateListRE.set(Num - 1, dateFormatter.format(new Date(curTime)));
							System.out.printf("%d 번의 게시물을 수정했습니다.\n", Num);
							break;
						}
					} else if (Num > titleList.size()) {
						System.out.printf("%d번의 게시물은 존재하지 않습니다.", Num);
					} else {
						System.out.println("올바른 게시물 번호를 입력해 주세요.");
					}
				}
			}

			else if (Cmd.equals("exit")) { // 탈출
				In.close();
				break;
			}

			else if (Cmd.equals("") || Cmd.length() == 0) { // 입력 요망
				System.out.println("명령어를 입력해 주세요.");
			}

			else if (Cmd.equals("test")) { // 테스트 데이터 작성
				int j = titleList.size() + 3;
				for (int i = titleList.size(); i < j; i++) {
					titleList.add(String.format("Test 제목 입니다%d", i + 1));
					textList.add(String.format("Test 내용 입니다%d", i + 1));
					long curTime = System.currentTimeMillis();
					dateList.add(dateFormatter.format(new Date(curTime)));
					dateListRE.add("0");
					viewList.add(0);
				}
				System.out.println("테스트용 3개의 게시글을 작성하였습니다.");
			}

			else {
				System.out.printf("존재하지 않는 명령어 입니다.\n");
			}
		}

		System.out.printf("프로그램 종료\n");

	}
}
