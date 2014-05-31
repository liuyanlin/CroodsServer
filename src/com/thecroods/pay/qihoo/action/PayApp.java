/**
 * 
 */

package com.thecroods.pay.qihoo.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import com.thecroods.pay.alipay.util.UtilDate;
import com.thecroods.pay.qihoo.dao.QiHooPayDao;
import com.thecroods.pay.qihoo.msdk.QException;
import com.thecroods.pay.qihoo.msdk.QUtil;

public class PayApp implements PayAppInterface {
	
	//TODO::需要修改为应用自身的app_key
	public static String _appKey = "0b0660ab66ed426c2e3d28cbb14e4e83";
	//TODO::需要修改为应用自身的app_secret(服务器之间通讯使用)
	public static String _appSecret = "c2db1af10113772c4dabd3e1803646c4";
	//TODO::人民币-游戏货币的兑换比例
	private int _cashRate = 10;

	QiHooPayDao dao ;
	
	public PayApp() 
	{
		dao = new QiHooPayDao();
		
	}
	
	@Override
	public String getAppKey() {
		return this._appKey;
	}
	
	@Override
	public String getAppSecret() {
		// TODO Auto-generated method stub
		return this._appSecret;
	}

	@Override
	public Boolean isValidOrder(HashMap<String, String> orderParams) {
		String orderId;

		orderId = orderParams.get("app_order_id");
		if (orderId == null || orderId.equals("")) {
			orderId = orderParams.get("order_id");
		}

		HashMap<String, String> order = this._getOrder(orderId);
		if (order == null) {
			return false;
		}

		String orderProcessed = order.get("processed");
		if (orderProcessed == null) {
			return true;
		}

		if (orderProcessed.equals("")) {
			return true;
		}

		return false;
	}

	//TODO::从数据库中获取订单
	private HashMap<String, String> _getOrder(String orderId) {
		
		HashMap<String, String> order = new HashMap<String, String>();
		order.put("order_id", orderId);
		//该订单是否已经处理过
		boolean b = dao.isOrderExist(orderId);
		if(!b){
			//没有处理过
			order.put("processed", "");
		}else{
			//如果已经处理过，
			order.put("processed", "1");			
		}


		return order;
	}

	//处理订单，发货或者增加游戏中的游戏币
	@Override
	public void processOrder(HashMap<String, String> orderParams) {
		Boolean re = this._updateOrder(orderParams);
		if (re) {
			return;
		}

		this._addCash(orderParams);
	}

	//TODO::更新数据库中的订单状态。
	private Boolean _updateOrder(HashMap<String, String> orderParams) {
		//更新订单,标识为已经处理，避免重复处理
		//如果更新订单状态失败,记录异常，以便再次处理。再次处理的逻辑需应用自己处理
		String id = UtilDate.getOrderNum();
		String project_id = orderParams.get("project_id");
		int amount = Integer.valueOf(orderParams.get("amount"));
		String app_uid = orderParams.get("app_uid");
		String order_id = orderParams.get("order_id");
		String app_order_id = orderParams.get("app_order_id");
		String bank_code = orderParams.get("bank_code");
		
		dao.setOrderInfo(id, project_id, amount, app_uid, order_id, app_order_id, bank_code);
		return true;
	}
    
    private int _getAmount(HashMap<String, String> orderParams){
        String isSms = orderParams.get("is_sms");
        int amount = 0;
        if(isSms==null || isSms.equals("") || isSms.equals("0"))
        {
            String strAmount = orderParams.get("amount");
            amount = Integer.parseInt(strAmount);
        }else{
            String payExtStr = orderParams.get("pay_ext");
            //TODO::根据consumeCode反推amount
            //json_decode(payExtStr),然后取payExt.get("notify_data").get("consumeCode")
            //TODO::从consumeCode反推amount，注意amount单位为分
        }
        return amount;
    }

	//TODO::发货或者增加游戏中的货币
	private Boolean _addCash(HashMap<String, String> orderParams) {
		
		//如果发货失败，记录异常，以便再次处理。处理的逻辑需应用自己处理。
		//充值金额，以人民币分为单位。例如2000代表20元
		int amount = this._getAmount(orderParams);
		//兑换比例(人民币兑换游戏货币，_cashRate==10,表示1元人民币可兑换10游戏货币)
		int gameCashNum = amount / 100 * this._cashRate;
		return true;
	}
	

}
