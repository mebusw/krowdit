package com.tadosoft.krowdit.loop;

/**
 * This javabean contains the infomation of a single password recover email.
 * 
 * @author jacky
 * 
 */
public class NotifyEmail {
	private String to;
	private String pwd;
	private String userName;

	
	public NotifyEmail(String to, String pwd, String userName) {
		super();
		this.to = to;
		this.pwd = pwd;
		this.userName = userName;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}