package com.homeminus.dto;

public class LifeProduct extends Product {
	private String maker;//제조사
	private boolean recycle;//재활용 여부
	private String usage;//용도:주방,욕실,거실,베란다 등
	
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public boolean isRecycle() {
		return recycle;
	}
	public void setRecycle(boolean recycle) {
		this.recycle = recycle;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	
	@Override
	public String toString() {
		String rec;
		
		if(recycle) rec = "재활용";
		else 		rec = "일회용";
		
		//좀더 간단하게 한다면? 조건연산자 활용
		//rec = (recycle) ? "재활용" : "일회용";
		
		String str = "제조사: " + maker + "\n"
				+ "재활용여부: " + rec + "\n"
				+ "용도: " + usage + "\n";
		
		return super.toString() + str;
	}
}
