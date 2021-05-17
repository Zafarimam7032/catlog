package com.grwts.main;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "SoftSubCatelog")
public class SoftSubCatelogVo {
	@Id
	@Column(name = "SubCatelog_Id")
	private int subCatelogId;
	@Column(name = "SuCatelogName")
	private String subCatelogName;
	@Temporal(TemporalType.DATE)
	@Column(name = "Date")
	private Date subCatelogDate;
	@Column(name = "UserName")
	private String userName;
	@ManyToOne
	private SoftCatelogVo softcatelogVo;

	public int getSubCatelogId() {
		return subCatelogId;
	}

	public void setSubCatelogId(int subCatelogId) {
		this.subCatelogId = subCatelogId;
	}

	public String getSubCatelogName() {
		return subCatelogName;
	}

	public void setSubCatelogName(String subCatelogName) {
		this.subCatelogName = subCatelogName;
	}

	public Date getSubCatelogDate() {
		return subCatelogDate;
	}

	public void setSubCatelogDate(Date subCatelogDate) {
		this.subCatelogDate = subCatelogDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public SoftCatelogVo getSoftcatelogVo() {
		return softcatelogVo;
	}

	public void setSoftcatelogVo(SoftCatelogVo softcatelogVo) {
		this.softcatelogVo = softcatelogVo;
	}

	@Override
	public String toString() {
		return "SoftSubCatelogVo [subCatelogId=" + subCatelogId + ", subCatelogName=" + subCatelogName
				+ ", subCatelogDate=" + subCatelogDate + ", userName=" + userName + ", softcatelogVo=" + softcatelogVo
				+ "]";
	}

	
}
