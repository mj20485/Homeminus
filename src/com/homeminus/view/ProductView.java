package com.homeminus.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.homeminus.dto.ElectProduct;
import com.homeminus.dto.FoodProduct;
import com.homeminus.dto.LifeProduct;
import com.homeminus.dto.Product;

public class ProductView {
	//입력, 출력 작업은 모두 이 클래스에서 처리
	private Scanner scan = new Scanner(System.in);

	//타이틀과 메인 메뉴를 출력하는 메소드 작성
	public void showMainMenu() {
		System.out.println();
		System.out.println("*** 홈마이너스 마트 제품 관리 프로그램 ***");
		System.out.println("==========================================");
		System.out.println("메뉴>");
		System.out.println("1. 제품 등록");
		System.out.println("2. 제품 출력");
		System.out.println("3. 제품 검색");
		System.out.println("4. 제품 수정");
		System.out.println("5. 제품 삭제");
		System.out.println("0. 종료");
		System.out.println("==========================================");
		System.out.print("선택>> "); 
	}//showMainMenu 끝

	//서브 타이틀과 서브메뉴의 번호를 입력받아서 해당 
	//서브 메뉴를 출력한다.
	public void showSubMenu(String title, int c) {
		System.out.println();//줄바꿈
		System.out.println(title);
		System.out.println("==========================================");
		System.out.println("메뉴>");

		switch(c) {
		case 1://카테고리 서브 메뉴
			System.out.println("1. 가전제품");
			System.out.println("2. 생필품");
			System.out.println("3. 식품");
			System.out.println("0. 돌아가기");
			break;
		case 2://출력 서브 메뉴
			System.out.println("1. 전체 출력");
			System.out.println("2. 카테고리별 출력");
			System.out.println("0. 돌아가기");
			break;
		case 3://삭제 서브 메뉴
			break;
		}

		//모든 서브 메뉴에서 공통으로 출력될 내용.
		System.out.println("==========================================");
		System.out.print("선택>> ");
	}

	/////////////////////////여기까지 메뉴처리 부분

	//번호 받기 메소드
	public int inputNum() {
		//scanner의 문제 
		//번호 뒤에 문자열을 입력받을 때 입력이 안되는 문제
		String str = scan.nextLine();//입력은 문자열로..
		int num = -1;

		//숫자를 입력하지 않고 엔터를 쳤을 때의 예외사항 막기.
		if(!str.equals("")) {
			num = Integer.parseInt(str);
		}

		return num;
	}//숫자 입력 메소드 끝
	
	//문자열 입력 메소드
	public String inputStr(String str) {
		System.out.print(str);
		
		return scan.nextLine();
	}//문자열 입력 메소드 끝

	public void inputProduct(Product p) {
		//공통부분 입력 처리
		System.out.print("제품명: ");
		p.setName(scan.nextLine());
		System.out.print("가격: ");
		p.setPrice(inputNum());//숫자문자열 -> 숫자 변환
		System.out.print("수량: ");
		p.setAmount(inputNum());
		System.out.print("생산국: ");
		p.setMade(scan.nextLine());
		System.out.print("생산일자: ");
		p.setMday(scan.nextLine());

		//각 카테고리 제품별 입력 처리
		if(p instanceof ElectProduct) {
			ElectProduct ep = (ElectProduct)p;
			System.out.print("제조사: ");
			//((ElectProduct)p).setMaker(scan.nextLine());
			ep.setMaker(scan.nextLine());
			System.out.print("보증기간: ");
			ep.setGuarantee(inputNum());
			System.out.print("등급: ");
			ep.setGrade(inputNum());
		}
		else if(p instanceof LifeProduct) {
			LifeProduct lp = (LifeProduct)p;
			System.out.print("제조사: ");
			lp.setMaker(scan.nextLine());
			System.out.print("재활용(y/n): ");
			String yesno = scan.nextLine();
			if(yesno.equals("y")) {
				lp.setRecycle(true);
			}
			else {
				lp.setRecycle(false);
			}
			System.out.print("용도: ");
			lp.setUsage(scan.nextLine());
		}
		else {
			FoodProduct fp = (FoodProduct)p;
			System.out.print("생산지: ");
			fp.setOrigin(scan.nextLine());
			System.out.print("유통기한: ");
			fp.setPeriod(inputNum());
			System.out.print("분류: ");
			fp.setKind(scan.nextLine());
		}
	}//메소드 끝

	//변경 수량 입력 메소드
	public int updateNum() {
		System.out.print("변경 수량: ");
		
		int uNum = inputNum();
		
		return uNum;
	}
	
	/////////////////////////////여기까지 입력 처리 부분

	public void printMsg(int m) {
		switch(m) {
		case 0://프로그램 종료 메시지
			System.out.println("프로그램 종료합니다.");
			break;
		case 1://이전메뉴 돌아가기 메시지
			System.out.println("이전 메뉴로 돌아갑니다.");
			break;
		case 2://메뉴입력 오류 메시지
			System.out.println("잘못 입력하셨습니다.");
			break;
		case 3://데이터 없음 메시지
			System.out.println("저장된 데이터 없음.");
			break;
		case 4://저장 완료 메시지
			System.out.println("저장 완료.");
			break;
		case 5://삭제 완료 메시지
			System.out.println("삭제 완료.");
			break;
		case 6://검색 결과 없음 메시지
			System.out.println("검색 결과 없음.");
			break;
		case 7://저장 실패 메시지
			System.out.println("저장 실패.");
			break;
		case 8://삭제 실패 메시지
			System.out.println("삭제 실패.");
			break;
		}
	}//printMsg 메소드 끝

	//전체 출력 및 카테고리 출력용 메소드
	//파리미터 : 전체 제품 목록, 카테고리 번호
	public void printProduct(ArrayList<Product> pList, int c) {
		int cnt = 0;//카테고리별 출력 시 제품 개수 카운트

		for(Product p : pList) {
			switch(c) {
			case 0://전체 출력(처리 안함)
			case 1://가전 출력
				if(p instanceof ElectProduct) {
					System.out.println(p);
					cnt++;
				}
				if(c != 0) {//전체 출력일 경우 break 스킵.
					break;
				}
			case 2://생필 출력
				if(p instanceof LifeProduct) {
					System.out.println(p);
					cnt++;
				}
				if(c != 0) {//전체 출력일 경우 break 스킵.
					break;
				}
			case 3://식품 출력
				if(p instanceof FoodProduct) {
					System.out.println(p);
					cnt++;
				}
				if(c != 0) {//전체 출력일 경우 break 스킵.
					break;
				}
			}//switch 끝
		}//for문 끝
		
		if(cnt == 0) {
			System.out.println("해당 제품이 없습니다.");
		}
	}//메소드 끝

	public void searchResult(Product p) {
		//전달받은 제품 인스턴스 출력
		System.out.println("검색 결과");
		System.out.println(p);
	}
	
}//클래스 끝









