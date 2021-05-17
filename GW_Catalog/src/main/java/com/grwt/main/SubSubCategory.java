package com.grwt.main;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Sub_Sub_CategoryListss")

public class SubSubCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int subsubid;
	@Column(name = "SubSubCtName")
	private String subsubName;
	@Column(name = "userName")
	private String username;
	/*
	 * @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL) private
	 * SubCategory subCategory;
	 */
	

	
	public String getSubsubName() {
		return subsubName;
	}
	public int getSubsubid() {
		return subsubid;
	}
	public void setSubsubid(int subsubid) {
		this.subsubid = subsubid;
	}
	public void setSubsubName(String subsubName) {
		this.subsubName = subsubName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
//	public SubCategory getSubCategory() {
//		return subCategory;
//	}
//	public void setSubCategory(SubCategory subCategory) {
//		this.subCategory = subCategory;
//	}
//	
	@Override
	public String toString() {
		return "SubSubCategory [subsubName=" + subsubName + ", username=" + username + "]";
	}
	
	

}
