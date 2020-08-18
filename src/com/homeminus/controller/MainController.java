package com.homeminus.controller;

import java.util.ArrayList;

import com.homeminus.dto.ElectProduct;
import com.homeminus.dto.FoodProduct;
import com.homeminus.dto.LifeProduct;
import com.homeminus.dto.Product;
import com.homeminus.service.ProductService;
import com.homeminus.view.ProductView;

public class MainController {

	//서비스, 뷰(화면처리) 용 인스턴스 생성
	ProductView pv = new ProductView();
	ProductService ps = new ProductService();

	public void controll() {
		//더미 데이터 추가(모든 프로그램이 작성완료되면 삭제)
		ps.startPs();

		//메뉴 입력용 변수
		int menu = 0;

		//프로그램 무한 반복 처리
		while(true) {
			//타이틀과 메인메뉴 출력
			pv.showMainMenu();

			//메뉴 번호 입력 받기(뷰 클래스)
			//뷰 클래스의 번호입력받는 메소드 실행.
			menu = pv.inputNum();

			//종료처리
			if(menu == 0) {
				//종료 메시지 출력(뷰 클래스)
				pv.printMsg(0);
				break;//또는 return;
			}

			//메뉴 분기 처리
			switch(menu) {
			case 1://제품 등록
				regProduct();
				break;
			case 2://제품 출력
				printProduct();
				break;
			case 3://제품 검색
				searchProduct();
				break;
			case 4://제품 수정
				updateProduct();
				break;
			case 5://제품 삭제
				deleteProduct();
				break;
			default:
				//잘못된 메뉴 번호 입력에 대한 메시지 출력
				pv.printMsg(2);
			}//switch 끝
		}//while 끝
	}//controll 메소드 끝

	//제품 등록 용 메소드
	private void regProduct() {
		//서브메뉴 입력용 변수
		int sub = 0;

		//돌아가기 메뉴가 선택되기 전까지 무한반복
		while(true) {
			//서브타이틀/서브메뉴 출력(뷰 클래스)
			pv.showSubMenu("제품 등록", 1);

			//서브메뉴 입력(뷰 클래스)
			//뷰 클래스의 번호입력받는 메소드 실행.
			sub = pv.inputNum();

			if(sub == 0) {
				//돌아기기 메시지 출력(뷰 클래스)
				pv.printMsg(1);
				return;
			}

			Product p = null;//제품 정보 저장용 임시 공간

			//서브메뉴 분기 처리
			switch(sub) {
			case 1://가전제품 등록
				//가전제품용 인스턴스(저장 공간 만들기)
				p = new ElectProduct();
				break;
			case 2://생필품 등록
				//생필품용 인스턴스(저장 공간 만들기)
				p = new LifeProduct();
				break;
			case 3://식품 등록
				//식품용 인스턴스(저장 공간 만들기)
				p = new FoodProduct();
				break;
			default:
				//잘못된 메뉴 번호 입력에 대한 메시지 출력(뷰 클래스)
				pv.printMsg(2);
			}

			//뷰 클래스에서 입력 받기
			pv.inputProduct(p);
			//서비스 클래스에서 저장
			boolean result = ps.registProd(p);
			if(result) {
				//저장 완료 메시지 출력(뷰 클래스)
				pv.printMsg(4);
			}
			else {
				//저장 실패 메시지 출력(뷰 클래스)
				pv.printMsg(7);
			}

		}//while문 끝

	}//메소드 끝

	//제품 출력 용 메소드
	private void printProduct() {
		//저장된 목록에 제품이 들어 있는지 확인
		//서비스 클래스로부터 제품 목록을 받아와서 확인 
		ArrayList<Product> pList = ps.getProductList();
		if(pList.isEmpty()) {
			//isEmpty() : ArrayList에 저장된 데이터가
			//			  없으면 true, 있으면 false.
			//뷰 클래스의 메시지 출력(목록이 비어 있음.)
			pv.printMsg(3);
			//메소드 종료
			return;
		}

		//2가지 출력 형태(서브메뉴)
		//1. 전체 출력
		//2. 카테고리별 출력
		//0. 돌아가기

		//서브메뉴 입력용 변수
		int sub = 0;

		//돌아가기 메뉴가 선택되기 전까지 무한반복
		while(true) {
			//서브타이틀/서브메뉴 출력(뷰 클래스)
			pv.showSubMenu("제품 출력", 2);

			//서브메뉴 입력(뷰 클래스)
			//뷰 클래스의 번호입력받는 메소드 실행.
			sub = pv.inputNum(); 

			if(sub == 0) {
				//돌아기기 메시지 출력(뷰 클래스)
				pv.printMsg(1);
				return;
			}

			switch(sub) {
			case 1://전체 출력
				//전체 목록을 뷰 클래스의 출력 메소드로 전달
				pv.printProduct(pList, 0);
				break;
			case 2://카테고리별 출력(카테고리 서브 메뉴처리)
				//카테고리별 출력 메소드 실행
				printCate(pList);
				break;
			default:
				//잘못된 메뉴 번호 입력에 대한 메시지 출력
				pv.printMsg(2);
			}
		}
	}

	//카테고리별 출력 용 메소드
	private void printCate(ArrayList<Product> pList) {
		//메뉴 번호 입력 변수
		int sub = 0;

		//돌아가기 메뉴가 선택되기 전까지 무한반복
		while(true) {
			//카테고리 서브 메뉴 출력
			//(제품 등록에서 사용한 메뉴 재활용)
			pv.showSubMenu("카테고리별 출력", 1);

			//서브메뉴 입력
			//뷰 클래스의 번호입력받는 메소드 실행.
			sub = pv.inputNum();

			if(sub == 0) {
				//돌아기기 메시지 출력
				pv.printMsg(1);
				return;
			}

			//뷰 클래스의 카테고리별 출력 메소드 사용
			//카테고리 선택 번호(서브메뉴번호)를 출력 
			//메소드에 전달
			pv.printProduct(pList, sub);
		}
	}

	//제품 검색 용 메소드
	private void searchProduct() {
		//저장된 목록에 제품이 들어 있는지 확인
		//서비스 클래스로부터 제품 목록을 받아와서 확인 
		ArrayList<Product> pList = ps.getProductList();
		if(pList.isEmpty()) {
			//isEmpty() : ArrayList에 저장된 데이터가
			//			  없으면 true, 있으면 false.
			//뷰 클래스의 메시지 출력(목록이 비어 있음.)
			pv.printMsg(3);
			//메소드 종료
			return;
		}

		//뷰 클래스의 문자열 입력 메소드 사용(검색 이름)
		String name = pv.inputStr("검색 이름: ");

		if(name.equals("")) {
			//이름을 입력하지 않은 경우. 
			//메시지 출력 후 메뉴로 돌아가기.
			pv.printMsg(2);
			return;
		}

		//서비스 클래스의 제품 검색 메소드 사용
		Product prod = ps.searchProd(name);

		//검색 결과가 있으면
		if(prod != null) {
			//뷰 클래스의 검색된 제품 정보 출력 메소드 사용
			pv.searchResult(prod);
		}
		else {//검색 결과가 없으면
			//검색 결과 없음 메시지 출력
			pv.printMsg(6);
		}
	}
	//제품 수정 용 메소드
	private void updateProduct() {
		//저장된 목록에 제품이 들어 있는지 확인
		//서비스 클래스로부터 제품 목록을 받아와서 확인 
		ArrayList<Product> pList = ps.getProductList();
		if(pList.isEmpty()) {
			//isEmpty() : ArrayList에 저장된 데이터가
			//			  없으면 true, 있으면 false.
			//뷰 클래스의 메시지 출력(목록이 비어 있음.)
			pv.printMsg(3);
			//메소드 종료
			return;
		}

		//뷰 클래스의 문자열 입력 메소드 사용(검색 이름)
		String name = pv.inputStr("검색 이름: ");

		//서비스 클래스의 제품 검색 메소드 사용
		Product prod = ps.searchProd(name);

		//검색한 제품 정보가 있으면
		if(prod != null) {
			//뷰 클래스의 검색된 제품 정보 출력 메소드 사용
			pv.searchResult(prod);
			//뷰 클래스의 수정 내용 입력 메소드 사용
			//가정. 이 프로그램에서는 제품의 수량만 수정할 수 있음.
			int updateNum = pv.updateNum(); 
			
			if(updateNum != -1) {
				//정상적인 변경 값이 입력되었을 경우
				//서비스 클래스의 정보 수정 메소드 사용
				boolean result = ps.updateProd(name, updateNum);
				if(result) {
					//수정 성공 메시지 출력
					pv.printMsg(4);
				}
				else {
					//수정 실패 메시지 출력
					pv.printMsg(7);
				}
			}
			else {
				//엔터가 입력되었을 경우. 메시지 출력
				pv.printMsg(2);
			}
			
		}
		else {
			pv.printMsg(6);
		}
		
	}

	//제품 삭제 용 메소드
	private void deleteProduct() {
		//메뉴 번호 입력 변수
		int sub = 0;

		//돌아가기 메뉴가 선택되기 전까지 무한반복
		while(true) {
			//삭제 서브 메뉴 출력

			//서브메뉴 입력
			//sub = 뷰 클래스의 번호입력받는 메소드 실행.

			if(sub == 0) {
				//돌아기기 메시지 출력
				pv.printMsg(1);
				return;
			}

			switch(sub) {
			case 1://서비스 클래스의 전체 삭제 메소드 실행
				deleteTotal();
				break;
			case 2://검색 삭제
				//검색 삭제 메소드 실행
				deleteSearch();
				break;
			default:
				//잘못된 메뉴 번호 입력에 대한 메시지 출력
				pv.printMsg(2);
			}
		}
	}

	private void deleteTotal() {
		String yesno = "";
		//뷰클래스의 문자열 입력 메소드 사용(실행여부. y/n)

		//yesno에 y가 입력되면 
		if(yesno.equals("y") || yesno.equals("Y")) {
			//서비스 클래스의 전체 삭제 메소드 사용
		}
		else {//yesno에 n이 입력되면 메소드 종료
			//뷰 클래스에서 취소 메시지 출력
			return;
		}
	}

	private void deleteSearch() {
		//뷰 클래스의 문자열 입력 메소드 사용(검색 이름)

		//서비스 클래스의 제품 검색 메소드 사용

		//검색한 제품 정보가 있으면
		//뷰 클래스의 검색된 제품 정보 출력 메소드 사용

		String yesno = "";
		//뷰클래스의 문자열 입력 메소드 사용(실행여부. y/n)

		//yesno에 y가 입력되면 
		if(yesno.equals("y") || yesno.equals("Y")) {
			//서비스 클래스의 제품 삭제 메소드 사용
		}
		else {//yesno에 n이 입력되면 메소드 종료
			//뷰 클래스에서 취소 메시지 출력
			return;
		}
	}

}//클래스 끝







