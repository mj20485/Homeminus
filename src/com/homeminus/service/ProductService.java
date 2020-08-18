package com.homeminus.service;

import java.util.ArrayList;

import com.homeminus.dto.Product;
import com.homeminus.util.TestDataInput;

public class ProductService {
	//전체 제품 저장 공간. ArrayList로 구성.
	private ArrayList<Product> pList = new ArrayList<>();
	
	//더미 데이터 추가용 메소드(테스트 용)
	public void startPs() {
		TestDataInput tdi = new TestDataInput();
		tdi.addTestData(pList);
	}//startPs 끝
	
	//제품 저장용 메소드
	public boolean registProd(Product p) {
		//반환형이 boolean인 이유는 저장에 실패할 수도 있기 때문.
		
		//전체 제품 목록에 넘어온 제품 인스턴스 추가
		pList.add(p);
		
		//정상적으로 추가되었다고 가정함.
		return true;
	}//메소드 끝
	
	//제품 목록을 전달하는 메소드
	public ArrayList<Product> getProductList(){
		return pList;
	}
	
	//제품 검색 처리 메소드
	public Product searchProd(String name) {
		for(Product p : pList) {
			if(name.equals(p.getName())) {
				return p;
			}
		}
		
		return null;//검색 결과 없음.(해당 제품 없음)
	}//검색 메소드 끝
	
	//수정 메소드
	public boolean updateProd(String name, int uNum) {
		for(Product p : pList) {
			if(name.equals(p.getName())) {
				//검색한 이름에 해당하는 제품의 수량 변경
				p.setAmount(uNum);
				return true;
			}
		}
		
		return false;
	}
	
}//클래스 끝






