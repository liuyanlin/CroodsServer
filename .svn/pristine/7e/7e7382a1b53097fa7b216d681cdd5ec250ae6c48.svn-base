package com.thecroods.client.gift.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;

import com.thecroods.client.gift.bean.GiftCodeBean;
import com.thecroods.util.DBUtil;

public class GiftServer {

	/**
	 * 生成15位兑换码
	 * 
	 * @param expire
	 *            兑换码保质期
	 * @return
	 */
	public String generateCode15(int expire) {
		String code = "";

		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);// 2位
		int day = c.get(Calendar.DAY_OF_YEAR);// 3位

		int newDay = day + expire;
		if (newDay > 365) {
			year = newDay / 365 + year;
			day = newDay % 365;
		}

		Random random = new Random();
		random.setSeed(c.getTimeInMillis());
		Long l = random.nextLong() * (9999999999L - 1000000000L) + 1000000000L;

		code = year + day + l + "";

		return code;
	}

	/**
	 * 验证code是否过期 过期：true
	 */
	public boolean CodeExpired(String code) {
		GiftCodeBean bean = new GiftCodeBean(code);
		int codeYear = Integer.valueOf(bean.getYear());
		int codeDay = Integer.valueOf(bean.getday());

		Calendar c = Calendar.getInstance();
		int curYear = c.get(Calendar.YEAR);
		int curDay = c.get(Calendar.DAY_OF_YEAR);

		if (codeYear > curYear)
			return false;
		
		if (codeYear == curYear)
			if (codeDay >= curDay)
				return false;

		
		return true;
	}
	
	/**
	 * 是否已被使用  已使用返回true
	 * @return
	 */
	public boolean isUsed(String code){
		String sql = "select del from c_code where code = " + code;
		ResultSet rs = DBUtil.get(sql);
		int result = 1;
		try {
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result == 1? true :false;
	}
}
