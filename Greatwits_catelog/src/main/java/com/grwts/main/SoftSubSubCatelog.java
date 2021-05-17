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
@Table(name="SoftSubSubCatelog")
public class SoftSubSubCatelog {
	@Id
	@Column(name = "SubSubcatelog_Id")
	private int subSubCatelogId;
	@Column(name = "SubSubName")
	private String subSubName;
	@Temporal(TemporalType.DATE)
	@Column(name = "Date")
	private Date subSubCatelogDate;
	@Column(name = "UserName")
	private String userName;
	@ManyToOne
	private SoftSubCatelogVo softsubCatelogVo;
	public int getSubSubCatelogId() {
		return subSubCatelogId;
	}
	public void setSubSubCatelogId(int subSubCatelogId) {
		this.subSubCatelogId = subSubCatelogId;
	}
	public String getSubSubName() {
		return subSubName;
	}
	public void setSubSubName(String subSubName) {
		this.subSubName = subSubName;
	}
	public Date getSubSubCatelogDate() {
		return subSubCatelogDate;
	}
	public void setSubSubCatelogDate(Date subSubCatelogDate) {
		this.subSubCatelogDate = subSubCatelogDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public SoftSubCatelogVo getSoftsubCatelogVo() {
		return softsubCatelogVo;
	}
	public void setSoftsubCatelogVo(SoftSubCatelogVo softsubCatelogVo) {
		this.softsubCatelogVo = softsubCatelogVo;
	}
	@Override
	public String toString() {
		return "SoftSubSubCatelog [subSubCatelogId=" + subSubCatelogId + ", subSubName=" + subSubName
				+ ", subSubCatelogDate=" + subSubCatelogDate + ", userName=" + userName + ", softsubCatelogVo="
				+ softsubCatelogVo + "]";
	}
	

}



