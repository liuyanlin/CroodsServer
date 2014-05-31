package com.thecroods.util;

import java.io.Serializable;

public class ConnectionParam implements Serializable {

	private String driver; //数据库驱动程序
	private String url; //数据连接的URL
	private String user; //数据库用户名
	private String password; //数据库密码
	//最大连接数
	private int MaxConnectionCount = 40;
	
	//最小连接数
	private int MinConnectionCount = 2; 
	
	private int IncrementalConnections = 2;


/**

* 唯一的构造函数，需要指定连接的四个必要参数
* @param driver 数据驱动
* @param url 数据库连接url
* @param user 用户名
* @param password 密码

*/
	public ConnectionParam(String driver,String url,String user,String password)	
	{
	
		this.driver = driver;
		
		this.url = url;
		
		this.user = user;
		
		this.password = password;
	
	}



	public String getDriver() {
	
		return driver;
	
	}



	public String getPassword() {
	
		return password;
	
	}



	public String getUrl() {
	
		return url;
	
	}



	public String getUser() {
	
		return user;
	
	}



	public void setDriver(String driver) {
	
		this.driver = driver;
	
	}



	public void setPassword(String password) {
	
		this.password = password;
	
	}



	public void setUrl(String url) {
	
		this.url = url;
	
	}



	public void setUser(String user) {
	
		this.user = user;
	
	}

	public int getMaxConnection()
	{
		return this.MaxConnectionCount;
	}
	
	public int getMinConnection()
	{
		return this.MinConnectionCount;
	}

	public void setMaxConnection(int value)
	{
		this.MaxConnectionCount = value;
	}
	
	public void setMinConnection(int value)
	{
		this.MinConnectionCount = value;
	}
	
	
	public int getIncrementalConnections()
	{
		return this.IncrementalConnections;
	}

	public void setIncrementalConnections(int value)
	{
		this.IncrementalConnections = value;
	}
	
	
	/**
	
	* @see java.lang.Object#clone()
	
	*/
	
	public Object clone(){
	
		ConnectionParam param = new ConnectionParam(driver,url,user,password);
	
		return param;
	
	}



	/**
	
	* @see java.lang.Object#equals(java.lang.Object)
	
	*/	
	public boolean equals(Object obj) {
	
		if(obj instanceof ConnectionParam){
		
			ConnectionParam param = (ConnectionParam)obj;
			
			return ((driver.compareToIgnoreCase(param.getDriver()) == 0)&&
			
			(url.compareToIgnoreCase(param.getUrl()) == 0)&&
			
			(user.compareToIgnoreCase(param.getUser()) == 0)&&
			
			(password.compareToIgnoreCase(param.getPassword()) == 0));
		
		}

		return false;
	
	}
}