package com.grwts.main;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "SoftCatelog")
public class SoftCatelogVo {
	@Id
	@Column(name = "Catelog_Id")
	private int catelogid;
	@Column(name = "Catelog_Name")
	private String catelogName;
	@Temporal(TemporalType.DATE)
	@Column(name = "Date")
	private Date catelogDate;
	@Column(name = "UserName")
	private String userName;

	public int getCatelogid() {
		return catelogid;
	}

	public void setCatelogid(int catelogid) {
		this.catelogid = catelogid;
	}

	public String getCatelogName() {
		return catelogName;
	}

	public void setCatelogName(String catelogName) {
		this.catelogName = catelogName;
	}

	public Date getCatelogDate() {
		return catelogDate;
	}

	public void setCatelogDate(Date catelogDate) {
		this.catelogDate = catelogDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "CatelogVo [catelogid=" + catelogid + ", catelogName=" + catelogName + ", catelogDate=" + catelogDate
				+ ", userName=" + userName + "]";
	}

}
