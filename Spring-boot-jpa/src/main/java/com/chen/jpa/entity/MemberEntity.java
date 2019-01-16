package com.chen.jpa.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity // 实体类声明
@Table(name = "t_member") // 表名
public class MemberEntity extends BaseEntity {
	private static final long serialVersionUID = 1951888847631866993L;

	private Date date;

	private String userName;

	private String state;

	private String country;

	private String map;

	private String temp;

	// OneToMany：一对多关系声明
	private List<HotelEntity> hotels = new ArrayList<>();

	@Temporal(TemporalType.TIMESTAMP) // 这个是带时分秒的类型
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "user_name", nullable = false, length = 50) // nullable:是否可以为空
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	// Transient：表示此数据不在数据库表里建立属性
	@Transient
	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@OrderBy(value = "id ASC") // 按照id升序排序
	public List<HotelEntity> getHotels() {
		return hotels;
	}

	public void setHotels(List<HotelEntity> hotels) {
		this.hotels = hotels;
	}

	public MemberEntity(Date date, String userName, String state, String country, String map, String temp,
			List<HotelEntity> hotels) {
		super();
		this.date = date;
		this.userName = userName;
		this.state = state;
		this.country = country;
		this.map = map;
		this.temp = temp;
		this.hotels = hotels;
	}

	public MemberEntity() {
		super();
	}

}