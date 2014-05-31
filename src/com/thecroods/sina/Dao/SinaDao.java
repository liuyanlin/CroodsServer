package com.thecroods.sina.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thecroods.sina.action.AccountInfo;
import com.thecroods.util.DBUtil;

public class SinaDao {
	//  玩家id获得了 还有玩家的starts和pets 是所有好友的!!
	
	//获得游戏玩家们的信息
	/**
	 * 玩家id列表
	 * @param ids
	 */
	public List<String> getFriendInGame(List<String> ids)
	{
		List<String> games = new ArrayList<String>();
		try {
			for (int i = 0; i < ids.size(); i++) {
			String id = ids.get(i);
			String sql = "SELECT * FROM [thecroods].[dbo].[c_sinainfo] where uid = '" + id+"'";

				ResultSet rs = DBUtil.get(sql);
				if(rs!=null && rs.next()){//存在
					games.add(id);
				}
			}
			return games;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.ConnClose();
		}
		return null;
	}
	
	/**
	 * 
	 * @param id 玩家id
	 * @return 玩家的starts和pets
	 */
	public AccountInfo getFriendsInfoForId(String id){
		try {
			String sql = "SELECT * FROM [thecroods].[dbo].[c_sinainfo] where uid = '" + id+"'";
			ResultSet rs = DBUtil.get(sql);
			AccountInfo info = new AccountInfo();
			while (rs.next()) {
				info.uid = rs.getString("uid");
				info.name = rs.getString("name");
				info.pic = rs.getString("pic");
				info.starts = rs.getInt("starts");
				info.pets = rs.getInt("pets");
				info.level = rs.getInt("lv");
			}
			return info;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.ConnClose();
		}
		return null;
	}
	
}
