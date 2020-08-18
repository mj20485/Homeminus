package com.homeminus.dto;

public class FoodProduct extends Product {
	private String origin;//생산지
	private int period;//유통기한
	private String kind;//분류:육류,생선류,채소,과일 등
	
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	
	@Override
	public String toString() {
		String str = "생산지: " + origin + "\n"
				+ "유통기한: " + period + "\n"
				+ "분류: " + kind + "\n";
		return super.toString() + str;
	}
}



