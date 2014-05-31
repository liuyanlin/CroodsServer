package com.thecroods.client.gift.bean;

import java.util.List;

public class CodeInfo {
	/**
	 * 
	 * Json示例：
		{
		    "id": "18",
		    "type": "测试大礼包2",
		    "about": "text2",
		    "codes":["123456","123456","123456"],
		    "gift": "101|200;105|2"
		}
	 */	
	private String id; 
	private String type;         //兑换码类型
	private String about;        //兑换码描述
	private String gift;         //兑换码礼品
	private List<String> codes;  //兑换码列表
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getGift() {
		return gift;
	}
	public void setGift(String gift) {
		this.gift = gift;
	}
	public List<String> getCodes() {
		return codes;
	}
	public void setCodes(List<String> codes) {
		this.codes = codes;
	}
	
	
	
}
