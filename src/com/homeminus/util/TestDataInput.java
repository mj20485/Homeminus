package com.homeminus.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import com.homeminus.dto.*;

public class TestDataInput {
	
	public void addTestData(ArrayList<Product> productList) {
		
		Calendar cal = Calendar.getInstance();
		//싱글턴 패턴
		int year = cal.get(Calendar.YEAR);//현재 연도
		int month = cal.get(Calendar.MONTH) + 1;//현재 월
		int day = cal.get(Calendar.DAY_OF_MONTH);//오늘
		String mday = "" + year + month + day;
		
		for(int i = 0; i < 3; i++) {
			ElectProduct p = new ElectProduct();
			p.setName(i + "elec");
			p.setPrice((i+1) * 1000);
			p.setAmount((i+1) * 10);
			p.setMade("made" + i);
			p.setGrade(2);
			p.setGuarantee(2);
			p.setMaker("maker" + i);
			p.setMday(mday);
			productList.add(p);
		}
		for(int i = 0; i < 3; i++) {
			LifeProduct p = new LifeProduct();
			p.setName(i + "life");
			p.setPrice((i+1) * 1000);
			p.setAmount((i+1) * 10);
			p.setMade("made" + i);
			p.setRecycle(false);
			p.setUsage("공용");
			p.setMaker("maker" + i);
			p.setMday(mday);
			productList.add(p);
		}
		for(int i = 0; i < 3; i++) {
			FoodProduct p = new FoodProduct();
			p.setName(i + "food");
			p.setPrice((i+1) * 1000);
			p.setAmount((i+1) * 10);
			p.setMade("made" + i);
			p.setKind("과일");
			p.setOrigin("origin");
			p.setPeriod(7);
			p.setMday(mday);
			productList.add(p);
		}
	}
}
