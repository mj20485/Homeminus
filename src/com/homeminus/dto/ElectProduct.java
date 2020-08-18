package com.homeminus.dto;

public class ElectProduct extends Product {
	private String maker;//제조사
	private int guarantee;//보증기간
	private int grade;//효율 등급. 1~5
	
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public int getGuarantee() {
		return guarantee;
	}
	public void setGuarantee(int guarantee) {
		this.guarantee = guarantee;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	@Override
	public String toString() {
		String str = "제조사: " + maker + "\n"
				+ "보증기간: " + guarantee + "\n"
				+ "등급: " + grade + "\n";
		return super.toString() + str;
	}
}





