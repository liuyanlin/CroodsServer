package com.thecroods.pay.bumobo.bean;

public class BaiduPayBean {

	private String app_Id; // 应用 id
	private String uin; // 用户 ID
	private String urecharge_Id; // 开发者发起购买请求时传过来的开发者自身订单 ID
	private String extra;// 开发者发起购买请求时传过来的 开发者自定义字段
	private String recharge_Money_Code; // USD 充值货币代码
	private String recharge_Money; // 充值金额
	private String recharge_M; // 充值 M 币个数
	private String pay_Status; // 支付状态:0 未处理，1 成功，2 失败
	private String create_Time; // 1351675829 创建时间
	public String getApp_Id() {
		return app_Id;
	}
	public void setApp_Id(String app_Id) {
		this.app_Id = app_Id;
	}
	public String getUin() {
		return uin;
	}
	public void setUin(String uin) {
		this.uin = uin;
	}
	public String getUrecharge_Id() {
		return urecharge_Id;
	}
	public void setUrecharge_Id(String urecharge_Id) {
		this.urecharge_Id = urecharge_Id;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public String getRecharge_Money_Code() {
		return recharge_Money_Code;
	}
	public void setRecharge_Money_Code(String recharge_Money_Code) {
		this.recharge_Money_Code = recharge_Money_Code;
	}
	public String getRecharge_Money() {
		return recharge_Money;
	}
	public void setRecharge_Money(String recharge_Money) {
		this.recharge_Money = recharge_Money;
	}
	public String getRecharge_M() {
		return recharge_M;
	}
	public void setRecharge_M(String recharge_M) {
		this.recharge_M = recharge_M;
	}
	public String getPay_Status() {
		return pay_Status;
	}
	public void setPay_Status(String pay_Status) {
		this.pay_Status = pay_Status;
	}
	public String getCreate_Time() {
		return create_Time;
	}
	public void setCreate_Time(String create_Time) {
		this.create_Time = create_Time;
	}


}
