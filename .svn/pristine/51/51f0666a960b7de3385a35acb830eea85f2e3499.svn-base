package com.thecroods.client.gift.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.thecroods.client.gift.bean.CodeInfo;
import com.thecroods.client.gift.bean.GiftCodeBean;
import com.thecroods.pay.alipay.util.UtilDate;
import com.thecroods.util.DBUtil;

public class GiftDao {

	
	/**
	 * 查找code  
	 * 不存在：0
	 * 存在：1
	 * @param code
	 * @return
	 */
	public int findCode(String code) {
		try {
			String sql = "";
			ResultSet rs = DBUtil.get(sql);
			if(rs.next())
				return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 通过code 查找礼物
	 * @param code
	 * @return
	 */
	public String findGiftbyCode(String code) {
		
		String gift = "";
		try {
			String sql = "select * from [thecroods].[dbo].[c_gift] a join [thecroods].[dbo].[c_code] b on a.id = b.giftid where b.del = 0 and b.code="+code;
			ResultSet rs = DBUtil.get(sql);
			while (rs.next()) {
				gift = rs.getString("gift");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return gift;
	}
	
	/**
	 * 设定code使用状态 
	 * @param code
	 * @param state 1:已使用  ， 0：未使用
	 */
	public void setUsed(String code, int state){
		String sql = "update [thecroods].[dbo].[c_code] set del = "+state+" where code = "+ code;
		DBUtil.update(sql);
	}
	
	
	/**
	 * 存储兑换码批次信息
	 * @param bean
	 */
		
	public void setGiftinfo(CodeInfo bean){
		
		String id = bean.getId();
		String type = bean.getType();
		String about = bean.getAbout();
		String gift = bean.getGift();
		String sql = "insert into [thecroods].[dbo].[c_gift] values ('"+id+"','"+type+"','"+about+"','"+gift+"')";
		DBUtil.update(sql);
	}
	
	/**
	 * 存储兑换码
	 * @param bean
	 */
	public void setCodeList(CodeInfo bean){
		String giftID = bean.getId();
		List<String> codes = bean.getCodes();
		System.out.println("存储兑换码，数量："+codes.size() );
		String sql = "";
		int i = 0;
		for (String code : codes) {
			String id = UtilDate.getOrderNum() + i++;
			sql = "insert into [thecroods].[dbo].[c_code] values ('"+id+"','"+code+"',0,'"+giftID+"')";
			DBUtil.update(sql);
		}
	}
}
