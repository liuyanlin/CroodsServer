package com.thecroods.pay.qihoo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.thecroods.util.DBUtil;

public class QiHooPayDao {
	
	/**
	 * 保存360支付信息
	 * @param id  
	 * @param Project_id		所购商品ID
	 * @param amount			总价
	 * @param app_uid			应用内用户iD
	 * @param order_id			360支付订单号
	 * @param app_order_id		应用订单号 下单时若指定验证时也要指定
	 * @param bank_code			支付方式
	 */
	public void setOrderInfo(String id,String project_id,int amount,String app_uid,String order_id,String app_order_id,String bank_code){
		String sql = "insert into c_qopay values('"+id+"','"+project_id+"',"+amount+",'"+app_uid+"','"+order_id+"','"+app_order_id+"','"+bank_code+"')";
		DBUtil.update(sql);
		
	}
	
	/**
	 * 查询订单是否存在
	 * @param order_id
	 * @return
	 */
	public boolean isOrderExist(String order_id){
		try {
			String sql = "select * from c_c_qopay where order_id =  " + order_id;
			ResultSet rs =  DBUtil.get(sql);
			while(rs.next()){
				
				rs.close();
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
