package com.chen.jpa.entity;

import javax.persistence.*;

@Entity
@Table(name="t_hotel")
public class HotelEntity extends BaseEntity {
	private static final long serialVersionUID = -5751793304485928229L;

	private MemberEntity member;

	private String name;

	private String address;
	
	@ManyToOne(optional = false,fetch=FetchType.LAZY)//多对一关系
	@JoinColumn(name="member_id",nullable = true)//注释指定OrderItem映射表的member_id列作为外键与member 映射表的主键列关联
	public MemberEntity getMember() {
		return member;
	}

	public void setMember(MemberEntity member) {
		this.member = member;
	}

	@Column(nullable = false)  //不为空
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public HotelEntity(MemberEntity member, String name, String address) {
		super();
		this.member = member;
		this.name = name;
		this.address = address;
	}

	public HotelEntity() {
		super();
	}
	
}
