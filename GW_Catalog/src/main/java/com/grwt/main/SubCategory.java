package com.grwt.main;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Sub_CategoryListss")
public class SubCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int subid;
	@Column(name="Sub_Cat_Name",length = 250)
	private String subName;
	@Column(name="UserName")
	private String username;
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<SubSubCategory> subsubcategory;

	/*
	 * @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL) private Category
	 * category;
	 */
	
	public String getSubName() {
		return subName;
	}
	public int getSubid() {
		return subid;
	}
	public void setSubid(int subid) {
		this.subid = subid;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
//	public Category getCategory() {
//		return category;
//	}
//	public void setCategory(Category category) {
//		this.category = category;
//	}
	public List<SubSubCategory> getSubsubcategory() {
		return subsubcategory;
	}
	public void setSubsubcategory(List<SubSubCategory> subsubcategory) {
		this.subsubcategory = subsubcategory;
	}
	@Override
	public String toString() {
		return "SubCategory [subName=" + subName + ", username=" + username + ", subsubcategory=" + subsubcategory
				+ "]";
	}
	

	
	

}
