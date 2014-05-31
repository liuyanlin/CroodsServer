package com.thecroods.client.gift.bean;

public class GiftCodeBean {

	private String year;
	private String day;
	private String random;

	public GiftCodeBean(String code){
		this.year = "20" + code.substring(0, 2);
		this.day = code.substring(2, 5);
		this.random = code.substring(8, 16);
	}
	/**
	 * 
	 * @param year 2014
	 * @param day 365
	 * @param random 1000000000
	 */
	public GiftCodeBean(String year,String day,String expire,String random){
		this.year = year;
		this.day = day;
		this.random = random;
	}
	
	public String toCode() {
		String mYear = year.substring(2, 4);
		return mYear + day + random;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getday() {
		return day;
	}

	public void setday(String day) {
		this.day = day;
	}

	public String getRandom() {
		return random;
	}

	public void setRode(String random) {
		this.random = random;
	}

}
