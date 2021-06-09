package com.example.demo.vo;

import java.util.Date;

public class ClientVo {

	private String shareKey;

	private String email;

	private String phone;

	private Date dateStart;

	private Date dateEnd;

	public String getShareKey() {
		return shareKey;
	}

	public void setShareKey(String shareKey) {
		this.shareKey = shareKey;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public ClientVo(String shareKey, String email, String phone, Date dateStart, Date dateEnd) {
		this.shareKey = shareKey;
		this.email = email;
		this.phone = phone;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
	}

	public ClientVo() {
	}

}
