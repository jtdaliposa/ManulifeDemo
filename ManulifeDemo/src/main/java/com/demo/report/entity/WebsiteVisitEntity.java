package com.demo.report.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="website_visit")
public class WebsiteVisitEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="local_date")
	private Date localDate;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="website_id", name="website_id")
	private WebsiteEntity website;
	
	@Column(name="visit_count")
	private Integer visitcount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public WebsiteEntity getWebsite() {
		return website;
	}

	public void setWebsite(WebsiteEntity website) {
		this.website = website;
	}

	public Integer getVisitcount() {
		return visitcount;
	}

	public void setVisitcount(Integer visitcount) {
		this.visitcount = visitcount;
	}

	public Date getLocalDate() {
		return localDate;
	}

	public void setLocalDate(Date localDate) {
		this.localDate = localDate;
	}
}
